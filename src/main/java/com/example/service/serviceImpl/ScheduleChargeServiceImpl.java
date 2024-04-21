package com.example.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ScheduleCharge;
import com.example.mapper.ScheduleChargeMapper;
import com.example.service.ScheduleChargeService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class ScheduleChargeServiceImpl  extends ServiceImpl<ScheduleChargeMapper, ScheduleCharge> implements ScheduleChargeService {
    @Override
    public List<ScheduleCharge> getAll() {
        List<ScheduleCharge> list = this.list();
        return list;
    }

    @Override
    public void add(ScheduleCharge scheduleCharge) {
        //设置默认值
        if(scheduleCharge.getStartTime()==null)
            scheduleCharge.setStartTime(new Time(8,0,0));
        if(scheduleCharge.getEndTime()==null)
            scheduleCharge.setEndTime(new Time(18,0,0));
        scheduleCharge.setCreateTime(LocalDateTime.now());
        scheduleCharge.setUpdateTime(LocalDateTime.now());
        scheduleCharge.setCreateUser(1);
        scheduleCharge.setUpdateUser(1);
        this.saveOrUpdate(scheduleCharge);
    }

    @Override
    public void deleteById(Integer id) {
        this.removeById(id);
    }

    @Override
    public void updateRecord(ScheduleCharge scheduleCharge) {
        this.updateById(scheduleCharge);
    }
}
