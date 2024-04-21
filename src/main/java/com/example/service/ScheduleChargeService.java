package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.ScheduleCharge;

import java.util.List;

public interface ScheduleChargeService extends IService<ScheduleCharge>{
    List<ScheduleCharge> getAll();

    void add(ScheduleCharge scheduleCharge);

    void deleteById(Integer id);

    void updateRecord(ScheduleCharge scheduleCharge);
}
