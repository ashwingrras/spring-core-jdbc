package org.example;

import org.example.without_annotation_modal.Employee;
import org.example.without_annotation_modal.EmployeeDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcTestWithContext
{
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");

        EmployeeDao dao=(EmployeeDao)ctx.getBean("edao");
        int status=dao.saveEmployee(new Employee(102,"Amit",35000f));
        System.out.println(status);
    }
}
