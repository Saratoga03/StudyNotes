# Spring5-复习
## 【java web 环境设定】
- 语言：java (jdk) 8u301
- IDE：IntelliJ IDEA 2018.3.6
- 项目管理：Maven 3.5.4
- 服务器：Tomcat 9.0.45
## 【网友笔记】
1. https://blog.csdn.net/weixin_45496190/article/details/107059038
2. https://blog.csdn.net/weixin_45496190/article/details/107067200
3. https://blog.csdn.net/weixin_45496190/article/details/107071204
4. https://blog.csdn.net/weixin_45496190/article/details/107082732
5. https://blog.csdn.net/weixin_45496190/article/details/107092107
## IOC AOP JdbcTemplate 【事务】
### IOC 控制反转 ***【依赖jar包：Spring4个基础jar包：Beans Core Context Expression + logging包】***
#### 底层原理
- XML解析，工厂模式，反射
#### 基于XML
- 创建对象
    - 配置文件：`<bean id="" class="类全路径" />`
    - 后台调用：`ApplicationContext context = new ClassPathXmlApplicationContext("XML文件名");`
- 属性注入
    - set方法注入：`<property name="变量名" value="值" />`
    - 有参构造注入：`<constructor-arg name="变量名" value="值" />`
    - p名称空间注入：`<bean id="" class="" p:变量名="值" />` (需要追加命名空间p)
- 注入对象
    - 空值：`<null>`
    - 符号：`<![CDATA[...]]>`
    - 变量：`<property name="" value="" />`
    - 数组：`<array><value>...</value></array>`
    - List：`<list><value>...</value></list>`
    - Map：`<map><entry key="" value=""></entry></map>`
    - Set：`<set><value>...</value></set>`
    - 对象：`<property name="" ref="" />`
    - 引入外部文件
        - 引入外部文件：`<context:property-placeholder location="文件路径">`
        - 引用文件内容：`<property name="" value="${key}">`
    - 设置公共对象
        - 设置对象：`<util:list id="utilList"><value>...</value><util:list> (util:array/map/set...)`
        - 引用对象：`<property name="" ref="utilList">`
    - FactoryBean：返回类型可以与Bean类型不一致 （定义返回Bean类继承FactoryBean<T>接口）
- 作用域
    - 单实例：`<bean id="" class="" scope="singleton">` （默认）
    - 多实例：`<bean id="" class="" scope="prototype">`
- 生命周期
    - 七步生命周期
        - 1. 通过构造器创建bean实例（无参构造）
        - 2. 通过set方法注入属性值
        - 3. 把bean实例传递给后置处理器方法postProcessBeforeInitialization
        - 4. 调用bean初始化方法（需要配置：init-method=""）
        - 5. 把bean实例传递给后置处理器方法postProcessAfterInitialization
        - 6. bean可以正常使用了（对象能获取到了）
        - 7. 调用bean销毁方法（需要配置：destroy-method=""）
#### 基于注解
- 创建对象 - 类/方法注释（四者作用完全一致）
    - View → @Component
    - Controller → @Controller
    - Service → @Service
    - Dao → @Repository
- 创建对象 - XML配置
    - 开启组件扫描：`<context:component-scan base-package="" />`
        - 扫描指定包：
            ```
            <context:component-scan base-package="" use-defaultfilters="false">
                <contex:include-filter type="annotation" expression="" />
            </contex:component-scan>
            ```
        - 不扫描指定包：
            ```
            <context:component-scan base-package="">
                <contex:include-filter type="annotation" expression="" />
            </contex:component-scan>
            ```
- 属性注入
    - @AutoWire（根据类型自动注入）
    - @Qualifier（根据名称注入）（需要与@AutoWire一起使用，区别同一接口下的不同实现方法）
    - @Resource（根据类型or名称注入）（javax包下的注解，并非Spring专属，不推荐使用）
    - @Value（普通变量注入）
