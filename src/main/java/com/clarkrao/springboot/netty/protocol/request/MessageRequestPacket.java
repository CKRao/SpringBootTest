package com.clarkrao.springboot.netty.protocol.request;

import com.clarkrao.springboot.netty.protocol.Packet;
import lombok.Data;

import static com.clarkrao.springboot.netty.protocol.command.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {

    /**
     * 请求消息
     */
    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }
    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
