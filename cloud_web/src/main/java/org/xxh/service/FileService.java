package org.xxh.service;

import org.xxh.pojo.File;

import java.util.List;

public interface FileService {

//    根据dirId查询该文件夹下的文件
    List<File> findBydirId(int dirId);

//    保存
    File save(File file);
//    查询该用户顶层文件
    List<File> findTopFileByUserId(Integer userId);

//    删除文件
    Integer deleteByFileName(String fileName);

    Integer updateFileStaus(String fileName);

    List<File> findByFileStatus(Integer fileStatus);
}
