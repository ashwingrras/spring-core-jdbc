package org.example.DAO;

import org.example.modal.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Person person) {
        return jdbcTemplate.update("INSERT INTO person (name, age) VALUES (?, ?)",
                person.getName(), person.getAge());
    }

    public int update(Person person) {
        return jdbcTemplate.update("UPDATE person SET name=?, age=? WHERE id=?",
                person.getName(), person.getAge(), person.getId());
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=?", new Object[]{id},
                new RowMapper<Person>() {
                    @Override
                    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Person person = new Person();
                        person.setId(rs.getInt("id"));
                        person.setName(rs.getString("name"));
                        person.setAge(rs.getInt("age"));
                        return person;
                    }
                });
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
                return person;
            }
        });
    }
}

