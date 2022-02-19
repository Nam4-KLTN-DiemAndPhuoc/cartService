package com.cartservice.VO;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
public class User {
    private Long id;
    private String userName;
    private String password;
    private Role role;
    private String email;
    private String phone;
    private boolean gender;
    private Date createdAt;
    private Date deletedAt;
    private Long deletedBy;
}
