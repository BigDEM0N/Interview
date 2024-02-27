package com.avgkin.tacocloudplusserver.utils;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    @Autowired
    private RedissonClient redissonClient;
    public <K,V> boolean putKv(String bucketName,K key,V value){
        if(key!=null&&value!=null&&bucketName!=null){
            RMap<K,V> map = redissonClient.getMap(bucketName);
            map.put(key,value);
            return true;
        }else{
            throw new RuntimeException();
        }
    }
    public <K,V> V getValue(String bucketName,K key){
        if(key!=null&&bucketName!=null){
            RMap<K,V> map = redissonClient.getMap(bucketName);
            return map.get(key);
        }else{
            throw new RuntimeException();
        }
    }
    public <K,V> V remove(String bucketName,K key){
        if(key!=null&&bucketName!=null){
            RMap<K,V> map = redissonClient.getMap(bucketName);
            return map.remove(key);
        }else{
            throw new RuntimeException();
        }
    }
}
