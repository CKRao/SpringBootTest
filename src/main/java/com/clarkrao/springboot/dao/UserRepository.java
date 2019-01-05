package com.clarkrao.springboot.dao;

import com.clarkrao.springboot.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: ClarkRao
 * @Date: 2018/10/25 22:38
 * @Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserName(String name);

    User findByUserNameAndEmail(String name, String email);

    User findByUserNameAndPassWord(String name, String passWord);

    @Cacheable(cacheNames = "user.service.findbyusername")
    User findByUserName(String name);
}
