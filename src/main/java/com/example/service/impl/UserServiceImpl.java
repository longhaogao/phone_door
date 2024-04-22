package com.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.ApprovalRecordDTO;
import com.example.dao.LoginFormDTO;
import com.example.dao.Result;
import com.example.dao.UserDTO;
import com.example.entity.Approval;
import com.example.entity.ApprovalRecord;
import com.example.entity.User;

import com.example.mapper.UserMapper;
import com.example.service.ApprovalRecordService;
import com.example.service.ApprovalService;
import com.example.service.IUserService;
import com.example.util.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private ApprovalRecordService approvalRecordService;

    @Resource
    private ApprovalService approvalService;

    /**
     * 登录
     * @param loginForm
     * @param session
     * @return
     */
    @Override
    public Result logIn(LoginFormDTO loginForm, HttpSession session) {
        //验证手机号
        String phone = loginForm.getUserAccount();
        if (RegexUtils.isPhoneInvalid(phone)){
            return Result.fail("手机号码格式错误！");
        }

        //根据手机号查询用户
        User user = query().eq("user_account", phone).one();
        if (user == null){
            //手机号不存在，创建手机号，自动注册
            user = createUser(phone);
        }
        //保存用户到session
        session.setAttribute("user", user);

        return Result.ok();
    }

    /**
     * 获取身份
     * @param session
     * @return
     */
    @Override
    public Result getIdentity(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return Result.ok(user.getAdminAuthority());
    }

    /**
     * 审批
     * @param approvalDTO
     * @param session
     * @return
     */
    @Override
    public Result approval(ApprovalRecordDTO approvalDTO, HttpSession session) {
        User user = (User) session.getAttribute("user");
        ApprovalRecord approvalRecord = new ApprovalRecord();
        approvalRecord.setApprovalAdvice(approvalDTO.getApprovalAdvice());
        approvalRecord.setApprovalId(approvalDTO.getApprovalId());
        approvalRecord.setApprovalTime(new java.sql.Timestamp(new java.util.Date().getTime()));
        approvalRecord.setApprovalUserId(user.getUserId());
        int itemsStatus = approvalDTO.getItemsStatus();
        //保存审批记录
        boolean update = approvalRecordService.save(approvalRecord);
        //更新申请状态
        boolean b = approvalService.update(new LambdaUpdateWrapper<Approval>().set(Approval::getItemsStatus, itemsStatus).eq(Approval::getApprovalId,approvalDTO.getApprovalId()));
        if (!update || !b){
            return Result.fail("审批失败");
        }
        return Result.ok("审批完成");
    }

    /**
     * 开门申请
     * @param approval
     * @param session
     * @return
     */
    @Override
    public Result questOpen(Approval approval, HttpSession session) {
        //保存申请信息
        boolean b = approvalService.save(approval);
        if (!b){
            return Result.fail("申请失败！");
        }
        return Result.ok("申请成功！");
    }

    /**
     * 记录查询
     * @param id
     * @param session
     * @return
     */
    @Override
    public Result questRecord(String id, HttpSession session) {
        //记录查询
        Approval approval = approvalService.query().eq("approval_id", id).one();
        return Result.ok(approval);
    }

    /**
     * 新建用户
     * @param phone
     * @return
     */
    private User createUser(String phone) {
        User user = new User();
        user.setUserAccount(phone);
        user.setUserPassword("12345678");  //默认密码
        user.setAdminAuthority(2);        //默认权限
        save(user);
        return user;
    }
}
