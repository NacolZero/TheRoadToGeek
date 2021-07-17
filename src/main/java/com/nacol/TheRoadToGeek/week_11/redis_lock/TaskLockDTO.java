package com.nacol.TheRoadToGeek.week_11.redis_lock;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/17
 * @Description 任务锁对象
 */
@Data
@Accessors(chain = true)
public class TaskLockDTO {

    /**
     * 任务锁 key
     */
    private String taskKey;

    /**
     * 自动释放时间
     */
    private int expireTime;

}
