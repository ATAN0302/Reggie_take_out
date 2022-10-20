package com.example.service;

import com.example.common.DishDto;
import com.example.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author T1821
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2022-09-30 15:41:55
*/

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dto);
    void updateWithFlavor(DishDto dto);
    DishDto getByIDWithFlavor(Long id);


}