#### 完全注解开发
★上记基于注解方式实现IOC的方式中，需要用到配置文件的只有开启组件扫描这一部分。
- 开启组件扫描（创建配置类）
    ```
    @Configuration
    @ComponentSacn(base-package="")
    public class confClass() {...}
    ```
***
### AOP 面向切面编程
#### 底层原理
- 动态代理
    - JDK动态代理 （有接口，创建接口实现类代理对象）
    - CGLIB动态代理 （无接口，创建子类代理对象）
- 实现
    - JDK动态代理：调用newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
        - 1. 参数1：ClassLoader loader 类加载器
        - 2. 参数2：Class<?>[] interfaces 增强方法所在类实现的接口（支持复数接口）
        - 3. 参数3：InvocationHandler h 实现接口InvocationHandler编写增强方法
#### 名词解释
- 连接点：类里面哪些方法可以被增强，这些方法称为连接点
- 切入点：实际被真正增强的方法称为切入点
- 通知（增强）：实际增强的逻辑部分称为通知，且分为以下五种类型
    - 前置通知
    - 后置通知
    - 环绕通知
    - 异常通知
    - 最终通知
- 切面：把通知应用到切入点过程
#### AOP操作（Spring + AspectJ框架）
- 基于XML
    - 1. 配置AOP增强
    - 2. 配置切点
    - 3. 配置切面
    - 4. 配置需要增强的方法
        ```
        <aop:config>
            <aop:pointcut id="cut" expression="execution(...)"/>
            <aop:aspect ref="proxyA">
                <aop:before method="before" pointcut-ref="cut"/>
            </aop:aspect>
        </aop:config>
        ```
- 基于注解
    - 切点表达式：execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表]))
        - 例：execution(* com.atguigu.dao.BookDao.add(..))
    - 相关注释
        - @Aspect 生成代理对象
        - @PointCut 抽取共通切点
        - @Before 前置通知
        - @AfterReturning 后置通知
        - @After 最终通知
        - @AfterThrowing 异常通知
        - @Around 环绕通知 带参数(ProceedingJoinPoint proceedingJoinPoint)
        - @Order(1) 多个增强类增强同一个方法时候，可以设置优先级（数字越小越优先）
    - 开启AspectJ生成代理对象：`<aop:aspectj-autoproxy></aop:aspectj-autoproxy>` （需要追加命名空间aop）
***
### JdbcTemplate
#### 在Spring配置文件中配置数据库连接池和jdbcTemplate对象
```
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
      <property name="url" value="jdbc:mysql:///test" />
      <property name="username" value="root" />
      <property name="password" value="root" />
      <property name="driverClassName" value="com.mysql.jdbc.Driver" />
    </bean>
    <!-- JdbcTemplate 对象 -->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
      <!--注入 dataSource-->
      <property name="dataSource" ref="dataSource"></property><!--set方式注入-->
    </bean>
```
#### 增加
- `jdbcTemplate.update(sql,args);`
#### 修改
- `jdbcTemplate.update(sql,args);`
#### 删除
- `jdbcTemplate.update(sql,args);`
##### 查询
- `queryForObject(sql, class, args)` 返回某个值
- `queryForObject(sql, BeanPropertyRowMapper<>(class), args)` 返回某个对象
    - 第一个参数：sql语句（需要填入的值用?代替）
    - 第二个参数：返回类型
    - 第三个参数：sql语句中需要填入的值
#### 批量操作
- `batchUpdate(sql, args);`
***
### 事务管理
#### 开启事务（需要追加命名空间tx）
- 配置文件
    - `<tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>`
- 注解（可配置在类或方法上）
    - @Transactional
#### 事务参数
- propagation：事务传播行为（常用：REQUIRED(默认) REQUIRED_NEW）
- ioslation：事务隔离级别（READ UNCOMMITTED / READ COMMITTED / PEPEATE READ / SERIALIZABLE）
- timeout：超时时间（默认-1：无超时时间）
- readOnly：是否只读（默认false）
- rollbackFor：回滚（设置哪些异常回滚）
- noRollbackFor：不回滚（设置哪些异常不回滚）
