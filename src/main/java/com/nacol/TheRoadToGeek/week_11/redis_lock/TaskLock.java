package com.nacol.TheRoadToGeek.week_11.redis_lock;
import java.lang.annotation.*;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/17
 * @Description 分布式锁注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskLock {

    /**
     * 锁释放时间 (毫秒)
     */
    int expireTime() default THIRTY;

    /**
     * 30秒解锁
     */
    int THIRTY = 30000;
}
