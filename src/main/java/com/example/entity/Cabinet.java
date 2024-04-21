package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;

@TableName
@Data
public class Cabinet {
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

}
