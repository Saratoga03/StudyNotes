package cn.gb.demo.spring5.jdbcTemplateDemo;

import cn.gb.demo.spring5.jdbcTemplateDemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeleteDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void delete(Book book) {

        String sql = "DELETE FROM BOOK WHERE BOOK_ID = ?";
        Object arg = book.getBook_id();
        int delNum = jdbcTemplate.update(sql,arg);

        System.out.println(delNum);
    }
}
