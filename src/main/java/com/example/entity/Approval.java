package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("approval")
public class Approval {
    /**
     * 主键，消息id
     */
    @TableId(value = "approval_id", type = IdType.ASSIGN_UUID)
    private String approvalId;

    /**
     * 申请人姓名
     */
    private String userName;

    /**
     * 账号
     */
    private String userId;

    /**
     * 班级
     */
    private String classId;

    /**
     * 审批事项
     */
    private String itemsSubject;

    /**
     * 原因
     */
    private String userReason;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 截止时间
     */
    private String endTime;

    /**
     * 状态
     */
    private String itemsStatus;

}
