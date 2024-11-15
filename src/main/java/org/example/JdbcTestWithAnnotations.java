package org.example;

import org.example.with_annotation_modal.User;
import org.example.with_annotation_modal.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JdbcTestWithAnnotations
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        UserDao userDao = context.getBean(UserDao.class);

        User user = new User();
        user.setId(1L);
        user.setName("Ashwin");

        userDao.create(user);
        System.out.println("User created successfully.");
    }
}
