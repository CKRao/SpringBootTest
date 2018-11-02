package com.clarkrao.springboot.netty.protocol;

import com.clarkrao.springboot.netty.protocol.request.LoginRequestPacket;
import com.clarkrao.springboot.netty.protocol.request.MessageRequestPacket;
import com.clarkrao.springboot.netty.protocol.response.LoginResponsePacket;
import com.clarkrao.springboot.netty.protocol.response.MessageResponsePacket;
import com.clarkrao.springboot.netty.serialize.Serializer;
import com.clarkrao.springboot.netty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.clarkrao.springboot.netty.protocol.command.Command.*;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/1 22:14
 * @Description: 协议编解码工具类
 */
public class PacketCodeC {
    /**
     * 定义魔数
     */
    private static final int MAGIC_NUMBER = 0x12345678;
    /**
     * 构造单例
     */
    public static final PacketCodeC INSTANCE = new PacketCodeC();


    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private static final Map<Byte, Serializer> serializerMap;

    static {
        /**
         * 初始化packetTypeMap
         * 将LOGIN_REQUEST，LOGIN_RESPONSE命令以及对应类放进map
         */
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        /**
         * 初始化serializerMap
         * 将对应序列化算法，序列化器实例放进map
         */
        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(Serializer.DEFAULT.getSerializerAlgorithm(), serializer);
    }

    /**
     * 编码方法
     *
     * @param byteBufAllocator
     * @param packet
     * @return
     */
    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        //1、创建ByteBuf对象
        ByteBuf byteBuf = byteBufAllocator.buffer();
        //2、序列化java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        //3、实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 解码方法
     *
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf) {
        //跳过magic number
        byteBuf.skipBytes(4);
        //跳过版本号
        byteBuf.skipBytes(1);
        //获取序列化算法
        byte serializeAlgorithm = byteBuf.readByte();
        //获取指令
        byte command = byteBuf.readByte();
        //获取数据包长度
        int length = byteBuf.readInt();
        //根据数据包长度构建byte数组
        byte[] bytes = new byte[length];
        //读取数据
        byteBuf.readBytes(bytes);
        //根据命令获取请求类型
        Class<? extends Packet> requestType = getRequestType(command);
        //根据序列化算法获取序列化器
        Serializer serializer = getSerializer(serializeAlgorithm);
        //判断请求类型和序列化器是否为空，如果不为空则返回解码后的数据包
        if (requestType != null && serializer != null) {
            return serializer.deserializer(requestType, bytes);
        }
        return null;
    }

    /**
     * 根据序列化算法编号从serializerMap获取序列化器
     *
     * @param serializeAlgorithm
     * @return
     */
    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    /**
     * 根据命令从packetTypeMap获取请求数据包类型
     *
     * @param command
     * @return
     */
    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }
}
