package com.clarkrao.springboot.netty.util;

import com.clarkrao.springboot.netty.attributes.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;


/**
 * 登录工具类
 */
public class LoginUtil {
    /**
     * 设置登录标记
     * @param channel
     */
    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
     * 判断是否有标志位
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttribute = channel.attr(Attributes.LOGIN);

        if (loginAttribute.get() != null) {
            return loginAttribute.get();
        }
        return false;
    }

}
