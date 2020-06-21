package org.xxh.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xxh.dao.DirRepository;
import org.xxh.dao.FileRepository;
import org.xxh.example.DirExample;
import org.xxh.pojo.Dir;
import org.xxh.service.DirService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirServiceImpl implements DirService {
    private final DirRepository dirRepository;


    @Override
    public List<Dir> findAllDirByUserId(int userId) {
        return  dirRepository.selectByExample(DirExample.findByUserId(userId));
    }

    @Override
    public List<Dir> findDirAnalysisByuserId(int userId) {
        return dirRepository.findTopDirByUserId(userId);
    }

    @Override
    public Dir findByDirName(String dirName) {
        return   dirRepository.selectOneByExample(DirExample.findByDirName(dirName));

    }

    @Override
    public List<Dir> findByParentId(Integer parentId) {
        return dirRepository.findByParentId(parentId);
    }

    @Override
    public Integer addDir(Dir dir) {
        return dirRepository.insert(dir);
    }

    @Override
    public List<Dir> findByStatus(Integer status) {
        return dirRepository.selectByExample(DirExample.findByStatus(status));
    }

    @Override
    public Dir findById(Integer dirId) {
        return dirRepository.selectByPrimaryKey(dirId);
    }

    @Override
    public Integer editDir(String dirName,Integer dirId) {
        return dirRepository.updateByDirname(dirName,dirId);
    }

    @Override
    public Dir findByDirPath(String path) {
        return dirRepository.selectOneByExample(DirExample.findByDirPath(path));
    }

    @Override
    public Integer updateStatusBydirName(String dirName) {
        return dirRepository.updateStatusByDirName(dirName);
    }

    @Override
    public Integer deleteBydirName(String dirName) {
        return dirRepository.deleteByExample(DirExample.findByDirName(dirName));
    }
}
