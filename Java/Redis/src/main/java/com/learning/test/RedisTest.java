package com.learning.test;

import com.learning.utils.JedisPoolUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RedisTest {

    private static final String host = "127.0.0.1";
    private List<Jedis> jedisList;
    private Jedis jedis;

    public Jedis getJedis(int port) {
        Jedis j = new Jedis(host, port);
        jedisList.add(j);
        return j;
    }

    @Before
    public void textBefore() {
        jedisList = new ArrayList<>();
        jedis = getJedis(6379);
    }

    @After
    public void textAfter() {
        jedisList.forEach(Jedis::close);
    }

    @Test
    public void testPing() {
        System.out.println(jedis.ping());
    }

    @Test
    public void testAPI() {
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");

        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        jedis.del("k1");
        jedis.del("k2");
        jedis.del("k3");

        keys = jedis.keys("*");
        System.out.println(keys);
    }

    @Test
    public void testTX() {
        Transaction tx = jedis.multi();

        tx.set("k1", "v1");
        tx.set("k2", "v2");

        tx.exec();
    }

    @Test
    public void testMS() {
        Jedis slave = getJedis(6380);
        slave.slaveof(host, 6379);

        String s = slave.get("k1");
        System.out.println(s);

        jedis.set("k1", "v1");
        s = slave.get("k1");
        System.out.println(s);

        jedis.del("k1");
        s = slave.get("k1");
        System.out.println(s);
    }

    @Test
    public void testPool() {
        System.out.println(JedisPoolUtil.getJedisPool() == JedisPoolUtil.getJedisPool());
    }
}
