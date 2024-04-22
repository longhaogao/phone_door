package com.example.controller;

import com.example.entity.Result;
import com.example.entity.ScheduleCharge;
import com.example.service.ScheduleChargeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("schedule_charge")
public class ScheduleChargeController {

    @Autowired
    private ScheduleChargeService scheduleChargeService;

    /**
     * 查询所有充电时间区段
     */
    @GetMapping("/getall")
    public Result getAll(){
        List<ScheduleCharge> list = scheduleChargeService.getAll();
        for (ScheduleCharge scheduleCharge : list) {
            log.info("查询的id:"+scheduleCharge.getId());
        }
        return Result.ok(list);
    }


    /**
     * 添加充电时间记录
     * @param scheduleCharge
     */
    @PostMapping("/add")
    public Result add(@RequestBody ScheduleCharge scheduleCharge){
        scheduleChargeService.add(scheduleCharge);
        log.info("插入一条数据的id："+scheduleCharge.getId());
        return Result.ok();
    }


    /**
     * 删除一条充电记录
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        scheduleChargeService.deleteById(id);
        log.info("删除一条充电记录id为："+id);
        return Result.ok();
    }

    /**
     * 更新一条记录
     * @param scheduleCharge
     */
    @PutMapping("/update")
    public Result update(@RequestBody ScheduleCharge scheduleCharge){
        scheduleChargeService.updateRecord(scheduleCharge);
        log.info("更新一条充电记录id为："+scheduleCharge.getId());
        return Result.ok();
    }
}
