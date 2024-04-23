package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Cabinet;

import java.util.List;

public interface CabinetService extends IService<Cabinet> {
    void bind(Integer cabinetId, Integer userId);

    Integer open(Integer cabinetId);

    void setCabinetTime(Cabinet cabinet);

    List<Cabinet> getall(Integer doorNumber);
}