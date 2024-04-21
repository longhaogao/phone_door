package com.example.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ScheduleCabinet;
import com.example.entity.ScheduleCharge;
import com.example.mapper.ScheduleCabinetMapper;
import com.example.service.ScheduleCabinetService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleCabinetServiceImpl extends ServiceImpl<ScheduleCabinetMapper,ScheduleCabinet> implements ScheduleCabinetService {
    @Override
    public List<ScheduleCabinet> getAll() {
        List<ScheduleCabinet> list = this.list();
        return list;
    }

    @Override
    public void add(ScheduleCabinet scheduleCabinet) {
        //设置默认值
        if(scheduleCabinet.getStartTime()==null)
            scheduleCabinet.setStartTime(new Time(8,0,0));
        if(scheduleCabinet.getEndTime()==null)
            scheduleCabinet.setEndTime(new Time(18,0,0));
        scheduleCabinet.setCreateTime(LocalDateTime.now());
        scheduleCabinet.setUpdateTime(LocalDateTime.now());
        scheduleCabinet.setCreateUser(1);
        scheduleCabinet.setUpdateUser(1);
        this.saveOrUpdate(scheduleCabinet);
    }

    @Override
    public void deleteById(Integer id) {
        this.removeById(id);
    }

    @Override
    public void updateRecord(ScheduleCabinet scheduleCabinet) {
        this.updateById(scheduleCabinet);
    }
}
