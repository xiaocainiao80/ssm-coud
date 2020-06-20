package org.xxh.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Many;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "dir_info")
@ToString
@Getter
@Setter
public class Dir {

    @Id
    @Column(name = "dir_id")
    private Integer id;
    private String dirName;
    private Integer parentDir;
    private Integer dirUserId;
    private String dirPath;
    private Integer status;

    private List<Dir> dirs;

    private List<File> files;
}
