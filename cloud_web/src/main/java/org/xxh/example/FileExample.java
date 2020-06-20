package org.xxh.example;

import org.xxh.pojo.Dir;
import org.xxh.pojo.File;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

public class FileExample {

//    查询顶层文件
    public static Example findTopFileByUserId(int userId){
        return Example.builder(File.class)
                .where(Sqls.custom().andEqualTo("fileUploadUserId",userId)
                .andIsNull("fileDirId").andEqualTo("fileStatus",1)).build();
    }

//    删除文件
    public static Example deleteByFileName(String fileName){
        return Example.builder(File.class)
                .where(Sqls.custom().andEqualTo("fileName",fileName))
                .build();
    }

//    查找状态为0的file
    public static  Example selectFileByFileStatus(Integer fileStatus){
        return Example.builder(File.class)
                .where(Sqls.custom().andEqualTo("fileStatus",fileStatus))
                .build();
    }


}
