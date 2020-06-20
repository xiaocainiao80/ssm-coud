package org.xxh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xxh.pojo.Dir;
import org.xxh.pojo.File;
import org.xxh.service.DirService;
import org.xxh.service.FileService;
import org.xxh.vo.DirFileVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/dir")
@RequiredArgsConstructor
public class DirController {
    private final DirService dirService;
    private final FileService fileService;

    @GetMapping("/findDir/{userId}")
    public ResponseEntity<Object> findAllDirByUserId(@PathVariable String userId) {
        System.out.println(userId);
//        查询顶层文件夹
        List<Dir> TopDirByUserId = dirService.findAllDirByUserId(
                Integer.parseInt(userId));
        Map<String,Object> map = new HashMap<>();

        List<DirFileVo> dirFileVos = new ArrayList<>();
        for (Dir dir:TopDirByUserId){
            DirFileVo dirFileVo = new DirFileVo();
            dirFileVo.setType("directory");
            dirFileVo.setName(dir.getDirName());
            dirFileVo.setPath(dir.getDirPath());
            dirFileVos.add(dirFileVo);
        }
        //查询顶层文件
        List<File> files = fileService.findTopFileByUserId(Integer.parseInt(userId));
        for (File file:files){
            DirFileVo dirFileVo = new DirFileVo();
            dirFileVo.setType(file.getFileType());
            dirFileVo.setPath("/"+file.getFileName());
            dirFileVo.setName(file.getFileName());
            dirFileVos.add(dirFileVo);
        }
        map.put("data",dirFileVos);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }


    //查询该文件夹下文件夹和文件
    @GetMapping("/getDirFile")
    public ResponseEntity<Object> findDirFiles(String dirName){
        System.out.println(dirName);
//        查询文件夹下文件夹
        Map<String,Object> map = new HashMap<>();

        List<DirFileVo> dirFileVos = new ArrayList<>();

//        查询到该文件夹
        Dir target = dirService.findByDirName(dirName);
//        查询该文件夹下所有子文件夹
        List<Dir> sonDirs = dirService.findByParentId(target.getId());
        for (Dir dir:sonDirs){
            DirFileVo dirFileVo = new DirFileVo();
            dirFileVo.setType("directory");
            dirFileVo.setName(dir.getDirName());
            dirFileVo.setPath(dir.getDirPath());
            dirFileVos.add(dirFileVo);
        }

//        查询该文件夹下文件
        List<File> files = fileService.findBydirId(target.getId());
        for (File file:files){
            DirFileVo dirFileVo = new DirFileVo();
            dirFileVo.setType(file.getFileType());
            dirFileVo.setPath(target.getDirPath()+"/"+file.getFileName());
            dirFileVo.setName(file.getFileName());
            dirFileVos.add(dirFileVo);
        }
        map.put("data",dirFileVos);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    //增加文件夹
    @PostMapping("/add")
    public ResponseEntity<Object> addFir(@RequestBody Map<String, String> map){
       String name = map.get("name");
      String path = map.get("path");
      String userId = map.get("userId");
//        切割path
        String prefixPathName = path.substring(0,path.indexOf("/"));
        Dir newDir = new Dir();
//        不是第一层目录
        if(!prefixPathName.equals("")){
//          查询到前一层路径
            Dir dir = dirService.findByDirName(prefixPathName);
//            拼接现有路径
            path = dir.getDirPath()+path.substring(path.indexOf("/"));
            newDir.setParentDir(dir.getId());
        }
        newDir.setStatus(1);
        newDir.setDirPath(path);
        newDir.setDirName(name);
        newDir.setDirUserId(Integer.parseInt(userId));
        int result = dirService.addDir(newDir);
        if(result > 0)
            return new ResponseEntity<Object>("200",HttpStatus.CREATED);
        return new ResponseEntity<Object>("400",HttpStatus.BAD_REQUEST);
    }


//    回收站展现
    @GetMapping("/recycle")
    public ResponseEntity<Object> recycleBin(){

//        查询文件夹下文件夹
//        Map<String,Object> map = new HashMap<>();

        List<DirFileVo> dirFileVos = new ArrayList<>();

//        查询到回收站的文件夹
        List<Dir> dirs = dirService.findByStatus(0);

        for (Dir dir:dirs){
            DirFileVo dirFileVo = new DirFileVo();
            dirFileVo.setType("directory");
            dirFileVo.setName(dir.getDirName());
            dirFileVo.setPath(dir.getDirPath());
            dirFileVos.add(dirFileVo);
        }

//        查询该文件夹下文件
        List<File> files = fileService.findByFileStatus(0);
        for (File file:files){
            DirFileVo dirFileVo = new DirFileVo();
            dirFileVo.setType(file.getFileType());
//            查询到dir
            if(file.getFileDirId()!=null ) {
                Dir fileDir = dirService.findById(file.getFileDirId());
                dirFileVo.setPath(fileDir.getDirPath()+"/"+file.getFileName());
            }else {
                dirFileVo.setPath("/" + file.getFileName());
            }
            dirFileVo.setName(file.getFileName());
            dirFileVos.add(dirFileVo);
        }
//        map.put("data",dirFileVos);
        return new ResponseEntity<Object>(dirFileVos, HttpStatus.OK);
    }
}
