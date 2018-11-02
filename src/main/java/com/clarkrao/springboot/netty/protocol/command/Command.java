package com.clarkrao.springboot.netty.protocol.command;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/1 21:52
 * @Description: 命令接口
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
}
