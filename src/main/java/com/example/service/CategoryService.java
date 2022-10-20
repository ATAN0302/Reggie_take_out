package com.example.service;

import com.example.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author T1821
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2022-09-30 15:41:55
*/

public interface CategoryService extends IService<Category> {
    public void remove(Long id);

}
