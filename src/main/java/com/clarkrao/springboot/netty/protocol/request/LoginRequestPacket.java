package com.clarkrao.springboot.netty.protocol.request;

import com.clarkrao.springboot.netty.protocol.Packet;
import lombok.Data;

import static com.clarkrao.springboot.netty.protocol.command.Command.LOGIN_REQUEST;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/1 21:54
 * @Description: 登录请求数据包
 */
@Data
public class LoginRequestPacket extends Packet {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    /**
     * 重写getCommand()方法
     * 获取命令
     * @return 登录请求命令
     */
    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
