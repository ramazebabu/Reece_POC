package com.learn.springreactivecrud.utils;

import com.learn.springreactivecrud.dto.EmployeeDto;
import com.learn.springreactivecrud.entity.Employee;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static EmployeeDto entityToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        return employeeDto;
    }

    public static Employee dtoToEntity(EmployeeDto employeeDto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        return employee;
    }

}
