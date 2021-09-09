# java web 环境设定

- 语言：java (jdk) 8u301  
- IDE：IntelliJ IDEA 2018.3.6  
- 项目管理：Maven 3.5.4  
- 服务器：Tomcat 9.0.45  
***
# Spring5-复习
## 【网友笔记】：
1、https://blog.csdn.net/weixin_45496190/article/details/107059038  
2、https://blog.csdn.net/weixin_45496190/article/details/107067200  
3、https://blog.csdn.net/weixin_45496190/article/details/107071204  
4、https://blog.csdn.net/weixin_45496190/article/details/107082732  
5、https://blog.csdn.net/weixin_45496190/article/details/107092107  
***
## IOC AOP JdbcTemplate 【事务】
1. **IOC 控制反转** *【依赖jar包：Spring4个基础jar包：Beans Core Context Expression，logging包】*  
    - 底层原理  
        - XML解析，工厂模式，反射
#### · 【练习1】工厂模式
  
  1-1 基于XML
    1-1-1 创建对象 <bean id="" class="类全路径" /> ApplicationContext context = new ClassPathXmlApplicationContext（“XML文件名”）；
	1-1-2 属性注入
	　　A set方法注入 <property name="变量名" value="值" />
	  B 有参构造注入 <constructor-arg name="变量名" value="值" />
	  C p名称空间注入 <bean id="" class="" p:bname="" bauthor="" /> (需要追加命名空间p)
    1-1-3 注入对象
	  0 空值和符号 <null> <![CDATA[内容]]>
	  A 变量 <property name="" value="" />
	  B 数组 <array><value></value></array>
	  C List <list><value></value></list>
	  D Map <map><entry key="" value=""></entry></map>
	  E Set <set><value></value></set>
	  F 对象 <property name="" ref="" />
	  G 引入外部properties文件 （需要追加命名空间context）
	    <context:property-placeholder location="classpath:properties文件路径">
		<property name="" value="${key}">
	1-1-4
	  设置对象类型值(提取公共对象值) (需要追加命名空间util)
	  <util:list id="utilList"><value>...</value><util:list> (util:array/map/set...)
	  <property name="" ref="utilList">
	1-1-4 FactoryBean
	  返回类型可以与Bean类型不一致 （定义返回Bean类继承FactoryBean<T>接口）
	1-1-5 作用域
	  A 单实例 <bean id="" class="" scope="singleton">（默认）
	  B 多实例 <bean id="" class="" scope="prototype">
· 【练习2】基于XML创建对象并注入属性
	1-1-6 生命周期
	  七步生命周期
	  1 通过构造器创建bean实例（无参构造）
	  2 通过set方法注入属性值
	  3 把bean实例传递给后置处理器方法postProcessBeforeInitialization
	  4 调用bean初始化方法（需要配置：init-method=""）
	  5 把bean实例传递给后置处理器方法postProcessAfterInitialization
      6 bean可以正常使用了（对象能获取到了）
      7	调用bean销毁方法（需要配置：destroy-method=""）
· 【练习3】生命周期演示

  
  1-2 基于注解（依赖jar包：基于XML的5个包+spring-aop包）
    1-2-1 创建对象（四者作用完全一致）
      A SpringMVC模式
	    View       → @Component
	    Controller → @Controller
	    Service    → @Service
	    Dao        → @Repository
	  B 开启组件扫描（xml方式）
	    <context:component-scan base-package="" />
	    B-1 扫描指定包
	      <context:component-scan base-package="" use-defaultfilters="false">
		    <contex:include-filter type="annotation" expression="" />
		  </contex:component-scan>
	    B-2 不扫描指定包
	      <context:component-scan base-package="">
		    <contex:include-filter type="annotation" expression="" />
		  </contex:component-scan>   
    1-2-2 属性注入
      @AutoWire（根据类型自动注入）
	  @Qualifier（根据名称注入）（需要与@AutoWire一起使用，区别同一接口下的不同实现方法）
	  @Resource（根据类型or名称注入）（javax包下的注解，并非spring专属，不推荐使用）
	  @Value（普通变量注入）
  
  1-3 完全注解开发
    上记基于注解方式实现IOC的方式中，需要用到配置文件的只有开启组件扫描这一部分。
	开启组件扫描（创建配置类）
	  @Configuration
	  @ComponentSacn(base-package="")
	  public class ***() {***}
	  
	  ApplicationContext contex = new AnnotationConfigApplicationContext(***.class);
