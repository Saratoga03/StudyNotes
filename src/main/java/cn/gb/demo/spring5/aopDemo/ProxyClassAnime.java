package cn.gb.demo.spring5.aopDemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProxyClassAnime {

    // 提取共通切点
    @Pointcut(value = "execution(* cn.gb.demo.spring5.aopDemo.MainClassAnime.testFunc(..))")
    private void pointCutFunc() {}

    // 前置通知
    @Before(value = "pointCutFunc()")
    public void testProxyBefore() {
        System.out.println("前置通知");
    }

    // 后置通知
    @AfterReturning(value = "pointCutFunc()")
    public void testProxyAfterReturning() {
        System.out.println("后置通知");
    }

    // 最终通知
    @After(value = "pointCutFunc()")
    public void testProxyAfter() {
        System.out.println("最终通知");
    }

    // 异常通知
    @AfterThrowing(value = "pointCutFunc()")
    public void testProxyAfterThrowing() {
        System.out.println("异常通知");
    }

    // 环绕通知
    @Around(value = "pointCutFunc()")
    public void testProxyAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知：前");

        proceedingJoinPoint.proceed();

        System.out.println("环绕通知：后");
    }
}
