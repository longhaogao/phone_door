package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;

@TableName
@Data
public class ScheduleCharge {

        private Integer id;

        private Integer week;

        private Time startTime;

        private Time endTime;

        private Integer status;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

        private Integer createUser;

        private Integer updateUser;
}
