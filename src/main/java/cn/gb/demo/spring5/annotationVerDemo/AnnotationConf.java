package cn.gb.demo.spring5.annotationVerDemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(value = "cn.gb.demo.spring5.annotationVerDemo")
public class AnnotationConf {

}
