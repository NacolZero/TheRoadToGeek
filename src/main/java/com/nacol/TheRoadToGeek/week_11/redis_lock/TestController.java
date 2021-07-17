package com.nacol.TheRoadToGeek.week_11.redis_lock;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("redis-lock-test")
public class TestController {

    @TaskLock
    @PostMapping("/test-auto")
    public String test(@RequestBody JSONObject param) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(4500);
        return "ok";
    }

}
