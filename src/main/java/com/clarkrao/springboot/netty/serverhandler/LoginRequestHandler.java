package com.clarkrao.springboot.netty.serverhandler;

import com.clarkrao.springboot.netty.protocol.request.LoginRequestPacket;
import com.clarkrao.springboot.netty.protocol.response.LoginResponsePacket;
import com.clarkrao.springboot.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/5 22:23
 * @Description: 登录请求处理器
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ": 登录成功!");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        //登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
