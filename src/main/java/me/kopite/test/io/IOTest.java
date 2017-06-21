package me.kopite.test.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhouwei on 2016年5月28日. see:
 * http://blog.csdn.net/u013256816/article/details/51457215
 */

public class IOTest {

    public static void catFile() {
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(new File(
                    "/Users/zhouwei/77_login.sh")));
            byte[] byteArray = new byte[1024];
            int result = inputStream.read(byteArray);
            System.out.println(result);
            while (result != -1) {
                for (int i = 0; i < result; i++) {
                    System.out.print((char) byteArray[i]);
                }
                result = inputStream.read(byteArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void catFileThroughNIO() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile(
                    new File("/Users/zhouwei/77_login.sh"), "rw");
            FileChannel channel = aFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = channel.read(buffer);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                buffer.flip();
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buffer.get());
                }
                buffer.compact();
                bytesRead = channel.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (aFile != null) {
                try {
                    aFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        catFileThroughNIO();
    }
}
