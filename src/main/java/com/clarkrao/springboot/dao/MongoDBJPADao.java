package com.clarkrao.springboot.dao;

import com.clarkrao.springboot.entity.AppEntity;
import com.clarkrao.springboot.entity.TestEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: ClarkRao
 * @Date: 2018/12/12 22:05
 * @Description:
 */
@Repository
public interface MongoDBJPADao extends MongoRepository<AppEntity,ObjectId> {
    AppEntity findByApiKey(String apiKey);
}
