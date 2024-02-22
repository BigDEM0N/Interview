## Taco Cloud Plus开发日志

### 2.22

#### 使用`mybatis-plus`和`MySql`建立存储库

##### 自动化配置数据库

由于在docker中使用MySql，使用docker-compose自动开启容器，项目根路径新建文件夹`/dev-ops/tc-environment`，新建文件`docker-compose.yml`，`.env`

```yaml
version: '3.9'

services:
  mysql:
  	#配置镜像
    image: mysql:8.0.32
    #配置容器名称
    container_name: tc-mysql
    #配置是否重启
    restart: always
    hostname: mysql
    
    #配置密码加密的插件
    command: --default-authentication-plugin=mysql_native_password
    
    #配置容器共享内存？？
    shm_size: 256m
    
    # 配置语言和用户密码
    environment:
      TZ: Asia/Shanghai
      MYSQL_ROOT_PASSWORD: 123456
      MYSQL_USER: tacocloud
      MYSQL_PASSWORD: 123456
      LANG=: en_US.UTF-8
    networks:
      - tc-network
    #映射端口
    ports:
      - "23306:3306"
    volumes:
      - ./sql:/docker-entrypoint-initdb.d
      - ${MYSQL_DATA}:/var/lib/mysql
    #健康检测
    healthcheck:
      test: ["CMD","mysqladmin","ping","-h","localhost"]
      interval: 5s
      timeout: 5s
      retries: 10
      start_period: 15s

#配置容器使用网络的名称和驱动
networks:
  tc-network:
    name: tc-network
    driver: bridge
```

配置宿主机挂载路径

```
MYSQL_DATA=~/tacocloud/data/mysql
```

新建`sql`脚本：`00_Database.sql`,`01_Schemas.sql`,`02_instances.sql`

配置`application.yaml`

```yaml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:23306/tacocloud
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
```

**遇见的问题：**

通过`MYSQL_USER: tacocloud`创建的用户没有建库的权限？？？ 

##### `mybatis-plus配置`：

1. 

##### `druid连接池配置`：

##### 使用`mybatis-plus`：

1. 建立实例：

   ```java
   @Data
   public class User {
       @TableId
       public Long id;
       public String username;
       public String password;
   }
   ```

2. 建立映射Mapper：

   ```java
   @Mapper
   public interface UserMapper extends BaseMapper<User> {
   }
   ```

3. `Service`接口继承`IService`

   ```java
   public interface UserService extends IService<User> {
   }
   ```

4. `ServiceImpl`实现接口并继承`ServiceImpl`

   ```java
   @Service
   public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
   }
   ```

5. 之后便能在`ServiceImpl`中使用单表的CRUD

### 使用`spring-security`进行登录注册