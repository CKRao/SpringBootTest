package com.clarkrao.springboot.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: ClarkRao
 * @Date: 2018/12/11 22:27
 * @Description: mongodb测试实体类
 */
@Document
@Data
public class TestEntity implements Serializable {

    @Id
    private ObjectId id;

    @Field("ifRecvTime")
    private LocalDateTime ifRecvTime;

    @Field("ifEntry")
    private List<org.bson.Document> ifEntry;

    @Field("ifNumber")
    private Integer ifNumber;
}
