package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ShoppingCart;
import com.example.service.ShoppingCartService;
import com.example.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

/**
* @author T1821
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2022-09-30 15:41:55
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{

}




