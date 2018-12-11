package com.clarkrao.springboot.dao;

import com.clarkrao.springboot.entity.TestEntity;

import java.util.List;

/**
 * @Author: ClarkRao
 * @Date: 2018/12/11 22:26
 * @Description: MongoDBTestDao层
 */
public interface MongoTestDao {
    /**
     * 保存
     * @param entity
     * @param collectionName
     */
    void saveData(TestEntity entity,String collectionName);

    /**
     * 获取所有实体
     * @return
     */
    List<TestEntity> getAllData(String collectionName);
}
