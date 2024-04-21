package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.ScheduleCabinet;
import com.example.entity.ScheduleCharge;

import java.util.List;

public interface ScheduleCabinetService extends IService<ScheduleCabinet> {
    List<ScheduleCabinet> getAll();

    void add(ScheduleCabinet scheduleCabinet);

    void deleteById(Integer id);

    void updateRecord(ScheduleCabinet scheduleCabinet);

}
