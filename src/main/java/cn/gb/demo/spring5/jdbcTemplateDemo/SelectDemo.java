package cn.gb.demo.spring5.jdbcTemplateDemo;

import cn.gb.demo.spring5.jdbcTemplateDemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 返回某个值
    public String select1(long bookId) {

        String sql = "SELECT NAME FROM BOOK WHERE BOOK_ID = ?;";
        return jdbcTemplate.queryForObject(sql, String.class, bookId);
    }

    // 返回某个对象
    public Book select2(long bookId) {

        String sql = "SELECT * FROM BOOK WHERE BOOK_ID = ?;";
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), bookId);
        return book;
    }

    // 返回某个集合
    public List<Book> select3(long bookId) {

        String sql = "SELECT * FROM BOOK;";
        List<Book> book = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return book;
    }
}
