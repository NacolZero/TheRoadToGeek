package com.nacol.TheRoadToGeek.week_11.redis_lock;

import com.nacol.TheRoadToGeek.common.exception.RepeatRequestException;
import com.nacol.TheRoadToGeek.common.utils.AspectUtils;
import com.nacol.TheRoadToGeek.common.utils.EncryptionUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/17
 * @Description
 * 设计思路：
 * 1. aop 拦截；
 * 2. 拦截方式：对请求参数进行 MD5 加密，的到唯一的 key，然后根据（类名+方法名+MD5的参数key）得到本次锁的 key
 * 3. redis 上锁；
 * 4. 重复获取锁则抛出 RepeatRequestException 重复请求异常。（未开发：可捕获此异常做对应处理）
 * 4. redis 使用 lua 脚本解锁
 */
@Log4j2
@Aspect
@Component
public class DistributedLockAspect {
    private static final Long EXEC_RESULT = 1L;
    private static final String TASK_LOCK = "task-lock-";
    private static final String HORI = "-";
    private static DefaultRedisScript<Long> LUA_DELETE;

    static {
        LUA_DELETE = new DefaultRedisScript<>();
        LUA_DELETE.setResultType(Long.class);
        LUA_DELETE.setScriptText("if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end");
    }

    @Autowired
    RedisTemplate redisTemplate;
    private static RedisSerializer<String> argsSerializer = new StringRedisSerializer();
    private static RedisSerializer resultSerializer = new StringRedisSerializer();

    @Pointcut("@annotation(com.nacol.TheRoadToGeek.week_11.redis_lock.TaskLock)")
    public void handlerRequest(){}

    @Before("handlerRequest()")
    public void doBebore(JoinPoint joinPoint) throws Exception{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        TaskLockDTO taskLockDTO = initTaskLockDTO(joinPoint);
        String taskKey = taskLockDTO.getTaskKey();
        request.setAttribute("task-key", taskKey);
        int expireTime = taskLockDTO.getExpireTime();
        boolean result = redisTemplate.opsForValue().setIfAbsent(taskKey, EXEC_RESULT, expireTime, TimeUnit.MILLISECONDS);
        if(result){
            log.info("task-key : {} 已经上锁. ({}毫秒后自动解锁) {}", taskKey, expireTime);
        } else {
            log.info("请求重复 task-key : {}", taskKey);
            throw new RepeatRequestException("请求重复!");
        }
    }

    @AfterReturning(pointcut = "handlerRequest()")
    public void doAfterReturning(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String taskKey = (String)request.getAttribute("task-key");
        Object result = redisTemplate.execute(LUA_DELETE, Arrays.asList(taskKey, EXEC_RESULT));
        log.info("exec result : {}", result);
    }

    private TaskLockDTO initTaskLockDTO(JoinPoint joinPoint) throws Exception{
        Method method = AspectUtils.getMethod(joinPoint);
        TaskLock taskLockAnnotation = AnnotationUtils.findAnnotation(method, TaskLock.class);
        int expireTime = taskLockAnnotation.expireTime();
        String md5Str = EncryptionUtils.argsToMd5(joinPoint.getArgs());

        StringBuilder taskKey = new StringBuilder(TASK_LOCK)
                .append(method.getDeclaringClass().getSimpleName())
                .append(HORI)
                .append(method.getName())
                .append(HORI)
                .append(md5Str);
        TaskLockDTO result = new TaskLockDTO()
                .setExpireTime(expireTime)
                .setTaskKey(taskKey.toString());
        return result;
    }

}
