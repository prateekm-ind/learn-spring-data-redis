package learn.redis.springdata.repository;

import learn.redis.springdata.model.UserInfo;


import java.util.Map;



public interface UserInfoRepository {
    void save(UserInfo user);

    Map<String, UserInfo> findAll();

    UserInfo findById(String id);

    void update(UserInfo user);

    void delete(String id);
}
