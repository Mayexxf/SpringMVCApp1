package ru.frizin.springcourse.config.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.frizin.springcourse.config.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(name, age, email) VALUES (?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person){
        jdbcTemplate.update(("UPDATE Person SET name=?, age=?, email=? WHERE id=?"), person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id){
    jdbcTemplate.update("DELETE FROM person WHERE id=?", id);

    }

    public void testMultipleUpdate(){
        List<Person> people = create100People();

        long before = System.currentTimeMillis();

        for (Person person: people){
            jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ?, ?)", person.getId(), person.getName(), person.getAge(), person.getEmail());
        }

        long after = System.currentTimeMillis();

        System.out.println("Time - " + (after - before));
    }

    public void deleteAlltestBatch(){
        jdbcTemplate.update("DELETE FROM person");
    }


    public void testBatchUpdate(){
        List<Person> people = create100People();

        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES (?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, people.get(i).getId());
                        ps.setString(2, people.get(i).getName());
                        ps.setInt(3, people.get(i).getAge());
                        ps.setString(4, people.get(i).getEmail());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });

        long after = System.currentTimeMillis();
        System.out.println("Time - " + (after - before));
    }

    private List<Person> create100People(){
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 1000; i++){
            people.add(new Person(i, "Name" + i, 30, "test" + i + "@mail.ru"));
        }

        return people;
    }
}