package com.example.mapper;

import com.example.entity.AddressBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author T1821
* @description 针对表【address_book(地址管理)】的数据库操作Mapper
* @createDate 2022-09-30 15:41:55
* @Entity com.example.entity.AddressBook
*/
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}




