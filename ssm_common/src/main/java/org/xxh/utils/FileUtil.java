package org.xxh.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {
    public static void getFile(byte[] bfile, String fileName) {    //创建文件
        File file=new File(fileName);
        try {
            if (!file.exists()){file.createNewFile();}
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void responseTo(File file, HttpServletResponse res) {  //将文件发送到前端
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        FileInputStream input = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            input = new FileInputStream(file);
            byte[] b = new byte[2048];
            int len;
            while ((len = input.read(b)) != -1) {
                os.write(b, 0, len);
            }
            res.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        System.out.println("success");
    }
}
