package com.clarkrao.springboot.util;

import com.clarkrao.springboot.entity.ResultMap;

/**
 * @Author: ClarkRao
 * @Date: 2018/12/12 22:32
 * @Description: 返回结果工具类
 */
public class ResultUtil {

    public static ResultMap success(Object object) {
        ResultMap result = new ResultMap();
        result.setCode(0);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static ResultMap success() {
        return success(null);
    }

    public static ResultMap error(Integer code, String msg) {
        ResultMap result = new ResultMap();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
