package com.clarkrao.springboot.dao;

import com.clarkrao.springboot.entity.AppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: ClarkRao
 * @Date: 2018/12/12 22:05
 * @Description:
 */
@Repository
public interface MongoDbJpaDao extends MongoRepository<AppEntity, ObjectId> {
    /**
     * 通过apiKey查找App实体
     *
     * @param apiKey
     * @return
     */
    AppEntity findByApiKey(String apiKey);
}
