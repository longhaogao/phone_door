package com.example.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Cabinet;
import com.example.entity.ScheduleCabinet;
import com.example.mapper.CabinetMapper;
import com.example.service.CabinetService;
import com.example.service.ScheduleCabinetService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

@Service
public class CabinetServiceImpl extends ServiceImpl<CabinetMapper, Cabinet> implements CabinetService {

    @Autowired
    private ScheduleCabinetService scheduleCabinetService;

    @Override
    public void bind(Integer cabinetId, Integer userId) {
        //检查柜子是否绑定为空
        Cabinet cabinet = this.getById(cabinetId);
        if(cabinet.getUserId()!=null)
            throw new RuntimeException("柜子已经绑定了用户，柜子id为："+cabinet.getId());
        LambdaUpdateWrapper<Cabinet> updateWrapper = new LambdaUpdateWrapper<>();
        //status 1为已绑定
        updateWrapper.eq(Cabinet::getId,cabinetId).set(Cabinet::getDoorStatus,1);
        updateWrapper.set(Cabinet::getUserId,userId);
        //更新时间，更新人
        updateWrapper.set(Cabinet::getUpdateTime, LocalDateTime.now());
        updateWrapper.set(Cabinet::getUpdateUser,1);

        this.update(updateWrapper);
    }

    @Override
    public Integer open(Integer cabinetId) {
        //1.先查是不是在公共开箱使用时间内
        LambdaQueryWrapper<ScheduleCabinet> queryWrapper = new LambdaQueryWrapper<>();
        //获得今天星期几
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //dayOfWeek 从星期天开始，需要特判
        if(dayOfWeek==0)
            dayOfWeek=7;
        else
            dayOfWeek--;
        //当前时间 LocalTime.now()

        //查询条件
        queryWrapper.eq(ScheduleCabinet::getWeek,dayOfWeek);
        queryWrapper.le(ScheduleCabinet::getStartTime, LocalTime.now());
        queryWrapper.ge(ScheduleCabinet::getEndTime,LocalTime.now());
        ScheduleCabinet one = scheduleCabinetService.getOne(queryWrapper);
        if(one!=null&&one.getModel()==1){
            return 1;
        }
        else if(one!=null){
            //2.再查自己箱子是不是单独设置了临时开箱时间
            Cabinet cabinet = this.getById(cabinetId);
            if(cabinet.getStartTime().toLocalTime().isBefore(LocalTime.now())&&
                    cabinet.getEndTime().toLocalTime().isAfter(LocalTime.now())){
                return 1;
            }
            if(cabinet.getStartTime()==null&&cabinet.getEndTime()==null){
                //如果没有设置单独开箱时间，则返回管控模式
                return one.getModel();
            }
        }
        //无此时间段记录，则默认返回一般时间段
        return 2;

    }

    @Override
    public void setCabinetTime(Cabinet cabinet) {
        cabinet.setUpdateTime(LocalDateTime.now());
        cabinet.setUpdateUser(1);
        this.updateById(cabinet);
    }
}
