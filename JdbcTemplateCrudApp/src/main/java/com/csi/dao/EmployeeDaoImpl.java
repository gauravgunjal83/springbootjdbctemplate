package com.csi.dao;

import com.csi.model.Employee;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component

public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    String INSERT_SQL="insert into employee(empid,empname,empaddress,empsalary,empcontactnumber,empemailid)values (?,?,?,?,?,?)";

    String SELECT_SQL="select * from employee ";

    String UPDATE_SQL="update employee set  empname=?, empaddress=?,empsalary=?,empcontactnumber=?,empemailid=? where empid=?";

    String DELETE_SQL="delete from employee where empid=?";

    private Employee employee(ResultSet resultSet,int n) throws SQLException {
        return  Employee.builder().empId(resultSet.getInt(1)).empName(resultSet.getString(2)).empAddress(resultSet.getString(3)).empSalary(resultSet.getDouble(4)).empContactNumber(resultSet.getLong(4)).empEmailId(resultSet.getString(6)).build();
    }
    @Override
    public void saveData(Employee employee) {

        jdbcTemplate.update(INSERT_SQL,employee.getEmpId(),employee.getEmpName(), employee.getEmpAddress(),employee.getEmpSalary(),employee.getEmpContactNumber(),employee.getEmpEmailId());
    }

    @Override
    public List<Employee> getAllData() {
        return jdbcTemplate.query(SELECT_SQL,this::employee);
    }

    @Override
    public void updateData(int empId, Employee employee) {

        jdbcTemplate.update(UPDATE_SQL,employee.getEmpName(), employee.getEmpAddress(),employee.getEmpSalary(),employee.getEmpContactNumber(),employee.getEmpEmailId(),empId);
    }

    @Override
    public void deleteDataById(int empId) {

        jdbcTemplate.update(DELETE_SQL,empId);
    }
}
