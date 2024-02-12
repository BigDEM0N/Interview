## [面试准备](http://localhost:9000/oauth2/authorize?repose_type=code&client_id=taco-admin-client&redirect_uri=http://localhost:9000/login/oauth2/code/taco-admin-client&scope=writeIngredients+deleteIngredients)

### Java基础（2.15）

[黑马程序员Java零基础视频教程_上部(Java入门，含斯坦福大学练习题+力扣算法题和大厂java面试题）_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV17F411T7Ao/?spm_id_from=333.337.top_right_bar_window_custom_collection.content.click&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

[比啃书效果好多了！腾讯大佬带你一周刷完Java面试八股文，高效掌握！真心别错过！_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV14P411h7Pp/?spm_id_from=333.788.top_right_bar_window_custom_collection.content.click&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

八股题库：[InterviewGuide大厂面试真题](https://top.interviewguide.cn/)

#### 基础

##### Path环境变量

配置环境变量的目的：我们想要在任意的目录下都可以打开指定的软件，就可以把软件的路径配置到环境变量中。

windows：

1. 先配置JAVA_HOME
2. 再配置Path（`%JAVA_HOME%\bin`）

Linux：

1. 用户级配置：编辑`.bashrc`(非登录shell)或者`.bash_profile`(登录shell)，或者`.profile`，在文件末尾添加环境变量

   ```bash
   VARIABLE_NAME="value"
   ```

2. 系统级配置

   1. 使用文本编辑器打开`/etc/environment`，可能需要root权限：

      ```bash
      sudo nano /etc/environment
      ```

      文件中添加环境变量，不需要`export`命令，直接写变量名和值：

      ```
      VARIABLE_NAME="value"
      ```

   2. 在文件夹`/etc/profile.d/`中创建shell脚本

      ```shell
      export VARIABLE_NAME="value"
      ```

##### Java的三个版本（Java SE、Java ME、Java EE）

1. Java SE：Java语言的标准版，用于桌面应用的开发
2. Java ME：Java语言的小型版：用于嵌入式电子设备或者小型移动设备
3. Java EE：Java语言的企业版：用于Web方向的网站开发

##### 高级语言的编译运行方式：

编译型：c，将.c文件编译成.o用不同平台的编译器将程序翻译成字节码

解释型：python：将.py文件逐行就不同平台进行解释

混合型: Java：.java首先生成.class二进制字节码，再虚拟机上逐行进行解释

##### JRE和JDK：

JDK包括：JVM，核心类库，开发工具（javac编译工具，java程序执行工具，jdb调试工具，jhat内存工具）

JRE包括：JVM，核心类库，运行工具

##### 不同进制的代码表现：

```java
int binary = 0b1101;
int decimal = 13;
int Octadecimal = 015;
int hexadecimal = 0xd;
```

##### 键盘录入：

`Scanner`：

```java
public class ScannerD{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println(i);
    }
}
```

##### 运算符详解：

1. 算数运算符：`+ - * / %`

2. 自增自减运算符：

   `a++`：这是后增量（post-increment）操作符。它表示先返回变量`a`的当前值，然后将`a`的值增加1。这意味着在赋值操作中，使用`a++`时，首先会使用`a`的原始值，然后`a`的值才会增加。

   `++a`：这是前增量（pre-increment）操作符。它表示先将变量`a`的值增加1，然后返回新的值。这意味着在赋值操作中，使用`++a`时，`a`的值会先增加，然后使用新的值进行赋值或其他操作。

3. 赋值运算符：`&&`和`||`结果和`& |`相同但是有短路效果；

4. 关系运算符：

5. 逻辑运算符：

6. 三元运算符：

7. 运算符优先级：

##### 隐式转换和强制转换：

隐式转换：自动类型提升，把一个取值范围小的数值，转成取值范围大的数据，其中当`byte,short,char`三种类型进行运算时，都会先提升为int再运算。

##### 原码、反码、补码：

- 反码：是为了解决原码不能计算负数的问题出现的

  正数的反码不变，复数的反码在原码的基础上，符号位不变，数值取反；

- 补码：反码中-0和0表现不同，去除这种不同，负数反码+1；

##### 类中的就近原则和this关键字：

当一个类中，成员变量和局部变量重名时，离谁进就用谁。使用this关键字来区别成员变量和局部变量

**使用JVM内存来解释this关键字：**

##### JavaBean是什么？

摘自ChatGPT：

JavaBean是一种特殊的Java类，遵循特定的编码约定，主要用于封装数据。JavaBean是Java语言中的可重用组件模型，广泛用于Java开发中，以简化应用程序的开发。JavaBean的主要特征和约定包括：

1. **类是公共的（Public）**：JavaBean本身必须被声明为public，这样它才能被外部代码访问和使用。
2. **有一个无参的构造函数**：JavaBean应该提供一个无参的构造函数。这使得JavaBean可以在运行时实例化，而不需要提供任何特定的构造参数。
3. **属性私有化**：JavaBean的属性通常被设置为私有（private），这意味着它们不能直接从类的外部访问。这有助于封装数据。
4. **通过公共方法访问属性**：JavaBean通过公共的getter和setter方法来访问私有属性。对于一个名为`property`的属性，通常提供`getProperty()`和`setProperty()`方法。这些方法分别用于获取和设置属性值。
5. **可序列化**：JavaBean通常实现`java.io.Serializable`接口，这允许它们的实例可以被序列化和反序列化，即对象的状态可以被保存和恢复。这对于将对象的状态持久化存储到文件或数据库，或者在网络上进行对象的传输是很重要的

##### 字符串：

字符串的内容是不会发生改变的，它的对象在创建后不能被更改

###### 创建String对象的两种方式：

1. 直接赋值
2. new

###### String的比较（`equals`）：

`==`比的是什么？

1. 当符号两边都是基本数据类型时，比较的是数据值；
2. 当符号两边是引用数据类型时，比较的时地址值；
3. 当符号两边一边时基本数据类型时，一边时引用数据类型时，会用数据值与地址值比较；

###### StringBuilder:

StringBuilder 可以看成一个容器，创建之后里面的内容是可变的；

StringBuilder常用方法:

| public StringBuilder append()  | 添加数据，并返回对象本身   |
| ------------------------------ | -------------------------- |
| public StringBuilder reverse() | 反转容器中内容             |
| public int length()            | 返回长度（字符出现的个数） |
| public String toString()       | 返回String                 |

StringBuilder源码解析：

###### StringJoiner：

StringJoiner构造方法：

```java
public StringJoiner('间隔符号');	//指定拼接时的间隔符号
public StringJoiner('间隔符号','开始符号','结束符号')
```

StringJoiner成员方法：

| public StringJoiner add() | 添加数据，并返回对象本身 |
| ------------------------- | ------------------------ |
| public int length()       | 返回长度                 |
| public String toString()  | 返回字符串               |

StringJoiner源码解析：

##### Java程序的启动：

所有Java程序都由`public static void main(String[] args)`方法开始执行。 [**那么Spring Boot是如何启动的？**](#springApplicationStart)

##### 类：

- **抽象类**：抽象类不能用来实例化对象
- **final类**：final 类不能被继承，父类中的 final 方法可以被子类继承，但是不能被子类重写
- **成员内部类**：这是最常见的内部类类型，它定义在类的内部，和其他成员（字段、方法）平级。成员内部类可以访问外部类的所有成员（包括私有成员）。
- **局部内部类**：局部内部类是定义在一个方法或者任意作用域内的类。它们只在定义它们的区块中可见和可用。和局部变量类似，局部内部类可以访问其封闭作用域中的局部变量，但是这些局部变量必须是最终（final）或者有效最终（effectively final）的。
- **匿名内部类**：匿名内部类是没有名称的内部类，通常用于实现接口或继承其他类的简短实例。它们在创建时被声明和实例化，通常用于事件处理或回调函数的实现。
- **静态内部类**：静态内部类与成员内部类相似，但是在定义时使用了`static`关键字。静态内部类不能访问外部类的非静态成员，它们的行为更像是外部类的一个静态成员。

##### 基本数据类型：

八种基本类型：

1. `byte`：8位，有符号，以二进制补码表示的整数`-128~127`
2. `short`：16位，`-2^15~2^15-1(-32768~32767)`
3. `int`：32位 21亿
4. `long`：64位 `0L`
5. `float`：32位 IEEE 754，浮点数不能用来表示精确的值，如货币。
6. `double`：64位，浮点数不能用来表示精确的值，如货币。
7. `boolean`
8. `char`：16位Unicode字符，`\u0000~\uffff(0,65535)`

##### 变量类型：

1. 局部变量：局部变量是在方法、构造函数或块内部声明的变量
2. 实例变量：类中声明，每个类的实例都有自己的副本
3. 类变量：`static`修饰的变量，属于类，类被加载时初始化
4. 参数变量

##### 文件操作：

##### 函数式接口和`Lambda`表达式

Java8 新增

- 函数式接口

  函数式接口满足：

  1. 一个函数式接口只有一个抽象方法
  2. Object类中的public abstract method不会被视为单一的抽象方法
  3. 函数式接口中可以右默认方法（`default` 默认访问修饰符）和静态方法
  4. 函数式接口可以用`@FunctionalIngterface`注解进行修饰`@FunctionalIngterface`表示该接口世世代代只能含有一个抽象方法

  例如Java8中的`Comparator`接口：

- `Lambda`表达式语法：

  ```java
  /**
  * 传入抽象方法的参数，返回抽象方法的返回值
  */
  (Type1 param1,Type2 param2,Type3 param3)->{
      statement1;
      statement2;
      return statement3;
  }
  ```

  当`lambda`表达式只包含一条`statement`时，就可以省略大括号，return和语句结尾的分号；例如

  ```java
  Predicate<Person> predicate = person -> {
      Integer age = person.getAge();
      return age>=18;
  };
  //可以变成
  Predicate<Person> predicate = person -> person.getAge()>=18;
  ```

- Java8 预定义函数式接口

  ![image-20240206143854451](notepics/image-20240206143854451.png)

  ```java
  
  ```

  



##### ：：的含义：

`::`是方法引用（Method Reference）的符号，它用于简化Lambda表达式的写法

方法引用有四种主要形式：

1. **静态方法引用** - 类名::静态方法名
   - 用于引用类的静态方法。例如，`Math::max`等价于`(x, y) -> Math.max(x, y)`。
2. **实例方法引用（特定对象的实例方法）** - 实例对象::实例方法名
   - 用于引用特定对象的实例方法。例如，如果你有一个`List<String> strings`，则可以使用`strings::size`来引用`size`方法。
3. **实例方法引用（特定类型的任意对象的实例方法）** - 类名::实例方法名
   - 用于引用指定类型所有对象的实例方法。例如，`String::length`等价于`(s) -> s.length()`，这里不是调用特定对象的`length`方法，而是用任意`String`对象来调用`length`方法。
4. **构造函数引用** - 类名::new
   - 用于引用类的构造函数。例如，`ArrayList::new`等价于`() -> new ArrayList<>()`。



##### 函数的传值方式（值，地址）

在Java中，当一个方法被调用时，它的参数是通过Java虚拟机（JVM）的调用栈（call stack）传递的。每个线程在JVM中都有自己的调用栈，这个栈跟踪着线程的方法调用和返回。每当一个方法被调用时，一个新的栈帧（stack frame）就会被创建并压入这个线程的调用栈中。这个栈帧包含了方法的所有局部变量、参数以及其他一些书keeping信息。

比如说，参数是int型和int[]，

- **`int`（基本类型）**：当一个`int`类型的参数被传递给方法时，它的值直接存储在调用栈的栈帧内的局部变量表中。
- **`int[]`（引用类型）**：对于`int[]`类型，即整数数组，传递的是数组的引用（即内存地址），而不是数组的实际内容。这个引用被存储在栈帧的局部变量表中。数组的实际内容存储在堆（heap）内存中，JVM通过这个引用来访问数组内容。

##### 反射 Reflection in Java

静态语言和动态语言：

1. 动态语言：是一类在运行时可以改变其结构的语言
2. 静态语言：运行时结构不可变的语言

当有些时候需要在运行时动态操作某些成员，例如根据类名或方法名动态的实例化对象或调用方法时，需要反射来动态创建。

使用类对象（`Class Object`）来访问类的结构，对类本身和它的实例进行操作。

**一个加载的类在JVM中只会有一个Class实例**

**获取Class对象的三种方式：**

1. 类字面常量：`类的名称.class`

2. 类对象的`getClass()`方法 **不初始化类**

   ```java
   Class<?> clazz = user.getClass();
   ```

3. `Class.forName("全限定名")`用于类名在编译时不可知的场景 **立刻初始化类**

   ```java
   Class<?> clazz = Class.forName("org.xxx.xxx");
   ```

**操作类**

```java
//获取类的所有字段名(不包括父类)
Field[] fields = clazz.getDeclaredFields();
//获取类的public字段名(包括父类的public字段)
Field[] fields = clazz.getFields();
//获取父类的所有字段
Field[] fields = clazz.getSuperclass().getDeclaredFields();
//根据名称获取字段
Field field = clazz.getDeclaredField("name");


//获取字段上的注解
field.getDeclaredAnnotation(MyAnnotation.class);
//获取字段类型
field.getType();
//当字段类型为泛型时
field.getGenericType();
//获取字段的值
field.get(null);
//访问私有字段时，报错 IllegalAccessException 需要设置访问权限
field.setAccessible(true);
//设置字段的值
field.set(null,100);

//方法的操作
Method[] methods = clazz.getDeclaredMethods();
//获取静态方法
Method method = clazz.getDeclaredMethod("mypublicStaticMethod");
//执行静态方法
method.invoke(null);
//获取带参数的方法
Method method = clazz.getDeclaredMethod("myPrivateStaticMethod",String.class);
```



##### 虚拟机创建Class对象的过程

![image-20240209220900778](notepics/image-20240209220900778.png)

##### 静态初始化块：

静态初始化块是一种特殊的代码块，用于初始化静态变量。静态初始化块在类第一次被加载到JVM时执行，这发生在任何对象创建之前，且静态初始化块只执行一次。

当初始化逻辑无法通过单一的赋值语句来完成，因此静态初始化块非常适合这种用途。

静态初始化块不能访问非静态变量或方法，因为它是在类加载时执行的，这时还没有任何对象实例存在。

##### Stream:

#### 工具类：

##### `Arrays`:

```java
/**
*This class contains various methods for manipulating arrays (such as sorting and searching). This *class also contains a static factory that allows arrays to be viewed as lists.
*/
```

提供方法：

1. `public static void sort(int[] a)`使用排序算法`DualPivotQuicksort.sort(a, 0, 0, a.length);`



#### 容器：

##### Collection:

`Collection`接口继承自`Iterable`

```java
public interface Collection<E> extends Iterable<E>
```

- `List`：

  `List 是interface`继承自`Collection`：

  包括抽象方法：

  ```java
  int size();
  boolean isEmpty();
  ```

  

  1. `Vector`：Vector是一个已经被弃用的类，因为他是**线程同步**的。***原理***

  2. `ArrayList` ：ArrayList是一个可以**动态增长**的数组。***动态增长***

     ```java
     public class ArrayList<E> extends AbstractList<E>
             implements List<E>, RandomAccess, Cloneable, java.io.Serializable
     
     /**
     * 默认大小
     */
     private static final int DEFAULT_CAPACITY = 10;
     ```

     **`Collection<? extends E> c`**：

     `Collection<? extends E>`是Java中泛型通配符的一个使用示例

  3. `LinkedList`：

- `Queue`：

  1. `LinkedList`：
  2. `PriorityQueue`：

- `Set`：

  1. `HashSet`：
  2. `TreeSet`：

##### Map:

- `HashMap`：
- `TreeMap`：

##### 双端队列`Deque`

双端队列实现了两端进出，可替代栈和队列，继承自`Queue`接口，实现类**`LinkedList,ArrayDeque,LinkedBlockingDeque`**

|      |       头        |       头       |       尾       |      尾       |
| :--: | :-------------: | :------------: | :------------: | :-----------: |
| 插入 |  `addFirst()`   | `offerFirst()` |  `addLast()`   | `offerLast()` |
| 删除 | `removeFirst()` | `pollFirst()`  | `removeLast()` | `pollLast()`  |
| 检查 |  `getFirst()`   |  `peekFirst`   |  `getLast()`   | `peekLast()`  |

##### 优先队列：

#### 并发&多线程(todo:2.13)

[（2023最新Java进阶学习路线【涵盖初，中，高级程序员以及架构师所有内容】）_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1QB4y1v7Si?p=2&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

#### JVM(todo:2.14)

[2023版最新JVM调优实战，深入理解JVM虚拟机，马士兵讲解JVM教程！_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1fe411A7JQ/?spm_id_from=333.337.top_right_bar_window_custom_collection.content.click&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

[【狂神说Java】JVM快速入门篇_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1iJ411d7jS/?spm_id_from=333.788.top_right_bar_window_custom_collection.content.click&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

##### JVM内存分配：

1. 栈：方法运行时使用的内存，比如main方法运行，进入方法栈中执行
2. 堆：存储对象或者数组，new来创建的，都存储在堆内存
3. 元空间：存储可以运行的class文件
4. 本地方法栈：JVM在使用系统调用时使用
5. 寄存器：给cpu使用

##### 对象在内存中的情况：

对象在内存中的生命周期：

```java
Student s = new Student();
```

1. 加载class文件，将编译好的.java文件加载到元空间（方法区）

2. 声明局部变量，在栈中声明一个局部变量

3. 在堆内存中开辟一个空间

4. 默认初始化

5. 显示初始化

6. 构造方法初始化

7. 将堆内存中的地址值赋值给左边的局部变量

   ![image-20240211190527632](notepics/image-20240211190527632.png)

##### this的内存原理：

this代表方法调用者的地址值

##### String的内存原理：

1. 直接赋值的String存放在堆内存的StringTable（串池，字符串常量池）中，并把**地址值传给变量**。当使用双引号直接赋值时，系统会检查该字符串在串池中是否存在，如果存在则复用；

2. new方法，会一直创建；

   ![image-20240211192358023](notepics/image-20240211192358023.png)

3. 使用`Scanner`读入键盘时生成的字符串，是new出来的

##### 类的加载过程：

1. 类的加载：将类的class文件读入内存，并为之创建一个java.lang.Class对象，有类加载器完成
2. 类的连接：将类的二进制数据合并到JRE中
   1. 验证：确保加载的类信息符合JVM规范，没有安全方面的问题
   2. 准备：正式为类变量分配内存并设置类变量默认初始值，在方法区进行分配
   3. 解析：虚拟机常量池内的符号引用（常量名）替换为直接引用的过程
3. 类的初始化：JVM负责对类进行初始化

**什么时候会发生类初始化？**

类的主动引用，一定会发生类的初始化：

1. 当虚拟机启动时，先初始化main方法所在的类
2. new一个类的对象
3. 调用类的静态成员
4. 使用java.lang.reflect包的方法对类进行反射调用
5. 当初始化一个类，如果其父类没有被初始化，则先会初始化它的父类

```java
//主动引用
Son son = new Son();
```

类的被动引用，不会发生类的初始化：

1. 当访问一个静态域的时候，只有真正声明这个域的类才会被初始化。例如，当通过子类引用父类的静态变量，不会导致子类初始化。
2. 通过数组定义类引用，不会触发此类的初始化
3. 引用常量不会触发此类的初始化（常量在链接阶段就存入调用类的常量池了）

```java

```

**类加载器**：

类加载的作用：将class文件字节码内容加载到内存中，并将这些静态数据转换成方法区的运行时数据结构，然后在堆中生成一个代表这个类的java.lang.Class对象，作为方法区中类数据的访问入口

类缓存：标准的JavaSE类加载器可以按照要求查找类，一旦某个类被加载到类加载器中，它将维持一段时间。

引导类加载器：JVM自带加载器，用来装载核心类库

扩展类加载器：负责目录jre/lib/ext下的jar包获取-D java.ext.dirs指定目录下的jar包装入工作库

系统类加载器：负责java -classpath或-D java.class.path所指的目录下的类和jar包

**双亲委派机制：**

#### 注解

##### 内置注解：

```java
@Override
@Deprecated //表示废弃
@SupressWarnings
```

##### 元注解：

meta-annotation

元注解的作用就是负责注解其他注解

```java
@Target(value = ElementType.TYPE) //用于描述注解的使用范围
@Retention(RetentionPolicy.RUNTIME) //用于表示需要在什么级别保存该注释信息(SOURCE<CLASS<RUNTIME),用于描述注解声明周期
@Documented //说明该注解将被包含在javadoc中
@Inherited //说明子类可以继承父类中的该注解
```

##### Annotation的格式：

例如Spring框架中的@Repository注解

`String value() default "";`就规定了这个注解的参数

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Repository {
    @AliasFor(
        annotation = Component.class
    )
    String value() default "";
}
```

##### Annotation如何被使用？

### 算法

#### 快排：

#### KMP算法：

#### 贪心：[095764-01.pdf (tsinghua.edu.cn)](http://www.tup.tsinghua.edu.cn/upload/books/yz/095764-01.pdf)

### 计算机基础知识

#### TCP&HTTP&计算机网络

#### 操作系统

#### 数据结构

#### 算法

### 技术栈

#### Spring 基础（2.16）

[27.【IOC】Bean 是单例的，会不会有线程安全问题_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1im411Q7tA?p=32&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

[18、手写源码实现Spring扫描流程_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1z84y197Xu/?p=18&spm_id_from=pageDriver&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

##### <span id = "springApplicationStart">spring应用的启动</span>

`@SpringBootApplication()`

```java
@SpringBootApplication(scanBasePackages = "com.avgkin")
public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class,args);
    }
```



##### `@Bean`注解是如何生成一个bean的？

##### AOP（面向切面编程）：

###### AOP和OOP的对比：

OOP，根据业务创建模型，基于模型展开业务开发。

###### 概念：

1. 连接点：定义在类中，每个具有独立功能的方法，称为连接点；
2. 切入点（pointcut）：某些方法具有一些共同的流程，抽取出来后，原始方法中就缺少了这些代码，被抽取了公共功能的方法称为切入点；
3. 通知（advice）：抽取的具有共性的代码，通知方法；
4. 引入（Introduction）：通知仅代表代码逻辑，如果存在有共性的成员变量或者成员方法，将共性功能的成员加入，引入是在编译期或类加载期完成的;
5. 目标对象（TargetObject）：缺少被抽取代码的对象
6. AOP代理（AOP Proxy）：代理对象把通知植入到目标对象中
7. 切面：
8. 织入:

```java

```

##### Spring事务

##### `@Configuration`注解



#### Spring Security & Oauth2 & Shiro

##### 基本概念：

认证：用户认证就是判断一个用户的身份是否合法的过程，为了保护系统的隐私数据与资源。

***为什么基于session的认证，sessionId必须存在cookie里？***

会话：为了避免用户的每次操作都进行认证，可将用户的信息保存在会话中。会话就是系统为了保持当前用户登录状态的机制。

授权：认证是为了保证用户身份的合法性，授权则是为了更细粒度的对隐私数据进行划分。

RBAC：根据角色或者权限

##### 基于Session的认证方式：

**基于`SpringMVC的工程构建`**：

SpringMVC与SpringBoot不同，SpringBoot提供了启动类`xxxApplication`和注解`@SpringBootApplication`其实现了自动装配Bean的功能，而SpringMVC我估计需要手动装配。

```java
/**
* ApplicationConfig.class
* Spring容器配置
*/
@Configuration
@ComponentScan(basePackages = "xxx.xxx.xxx",excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class ApplicationConfig{
    
}
```

```java
/**
* WebConfig.class
* Servlet配置
*/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "xxx.xxx.xxx",includeFilters={@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class WebConfig implements WebMvcConfigurer{
    //视图解析器
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/resource/view");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
```

```java
/**
* SpringApplicationInitializer.class
*/
public class SpringApplicationInitializer extends AbstractAnnotationConfigDispacherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses(){
        return new Class[]{ApplicationConfig.class};
    }
    @Override
    protected Class<?>[] getServletConfigClasses(){
        return new Class[]{WebConfig.class};
    }
    @Override
    protected String[] getServletMapping(){
        return new String[]{"/"};
    }
}
```

#### （推送？）RSocket&WebSocket:

#### Nginx

##### 跨域：

跨域指的是页面所在域名和异步请求域名不一致

#### 风险控制

#### 缓存

#### 数据库Mysql（2.17）

#### 消息队列

#### 分布式

#### RPC

#### 高并发

#### 高可用

#### Netty

#### Dubbo

#### Zookeeper

#### 无感刷新



http://localhost:9000/oauth2/authorize?response_type=code&client_id=taco-admin-client&redirect_uri=http://localhost:9000/login/oauth2/code/taco-admin-client&scope=writeIngredients+deleteIngredients

```powershell
curl localhost:9000/oauth2/token -H"Content-type:application/x-www-form-urlencoded" -d"grant_type=authorization_code&code=s_9BIDUnF9zIKVtWzwZEeVMVnmG9ddgMxz_raUDQxjmzlc9zgdEhBsVd_d80GmBk8wEoJL26bG4GjQu3Zza-xxVB5i621Np2o7gjVISeQc1f3v05r-vbro6N1X9mFh3O" -d"redirect_uri=http://localhost:9000/login/oauth2/code/taco-admin-client" -u taco-admin-client:secret
```

```powershell
curl localhost:8080/api/ingredients -H "Content-type: application/json" -d "{\"id\":\"CRKT\",\"name\":\"Legless Crickets\",\"type\":\"PROTEIN\"}" -H "Authorization: Bearer eyJraWQiOiI2ZmU3ZThlNC1jZDFiLTRjNDUtYTU1Ni0zNjAzZDkxY2M1YTgiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJpbXVzZXIiLCJhdWQiOiJ0YWNvLWFkbWluLWNsaWVudCIsIm5iZiI6MTcwNzAzNzkzNiwic2NvcGUiOlsiZGVsZXRlSW5ncmVkaWVudHMiLCJ3cml0ZUluZ3JlZGllbnRzIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6OTAwMCIsImV4cCI6MTcwNzAzODIzNiwiaWF0IjoxNzA3MDM3OTM2LCJqdGkiOiI3YzRiOGYzNC00MzhmLTQ5MWYtOWEwZS04NjE0NWU5YzZiOTUifQ.B_kQdHaC4QCOshH5yXLlkZMz3ogRv4FRBp_Qi0wWd5Q1diiE-tUARHY7XNbq09G0n2jv3dgmTRs734_SBgeRAcB2B_FxVMtfE43Aw8EF9pzkN2SJ2mFTqYK7dpN73-f61hhqO1hyfN4LkF3r5lNx6OacwFzIsu6Gm97OHZoqbZtBDEmBRC5W01juOB4nrNZ2BMmn6HJOAgLQ0bNTcBareLqL9cPV86Sz68STmhkxCZ70C-p1CbIvc9dvZw8uLsGEBrQ5gce6ioOKVMrQM1TnDNt7o8IZ-CYhy6yjmwV2Q2cXwRha0L9Bv2h31dU2FB23ZQKHoJP0SBjdFGjeKzrX4g"
```

