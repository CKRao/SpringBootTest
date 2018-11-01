package com.clarkrao.springboot.netty.serialize;

import com.clarkrao.springboot.netty.serialize.impl.JSONSerializer;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/1 22:03
 * @Description:
 */
public interface Serializer {
    /**
     * 默认使用json序列化
     */
    Serializer DEFAULT = new JSONSerializer();
    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     *java对象转换成二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     *二进制转换成java对象
     * @param clazz 要转换的类对象
     * @param bytes 转换的byte数组
     * @param <T> 转换的类型
     * @return
     */
    <T> T deserializer(Class<T> clazz, byte[] bytes);
}
