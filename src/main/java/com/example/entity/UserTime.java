package com.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

import java.sql.Date;
@Getter
@Setter
public class UserTime {
    private Integer USER_ID;
    private Integer ID;
    private Date now_time;

}
