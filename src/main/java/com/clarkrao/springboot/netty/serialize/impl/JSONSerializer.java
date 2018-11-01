package com.clarkrao.springboot.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.clarkrao.springboot.netty.serialize.Serializer;
import com.clarkrao.springboot.netty.serialize.SerializerAlgorithm;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/1 22:11
 * @Description:
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserializer(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
