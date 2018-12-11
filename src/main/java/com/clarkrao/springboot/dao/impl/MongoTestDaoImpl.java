package com.clarkrao.springboot.dao.impl;

import com.clarkrao.springboot.dao.MongoTestDao;
import com.clarkrao.springboot.entity.TestEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ClarkRao
 * @Date: 2018/12/11 22:33
 * @Description: MongoTestDao实现类
 */
@Service
@Slf4j
public class MongoTestDaoImpl implements MongoTestDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveData(TestEntity entity, String collectionName) {
        mongoTemplate.save(entity, collectionName);
        log.info("MongoDB保存成功 -> ",collectionName);
    }

    @Override
    public List<TestEntity> getAllData(String collectionName) {
        return mongoTemplate.findAll(TestEntity.class,collectionName);
    }
}
