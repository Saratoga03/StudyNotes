package cn.gb.demo.spring5.testClass;

import cn.gb.demo.spring5.annotationVerDemo.Anime;
import cn.gb.demo.spring5.annotationVerDemo.AnnotationConf;
import cn.gb.demo.spring5.aopDemo.MainClassAnime;
import cn.gb.demo.spring5.demoClass.Person;
import cn.gb.demo.spring5.demoClass.Skill;
import cn.gb.demo.spring5.jdbcTemplateDemo.*;
import cn.gb.demo.spring5.jdbcTemplateDemo.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDemo {

    @Test
    public void testFunc() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanDemo.xml");
        Person person = context.getBean("person", Person.class);

        System.out.println("生命周期演示：第六步：成功创建bean实例。");

        System.out.println(person);

        context.close();
    }

    @Test
    public void testFuncAnnotationVer() {
        /*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("annotationVerBean.xml");*/
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConf.class);
        Anime anime = context.getBean("anime", Anime.class);

        System.out.println(anime);
    }

    @Test
    public void testAop() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aopBean.xml");
        MainClassAnime anime = context.getBean("mainClassAnime", MainClassAnime.class);

        anime.testFunc();
        System.out.println(anime);
    }

    @Test
    public void testjdbcTemplate() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("jdbcTemplateBean.xml");
        InsertDemo insertDemo = context.getBean("insertDemo", InsertDemo.class);
        UpdateDemo updateDemo = context.getBean("updateDemo", UpdateDemo.class);
        DeleteDemo deleteDemo = context.getBean("deleteDemo", DeleteDemo.class);
        SelectDemo selectDemo = context.getBean("selectDemo", SelectDemo.class);
        BatchDemo batchDemo = context.getBean("batchDemo", BatchDemo.class);

        /*Book book = new Book();
        book.setBook_id(888);
        book.setName("我的青春恋爱物语果然有问题");
        book.setNumber(14);
        insertDemo.inset(book);*/

        /*Book book = new Book();
        book.setName("改一改看看");
        book.setBook_id(888);
        updateDemo.update(book);*/

        /*Book book = new Book();
        book.setBook_id(888);
        deleteDemo.delete(book);*/

        /*long bookId = 1001;
        System.out.println(selectDemo.select1(bookId));

        System.out.println(selectDemo.select2(bookId));

        System.out.println(selectDemo.select3(bookId));*/

        /*List<Object[]> list = new ArrayList<>();
        Object[] o1 = {81, "空之境界", 1};
        Object[] o2 = {82, "我的青春恋爱物语果然有问题", 2};
        Object[] o3 = {83, "你的名字", 3};
        list.add(o1);
        list.add(o2);
        list.add(o3);

        System.out.println(Arrays.toString(batchDemo.batchInsert(list)));*/

        /*List<Object[]> list = new ArrayList<>();
        Object[] o1 = {8, 81};
        Object[] o2 = {88, 82};
        Object[] o3 = {888, 83};
        list.add(o1);
        list.add(o2);
        list.add(o3);

        System.out.println(Arrays.toString(batchDemo.batchUpdate(list)));*/

        List<Object[]> list = new ArrayList<>();
        Object[] o1 = {81};
        Object[] o2 = {82};
        Object[] o3 = {83};
        list.add(o1);
        list.add(o2);
        list.add(o3);

        System.out.println(Arrays.toString(batchDemo.batchDelete(list)));
    }
}