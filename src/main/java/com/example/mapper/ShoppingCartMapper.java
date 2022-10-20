package com.example.mapper;

import com.example.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author T1821
* @description 针对表【shopping_cart(购物车)】的数据库操作Mapper
* @createDate 2022-09-30 15:41:55
* @Entity com.example.entity.ShoppingCart
*/
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}




