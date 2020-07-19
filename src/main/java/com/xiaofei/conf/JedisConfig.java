package com.xiaofei.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author xiaofei
 * @Classname JedisConfig
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */

@Configuration
public class JedisConfig {

    @Value("${jedis.maxTotal}")
    private int maxTotle;
    @Value("${jedis.maxIdle}")
    private int maxIdle;
    @Value("${jedis.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${jedis.testOnBorrow}")
    private boolean testOnBorrow;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotle);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }


    @Bean
    public JedisPool jedisPool (JedisPoolConfig jedisPoolConfig) {

        return new JedisPool(jedisPoolConfig,"127.0.0.1",6379);
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
