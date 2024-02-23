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

### 2.23

#### 使用`spring-security`进行登录注册

#### 自定义前后端认证

[Spring-Security认证流程-ProcessOn](https://www.processon.com/diagraming/65d854889468bb665db86eec)

**必须四件：**

1. PasswordEncoder

   ```java
   @Component
   public class MyPasswordEncoder {
       @Bean
       public PasswordEncoder passwordEncoder(){
           return new BCryptPasswordEncoder();
       }
   }
   ```

2. UserDetailService

   ```java
   @Component
   public class UserDetailServiceImpl implements UserDetailsService {
   
       private UserService userService;
       private PasswordEncoder passwordEncoder;
       @Autowired
       public UserDetailServiceImpl(UserService userService,PasswordEncoder passwordEncoder){
           this.userService = userService;
           this.passwordEncoder = passwordEncoder;
       }
       @Override
       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           User user = userService.getByUsername(username);
           if(user == null){
               throw new UsernameNotFoundException(username);
           }
           List<GrantedAuthority> authorities = new ArrayList<>();
           authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
           UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
           return userDetails;
       }
   }
   ```

   需要实现`loadUserByUsername(String username)`方法提供给内部使用，**并在此处处理用户角色**

3. authenticationProvider

   ```java
   @Bean
   public AuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
       provider.setPasswordEncoder(passwordEncoder);
       provider.setUserDetailsService(userDetailsService);
       return provider;
   }
   ```

4. authenticationManager

   ```java
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
       return configuration.getAuthenticationManager();
   }
   ```

   

**再通过SecurityFilterChain进行登录url映射**

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.formLogin(AbstractHttpConfigurer::disable)
        .logout(AbstractHttpConfigurer::disable)
        .authenticationProvider(authenticationProvider())
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
        .httpBasic(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(request->request.requestMatchers(HttpMethod.POST,"/user/login","/user/register").permitAll().anyRequest().authenticated())	//只允许匿名用户使用login和register
        .build();
}
```

**最后在controller层中使用authenticationManager**

```java
UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(requestDTO.getUsername(),requestDTO.getPassword());
Authentication authentication = authenticationManager.authenticate(authToken);
```

#### 注册功能

```java
@Override
public User registerUser(RegistrationRequestDTO requestDTO) {
    String username = requestDTO.getUsername();
    LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(User::getUsername,username);
    User user = new User(IdUtil.getSnowflakeNextId(),requestDTO.getUsername(),passwordEncoder.encode(requestDTO.getPassword()));
    if(exists(wrapper)){
        return null;
    }else{
        save(user);
    }
    return user;
}
```

直接判断是否存在用户名，不存在就注册。

#### APIFOX自动设置request header

#### 记录用户状态session

发现，登录注册用的是同一个session，不正确