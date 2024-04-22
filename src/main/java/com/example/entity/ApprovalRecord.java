package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;


@Data
@TableName("approval_record")
public class ApprovalRecord {
    /**
     * 主键
     */
    private int id;

    /**
     * 消息id
     */
    private String approvalId;

    /**
     * 审批人
     */
    private String approvalUserId;

    /**
     * 审批意见
     */
    private String approvalAdvice;

    /**
     * 审批时间
     */
    private Timestamp approvalTime;
}
