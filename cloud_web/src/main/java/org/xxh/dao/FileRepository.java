package org.xxh.dao;


import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.xxh.pojo.File;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface FileRepository extends Mapper<File> {

//    根据dirId查询
    @Select("select file_id,file_size,file_upload_time,download_count,file_name,file_type from file_info where file_dir_id = #{dirId} and file_status = 1")
    @Results(
            @Result(column = "file_id",property = "id",id = true)
    )
    List<File> findByDirId(int dirId);

    @Update("update file_info set file_status = 0 where file_name = #{fileName}")
    Integer updateStatusByFileName(String fileName);
}
