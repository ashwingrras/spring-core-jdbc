package org.example.with_annotation_modal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    // field level auto wiring
    @Autowired
    public JdbcTemplate jdbcTemplate;

    // constructor level auto wiring
    /*@Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    public void create(User user) {
        String sql = "INSERT INTO users_spring (id, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName());
    }
}
