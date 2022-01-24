package learn.redis.springdata.repository;

import learn.redis.springdata.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // used for hashmap
    private HashOperations hashOperations;

    // used for string and integer/floating values
    //@Autowired
    //private ValueOperations<String, Object> valueOperations;

    public UserInfoRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(UserInfo user) {
        hashOperations.put("USER", user.getId(), user);
    }

    @Override
    public Map<String, UserInfo> findAll() {
        return hashOperations.entries("USER");
    }

    @Override
    public UserInfo findById(String id) {
        return (UserInfo) hashOperations.get("USER", id);
    }

    @Override
    public void update(UserInfo user) {
        hashOperations.put("USER", user.getId(), user);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete("USER", id);
    }
}
