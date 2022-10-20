package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.DishDto;
import com.example.common.R;
import com.example.entity.Dish;
import com.example.entity.DishFlavor;
import com.example.service.DishFlavorService;
import com.example.service.DishService;
import com.example.mapper.DishMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author T1821
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2022-09-30 15:41:55
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{
    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品保存信息
     * @param dto
     */
    @Override
    public void saveWithFlavor(DishDto dto) {
        //保存菜品信息
        this.save(dto);
        //菜品口味
        List<DishFlavor> flavors = dto.getFlavors();

        flavors = flavors.stream().map(item->{
            item.setDishId(dto.getId());
            return item;
                }).collect(Collectors.toList());
        //批量保存菜品口味数据,向dish_flavor表插入数据
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 修改菜品信息保存
     * @param dto
     */
    @Override
    public void updateWithFlavor(DishDto dto) {
        this.updateById(dto);

        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dto.getId());
        //删除当前菜品关联的口味信息
        dishFlavorService.remove(queryWrapper);

        List<DishFlavor> list = dto.getFlavors();

        list = list.stream().map(item->{
            item.setDishId(dto.getId());
            return item;
        }).collect(Collectors.toList());

        //重新保存当前菜品关联的口味信息
        dishFlavorService.saveBatch(list);
    }

    @Override
    public DishDto getByIDWithFlavor(Long id) {
        //根据id查询菜品基本信息
        Dish dish = this.getById(id);
        //查询口味信息
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        //条件构造器
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        //设置菜品对应的口味信息
        dishDto.setFlavors(flavors);
        return dishDto;
    }
}




