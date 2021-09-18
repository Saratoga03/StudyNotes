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
    - 



- 属性注入
    - set方法注入
    - 有参构造注入
    - p名称空间注入





- 注入对象
    - 空值
    - 符号
    - 变量
    - 数组
    - List
    - Map
    - Set
    - 对象
    - 引入外部properties文件
    - 设置对象类型值(提取公共对象值)
    - FactoryBean




- 作用域
    - 单实例
    - 多实例


- 生命周期
    - 七步生命周期



#### 基于注解


#### 完全注解开发

***
### AOP 面向切面编程
#### 底层原理



#### 名词解释



#### AOP操作（Spring + AspectJ框架）

***
### JdbcTemplate
#### 在Spring配置文件中配置数据库连接池和jdbcTemplate对象



#### 增加



#### 修改



#### 删除



##### 查询



#### 批量操作


***
### 事务管理
#### 开启事务（需要追加命名空间tx）




#### 事务参数




