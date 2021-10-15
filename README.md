# SpringMVC-复习
## 【java web 环境设定】
- 语言：java (jdk) 8u301
- IDE：IntelliJ IDEA 2018.3.6
- 项目管理：Maven 3.5.4
- 服务器：Tomcat 9.0.45
## 【网友笔记】
- https://blog.csdn.net/weixin_44751434/article/details/119358203?spm=1001.2014.3001.5501
## http请求捕获 前后台数据交互 画面渲染 文件上传/下载 拦截器 异常处理
### 依赖包
- Spring Web MVC
***
### 配置文件
#### Web.xml
  - filter（过滤器）
    - 设置编码格式
    ```
    <!--filter过滤器1：设置编码格式-->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceResponseEncoding</param-name>
            <param-value>true</param-value>
        </init-param>        
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    ```
    - 设置PUT/DELETE请求
    ```
    <!--filter过滤器2：发送PUT和DELETE请求-->
    <filter>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    ```
  - DispatcherServlet
    ```
    <!--配置DispatcherServlet-->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:SpringMVCDemo.xml</param-value>（Spring配置文件位置）
        </init-param>
        <load-on-startup>1</load-on-startup>（启动加载）
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <!--
        /：匹配jsp以外所有请求
        /*：匹配所有请求
        -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    ```
#### Spring配置文件
  - 视图解析器
    - Thymeleaf
      ```
      <!--配置视图解析器-->
      <bean id="viewResolver" class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
          <property name="order" value="1" />
          <property name="characterEncoding" value="UTF-8" />
          <property name="templateEngine">
              <bean id="templateEngine" class="org.thymeleaf.spring5.SpringTemplateEngine">
                  <property name="templateResolver">
                      <bean id="templateResolver" class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                          <property name="prefix" value="/WEB-INF/demo/" />
                          <property name="suffix" value=".html" />
                          <property name="templateMode" value="HTML5" />
                          <property name="characterEncoding" value="UTF-8" />
                      </bean>
                  </property>
              </bean>
          </property>
      </bean>
      ```
    - Jsp
      ```
      <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <property name="prefix" value="/WEB-INF/demo/" />
          <property name="suffix" value=".jsp" />
      </bean>
      ```
  - 拦截器
    ```
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <mvc:exclude-mapping path="/" />
            <ref bean="interceptorDemo" />
        </mvc:interceptor>
    </mvc:interceptors>
    ```    
  - 开启Spring注解驱动
    ```
    <context:component-scan base-package="cn.gb.springmvc.demo.restful" />
    ```
  - 配置首页访问（访问不需要任何操作的情况下可以直接配置，等效于后台捕获http请求后直接返回view-name）
    ```
    <mvc:view-controller path="/" view-name="index" />
    ```
  - 开启mvc注解驱动
    ```
    <mvc:annotation-driven />
    ```
  - 开启默认Servlet访问静态资源（需要配置mvc注解驱动，防止js/css等静态资源被DispatcherServlet拦截）
    ```
    <mvc:default-servlet-handler />
    ```
  - 文件解析器（用于文件上传）
    ```
    <bean class="org.springframework.web.multipart.commons.CommonsMultipartResolver" id="multipartResolver" />
    ```
#### 映射相关
1. 捕获http请求触发后台事件（@RequestMapping）
  - 可以修饰类或方法，用于捕获http请求。
  - 属性
    - value  
      http请求地址，用于匹配请求映射。
    - method  
      http请求方法，主要有POST, GET, PUT, DELETE四种。  
      POST, GET请求浏览器可以直接发送，但PUT和DELETE请求则需要通过过滤器实现。  
      派生注解：@GetMapping等效于@RequestMapping(method=get)，其余同理。
2. 后台接收前台参数（获取请求参数）
  - ServletAPI（原生）  
    HttpServletRequest
  - 控制器形参  
    ★变量名必须与前台元素名一致
  - @RequestParam
    ★修饰变量，变量名任意  
    ★属性  
    - value：前台元素名
    - required：是否必须
    - defaultValue：默认值
  - @PathVariable
    ★接收占位符中的参数（Rest风格url）
  - @RequestHeader  
    ★映射请求头信息
  - @CookieValue  
    ★映射Cookie信息
  - POJO  
    ★用简单类型封装一组变量
3. 前台接收后台数据（域对象共享数据）



4. 渲染绘图（视图）



#### 文件上传/下载

#### 拦截器

#### 异常处理
