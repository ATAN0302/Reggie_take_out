package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.DishDto;
import com.example.common.R;
import com.example.entity.Category;
import com.example.entity.Dish;
import com.example.service.CategoryService;
import com.example.service.DishFlavorService;
import com.example.service.DishService;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureDSA;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 阿谭
 * <p>
 * 2022-10-13 10:47
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 菜品管理列表查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(Dish dish,int page,int pageSize){
        Page<Dish> pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Dish::getCreateTime);
        dishService.page(pageInfo,queryWrapper);

        Page<DishDto> resPage = new Page(page,pageSize);
        BeanUtils.copyProperties(pageInfo,resPage,"records");
        resPage.setRecords(pageInfo.getRecords().stream().map(item->{
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item,dishDto);
            Category category = categoryService.getById(item.getCategoryId());
            if(category != null){
                dishDto.setCategoryName(category.getName());
            }
            return dishDto;
        }).collect(Collectors.toList()));
        return R.success(resPage);

    }

    @PostMapping
    public R<String> save(@RequestBody DishDto dto){
        dishService.saveWithFlavor(dto);
        return R.success("新增菜品成功");
    }

    @DeleteMapping
    public R<String> delete(Long ids){
        dishService.removeById(ids);
        return R.success("删除成功");
    }

    /**
     * 修改页面口味栏
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> update(Category category){
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType() != null,Category::getType,category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        return R.success(categoryService.list(queryWrapper));
    }

    /**
     * 修改页面通过id获取信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id){
        DishDto dto = dishService.getByIDWithFlavor(id);
        if(dto != null){
            return R.success(dto);
        } else {
            return R.error("没有找到菜品");
        }
    }

    /**
     * 提交保存修改信息
     * @param dto
     * @return
     */
    @PutMapping
    public R<DishDto> put(@RequestBody DishDto dto){
        dishService.updateWithFlavor(dto);
        return R.success(dto);
    }

    @PostMapping("/status")
    public R<String> status(HttpServletRequest request, @RequestBody Dish dish){
        //获得当前id
        Long id = (Long) request.getSession().getAttribute("dish");
        dish.setCreateUser(id);
        dish.setUpdateTime(LocalDateTime.now());

        dishService.updateById(dish);
        return R.success("修改成功");
    }

}
