package com.example.controller;

import com.example.Dto.Door;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dto.State;
import com.example.websocket.to.UnifiedTo;

@RestController
@RequestMapping("/door")
public class DoorManageController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostMapping("/open")
    public UnifiedTo openDoor(@RequestBody Door openDoor) {
        Integer adminAuthority = openDoor.getAUTHORIZATION_TOKEN();
        Integer userId = openDoor.getUserId();
        Integer id = openDoor.getId();

        if (isValidUser(adminAuthority, userId) && isClose(id)) {
            String sql = "UPDATE cabinet SET DOOR_STATUS = '1' WHERE id = ?";
            try {
                int updatedRows = jdbcTemplate.update(sql,id);
                UnifiedTo response = new UnifiedTo();
                response.setReqType(UnifiedTo.ReqType.ACK_TYPE.value);
                if (updatedRows > 0) {
                    State state = new State();
                    state.setState_code(200);
                    state.setState_msg("Door opened successfully");
                    response.setState(state);
                    // saveLogToDatabase("打开门", adminAuthority);
                } else {
                    State state = new State();
                    state.setState_code(404);
                    state.setState_msg("No data found");
                    response.setState(state);
                }
                return response;
            } catch (DataAccessException e) {
                UnifiedTo errorResponse = new UnifiedTo();
                errorResponse.setReqType(UnifiedTo.ReqType.ACK_TYPE.value);
                State errorState = new State();
                errorState.setState_code(500);
                errorState.setState_msg("Failed to open door: Database error");
                errorResponse.setState(errorState);
                return errorResponse;
            }
        } else {
            UnifiedTo unauthorizedResponse = new UnifiedTo();
            unauthorizedResponse.setReqType(UnifiedTo.ReqType.ACK_TYPE.value);
            State unauthorizedState = new State();
            unauthorizedState.setState_code(401);
            unauthorizedState.setState_msg("Unauthorized access or door is already open");
            unauthorizedResponse.setState(unauthorizedState);
            return unauthorizedResponse;
        }
    }

    @PostMapping("/close")
    public UnifiedTo closeDoor(@RequestBody Door closeDoor) {
        Integer adminAuthority = closeDoor.getAUTHORIZATION_TOKEN();
        Integer userId = closeDoor.getUserId();
        Integer id = closeDoor.getId();

        if (isValidUser(adminAuthority, userId) && isOpen(id)) {
            String sql = "UPDATE cabinet SET DOOR_STATUS = '0' WHERE id = ?";
            try {
                int updatedRows = jdbcTemplate.update(sql, id);
                UnifiedTo response = new UnifiedTo();
                response.setReqType(UnifiedTo.ReqType.ACK_TYPE.value);
                if (updatedRows > 0) {
                    State state = new State();
                    state.setState_code(200);
                    state.setState_msg("Door closed successfully");
                    response.setState(state);
                    // saveLogToDatabase("关闭门", adminAuthority);
                } else {
                    State state = new State();
                    state.setState_code(404);
                    state.setState_msg("No data found");
                    response.setState(state);
                }
                return response;
            } catch (DataAccessException e) {
                UnifiedTo errorResponse = new UnifiedTo();
                errorResponse.setReqType(UnifiedTo.ReqType.ACK_TYPE.value);
                State errorState = new State();
                errorState.setState_code(500);
                errorState.setState_msg("Failed to close door: Database error");
                errorResponse.setState(errorState);
                return errorResponse;
            }
        } else {
            UnifiedTo unauthorizedResponse = new UnifiedTo();
            unauthorizedResponse.setReqType(UnifiedTo.ReqType.ACK_TYPE.value);
            State unauthorizedState = new State();
            unauthorizedState.setState_code(401);
            unauthorizedState.setState_msg("Unauthorized access or door is already closed");
            unauthorizedResponse.setState(unauthorizedState);
            return unauthorizedResponse;
        }
    }

    // 验证用户身份的方法
    private boolean isValidUser(Integer authorizationToken, Integer userId) {
        // 当前登录的人 和 柜门对应的人 两个string都是账号
        String sql = "SELECT ADMIN_AUTORITY FROM user WHERE USER_ID = ?";
        Integer adminAuthority = jdbcTemplate.queryForObject(sql, Integer.class, authorizationToken);
        /*
         * 当前登陆的人权限是管理员或者是超级管理员可以通过或者当前登录的人是柜门对应的人也可以通过
         */
        return adminAuthority != null
                && (adminAuthority == 1 || adminAuthority == 2 || authorizationToken.equals(userId));
    }

    private boolean isClose(int doorUnion) {
        String sql = "SELECT DOOR_STATUS FROM cabinet WHERE id = ?";
        Integer doorStatus = jdbcTemplate.queryForObject(sql, Integer.class, doorUnion);
        /* 当前的状态 */
        return doorStatus != null && doorStatus == 0;
    }

    private boolean isOpen(int doorUnion) {
        String sql = "SELECT DOOR_STATUS FROM cabinet WHERE id = ?";
        Integer doorStatus = jdbcTemplate.queryForObject(sql, Integer.class, doorUnion);
        /* 当前的状态 */
        return doorStatus != null && doorStatus == 1;
    }
}
