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
                        <addmavendescriptor>false</addmavendescriptor>
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

    <groupId>com.qpf</groupId>
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

    <groupId>com.qpf</groupId>
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

    <groupId>com.qpf</groupId>
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

    <groupId>com.qpf</groupId>
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
    public String demo(@RequestParam(value = "msg", required = false) String msg);
}
```

##控制器

控制器调用`Feign`接口,获取服务返回结果

```
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
``