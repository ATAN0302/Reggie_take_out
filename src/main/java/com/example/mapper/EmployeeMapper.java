package com.example.mapper;

import com.example.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author T1821
* @description 针对表【employee(员工信息)】的数据库操作Mapper
* @createDate 2022-09-30 15:37:22
* @Entity com.example.entity.Employee
*/

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}




