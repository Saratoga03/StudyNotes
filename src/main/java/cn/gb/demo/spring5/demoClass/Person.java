package cn.gb.demo.spring5.demoClass;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.PrivateKey;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

//set方式注入
/*public class Person implements FactoryBean<Skill> {

    @Override
    public Skill getObject() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("beanDemo.xml");
        return context.getBean("skill1", Skill.class);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }*/

public class Person {

    // 变量
    private String name;
    // 数组
    private String[] arrayDemo;
    // List
    private List<String> listDemo;
    // Map
    private Map<Integer,String> mapDemo;
    // Set
    private Set<String> setDemo;
    // 对象
    private Skill skill;

    public void setName(String name) {
        this.name = name;
        System.out.println("生命周期演示：第二步：执行Set方法注入属性值。");
    }

    public void setArrayDemo(String[] arrayDemo) {
        this.arrayDemo = arrayDemo;
    }

    public void setListDemo(List<String> listDemo) {
        this.listDemo = listDemo;
    }

    public void setMapDemo(Map<Integer, String> mapDemo) {
        this.mapDemo = mapDemo;
    }

    public void setSetDemo(Set<String> setDemo) {
        this.setDemo = setDemo;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", arrayDemo=" + Arrays.toString(arrayDemo) +
                ", listDemo=" + listDemo +
                ", mapDemo=" + mapDemo +
                ", setDemo=" + setDemo +
                ", skill=" + skill +
                '}';
    }

    public Person() {
        System.out.println("生命周期演示：第一步：执行无参构造创建bean实例。");
    }

    // bean初始化方法
    public void initMethod() {
        System.out.println("生命周期演示：第四步：执行bean初始化方法。");
    }

    // bean销毁方法
    public void destroyMethod() {
        System.out.println("生命周期演示：第七步：执行bean销毁方法。");
    }

}
