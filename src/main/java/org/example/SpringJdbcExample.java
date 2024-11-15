package org.example;

import org.example.DAO.PersonDAO;
import org.example.modal.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SpringJdbcExample {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        PersonDAO personDAO = context.getBean(PersonDAO.class);

        // Save a new person
        Person person = new Person();
        person.setName("Rohan");
        person.setAge(22);
        personDAO.save(person);

        // Update person
        /*person.setName("Mehak");
        person.setAge(24);
        person.setId(1);
        personDAO.update(person);*/

        // Find person by ID
        Person foundPerson = personDAO.findById(person.getId());
        System.out.println("Found Person: " + foundPerson.getName());

        // Find all persons
        List<Person> persons = personDAO.findAll();
        persons.forEach(p -> System.out.println(p.getName()));

        // Delete person
        personDAO.deleteById(person.getId());

        context.close();
    }
}

