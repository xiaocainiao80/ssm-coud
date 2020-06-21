package org.xxh.example;

import org.xxh.pojo.Dir;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

public class DirExample {

    public static Example findByUserId(int userId){
        return Example.builder(Dir.class)
                .where(Sqls.custom().andEqualTo("dirUserId",userId)
                        .andIsNull("parentDir").andEqualTo("status",1))
                .build();
    }

    public static Example findByDirName(String dirName){
        return Example.builder(Dir.class)
                .where(Sqls.custom().andEqualTo("dirName",dirName))
                .build();
    }

    public static Example findByStatus(Integer status){
        return Example.builder(Dir.class)
                .where(Sqls.custom().andEqualTo("status",status))
                .build();
    }

    public static Example findByDirPath(String path){
        return Example.builder(Dir.class)
                .where(Sqls.custom().andEqualTo("dirPath",path))
                .build();
    }
}
