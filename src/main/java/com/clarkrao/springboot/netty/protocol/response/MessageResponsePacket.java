package com.clarkrao.springboot.netty.protocol.response;

import com.clarkrao.springboot.netty.protocol.Packet;
import lombok.Data;

import static com.clarkrao.springboot.netty.protocol.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    /**
     * 响应消息
     */
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
