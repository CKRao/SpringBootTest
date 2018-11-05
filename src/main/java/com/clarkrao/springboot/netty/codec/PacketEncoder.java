package com.clarkrao.springboot.netty.codec;

import com.clarkrao.springboot.netty.protocol.Packet;
import com.clarkrao.springboot.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/5 22:35
 * @Description:
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTANCE.encode(byteBuf,packet);
    }
}
