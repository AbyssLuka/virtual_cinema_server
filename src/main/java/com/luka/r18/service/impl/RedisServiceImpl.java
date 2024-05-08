package com.luka.r18.service.impl;

import com.luka.r18.service.RedisService;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, Object> template;


    @Override
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                template.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long getExpire(String key) {
        Long expire = template.getExpire(key, TimeUnit.SECONDS);
        if (expire == null) {
            return -1;
        }
        return expire;
    }

    @Override
    public boolean hasKey(String key) {
        try {
            Boolean bool = template.hasKey(key);
            return bool != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                template.delete(key[0]);
            } else {
                template.delete(Arrays.asList(key));
            }
        }
    }

    @Override
    public Object get(String key) {
        if (key == null) {
            return null;
        }
        return template.opsForValue().get(key);
    }

    @Override
    public boolean set(String key, Object value) {
        try {
            template.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                template.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long incr(String key, long delta) {
        if (delta < 0) {
            return 0;
        }
        Long increment = template.opsForValue().increment(key, delta);
        if (increment != null) {
            return increment;
        }
        return 0;
    }

    @Override
    public long decr(String key, long delta) {
        if (delta < 0) {
            return 0;
        }
        Long increment = template.opsForValue().increment(key, -delta);
        if (increment != null) {
            return increment;
        }
        return 0;
    }

    @Override
    public Object hashGet(String key, String item) {
        return template.opsForHash().get(key, item);
    }

    @Override
    public Map<Object, Object> hashGetMap(String key) {
        return template.opsForHash().entries(key);
    }

    @Override
    public boolean hashSetMap(String key, Map<Object, Object> map) {
        try {
            template.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean hashSet(String key, String item, Object value) {
        try {
            template.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean hashSet(String key, String item, Object value, long time) {
        try {
            template.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void hashDel(String key, Object... item) {
        template.opsForHash().delete(key, item);
    }

    @Override
    public boolean hashHasKey(String key, String item) {
        return template.opsForHash().hasKey(key, item);
    }

    @Override
    public double hashIncr(String key, String item, double by) {
        return template.opsForHash().increment(key, item, by);
    }

    @Override
    public double hashDecr(String key, String item, double by) {
        return template.opsForHash().increment(key, item, -by);
    }

    @Override
    public Set<Object> setGet(String key) {
        try {
            return template.opsForSet().members(key);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean setHasKey(String key, Object value) {
        try {
            return template.opsForSet().isMember(key, value);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long setSet(String key, Object... values) {
        try {
            Long count = template.opsForSet().add(key, values);
            if (count == null) {
                return 0;
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long setSetAndTime(String key, long time, Object... values) {
        try {
            Long count = template.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            if (count == null) {
                return 0;
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long setGetSetSize(String key) {
        try {
            Long size = template.opsForSet().size(key);
            if (size == null) {
                return 0;
            }
            return size;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long setRemove(String key, Object... values) {
        try {
            Long count = template.opsForSet().remove(key, values);
            if (count == null) {
                return 0;
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Object> listGet(String key, long start, long end) {
        try {
            return template.opsForList().range(key, start, end);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public long listGetListSize(String key) {
        try {
            Long size = template.opsForList().size(key);
            if (size == null) {
                return 0;
            }
            return size;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Object listGetIndex(String key, long index) {
        try {
            return template.opsForList().index(key, index);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean listSet(String key, Object value) {
        try {
            template.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean listSet(String key, Object value, long time) {
        try {
            template.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean listSet(String key, List<Object> value) {
        try {
            template.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean listSet(String key, List<Object> value, long time) {
        try {
            template.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            template.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long listRemove(String key, long count, Object value) {
        try {
            Long reCount = template.opsForList().remove(key, count, value);
            if (reCount == null) {
                return 0;
            }
            return reCount;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean zSetAdd(String key, Object member, double score, long time) {
        try {
            template.opsForSet().add(key, member, score);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Set<Object> zSetRangeByScore(String key, double minScore, double maxScore) {
        try {
            return template.opsForZSet().rangeByScore(key, minScore, maxScore);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public double zSetScore(String key, Object member) {
        try {
            Double score = template.opsForZSet().score(key, member);
            if (score == null) {
                return 0;
            }
            return score;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long zSetRank(String key, Object member) {
        try {
            Long rank = template.opsForZSet().rank(key, member);
            if (rank == null) {
                return 0;
            }
            return rank;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Cursor<ZSetOperations.TypedTuple<Object>> zSetScan(String key) {
        try {
            return template.opsForZSet().scan(key, ScanOptions.NONE);
        } catch (Exception e) {
            return null;
        }
    }
}
