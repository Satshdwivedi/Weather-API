package com.example.demo.redispractice;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration 
public class RedisConfig {
   @Bean
   public RedisTemplate<String,Object>redisTemplate(RedisConnectionFactory redisConnectionFactory){
     RedisTemplate<String,Object>template=new RedisTemplate<>();
     template.setConnectionFactory(redisConnectionFactory);
     template.setKeySerializer(new StringRedisSerializer());
     template.setHashKeySerializer(new StringRedisSerializer());
     template.setValueSerializer(new StringRedisSerializer());
     template.setHashValueSerializer(new StringRedisSerializer());
     return template;
   }
   @Bean 
   public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
    RedisCacheConfiguration config=RedisCacheConfiguration.defaultCacheConfig().
    entryTtl(Duration.ofSeconds(30));
     return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
   }
}
