package com.example.controller;

import com.example.Dto.ApprovalRecordDTO;
import com.example.Dto.LoginFormDTO;
import com.example.Dto.Result;
import com.example.entity.Approval;
import com.example.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;


    /**
     * 登录功能
     * @param loginForm 登录参数，包含手机号或者手机号、密码
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginForm, HttpSession session){
        // TODO 实现登录功能
        return userService.logIn(loginForm,session);
    }

    /**
     * 登出功能
     * @return 无
     */
    @PostMapping("/logout")
    public Result logout(){
        // TODO 实现登出功能
        return Result.fail("功能未完成");
    }

    /**
     * 申请审批
     * @return
     */
    @PostMapping("/approval")
    public Result approval(@RequestBody ApprovalRecordDTO approvalDTO, HttpSession session){
        return userService.approval(approvalDTO,session);
    }

    /**
     * 记录查询
     * @return
     */
    @GetMapping("/questRecord/{id}")
    public Result questRecord(@PathVariable("id") String id, HttpSession session){
        return userService.questRecord(id,session);
    }


    /**
     * 开门申请
     * @return
     */
    @PostMapping("/questOpen")
    public Result questOpen(@RequestBody Approval approval, HttpSession session){
        return userService.questOpen(approval,session);
    }

    /**
     * 获取用户身份
     */
    @PostMapping("/getIdentity")
    public Result getIdentity(HttpSession session){
        return userService.getIdentity(session);
    }

}
