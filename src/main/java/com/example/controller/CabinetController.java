package com.example.controller;

import com.example.Dto.Bind;
import com.example.Dto.Result;
import com.example.entity.Cabinet;
import com.example.service.CabinetService;
import com.example.service.ScheduleCabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabinet")
public class CabinetController {

    @Autowired
    private CabinetService cabinetService;

    @Autowired
    private ScheduleCabinetService scheduleCabinetService;


    @GetMapping("/getall/{doorNumber}")
    public Result getAll(@PathVariable Integer doorNumber){
        List<Cabinet> cabinetList = cabinetService.getall(doorNumber);
        return Result.ok(cabinetList);
    }

    /**
     * 绑定柜子id和用户id
     * @param
     */
    @PutMapping("/bind")
    public Result bind(@RequestBody Bind bind){
        System.out.println("++++++++++++++"+bind.getCabinetId()+"++++++"+bind.getUserId());
        cabinetService.bind(bind.getCabinetId(),bind.getUserId());
        return Result.ok();
    }

    /**
     * 开箱 返回 1可以开箱，2管理时间段 3管控时间段
     */
    @GetMapping("/open")
    public Result openCabinet(Integer cabinetId){
        Integer status = cabinetService.open(cabinetId);
        return Result.ok(status);
    }


    /**
     * 为某个箱子设置单独的开柜时间
     */
    @PutMapping("/setCabinetTime")
    public Result setCabinetTime(@RequestBody Cabinet cabinet){
        cabinetService.setCabinetTime(cabinet);
        return Result.ok();
    }

    /**
     * 一键全清功能，解除所有该箱柜的绑定的人员id
     * @return doorNumber 箱柜的id
     */
    @PutMapping("/clearAll/{doorNumber}")
    public Result clearAll(@PathVariable int doorNumber){
        cabinetService.clearAll(doorNumber);
        return Result.ok();
    }
}
