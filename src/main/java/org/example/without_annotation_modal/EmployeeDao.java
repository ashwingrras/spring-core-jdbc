package org.example.without_annotation_modal;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao
{
    private JdbcTemplate jdbcTemplate;

    // setter injection
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveEmployee(Employee e){
        String query="insert into employee_spring values( '"+e.getId()+"','"+e.getName()+"','"+e.getSalary()+"')";
        return jdbcTemplate.update(query);
    }
    public int updateEmployee(Employee e){
        String query="update employee_spring set name='"+e.getName()+"',salary='"+e.getSalary()+"' where id='"+e.getId()+"' ";
        return jdbcTemplate.update(query);
    }
    public int deleteEmployee(Employee e){
        String query="delete from employee_spring where id='"+e.getId()+"' ";
        return jdbcTemplate.update(query);
    }

    public Boolean saveEmployeeByPreparedStatement(final Employee e){
        String query="insert into employee_spring values(?,?,?)";
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setInt(1,e.getId());
                ps.setString(2,e.getName());
                ps.setFloat(3,e.getSalary());

                return ps.execute();

            }
        });
    }

    public List<Employee> getAllEmployees(){
        return jdbcTemplate.query("select * from employee_spring",new ResultSetExtractor<List<Employee>>(){
            @Override
            public List<Employee> extractData(ResultSet rs) throws SQLException,
                    DataAccessException {

                List<Employee> list=new ArrayList<Employee>();
                while(rs.next()){
                    Employee e=new Employee();
                    e.setId(rs.getInt(1));
                    e.setName(rs.getString(2));
                    e.setSalary(rs.getInt(3));
                    list.add(e);
                }
                return list;
            }
        });
    }

    public List<Employee> getAllEmployeesRowMapper(){
        return jdbcTemplate.query("select * from employee_spring",new RowMapper<Employee>(){
            @Override
            public Employee mapRow(ResultSet rs, int rownumber) throws SQLException {
                Employee e=new Employee();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setSalary(rs.getInt(3));
                return e;
            }
        });
    }

}
