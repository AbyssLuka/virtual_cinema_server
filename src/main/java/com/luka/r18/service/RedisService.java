package com.luka.r18.service;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

    boolean expire(String key, long time);

    long getExpire(String key);

    boolean hasKey(String key);

    void del(String... key);

    Object get(String key);

    boolean set(String key,Object value);

    boolean set(String key, Object value, long time);

    long incr(String key, long delta);

    long decr(String key, long delta);

    Object hashGet(String key, String item);

    Map<Object, Object> hashGetMap(String key);

    boolean hashSetMap(String key, Map<Object, Object> map);

    boolean hashSet(String key, String item, Object value);

    boolean hashSet(String key, String item, Object value, long time);

    void hashDel(String key, Object... item);

    boolean hashHasKey(String key, String item);

    double hashIncr(String key, String item, double by);

    double hashDecr(String key, String item, double by);

    Set<Object> setGet(String key);

    boolean setHasKey(String key, Object value);

    long setSet(String key, Object... values);

    long setSetAndTime(String key, long time, Object... values);

    long setGetSetSize(String key);

    long setRemove(String key, Object... values);

    List<Object> listGet(String key, long start, long end);

    long listGetListSize(String key);

    Object listGetIndex(String key, long index);

    boolean listSet(String key, Object value);

    boolean listSet(String key, Object value, long time);

    boolean listSet(String key, List<Object> value);

    boolean listSet(String key, List<Object> value, long time);

    boolean lUpdateIndex(String key,long index,Object value);

    long listRemove(String key,long count,Object value);

    boolean zSetAdd(String key,Object member,double score,long time);

    Set<Object> zSetRangeByScore(String key,double minScore,double maxScore);

    double zSetScore(String key,Object member);

    long zSetRank(String key,Object member);

    Cursor<ZSetOperations.TypedTuple<Object>> zSetScan(String key);

}
