# Spring Cloud

[toc]

`Spring Cloud`是提供全套的分布式系统解决方案的微服务框架,为开发者提供了在分布式系统(配置管理,服务发现,熔断,路由,微代理,控制总线,一次性`Token`,全局琐,`Leader`选举,分布式`Session`,集群状态)中快速构建的工具,使用`Spring Cloud`的开发者可以快速的启动服务或构建应用、同时能够快速和云平台资源进行对接.

# 创建统一的依赖管理

`Spring Cloud`项目都是基于`Spring Boot`进行开发,并且大都使用`Maven`做项目管理工具.在实际开发中,一般都会创建一个依赖管理项目作为`Maven`的`Parent`项目使用,这样做可以极方便地对`Jar`包版本的统一管理.

## Pom

创建名为`spring-cloud-demo-dependencies`的项目,`pom.xml`文件如下:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.2.RELEASE</version>
    </parent>

    <groupId>com.qpf</groupId>
    <artifactId>spring-cloud-demo-dependencies</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>pom</packaging>

    <name>spring-cloud-demo-dependencies</name>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceencoding>UTF-8</project.build.sourceencoding>
        <project.reporting.outputencoding>UTF-8</project.reporting.outputencoding>
        <spring.cloud.version>Finchley.RELEASE</spring.cloud.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring.cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>


    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <showWarnings>true</showWarnings>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <addMavenDescriptor>false</addMavenDescriptor>
                    </archive>
                </configuration>
                <executions>
                    <execution>
                        <configuration>
                            <archive>
                                <manifest>
                                    <adddefaultimplementationentries>true</adddefaultimplementationentries>
                                    <adddefaultspecificationentries>true</adddefaultspecificationentries>
                                    <addclasspath>true</addclasspath>
                                </manifest>
                            </archive>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-install-plugin</artifactId>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-clean-plugin</artifactId>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-antrun-plugin</artifactId>
            </plugin>


            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
            </plugin>
        </plugins>

        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-javadoc-plugin</artifactId>
                    <executions>
                        <execution>
                            <phase>prepare-package</phase>
                            <goals>
                                <goal>jar</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>

                <plugin>
                    <groupId>net.alchim31.maven</groupId>
                    <artifactId>yuicompressor-maven-plugin</artifactId>
                    <version>1.5.1</version>
                    <executions>
                        <execution>
                            <phase>prepare-package</phase>
                            <goals>
                                <goal>compress</goal>
                            </goals>
                        </execution>
                    </executions>
                    <configuration>
                        <encoding>UTF-8</encoding>
                        <jswarn>false</jswarn>
                        <nosuffix>true</nosuffix>
                        <linebreakpos>30000</linebreakpos>
                        <force>true</force>
                        <includes>
                            <include>**/*.js</include>
                            <include>**/*.css</include>
                        </includes>
                        <excludes>
                            <exclude>**/*.min.js</exclude>
                            <exclude>**/*.min.css</exclude>
                        </excludes>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>

        <resources>
            <resource>
                <directory>src/main/java</directory>
                <excludes>
                    <exclude>**/*.java</exclude>
                </excludes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
            </resource>
        </resources>
    </build>

    <repositories>
        <repository>
            <id>aliyun-repos</id>
            <name>Aliyun Repository</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>

        <repository>
            <id>sonatype-repos</id>
            <name>Sonatype Repository</name>
            <url>https://oss.sonatype.org/content/groups/public</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
        <repository>
            <id>sonatype-repos-s</id>
            <name>Sonatype Repository</name>
            <url>https://oss.sonatype.org/content/repositories/snapshots</url>
            <releases>
                <enabled>false</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>

        <repository>
            <id>spring-snapshots</id>
            <name>Spring Snapshots</name>
            <url>https://repo.spring.io/snapshot</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

    <pluginRepositories>
        <pluginRepository>
            <id>aliyun-repos</id>
            <name>Aliyun Repository</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>

</project>
```

- `parent`: 继承了`Spring Boot`的`Parent`,表示我们是一个`Spring Boot`工程
- `package`: `pom`,表示该项目仅当做依赖项目,没有具体的实现代码
- `spring-cloud-dependencies`: 在`properties`配置中预定义了`Spring Cloud`的版本号为`Finchley.RELEASE`
- `build`: 配置了项目所需的各种插件
- `repositories`: 配置项目下载依赖时的第三方库

# 服务注册与发现

`Spring Cloud Netflix`的`Eureka`是一个服务注册和发现模块,可以作为服务注册中心
 
## Pom


创建名为`spring-cloud-demo-eureka`的项目,`pom.xml`文件如下:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.qpf</groupId>
        <artifactId>spring-cloud-demo-dependencies</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>../spring-cloud-demo-dependencies/pom.xml</relativePath>
    </parent>

    <artifactId>spring-cloud-demo-eureka</artifactId>
    <packaging>jar</packaging>

    <name>spring-cloud-demo-eureka</name>

    <dependencies>
        <!-- Spring Boot Begin -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- Spring Boot End -->

        <!-- Spring Cloud Begin -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <!-- Spring Cloud End -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <mainClass>com.qpf.spring.cloud.demo.eureka.EurekaApplication</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

主要是添加依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```
 
## 入口程序

注册中心的入口程序需要标注`@EnableEurekaServer`注解,启动`Eureka`服务端功能

```java
package com.qpf.spring.cloud.demo.eureka;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
```
 
## 配置文件

配置文件`application.yml`配置注册中心的应用名称,端口以及`eureka`相关配置

```yml
spring:
application:
name: spring-cloud-demo-eureka

server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  # 注册中心不需要注册
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```
`eureka.client.registerWithEureka = false`和`eureka.client.fetchRegistry = false`表示服务端不在注册中心注册

浏览器访问`http://localhost:8761`,管理注册中心
 
# 创建服务提供者

`Client`向`Server`注册时,提供主机,端口号等信息,`Server`与`Client`之间维持心跳连接,如果超时,则会从注册中心删除.

##  `pom.xml`

创建名为`spring-cloud-demo-service-admin`的项目,`pom.xml`文件如下

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.qpf</groupId>
        <artifactId>spring-cloud-demo-dependencies</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>../spring-cloud-demo-dependencies/pom.xml</relativePath>
    </parent>

    <artifactId>spring-cloud-demo-service-admin</artifactId>
    <packaging>jar</packaging>

    <name>spring-cloud-demo-service-admin</name>

    <dependencies>
        <!-- Spring Boot Begin -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- Spring Boot End -->

        <!-- Spring Cloud Begin -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <!-- Spring Cloud End -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```
 
## 入口程序

入口程序标注`@EnableEurekaClient`注解,启用`Eureka`客户端功能

```java
package com.qpf.spring.cloud.service.admin;

@SpringBootApplication
@EnableEurekaClient
public class ServiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class, args);
    }
}
 ```
 
## 配置文件

`application.yml`配置应用名称以及端口,注册中心

```yml
spring:
  application:
    name: spring-cloud-demo-service-admin

server:
  port: 8762

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/ 
```
`eureka.client.serviceUrl.defaultZone = http://localhost:8761/eureka/`指向了注册中心

 
## 控制器

对外暴露的`REST API`,供服务消费者调用

```java
package com.qpf.spring.cloud.service.admin.controller;

@RestController
public class DemoController {
    @Value("${server.port}")
    private String port;

    @GetMapping("demo")
    public String demo(@RequestParam(value = "msg", required = false) String msg) {
        return String.format("msg: %s, port: %s", msg, port);
    }
}
```
 
# 创建消费者(Ribbon)

创建基于`ribbon + restTemplate`的消费者
- `Ribbon`是一个负载均衡客户端,可以很好的控制`http`和`tcp`的一些行为
- 业务都会被拆分成一个独立的服务，服务与服务的通讯是基于`http restful`的

## `Pom`

创建名为`spring-cloud-demo-web-admin-ribbon`的服务消费者项目
 
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.qpf</groupId>
        <artifactId>spring-cloud-demo-dependencies</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>../spring-cloud-demo-dependencies/pom.xml</relativePath>
    </parent>

    <artifactId>spring-cloud-demo-web-admin-ribbon</artifactId>
    <packaging>jar</packaging>

    <name>spring-cloud-demo-web-admin-ribbon</name>

    <dependencies>
        <!-- Spring Boot Begin -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <!-- Spring Boot End -->

        <!-- Spring Cloud Begin -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
        <!-- Spring Cloud End -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

主要是添加了`spring-cloud-starter-netflix-ribbon`依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```
 
## 入口程序
 
入口程序标注`@EnableDiscoveryClient`注解,启用付发现功能

> `@EnableEurekaClient`注解貌似也可以
 
```java
package com.qpf.spring.cloud.demo.web.admin.ribbon;

//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class WebAdminRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminRibbonApplication.class, args);
    }
}
```
 
## 配置文件

`application.yml`配置`thymeleaf`使应用提供前端页面访问功能,并指定了注册中心

```yml
spring:
  application:
    name: spring-cloud-demo-web-admin-ribbon
  thymeleaf:
    encoding: UTF-8
    cache: false
    servlet:
      content-type: text/html

server:
  port: 8764

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```
 
## 配置类

配置类中添加`RestTemplate`组件,并标注注解`@LoadBalanced`开启负载均衡

```java
package com.qpf.spring.cloud.demo.web.admin.ribbon.config;

@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```
 
## Service中调用服务

使用服务名称调用服务,`Ribbon`会根据服务名来选择具体的服务实例

```java
package com.qpf.spring.cloud.demo.web.admin.ribbon.service.impl;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private RestTemplate restTemplate;

    public String demo(String msg) {
        return restTemplate.getForObject(String.format("http://SPRING-CLOUD-DEMO-SERVICE-ADMIN/demo?msg=%s", msg), String.class);
    }
}
```
 
## 控制器

控制器调用`service`获取访问结果

```java
package com.qpf.spring.cloud.demo.web.admin.ribbon.controller;

@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;
    @GetMapping("demo")
    public String demo(@RequestParam(value = "msg", required = false) String msg) {
        return demoService.demo(msg);
    }
}
```
 
# 创建基于`Feign`的服务消费者
 
`Feign`是一个声明式的伪`Http`客户端,它使得写`Http`客户端变得更简单,只需要创建一个接口并注解.它具有可插拔的注解特性,可使用`Feign`注解和`JAX-RS`注解.`Feign`支持可插拔的编码器和解码器.`Feign`默认集成了`Ribbon`,并和`Eureka`结合,默认实现了负载均衡的效果

## Pom

创建名为`spring-cloud-demo-web-admin-feign`的服务消费者,`pom.xml`文件如下

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.qpf</groupId>
        <artifactId>spring-cloud-demo-dependencies</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>../spring-cloud-demo-dependencies/pom.xml</relativePath>
    </parent>

    <artifactId>spring-cloud-demo-web-admin-feign</artifactId>
    <packaging>jar</packaging>

    <name>spring-cloud-demo-web-admin-feign</name>

    <dependencies>
        <!-- Spring Boot Begin -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <!-- Spring Boot End -->

        <!-- Spring Cloud Begin -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!-- Spring Cloud End -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

主要添加`spring-cloud-starter-openfeign`的依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

## 入口程序

入口程序标注`@EnableFeignClients`注解和`@EnableDiscoveryClient`注解开启`Feign`客户端功能和服务发现功能

```java
package com.qpf.spring.cloud.demo.web.admin.feign;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class WebAdminFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminFeignApplication.class, args);
    }
}
```

## 配置文件

`application.yml`配置`thymeleaf`使应用提供前端页面访问功能,并指定了注册中心

```yml
spring:
  application:
    name: spring-cloud-demo-web-admin-ribbon
  thymeleaf:
    encoding: UTF-8
    cache: false
    servlet:
      content-type: text/html

server:
  port: 8764

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## 创建 Feign 接口

创建`Feign`接口,标注`@FeignClient`注解,并指定服务名称,以消费指定服务,`@Service`注解是将其放入`Ioc`容器

```java
package com.qpf.spring.cloud.demo.web.admin.feign.service;

@Service
@FeignClient("SPRING-CLOUD-DEMO-SERVICE-ADMIN")
public interface DemoService {
    @GetMapping("demo")
    String demo(@RequestParam(value = "msg", required = false) String msg);
}
```

##控制器

控制器调用`Feign`接口,获取服务返回结果

```java
package com.qpf.spring.cloud.demo.web.admin.feign.controller;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("demo")
    public String demo(@RequestParam(value = "msg", required = false) String msg) {
        return demoService.demo(msg);
    }
}
```

# 启用服务熔断器

微服务架构中,根据业务拆分一个个服务,服务之间通过`RPC`或者`REST API`通讯,为了高可用,通常将单个服务进行集群部署.

但是不能保证每个服务`100%`可用,如果单个服务出现问题,就会导致调用这个服务的线程阻塞.如果此时有大量请求涌入,就会导致`Servlet`容器的线程资源耗尽,导致服务瘫痪.

服务之间相互依赖,故障就会传播,最终可能瘫痪整个微服务系统.服务故障的"雪崩"效应.

使用熔断器就可以避免这样的问题.

`Netflix`开源了`Hystrix`组件,实现了熔断器模式,`Spring Cloud`对这一组件进行了整合.

当对特定的服务的调用的不可用达到一个阀值(`Hystrix`是`5`秒`20`次)熔断器将会被打开.

熔断器打开后,为了避免连锁故障,通过`fallback`方法可以直接返回一个固定值.

## Ribbon中使用熔断器

### Pom

`pom.xml`文件添加`spring-cloud-starter-netflix-hystrix`依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

### 入口程序

入口程序添加`@EnableHystrix`注解,启用`Hystrix`

```java
package com.qpf.spring.cloud.demo.web.admin.ribbon;

@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class WebAdminRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminRibbonApplication.class, args);
    }
}
```

### Service

`Service`中`Ribbon`调用的方法添加`@HystrixCommand`注解,并指定`fallback`方法

```java
package com.qpf.spring.cloud.demo.web.admin.ribbon.service.impl;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hasError")
    public String demo(String msg) {
        return restTemplate.getForObject(String.format("http://SPRING-CLOUD-DEMO-SERVICE-ADMIN/demo?msg=%s", msg), String.class);
    }

    public String hasError(String msg) {
        return String.format("msg: %s, but request bad", msg);
    }
}
```

### 测试服务熔断

关闭服务提供者,浏览器请求`http://localhost:8764/demo?msg=hello`,返回结果
```text
msg: hello, but request bad
```

## Feign中使用熔断器

### 配置文件

`feign`自带熔断器,默认是关闭的,配置文件中启用即可:
```properties
feign.hystrix.enabled = true
```

### Service接口

`Service`接口中的`@FeignClient`注解指定`fallback`类,这个类是该`Service`接口的实现类
```java
@Service
@FeignClient(value = "SPRING-CLOUD-DEMO-SERVICE-ADMIN", fallback = DemoServiceHystrix.class)
public interface DemoService {
    @GetMapping("demo")
    String demo(@RequestParam(value = "msg", required = false) String msg);
}
```

### fallback类

`fallback`类实现对应`Service`接口,并标注`@Component`注解,将其放入`Ioc`容器中.
```java
package com.qpf.spring.cloud.demo.web.admin.feign.service.hystrix;

@Component
public class DemoServiceHystrix implements DemoService {
    @Override
    public String demo(String msg) {
        return String.format("msg: %s, but request bad", msg);
    }
}
```

# 熔断器监视器

在`Ribbon`和`Feign`项目增加`Hystrix`仪表盘功能,两个项目的改造方式相同.

## Pom

`pom.xml`文件中添加`spring-cloud-starter-netflix-hystrix-dashboard`依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
```

## 入口程序

入口程序标注`@EnableHystrixDashboard`注解,启用熔断器监视器
```java
package com.qpf.spring.cloud.demo.web.admin.ribbon;

@EnableHystrixDashboard
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class WebAdminRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminRibbonApplication.class, args);
    }
}
```

## 注册HystrixMetricsStreamServlet

`Spring Boot 1.x`只需要在配置文件中配置`HystrixMetricsStreamServlet`,`Spring Boot 2.x`需要在容器中注册`HystrixMetricsStreamServlet`
```java
package com.qpf.spring.cloud.demo.web.admin.ribbon.config;

@Configuration
public class HystrixDashboardConfig {
    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> histrixStream() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");

        return registrationBean;
    }
}
```

## 测试

启动`Eureka`注册中心以及服务消费者,然后浏览器访问`http://localhost:8764/hystrix`

在页面中输入监听的消费者实例`http://localhost:8764/hystrix.stream`,监听器标题`WebAdminRibbonHystrixDashboard`

点击`Monitor Stream`

访问`http://localhost:8764/demo`触发熔断器,在监视器中显示熔断信息

# API网关

在`Spring Cloud`微服务系统中,一种常见的负载均衡方式是,客户端的请求先经过负载均衡(`Zuul`或`Ngnix`),再到网关服务(`Zuul`集群),再到具体的服务.

服务统一注册到高可用服务注册中心,服务配置由配置服务管理,配置文件放在`Git`仓库中,方便维护人员管理.

`Zuul`的路由转发功能和默认的负载均衡功能很适合作为`API`网关.

## Pom

创建路由网关模块,`pom.xml`文件如下:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.qpf</groupId>
        <artifactId>spring-cloud-demo-dependencies</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>../spring-cloud-demo-dependencies/pom.xml</relativePath>
    </parent>

    <artifactId>spring-cloud-demo-zuul</artifactId>
    <packaging>jar</packaging>

    <name>spring-cloud-demo-zuul</name>

    <dependencies>
        <!-- Spring Boot Begin -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- Spring Boot End -->

        <!-- Spring Cloud Begin -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
        </dependency>
        <!-- Spring Cloud End -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

主要是导入`spring-cloud-starter-netflix-zuul`和`spring-cloud-starter-netflix-eureka-server`的依赖

## 入口程序

入口程序标注`@EnableZuulProxy`注解,开启路由转发功能,`@EnableEurekaClient`注解注册服务到注册中心
```java
package com.qpf.spring.cloud.demo.zuul;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
```

## 配置文件
`application.yml`文件配置路由
```yaml
spring:
  application:
    name: spring-cloud-demo-zuul
server:
  port: 8769
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
zuul:
  routes:
    api-a:
      path: /api/a/**
      serviceId: spring-cloud-demo-web-admin-feign
    api-b:
      path: /api/b/**
      serviceId: spring-cloud-demo-web-admin-ribbon
```

## 测试访问

启动`Eureka`注册中心,服务生产者,服务发现者和`Zuul`网关

浏览器访问`http://localhost:8769/api/a/demo?msg=hello`,调用`spring-cloud-demo-web-admin-feign`服务
浏览器访问`http://localhost:8769/api/b/demo?msg=hello`,调用`spring-cloud-demo-web-admin-ribbon`服务

## 网关高可用

配置网关失败时的回调,可以提高用户体验.

`Ioc`容器添加公共的响应`CommonRespone`

```java
package com.qpf.spring.cloud.demo.zuul.fallback;

@Component
public class CommonResponse implements ClientHttpResponse {
    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    @Override
    public InputStream getBody() throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("status", 200);
        params.put("message", "无法连接,请检查你的网络");
        return new ByteArrayInputStream(new ObjectMapper().writeValueAsString(params).getBytes("UTF-8"));
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.OK;
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return HttpStatus.OK.value();
    }

    @Override
    public String getStatusText() throws IOException {
        return HttpStatus.OK.getReasonPhrase();
    }

    @Override
    public void close() {

    }
}
```

为对应的服务调用配置回调,服务`Id`对应响应

- `spring-cloud-demo-web-admin-feign`
```java
package com.qpf.spring.cloud.demo.zuul.fallback;

@Component
public class WebAdminFeignFallbackProvider implements FallbackProvider {

    @Autowired
    private CommonResponse commonResponse;

    @Override
    public String getRoute() {
        return "spring-cloud-demo-web-admin-feign";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return commonResponse;
    }
}
```
- `spring-cloud-demo-web-admin-ribbon`
```java
package com.qpf.spring.cloud.demo.zuul.fallback;

@Component
public class WebAdminRibbonFallbackProvider implements FallbackProvider {
    @Autowired
    private CommonResponse commonResponse;

    @Override
    public String getRoute() {
        return "spring-cloud-demo-web-admin-ribbon";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return commonResponse;
    }
}
```

关闭服务消费者
- 浏览器访问`http://localhost:8769/api/a/demo?msg=hello`,调用`spring-cloud-demo-web-admin-feign`服务失败,返回`{"message":"无法连接,请检查你的网络","status":200}`
- 浏览器访问`http://localhost:8769/api/b/demo?msg=hello`,调用`spring-cloud-demo-web-admin-ribbon`服务失败,返回`{"message":"无法连接,请检查你的网络","status":200}`

## 服务过滤


`Zuul`不仅有路由转发的功能,还有过滤器的功能.

在`Ioc`容器中添加`ZuulFilter`的子类,即可根据过滤规则过滤请求
```java
package com.qpf.spring.cloud.demo.zuul.filter;

@Component
public class ApiFilter extends ZuulFilter {
    private final static Logger logger = LoggerFactory.getLogger(ApiFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        logger.info("{}>>>{}", request.getMethod(), request.getRequestURI());

        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            logger.warn("Token is Empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            HttpServletResponse response = context.getResponse();
            try {
                response.setHeader("contentType", "text/html;charset=utf-8");
                response.getWriter().println("Token is Empty");
            } catch (IOException e) {
                logger.error("response error: {}", e.getMessage());
            }
        } else {
            logger.info("status: Ok");
        }

        return null;
    }
}
```

- `filterType`: 指定过滤器发生的时间,可以返回的值有:
    - `pre`: 路由之前
    - `routing`: 路由发生之时
    - `post`: 路由之后
    - `error`: 发送错误之后
- `filterOrder`: 过滤器顺序号
- `shouldFilter`: 是否启用过滤器
- `run`: 过滤规则

浏览器访问`http://localhost:8769/api/a/demo?msg=hello`,返回
```text
Token is Empty
```

浏览器访问`http://localhost:8769/api/a/demo?msg=hello&token=1111`,返回
```text
msg: hello, port: 8763
```

# 分布式配置中心

在分布式系统中,由于服务数量巨多,为了方便服务配置文件统一管理,实时更新,所以需要分布式配置中心组件.

在`Spring Cloud`中,有分布式配置中心组件`Spring Cloud Config`,它支持配置服务放在配置服务的内存中(即本地),也支持放在远程`Git`仓库中.

在`Spring Cloud Config`组件中,分两个角色,一是`Config Server`,二是`Config Client`.

## 配置中心服务端

### Pom

创建名为`spring-cloud-config`的项目,`pom.xml`文件如下:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.qpf</groupId>
        <artifactId>spring-cloud-demo-dependencies</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>spring-cloud-demo-config</artifactId>
    <packaging>jar</packaging>

    <name>spring-cloud-demo-config</name>

    <dependencies>
        <!-- Spring Boot Begin -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- Spring Boot End -->

        <!-- Spring Cloud Begin -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <!-- Spring Cloud End -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

主要添加`spring-cloud-config-server`的依赖

### 入口程序

入口程序标注`@EnableConfigServe`注解,启用配置服务,`@EnableEurekaClient`注解将服务注册到注册中心

```java
package com.qpf.spring.cloud.config;

@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
```

### 配置文件

`application.yml`文件中配置配置文件的位置

```yaml
spring:
  application:
    name: spring-cloud-demo-config
  cloud:
    config:
      label: master
      server:
        git:
          uri: https://github.com/shadaileng/spring-cloud-demo
          search-paths: config
server:
  port: 8888
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

配置中心的默认端口是`8888`,如果需要修改,必须在`bootstrap.yml`文件中配置
- `spring.cloud.config.label`: 配置仓库的分支
- `spring.cloud.config.server.git.uri`: 配置`Git`仓库地址(`GitHub`,`GitLab`,码云...)
- `spring.cloud.config.server.git.search-paths`: 配置仓库路径(存放配置文件的目录)
- `spring.cloud.config.server.git.username`: 访问`Git`仓库的账号(选填)
- `spring.cloud.config.server.git.password`: 访问`Git`仓库的密码(选填)

### 测试

`github`仓库中添加配置文件`/config/zuul-dev.yml`

浏览器访问`http://localhost:8888/zuul/dev/master`

> HTTP 请求地址和资源文件映射
  http://ip:port/{application}/{profile}[/{label}]
  http://ip:port/{application}-{profile}.yml
  http://ip:port/{label}/{application}-{profile}.yml
  http://ip:port/{application}-{profile}.properties
  http://ip:port/{label}/{application}-{profile}.properties

## 配置中心客户端

### Pom

其他服务都可以作为客户端,`pom.xml`文件添加`spring-cloud-starter-config`依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

### 配置文件

本地的配置文件指定配置在`Git`仓库中的位置
```yaml
spring:
  cloud:
    config:
      uri: http://localhost:8888
      label: master
      name: zuul
      profile: dev
```

- `spring.cloud.config.uri`: 配置服务中心的网址
- `spring.cloud.config.name`: 配置文件名称的前缀
- `spring.cloud.config.label`: 配置仓库的分支
- `spring.cloud.config.profile`: 配置文件的环境标识,一般的标识有
    - `dev`: 表示开发环境
    - `test`: 表示测试环境
    - `prod`: 表示生产环境

这个配置访问的是`Git`仓库中`master`分支的`/config/zuul-dev.yml`或`/config/zuul-dev.properties`文件

为不同的环境编写专门的配置文件,如: `application-dev.yml`、`application-prod.yml`, 启动项目时只需要增加一个命令参数`--spring.profiles.active=环境配置`即可,启动命令如下：
```shell
java -jar spring-cloud-demo-web-admin-feign-1.0.0-SNAPSHOT.jar --spring.profiles.active=prod
```

# 链路追踪

微服务架构是通过业务来划分服务的,使用`REST`调用.对外暴露的一个接口,可能需要很多个服务协同才能完成这个接口功能,如果链路上任何一个服务出现问题或者网络超时,都会形成导致接口调用失败.随着业务的不断扩张,服务之间互相调用会越来越复杂

## Pom

创建名为`spring-cloud-demo-zipkin`的项目,`spring-cloud-demo-dependencies`项目的`pom.xml`添加以下依赖管理

```xml
<dependencyManagement>
    <!-- zipkin begin -->
    <dependency>
        <groupId>io.zipkin.java</groupId>
        <artifactId>zipkin</artifactId>
        <version>${zipkin.version}</version>
    </dependency>
    <dependency>
        <groupId>io.zipkin.java</groupId>
        <artifactId>zipkin-server</artifactId>
        <version>${zipkin.version}</version>
    </dependency>
    <dependency>
        <groupId>io.zipkin.java</groupId>
        <artifactId>zipkin-autoconfigure-ui</artifactId>
        <version>${zipkin.version}</version>
    </dependency>
    <!-- zipkin end -->
</dependencyManagement>
```

其中`${zipkin.version}`的值为当前`zipkin`的最新版`2.10.1`

`spring-cloud-demo-zipkin`的`pom.xml`如下
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.qpf</groupId>
        <artifactId>spring-cloud-demo-dependencies</artifactId>
        <version>0.0.1-SNAPSHOT</version>
<!--        <relativePath>../spring-cloud-demo-dependencies/pom.xml</relativePath>-->
    </parent>

    <artifactId>spring-cloud-demo-zipkin</artifactId>
    <packaging>jar</packaging>

    <name>spring-cloud-demo-zipkin</name>

    <dependencies>
        <!-- Spring Boot Begin -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- Spring Boot End -->

        <!-- Spring Cloud Begin -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <!-- Spring Cloud End -->
        <!-- zipkin begin -->
        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin</artifactId>
        </dependency>
        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin-server</artifactId>
        </dependency>
        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin-autoconfigure-ui</artifactId>
        </dependency>
        <!-- zipkin end -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```
主要是添加了`zipkin`依赖
```xml
<dependency>
    <!-- zipkin begin -->
    <dependency>
        <groupId>io.zipkin.java</groupId>
        <artifactId>zipkin</artifactId>
    </dependency>
    <dependency>
        <groupId>io.zipkin.java</groupId>
        <artifactId>zipkin-server</artifactId>
    </dependency>
    <dependency>
        <groupId>io.zipkin.java</groupId>
        <artifactId>zipkin-autoconfigure-ui</artifactId>
    </dependency>
    <!-- zipkin end -->
</dependency>
```

## 入口程序

入口程序标注`@EnableZipkinServer`,启用`zipkin`服务,`@EnableEurekaClient`将服务注册到注册中心

```java
@EnableZipkinServer
@EnableEurekaClient
@SpringBootApplication
public class ZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }
}
```

## 配置文件

`application.yml`配置默认端口`9411`

```yaml
spring:
  application:
    name: spring-cloud-demo-zipkin
server:
  port: 9411
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
management:
  metrics:
    web:
      server:
        auto-time-requests: false
```

## 配置客户端

除了`dependencies`的工程,其他服务都可以配置为客户端,`pom.xml`文件中添加`spring-cloud-starter-zipkin`的依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

配置文件配置`zipkin`中心
```yaml
spring:
  zipkin:
    base-url: http://localhost:9411
```

启动全部项目,然后浏览器访问`http://localhost:9411/`