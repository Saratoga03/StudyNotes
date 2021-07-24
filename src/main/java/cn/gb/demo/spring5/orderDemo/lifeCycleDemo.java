package cn.gb.demo.spring5.orderDemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class lifeCycleDemo implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("person".equals(beanName))
        System.out.println("生命周期演示：第三步：把bean实例传递给后置处理器方法postProcessBeforeInitialization：" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("person".equals(beanName))
        System.out.println("生命周期演示：第五步：把bean实例传递给后置处理器方法postProcessAfterInitialization：" + beanName);
        return bean;
    }
}
