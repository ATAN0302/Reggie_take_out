package com.example.mapper;

import com.example.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author T1821
* @description 针对表【user(用户信息)】的数据库操作Mapper
* @createDate 2022-09-30 15:41:55
* @Entity com.example.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




