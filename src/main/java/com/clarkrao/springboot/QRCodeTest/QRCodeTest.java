package com.clarkrao.springboot.QRCodeTest;

import net.glxn.qrgen.core.AbstractQRCode;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.*;
import java.util.Random;

public class QRCodeTest {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            makeQRCode("name"+i);
        }

    }

    private static void makeQRCode(String name) {
        AbstractQRCode qrCode = QRCode.from("衣带渐宽终不悔,为伊消得人憔悴"+ Math.random()*Math.random());
        //设置字符集，支持中文
        qrCode.withCharset("utf-8");
        //设置生成二维码的图片大小
        qrCode.withSize(100, 100);
        ByteArrayOutputStream stream = qrCode.to(ImageType.PNG).stream();
        File file = new File("C:\\Users\\user\\Documents\\qrcode\\"+name+".png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
//            fos.write(stream.toByteArray());
//            fos.flush();
            stream.writeTo(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fos.close();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
