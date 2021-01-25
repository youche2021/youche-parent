package com.youche.redis.service;

import com.youche.redis.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@Log4j2
public class UserService {

    private static List<User> users = new ArrayList<>();

    static {
        User user1 = new User(1L, "simon");
        User user2 = new User(2L, "amy");
        users.add(user1);
        users.add(user2);
    }

    @CachePut(value = "user", key = "#id")
    public User saveOrUpdateUser(Long id, String name) {
        log.error("saveOrUpdateUser id:{}", id);
        log.error("saveOrUpdateUser users:{}", users.size());
        User findUser = getUser(id);
        if (findUser == null) {
            findUser = new User(id, name);
            users.add(findUser);
        } else {
            findUser.setName(name);
        }
        return findUser;
    }

    @Cacheable(value = "user", key = "#id")
    public User getUser(Long id) {
        log.error("getUser:{}", id);
        Predicate<User> idPredicate = value -> value.getId().equals(id);

        Optional<User> optionalUser = users.stream().filter(idPredicate).findFirst();
        if (optionalUser.isPresent()) {
            User findUser = optionalUser.get();
            return findUser;
        }

        return null;
    }

    @CacheEvict(value = "user", key = "#id")
    public void delete(Long id) {
        log.error("delete:{}", id);
        User user = getUser(id);
        users.remove(user);
    }
}
