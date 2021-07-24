package cn.gb.demo.spring5.jdbcTemplateDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 批量插入
    public int[] batchInsert(List<Object[]> list) {

        String sql = "INSERT INTO BOOK VALUES (?,?,?);";
        return jdbcTemplate.batchUpdate(sql, list);
    }


    // 批量修改
    public int[] batchUpdate(List<Object[]> list) {

        String sql = "UPDATE BOOK SET NUMBER = ? WHERE BOOK_ID = ?;";
        return jdbcTemplate.batchUpdate(sql, list);
    }

    // 批量删除
    public int[] batchDelete(List<Object[]> list) {

        String sql = "DELETE FROM BOOK WHERE BOOK_ID = ?;";
        return jdbcTemplate.batchUpdate(sql, list);
    }
}
