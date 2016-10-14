package com.itheima.redboyclient.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 流的工具类
 * @author Michael
 */
public class StreamTools {

    /**
     * 读取一个流 把流的内容转化成字符串
     * @param is	输入流
     * @return	以字符串的形式返回解析的结果
     * @throws IOException
     */
    public static String readStream(InputStream is) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        is.close();
        return baos.toString();
    }
}
