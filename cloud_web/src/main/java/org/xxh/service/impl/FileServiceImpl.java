package org.xxh.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xxh.dao.FileRepository;
import org.xxh.example.FileExample;
import org.xxh.pojo.File;
import org.xxh.service.FileService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Override
    public List<File> findBydirId(int dirId) {
        return fileRepository.findByDirId(dirId);
    }

    @Override
    public File save(File file) {
         int i = fileRepository.insertSelective(file);
         if(i >0)
             return file;
         else
             return null;
    }

    @Override
    public List<File> findTopFileByUserId(Integer userId) {
        return fileRepository.selectByExample(FileExample.findTopFileByUserId(userId));
    }

    @Override
    public Integer deleteByFileName(String fileName) {
        return fileRepository.deleteByExample(FileExample.deleteByFileName(fileName));
    }

    @Override
    public Integer updateFileStaus(String fileName) {

        return fileRepository.updateStatusByFileName(fileName);
    }

    @Override
    public List<File> findByFileStatus(Integer fileStatus) {
        return fileRepository.selectByExample(FileExample.selectFileByFileStatus(fileStatus));
    }

    @Override
    public List<File> selectByDirId(Integer dirId) {
        return fileRepository.selectByDirId(dirId);
    }
}
