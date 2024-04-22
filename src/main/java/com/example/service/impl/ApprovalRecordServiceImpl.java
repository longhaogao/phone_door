package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ApprovalRecord;
import com.example.mapper.ApprovalRecordMapper;
import com.example.service.ApprovalRecordService;
import org.springframework.stereotype.Service;

@Service
public class ApprovalRecordServiceImpl extends ServiceImpl<ApprovalRecordMapper, ApprovalRecord> implements ApprovalRecordService {
}
