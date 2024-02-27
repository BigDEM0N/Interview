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

#### 授权流程

1. 增加Jwt工具类

   ```java
   @Component
   @Slf4j
   public class JwtUtil {
   
       //常量
       public static final long EXPIRE = 1000 * 60 * 60 * 4; //token过期时间,4个小时
       public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO"; //秘钥
   
       //生成token字符串的方法
       public String getToken(String userName) {
           return Jwts.builder()
                   .setHeaderParam("typ", "JWT")
                   .setHeaderParam("alg", "HS256")
                   .setSubject("user")
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                   .claim("userName", userName)//设置token主体部分 ，存储用户信息
                   .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                   .compact();
       }
   
       //验证token字符串是否是有效的  包括验证是否过期
       public boolean checkToken(String jwtToken) {
           if (jwtToken == null || jwtToken.isEmpty()) {
               log.error("Jwt is empty");
               return false;
           }
           try {
               Jws<Claims> claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
               Claims body = claims.getBody();
               if (body.getExpiration().after(new Date(System.currentTimeMillis()))) {
                   return true;
               } else
                   return false;
           } catch (Exception e) {
               log.error(e.getMessage());
               return false;
           }
       }
   
       public Claims getTokenBody(String jwtToken) {
           if (jwtToken == null || jwtToken.isEmpty()) {
               log.error("Jwt is empty");
               return null;
           }
           try {
               return Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken).getBody();
           } catch (Exception e) {
               log.error(e.getMessage());
               return null;
           }
       }
   }
   ```

2. 增加自定义JwtFilter，继承自OncePerRequestFilter，把获取的权限写到SecurityContext中

   ```java
   @Component
   @Slf4j
   public class JwtFilter extends OncePerRequestFilter {
   
       @Autowired
       JwtUtil jwtUtil;
   
       @Autowired
       UserDetailsService userDetailsService;
   
       @Override
       protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
           String jwtToken = request.getHeader("token");//从请求头中获取token
           if (jwtToken != null && !jwtToken.isEmpty() && jwtUtil.checkToken(jwtToken)){
               try {//token可用
                   Claims claims = jwtUtil.getTokenBody(jwtToken);
                   String userName = (String) claims.get("userName");
                   //todo:这里是从数据库取，之后改成从redis
                   UserDetails user = userDetailsService.loadUserByUsername(userName);
                   if (user != null){
                       UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                       SecurityContextHolder.getContext().setAuthentication(auth);
                   }
               } catch (Exception e){
                   log.error(e.getMessage());
               }
           }else {
               log.warn("token is null or empty or out of time, probably user is not log in !");
           }
           filterChain.doFilter(request, response);//继续过滤
       }
   }
   ```

3. 把filter加入到HttpSecurity对象中，让spring把它注册到tomcat的filterchain当中。

#### APIFOX自动设置request header



### 2.27

#### 设置全局异常处理

新增类

```java
@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(Exception.class)
    public String handleCommonException(Exceptioin e){
        return "error";
    }
}
```

这两个注解的组合可以捕获spring应用里的异常，但是经过实测，filter里的异常属于Servlet没法被这个捕获。



#### 设置全局统一返回参数CommonResult

在包result中

#### 设置登出

计划用redis实现



#### 通过SpringAOP进行log记录

```java
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Before("execution(* com.avgkin.tacocloudplusserver.controller..*(..))")
    public void log(JoinPoint joinPoint){
        log.info("请求传入："+ Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(value = "execution(* com.avgkin.tacocloudplusserver.controller..*(..))",returning = "returnValue")
    public Object afterLog(JoinPoint joinPoint,Object returnValue){
        log.info("返回响应:"+ returnValue.toString());
        return returnValue;
    }
}
```



#### 整合redis缓存

