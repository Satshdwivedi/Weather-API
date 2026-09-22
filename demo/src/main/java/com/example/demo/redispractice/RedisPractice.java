package com.example.demo.redispractice;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
@Component 
public class RedisPractice implements CommandLineRunner {
    private RedisTemplate<String,Object>redisTemplate;

    public RedisPractice(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public void saveData(){
        redisTemplate.opsForValue().set("name","Raj");}
    public Object getData(){
      return  redisTemplate.opsForValue().get("city");
    }
    public void deleteData(){
        redisTemplate.delete("name");
    }
    public void saveWithExpiry(){
        redisTemplate.opsForValue().set("city","kochi");
        redisTemplate.expire("city",10,TimeUnit.SECONDS);
    }
    public long getTTL(){
        return redisTemplate.getExpire("city");
    }
    public void saveHashData(){
        redisTemplate.opsForHash().put("stu:1", "name", "Raj");
        redisTemplate.opsForHash().put("stu:1","age","22");
        redisTemplate.opsForHash().put("stu:1", "city", "Satna");
    }
    @Override
    public void run(String... args) throws Exception {
          saveData();
         deleteData();
        saveWithExpiry();
         Object d=getData();
           System.out.println(d);
           Thread.sleep(2000);
           long t=getTTL();
           System.out.println(t);
           Thread.sleep(10000);
            Object d1=getData();
           System.out.println(d1);
            saveHashData(); 
  
  
        }

    
    
}
