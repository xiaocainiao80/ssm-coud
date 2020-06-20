package org.xxh.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "file_info")
@ToString
@Getter
@Setter
public class File {

    @Id
    @Column(name = "file_id")
    private Integer id;

    private String fileName;
    private Integer fileSize;
    private String fileType;
    private Timestamp fileUploadTime;
    private Integer fileStatus;
    private Integer fileDirId;
    private Integer fileUploadUserId;
    private Integer downloadCount;


}