1. 配置redis docker启动脚本：

   ```dockerfile
   services:
     redis:
       image: redis:3.0
       container_name: tc-redis
       restart: always
       ports:
         - "26379:6379"
       volumes:
         - ${REDIS_DATA}:/data
         - ./redis/redis.conf:/usr/local/etc/redis/redis.conf
       command: ["redis-server","/usr/local/etc/redis/redis.conf"]
       networks:
         - tc-network
       healthcheck:
         test: [ "CMD", "redis-cli", "ping" ]
         interval: 10s
         timeout: 5s
         retries: 3
   ```

2. 建立属性类

   ```java
   @Data
   @ConfigurationProperties(prefix = "redis.config")
   public class RedisConfigProperties {
       /** host:ip */
       private String host;
       /** 端口 */
       private int port;
       /** 账密 */
       private String password;
       /** 设置连接池的大小，默认为64 */
       private int poolSize = 64;
       /** 设置连接池的最小空闲连接数，默认为10 */
       private int minIdleSize = 10;
       /** 设置连接的最大空闲时间（单位：毫秒），超过该时间的空闲连接将被关闭，默认为10000 */
       private int idleTimeout = 10000;
       /** 设置连接超时时间（单位：毫秒），默认为10000 */
       private int connectTimeout = 10000;
       /** 设置连接重试次数，默认为3 */
       private int retryAttempts = 3;
       /** 设置连接重试的间隔时间（单位：毫秒），默认为1000 */
       private int retryInterval = 1000;
       /** 设置定期检查连接是否可用的时间间隔（单位：毫秒），默认为0，表示不进行定期检查 */
       private int pingInterval = 0;
       /** 设置是否保持长连接，默认为true */
       private boolean keepAlive = true;
   }
   ```

3. 引入依赖redission

   ```xml
   <dependency>
       <groupId>org.redisson</groupId>
       <artifactId>redisson-spring-boot-starter</artifactId>
       <version>3.23.4</version>
   </dependency>
   ```

   

4. 通过Configuration建立redis客户端实例

   ```java
   @Configuration
   @EnableConfigurationProperties(RedisConfigProperties.class)
   public class RedisConfig {
       @Bean
       public RedissonClient redissonClient(ConfigurableApplicationContext applicationContext,RedisConfigProperties properties){
           Config config = new Config();
           config.useSingleServer()
                   .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
                   .setPassword(properties.getPassword())
                   .setConnectionPoolSize(properties.getPoolSize())
                   .setConnectionMinimumIdleSize(properties.getMinIdleSize())
                   .setIdleConnectionTimeout(properties.getIdleTimeout())
                   .setConnectTimeout(properties.getConnectTimeout())
                   .setRetryAttempts(properties.getRetryAttempts())
                   .setRetryInterval(properties.getRetryInterval())
                   .setPingConnectionInterval(properties.getPingInterval())
                   .setKeepAlive(properties.isKeepAlive())
                   .setDatabase(0)
           ;
           return Redisson.create(config);
       }
   }
   ```

5. 定义CacheManager，通过CacheManager启用Caching

   ```java
   @Configuration
   @EnableCaching
   public class CacheConfig {
       @Bean
       public CacheManager cacheManager(RedissonClient redissonClient) {
           return new RedissonSpringCacheManager(redissonClient);
       }
   }
   ```

6. 设置缓存过期时间？？？

#### 引入RedisComander

```java
  redis-commander:
    image: spryker/redis-commander:0.8.0
    container_name: tc-redis-commander
    hostname: redis-commander
    restart: always
    ports:
      - "18081:8081"
    environment:
      - TZ=Asia/Shanghai
      - REDIS_HOST=redis
      - REDIS_PORT=6379
      - REDIS_PASSWORD=123456
    networks:
      - tc-network
    depends_on:
      redis:
        condition: service_healthy
```

##### 构建自定义redis方法类用于放入认证结果

```java
@Component
public class RedisUtil {
    @Autowired
    private RedissonClient redissonClient;
    public <K,V> boolean putKv(String bucketName,K key,V value){
        if(key!=null&&value!=null&&bucketName!=null){
            RMap<K,V> map = redissonClient.getMap(bucketName);
            map.put(key,value);
            return true;
        }else{
            throw new RuntimeException();
        }
    }
}
```

