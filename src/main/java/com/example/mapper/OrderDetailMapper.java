package com.example.mapper;

import com.example.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author T1821
* @description 针对表【order_detail(订单明细表)】的数据库操作Mapper
* @createDate 2022-09-30 15:41:55
* @Entity com.example.entity.OrderDetail
*/
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}




