package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    /**
     * 主键，用户id
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 人脸信息
     */
    private String faceInformation;

    /**
     * 指纹信息
     */
    private String fingerInformation;

    /**
     * 管理权限
     */
    private int adminAuthority;

    /**
     * 班级编号
     */
    private String classId;

    /**
     * 柜子编号
     */
    private String doorId;

    /**
     * 柜门编号
     */
    private String doorNumber;

    /**
     * 所属柜门编号
     */
    private String doorUnion;
}
