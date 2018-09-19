package com.backstage.base.service;

/**
 */
public interface IRedisService {
    boolean set(String key, String value);

    String get(String key);

    boolean expire(String key, long expire);

    public boolean set(String key,Object value,Long expire);
}