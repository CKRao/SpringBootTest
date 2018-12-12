package com.clarkrao.springboot.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @Author: ClarkRao
 * @Date: 2018/12/12 22:09
 * @Description:
 */
@Document(collection = "t_app")
@Data
public class AppEntity {
    @Id
    private ObjectId id = new ObjectId();
    @Field("api_key")
    private String apiKey;
    private String appname;
    private List<Object> activities;

}
