package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dao.ApprovalRecordDTO;
import com.example.dao.LoginFormDTO;
import com.example.dao.Result;
import com.example.entity.Approval;
import com.example.entity.User;


import javax.servlet.http.HttpSession;


public interface IUserService extends IService<User> {


    Result logIn(LoginFormDTO loginForm, HttpSession session);


    Result getIdentity(HttpSession session);

    Result approval(ApprovalRecordDTO approvalDTO, HttpSession session);

    Result questOpen(Approval approval, HttpSession session);

    Result questRecord(String id, HttpSession session);
}