· 【练习4】基于注解创建对象并注入属性（基于XML开启组件扫描&完全注解开发）
  
2 AOP 面向切面编程
  2-0 底层原理
    A 动态代理
	  A-1 JDK动态代理   （有接口，创建接口实现类代理对象）
	  A-2 CGLIB动态代理 （无接口，创建子类代理对象）
	B 实现
      B-1 JDK动态代理：调用newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
	    参数1：ClassLoader loader 类加载器
		参数2：Class<?>[] interfaces 增强方法所在类实现的接口（支持复数接口）
		参数3：InvocationHandler h 实现接口InvocationHandler编写增强方法
		
  2-1 名词解释
     a）连接点：类里面哪些方法可以被增强，这些方法称为连接点
​     b）切入点：实际被真正增强的方法称为切入点
​     c）通知（增强）：实际增强的逻辑部分称为通知，且分为以下五种类型：
​       1）前置通知 
       2）后置通知 
	   3）环绕通知  
	   4）异常通知 
	   5）最终通知
​     d）切面：把通知应用到切入点过程
  
  2-2 AOP操作（Spring + AspectJ框架）
    2-2-1 基于XML
      A 配置AOP增强
	  B 配置切点
	  C 配置切面
	  D 配置需要增强的方法
	  <aop:config>
	    <aop:pointcut id="cut" expression="execution(...)"/>
		<aop:aspect ref="proxyA">
		  <aop:before method="before" pointcut-ref="cut"/>
		</aop:aspect>
	  </aop:config>
	  
	2-2-2 基于注解
	  A 切点表达式：execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表]))
	    例：execution(* com.atguigu.dao.BookDao.add(..))
	  B 相关注释
	    @Aspect 生成代理对象
		@PointCut 抽取共通切点
		
		@Before 前置通知
		@AfterReturning 后置通知
		@After 最终通知
		@AfterThrowing 异常通知
		@Around 环绕通知 带参数(ProceedingJoinPoint proceedingJoinPoint)
		
		@Order(1) 多个增强类增强同一个方法时候，可以设置优先级（数字越小越优先）
	  C 开启AspectJ生成代理对象 （需要追加命名空间aop）
	    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
· 【练习5】基于注解实现AOP操作

3 JdbcTemplate（依赖Jar包：数据库连接池+数据库连接驱动）
  3-1 在Spring配置文件中配置数据库连接池和jdbcTemplate对象
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
  3-2 增加
    jdbcTemplate.update(sql,args);
  3-3 修改
    jdbcTemplate.update(sql,args);
  3-4 删除
    jdbcTemplate.update(sql,args);
  3-5 查询
    queryForObject(sql, class, args) 返回某个值
	queryForObject(sql, BeanPropertyRowMapper<>(class), args) 返回某个对象
	 -- 第一个值：sql语句（需要填入的值用？代替）
	 -- 第二个值：返回类型
	 -- 第三个值：sql语句中需要填入的值
	query 返回某个集合
  3-6 批量操作
    batchUpdate(sql, args)

4 事务管理
  4-1 开启事务（需要追加命名空间tx）
    4-1-1 配置文件
	  <!--开启事务注解-->
        <tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
    4-1-2 注解（可配置在类上或方法上）
	  @@Transactional

  4-2 事务参数
    4-2-1 propagation：事务传播行为（常用：REQUIRED(默认) REQUIRED_NEW）
	4-2-2 ioslation：事务隔离级别（READ UNCOMMITTED / READ COMMITTED / PEPEATE READ / SERIALIZABLE）
	4-2-3 timeout：超时时间（默认-1：无超时时间）
	4-2-4 readOnly：是否只读（默认false）
	4-2-5 rollbackFor：回滚（设置哪些异常回滚）
	4-2-6 noRollbackFor：不回滚（设置哪些异常不回滚）
