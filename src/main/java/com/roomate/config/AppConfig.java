package com.roomate.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@Slf4j
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        log.info("========= inside AppConfig.jedisConnectionFactory =============");
        try {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(env.getProperty("redis.host"), Integer.valueOf(env.getProperty("redis.port")));
            return new JedisConnectionFactory(redisStandaloneConfiguration);
        } catch (Exception ex) {
            log.info("========= inside AppConfig.jedisConnectionFactory->" + ex);
        }
        log.info("========= inside AppConfig.jedisConnectionFactory =============");
        return null;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        log.info("========= inside AppConfig.redisTemplate =============");
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        try {
            template.setConnectionFactory(jedisConnectionFactory());
        } catch (Exception ex) {
            log.info("========= inside AppConfig.redisTemplate->" + ex);
        }
        log.info("========= inside AppConfig.redisTemplate =============");
        return template;
    }

    @Bean
    public HashOperations hashOperations() {
        return redisTemplate().opsForHash();
    }
}