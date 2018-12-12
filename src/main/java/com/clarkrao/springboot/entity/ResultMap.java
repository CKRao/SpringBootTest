package com.clarkrao.springboot.entity;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author: ClarkRao
 * @Date: 2018/12/12 22:30
 * @Description: 返回结果实体类
 */
@Data
public class ResultMap<T> {
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体的内容.
     */
    private T data;

    /**
     * 用户token信息
     */
    private String token;
}
