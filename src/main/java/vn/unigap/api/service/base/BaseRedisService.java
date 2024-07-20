package vn.unigap.api.service.base;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
@Service
public interface BaseRedisService {
    // Save key-value to Redis
    void set(String key, String value);
    // Set the timer for saving key in Redis
    void setTimeToLive(String key, long timeoutInDays);
    // Set Redis hash
    void hashSet(String key, String field, Object value);
    // Check <key, field> in Redis
    boolean hashExists(String key, String field);

    Object get(String key);

    Map<String, Object> getField(String key);

    Object hashGet(String key, String field);

    List<Object> hashGetByFieldPrefix(String key, String filedPrefix);

    Set<String> getFieldPrefixed(String key);

    void delete(String key);

    void delete(String key, String field);

    void delete(String key, List<String> fields);

}
