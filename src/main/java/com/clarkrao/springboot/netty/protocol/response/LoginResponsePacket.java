package com.clarkrao.springboot.netty.protocol.response;

import com.clarkrao.springboot.netty.protocol.Packet;
import lombok.Data;

import static com.clarkrao.springboot.netty.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/1 21:57
 * @Description: 登录响应数据包
 */
@Data
public class LoginResponsePacket extends Packet{

    /**
     * 是否成功标志
     */
    private boolean success;
    /**
     * 失败的原因
     */
    private String reason;

    /**
     * 重写获取命令方法
     * @return 登录响应命令
     */
    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
