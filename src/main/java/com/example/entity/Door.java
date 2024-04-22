package com.example.entity;
import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
public class Door {
    private  Integer id;

    private Integer doorId;

    private Integer doorNumber;

    private Integer userId;

    private Time startTime;

    private Time endTime;

    private Integer doorStatus;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createUser;

    private Integer updateUser;

    private Integer AUTHORIZATION_TOKEN;
}
