package com.clarkrao.springboot.entity;

import lombok.Data;

/**
 * @Author: ClarkRao
 * @Date: 2019/1/5 19:47
 * @Description:
 */
@Data
public class TUser {

    private int userId;
    private String userName;
    private String password;
    private String phone;

}
