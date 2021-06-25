package com.nacol.shardingsphererw;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nacol.shardingsphererw.entity.T;
import com.nacol.shardingsphererw.mapper.TMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingsphereRwApplicationTests {

    @Autowired
    TMapper tMapper;

    @Test
    void write() {
        for (int i = 10; i < 13; i++) {
            T t = new T();
            t.setId(i);
            tMapper.insert(t);
        }
    }

    @Test
    void read() {
        System.out.println(tMapper.selectList(new QueryWrapper<T>().lambda()));
    }

}
