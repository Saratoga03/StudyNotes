package cn.gb.demo.spring5.aopDemo;

import org.springframework.stereotype.Component;

@Component
public class MainClassAnime {

    private String name;

    public void testFunc() {
        System.out.println("I'm watching an anime.");
    }


}
