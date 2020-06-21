package org.xxh.dao;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.xxh.pojo.Dir;
import org.xxh.pojo.File;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DirRepository extends Mapper<Dir> {

//    自查询链接 查询该用户顶级dir
    @Select("select * from dir_info where dir_user_id = #{userId}")
    @Results(value = {
            @Result(column = "dir_id",property = "id",id = true),
            @Result(column = "dir_id",property = "dirs",many = @Many(
                    select = "org.xxh.dao.DirRepository.findByParentId"
            ))
    }
    )
    List<Dir> findTopDirByUserId(int userId);

    @Select("select * from dir_info where parent_dir = #{parentDirId} and status = 1")
    @Results(
        @Result(id = true,column = "dir_id",property = "id")
    )
    List<Dir> findByParentId(int parentDirId);

    @Update("update dir_info set dir_name = #{dirName} where dir_id =#{dirId}")
    Integer updateByDirname(@Param("dirName")String dirName,@Param("dirId")Integer dirId);

    @Update("update dir_info set status = 0 where dir_name = #{dirName}")
    Integer updateStatusByDirName(String dirName);
}
