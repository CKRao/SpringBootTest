package com.clarkrao.springboot.mapper;

import com.clarkrao.springboot.entity.TUser;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @Author: ClarkRao
 * @Date: 2019/1/5 19:50
 * @Description:
 */
public interface UserMapper {
    /**
     * 通过id删除
     * @param id
     * @return
     */
    int deleteUserById(int id);

    /**
     * 插入
     * @param tUser
     * @return
     */
    int insert(TUser tUser);

    /**
     * 更新
     * @param tUser
     * @return
     */
    int update(TUser tUser);

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    TUser getTUserById(int id);

    /**
     * 查找所有
     * @return
     */
    List<TUser> findAll();

    /**
     * 分页查找
     * @return
     */
    Page<TUser> findByPage();
}
