package ru.frizin.springcourse.config.DAO;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.frizin.springcourse.config.models.Book;
import ru.frizin.springcourse.config.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("Select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show (int id){
        return jdbcTemplate.query("select * from Book where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT into Book(name_book, autor, year_book) values (?, ?, ?)",
                book.getName_book(), book.getAutor(), book.getYear_book());
    }

    public void update (Book book, int id){
        jdbcTemplate.update(("UPDATE book set name_book=?, autor=?, year_book=? where id=?"),
                book.getName_book(), book.getAutor(), book.getYear_book(), id);
    }


}
