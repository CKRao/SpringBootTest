package com.clarkrao.springboot.des;

import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;
import java.security.SecureRandom;

/**
 * @Author: ClarkRao
 * @Date: 2019/1/5 12:48
 * @Description:
 */
public class DESTest {

    private static final String KA = "0B4DBD5460E47E54";

    private static final String KB = "54DC0B577B4F759D";
    private static final String NUM = "1035801";
    /**
     * DES常量
     */
    private static final String DES = "DES";

    public static void main(String[] args) {
        String[] pan = toStringArray("6228232687713098564");
        String[] expdate = toStringArray("1812");
        String[] ccd = new String[7];
        ccd = myEncrypt(pan, expdate);

        System.out.println( array2String(pan)+ " = " + array2String(expdate) +  array2String(ccd)+ NUM);
    }

    public static String[] myEncrypt(String[] pan, String[] expdate) {
        //定义变量
        int i, j, l;
        String[] ccd = new String[7];
        String[] cblk = new String[36];
        String ka = "";
        String kb = "";
        byte[] blk1 = new byte[8];
        byte[] blk2 = new byte[8];
        byte[] s1 = new byte[8];
        byte[] s2 = new byte[8];
        byte[] s3 = new byte[8];
        byte[] s4 = new byte[8];
        byte[] s5 = new byte[8];
        int[] d = new int[16];

        ka = transStr2Hex(KA).substring(0,9);
        kb = transStr2Hex(KB).substring(0,9);
        String[] append =strAppend(toStringArray("414"),expdate) ;
        cblk = strAppend(pan, append);
        l = cblk.length;

        for (i = l; i < 32; i++) {
            cblk = strAppend(cblk, toStringArray("0"));
        }

        blk1 = ASCII_To_BCD(subStringArray(cblk, 0, 16).toString().getBytes(), 16);
        blk2 = ASCII_To_BCD(subStringArray(cblk, 16, -1).toString().getBytes(), 16);

        s1 = DESEncrypt(blk1, ka.getBytes());
        for (i = 0; i < 8; i++) {
            s2[i] = (byte) (blk2[i] ^ s1[i]);
        }

        s3 = DESEncrypt(s2, ka.getBytes());

        s4 = DESDencrypt(s3, kb.getBytes());

        s5 = DESEncrypt(s4, ka.getBytes());
        for (i = 0, j = 0; i < 8; i++, j += 2) {
            d[j] = (int) s5[i] / 16;
            d[j + 1] = (int) s5[i] % 16;
        }
        i = 0;
        for (j = 0; j < 16; j++) {
            if (d[j] < 10) {
                cblk[i++] = String.valueOf((byte) (d[j] + '0'));
            }
        }
        for (j = 0; j < 16; j++) {
            if (d[j] >= 10) {
                cblk[i++] = String.valueOf((byte) (d[j] - 10 + '0'));
            }
        }

        ccd = stringArrayCopy(array2String(cblk).substring(6,12), "\0",6);
        return ccd;
    }

    private static String[] stringArrayCopy(String s, String append, int l) {
        int length = s.length();
        String[] strings = new String[l];
        for (int i = 0; i < l; i++) {
            strings[i] = append;
        }
        for (int i = 0; i < length; i++) {
            strings[i] = String.valueOf(s.charAt(i));
        }

        return strings;
    }

    private static String[] subStringArray(String[] cblk, int i, int i1) {
        int length = cblk.length;
        if (i1 != -1) {
            length = i1;
        }
        String[] strings = new String[length];
        for (int j = i; j < length; j++) {
            strings[i] = cblk[i];
        }
        return strings;
    }


    private static String[] toStringArray(String s) {
        char[] chars = s.toCharArray();
        String[] strings = new String[chars.length];
        for (int i = 0; i < chars.length; i++) {
            strings[i] = String.valueOf(chars[i]);
        }

        return strings;
    }

    private static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
        byte[] bcd = new byte[asc_len / 2];
        int j = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++) {
            bcd[i] = asc_to_bcd(ascii[j++]);
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
            System.out.format("%02X\n", bcd[i]);
        }
        return bcd;
    }

    private static byte asc_to_bcd(byte asc) {
        byte bcd;

        if ((asc >= '0') && (asc <= '9')) {
            bcd = (byte) (asc - '0');
        } else if ((asc >= 'A') && (asc <= 'F')) {
            bcd = (byte) (asc - 'A' + 10);
        } else if ((asc >= 'a') && (asc <= 'f')) {
            bcd = (byte) (asc - 'a' + 10);
        } else {
            bcd = (byte) (asc - 48);
        }
        return bcd;
    }

    private static String transStr2Hex(String ka) {
        int len = ka.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(Integer.toHexString(ka.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * DES加密
     *
     * @param data
     * @param keyBytes
     * @return
     */
    public static byte[] DESEncrypt(byte[] data, byte[] keyBytes) {
        byte[] result = null;
        try {

            //生成随机数
            SecureRandom random = new SecureRandom();
            //创建DESKeySpec对象
            DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
            //转换DESKeySpec对象为SecretKeyFactory
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            //完成加密
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            //初始化对象
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            byte[] bytes = cipher.doFinal(data);
            System.out.println("数据加密后byte数组：" + bytes);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * DES解密
     *
     * @param data
     * @param keyBytes
     * @return
     */
    public static byte[] DESDencrypt(byte[] data, byte[] keyBytes) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] strAppend(String[] old, String[] append) {
        String[] strings = new String[old.length + append.length];
        strings = ArrayUtils.addAll(old, append);
        return strings;
    }

    public static String array2String(String[] arr){
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < arr.length;i++){
            sb.append(arr[i]);

        }

        return sb.toString();
    }
}
