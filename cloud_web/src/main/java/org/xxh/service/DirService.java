package org.xxh.service;

import org.xxh.pojo.Dir;

import java.util.List;

public interface DirService  {
    //查询该用户dir
    List<Dir> findAllDirByUserId(int userId);

//    查询该用户的分层级dir
    List<Dir> findDirAnalysisByuserId(int userId);

//    根据名称查询文件夹
    Dir findByDirName(String dirName);

//    根据dirId查询子文件
    List<Dir> findByParentId(Integer parentId);

    Integer addDir(Dir dir);
//    根据状态查询文件夹
    List<Dir> findByStatus(Integer status);

    Dir findById(Integer dirId);

    Integer editDir(String newDirName,Integer dirId);

    Dir findByDirPath(String path);

    Integer updateStatusBydirName(String dirName);

    Integer deleteBydirName(String dirName);
}
