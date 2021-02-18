package com.learning.springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class SpringbootWebAdminApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from accounts", Long.class);
        log.info("当前记录数：{}", aLong);
        log.info("数据源类型是：{}", dataSource.getClass());
    }

    @Test
    public void testRedis() {
        //Lettues
        //ValueOperations<String, String> operations = redisTemplate.opsForValue();
        //operations.set("hello", "world");
        //String hello = operations.get("hello");
        //log.info(hello);
    }

}
