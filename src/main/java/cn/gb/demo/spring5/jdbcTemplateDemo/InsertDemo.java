package cn.gb.demo.spring5.jdbcTemplateDemo;

import cn.gb.demo.spring5.jdbcTemplateDemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class InsertDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void inset(Book book) {

        String sql = "INSERT INTO BOOK VALUES (?,?,?);";
        Object[] arg = {book.getBook_id(),book.getName(),book.getNumber()};

        int insertNum = jdbcTemplate.update(sql,arg);

        System.out.println(insertNum);
    }
}
