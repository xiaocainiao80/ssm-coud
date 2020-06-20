package org.xxh.controller;

import cn.hutool.core.lang.UUID;
import edu.princeton.cs.algs4.StdOut;
import lombok.RequiredArgsConstructor;



import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xxh.pojo.Dir;
import org.xxh.service.DirService;
import org.xxh.service.FileService;
import org.xxh.utils.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final FileService fileService;
    private final DirService dirService;
    /**
     * 上传文件到本地磁盘，数据库中只保存文件的访问路径，这个方法更安全
     * @param userId
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<Object> insertCustomer(String userId, String dirId,MultipartFile file
    , HttpServletRequest request) {
        Dir dir = new Dir();
        if(!dirId.equals("")||dirId!=null||!dirId.equals("")||!dirId.equals("null")) {
            dir = dirService.findByDirName(dirId);
        }
//        System.out.println(userId);
        //获取文件在服务器上的储存位置
        String path = request.getSession().getServletContext().getRealPath("/upload");
        if(dir!=null){
            path = path+dir.getDirPath();
        }
        File filePath = new File(path);
        System.out.println("文件保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录：" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);

        //设置文件新名字
        String fileName = UUID.fastUUID()+ "." + type;
        System.out.println("文件新名称：" + fileName);
        //在指定路径创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            file.transferTo(targetFile);
            org.xxh.pojo.File fileInfo = new org.xxh.pojo.File();
            fileInfo.setFileName(fileName);
            if(dir!=null){
                fileInfo.setFileDirId(dir.getId());
            }
            fileInfo.setFileUploadUserId(Integer.parseInt(userId));
            fileInfo.setFileType(type);
            fileInfo.setFileStatus(1);
            fileInfo.setDownloadCount(0);
            fileInfo.setFileUploadTime(new Timestamp(System.currentTimeMillis()));
            System.out.println(fileInfo.toString());
            return new ResponseEntity<Object>(fileService.save(fileInfo), HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Object>("上传失败!请稍后重试",HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/download")
    public void  down(String path, HttpServletRequest request, HttpServletResponse response) throws Exception{
//        String data="这是一个下载文件";   //传入数据
        String filePath = request.getServletContext().getRealPath("/upload");
        filePath = filePath + path;
        File file=new File(filePath);
//        FileUtil.getFile(file.getBytes(),file.getName());
        FileUtil.responseTo(file,response);
//        file.delete();
//        System.out.println("success");
    }

//   删除文件
    @DeleteMapping("/file/delete/{name}")
    public ResponseEntity<Object> deleteFile(@PathVariable String name,String path,HttpServletRequest request){
        System.out.println(name);
        System.out.println(path);
        String suffix = path.substring(path.lastIndexOf("."));
        System.out.println(suffix);
//        删除文件
        String filePath = request.getServletContext().getRealPath("/upload");
        filePath = filePath + path;
        File file = new File(filePath);
        name = name + suffix;
        Integer result = fileService.deleteByFileName(name);
        if (result > 0 &&file.delete()){
            return new ResponseEntity<Object>("200",HttpStatus.OK);
        }
         return new ResponseEntity<Object>("400",HttpStatus.OK);
    }

//    进入回收站
    @PostMapping("/file/delete")
    public ResponseEntity<Object> fileDelete(@RequestBody Map<String,String> map){
        String name = map.get("name");
        System.out.println(name);
//        查找到file
        Integer result = fileService.updateFileStaus(name);
        if (result > 0){
            return new ResponseEntity<Object>("200",HttpStatus.OK);
        }
        return new ResponseEntity<Object>("400",HttpStatus.OK);
    }
}
