package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.example.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author T1821
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2022-09-30 15:37:22
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{

}




