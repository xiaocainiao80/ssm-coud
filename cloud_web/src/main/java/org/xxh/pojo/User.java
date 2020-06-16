package org.xxh.pojo;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user_info")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    private int user_id;
    private String username;
    private String email;
    private String password;
    private String telephone;
}
