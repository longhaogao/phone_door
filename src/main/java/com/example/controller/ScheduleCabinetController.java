package com.example.controller;

import com.example.Dto.Result;
import com.example.entity.ScheduleCabinet;
import com.example.service.ScheduleCabinetService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 柜门可用时间段controller
 */
@Slf4j
@RestController
@RequestMapping("schedule_cabinet")
public class ScheduleCabinetController {

    @Autowired
    private ScheduleCabinetService scheduleCabinetService;

    /**
     * 查询所有柜门可用时间区段
     */
    @GetMapping("/getall")
    public Result getAll(){
        List<ScheduleCabinet> list = scheduleCabinetService.getAll();
        for (ScheduleCabinet scheduleCharge : list) {
            log.info("查询的id:"+scheduleCharge.getId());
        }
        return Result.ok(list);
    }


    /**
     * 添加柜门时间记录
     * @param scheduleCabinet
     */
    @PostMapping("/add")
    public Result add(@RequestBody ScheduleCabinet scheduleCabinet ){
        scheduleCabinetService.add(scheduleCabinet);
        log.info("插入一条数据的id："+scheduleCabinet.getId());
        return Result.ok();
    }


    /**
     * 删除一条柜门时间记录
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id){
        scheduleCabinetService.deleteById(id);
        log.info("删除一条充电记录id为："+id);
        return Result.ok();
    }

    /**
     * 更新一条记录
     * @param scheduleCabinet
     */
    @PutMapping("/update")
    public Result update(@RequestBody ScheduleCabinet scheduleCabinet){
        scheduleCabinetService.updateRecord(scheduleCabinet);
        log.info("更新一条充电记录id为："+scheduleCabinet.getId());
        return Result.ok();
    }
}
