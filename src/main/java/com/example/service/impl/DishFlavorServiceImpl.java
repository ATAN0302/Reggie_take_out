package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.DishFlavor;
import com.example.service.DishFlavorService;
import com.example.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

/**
* @author T1821
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2022-09-30 15:41:55
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService{

}




