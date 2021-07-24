package cn.gb.demo.spring5.jdbcTemplateDemo;

import cn.gb.demo.spring5.jdbcTemplateDemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UpdateDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void update(Book book) {
        String sql = "UPDATE BOOK SET NAME=? WHERE BOOK_ID=?";
        Object[] arg = {book.getName(),book.getBook_id()};
        int updateNum = jdbcTemplate.update(sql,arg);

        System.out.println(updateNum);
    }

}
