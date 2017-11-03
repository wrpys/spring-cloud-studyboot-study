package com.example.demo.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author wrp
 * @Description com.example.demo.utils.FileUtil
 * @Date 2017/9/18
 */
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
