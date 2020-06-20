package org.xxh.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "user_info")
@ToString
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    private int userId;
    private String username;
    private String email;
    private String password;
    private String telephone;
    private Timestamp registerTime;
}
