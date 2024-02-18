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

##### 动态代理：

##### 静态初始化块：

静态初始化块是一种特殊的代码块，用于初始化静态变量。静态初始化块在类第一次被加载到JVM时执行，这发生在任何对象创建之前，且静态初始化块只执行一次。

当初始化逻辑无法通过单一的赋值语句来完成，因此静态初始化块非常适合这种用途。

静态初始化块不能访问非静态变量或方法，因为它是在类加载时执行的，这时还没有任何对象实例存在。

##### Stream:

`map.keySet().stream.toList()`获取到的是不可修改的列表，不支持改变列表的操作例如`add`,`remove`或`Collections.reverse`

##### BigInteger：

##### BigDecima：

##### Runtime：

##### 正则表达式：

##### 包装类：

##### 时间相关类util包和time包：

Date：

SimpleDateFormat：

Calendar：

##### 异常：

##### File：

##### IO流：

#### 工具类：

##### `Arrays`:

```java
/**
*This class contains various methods for manipulating arrays (such as sorting and searching). This *class also contains a static factory that allows arrays to be viewed as lists.
*/
```

提供方法：

1. `public static void sort(int[] a)`使用排序算法`DualPivotQuicksort.sort(a, 0, 0, a.length);`

**Arrays.toString(mark)和mark.toString()的区别**

#### 容器：

##### Collection:

`Collection`接口继承自`Iterable`

```java
public interface Collection<E> extends Iterable<E>
```

**Collection接口的常用方法**（默认实现的）：

```java
default boolean removeIf(Predicate<? super E>filter){
    Objects.requireNonNull(filter);
    boolean removed = false;
    //通过Collection内部方法iterator()获取，每个不同子类的实现
    final Iterator<E> each = iterator();
    while (each.hasNext()) {
        if (filter.test(each.next())) {
            each.remove();
            removed = true;
        }
    }
    return removed;
}
@Override
 default Spliterator<E> spliterator() {
     //使用了Spliterators工具类，干了什么？Spliterator是Java 8引入的一个用于并行遍历元素的迭代器。与传统的Iterator相比，Spliterator提供了一种并行遍历数据元素的能力，特别适用于对集合进行并发操作时提高效率。
     
     return Spliterators.spliterator(this, 0);
 }
default Stream<E> stream() {
    //使用StreamSupport工具类，干了什么？
    return StreamSupport.stream(spliterator(), false);
}
default Stream<E> parallelStream() {
    //使用StreamSupport工具类
    return StreamSupport.stream(spliterator(), true);
}
```

**Collection接口的遍历方式：**

1. 迭代器：

   ```java
   Collection<String> collection = new ArrayList<>();
   Iterator<String> iterator = collection.iterator();
   ```

   Collection的迭代器实现

2. 增强的for循环

   增强的for循环内部使用Iterator实现

3. stream

4. `forEach`方式

   从Java 8开始，`Collection`接口支持`forEach`方法，可以接受一个`Consumer`接口的实现

- `List`接口：

  `List 是interface`继承自`Collection`：

  ```java
  
  ```

  `list.add(0,num)`可以在列表的最前面插入

  1. `Vector`：Vector是一个已经被弃用的类，因为他是**线程同步**的。***原理***

  2. **`ArrayList`** ：ArrayList是一个可以**动态增长**的数组。***动态增长***

     ```java
     public class ArrayList<E> extends AbstractList<E>
             implements List<E>, RandomAccess, Cloneable, java.io.Serializable
     
     /**
     * 默认大小
     */
     private static final int DEFAULT_CAPACITY = 10;
     
     //这个特定的数组可能用于区别那些被初始化但尚未指定容量的集合
     //集合可以是空的（即没有元素），但不是null
     //存放在方法区中，作为共享数组
     private static final Object[] EMPTY_ELEMENTDATA = {};
     private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
     
     //真正用于存放
     //关键字transient用于声明一个实例变量不应该被序列化
     //序列化时排除elementData数组可以减少存储或传输时所需的空间，特别是当数组的实际使用量小于它的容量时
     transient Object[] elementData;
     
     //提供数组大小的构造方法
     public ArrayList(int initialCapacity) {
             if (initialCapacity > 0) {
                 this.elementData = new Object[initialCapacity];
             } else if (initialCapacity == 0) {
                 this.elementData = EMPTY_ELEMENTDATA;
             } else {
                 throw new IllegalArgumentException("Illegal Capacity: "+
                                                    initialCapacity);
             }
         }
     //空构造方法
     public ArrayList() {
             this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
         }
     //通过Collection对象的构造方法
     //先调用Collection对象的toArray()方法
     public ArrayList(Collection<? extends E> c) {
         Object[] a = c.toArray();
         if ((size = a.length) != 0) {
             if (c.getClass() == ArrayList.class) {
                 elementData = a;
             } else {
                 elementData = Arrays.copyOf(a, size, Object[].class);
             }
         } else {
             // replace with empty array.
             elementData = EMPTY_ELEMENTDATA;
         }
     }
     
     //让ArrayList内部使用的数组（elementData）的大小与ArrayList实际存储的元素数量（size）相匹配
     public void trimToSize() {
         modCount++;
         if (size < elementData.length) {
             elementData = (size == 0)
                 ? EMPTY_ELEMENTDATA
                 : Arrays.copyOf(elementData, size);
         }
     }
     
     //让数组大小最小保持minCapacity
     public void ensureCapacity(int minCapacity) {
         if (minCapacity > elementData.length
             && !(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
                  && minCapacity <= DEFAULT_CAPACITY)) {
             modCount++;
             grow(minCapacity);
         }
     }
     
     //使用ArraysSupport获取新的数组长度，并将原数组复制
     //增长两倍和minCapacity中的最大值
     private Object[] grow(int minCapacity) {
         int oldCapacity = elementData.length;
         if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
             int newCapacity = ArraysSupport.newLength(oldCapacity,
                                                       minCapacity - oldCapacity, /* minimum growth */
                                                       oldCapacity >> 1           /* preferred growth */);
             return elementData = Arrays.copyOf(elementData, newCapacity);
         } else {
             return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
         }
     }
     //默认增长方法
     private Object[] grow() {
         return grow(size + 1);
     }
     
     //contains的实现方法
     public boolean contains(Object o) {
         return indexOf(o) >= 0;
     }
     public int indexOf(Object o) {
         return indexOfRange(o, 0, size);
     }
     //start到end之间o的第一次出现的index
     int indexOfRange(Object o, int start, int end) {
         Object[] es = elementData;
         //ArrayList有可能出现null
         if (o == null) {
             for (int i = start; i < end; i++) {
                 if (es[i] == null) {
                     return i;
                 }
             }
         } else {
             for (int i = start; i < end; i++) {
                 if (o.equals(es[i])) {
                     return i;
                 }
             }
         }
         return -1;
     }
     //最后一次的出现
     public int lastIndexOf(Object o) {
         return lastIndexOfRange(o, 0, size);
     }
     
     int lastIndexOfRange(Object o, int start, int end) {
         Object[] es = elementData;
         if (o == null) {
             for (int i = end - 1; i >= start; i--) {
                 if (es[i] == null) {
                     return i;
                 }
             }
         } else {
             for (int i = end - 1; i >= start; i--) {
                 if (o.equals(es[i])) {
                     return i;
                 }
             }
         }
         return -1;
     }
     //克隆操作
     //super.clone()方法是Object类的一个protected方法，用于创建并返回当前对象的一个浅拷贝（shallow copy）
     public Object clone() {
         try {
             ArrayList<?> v = (ArrayList<?>) super.clone();
             v.elementData = Arrays.copyOf(elementData, size);
             v.modCount = 0;
             return v;
         } catch (CloneNotSupportedException e) {
             // this shouldn't happen, since we are Cloneable
             throw new InternalError(e);
         }
     }
     
     //toArray方法
     public Object[] toArray() {
         return Arrays.copyOf(elementData, size);
     }
     //如果传入数组的长度大于集合的元素数量，那么数组中紧跟集合尾部的元素应该被设置为null，以标识集合结束的位置。
     public <T> T[] toArray(T[] a) {
         if (a.length < size)
             // Make a new array of a's runtime type, but my contents:
             return (T[]) Arrays.copyOf(elementData, size, a.getClass());
         System.arraycopy(elementData, 0, a, 0, size);
         if (a.length > size)
             a[size] = null;
         return a;
     }
     
     //set方法，会返回旧值
     public E set(int index, E element) {
         Objects.checkIndex(index, size);
         E oldValue = elementData(index);
         elementData[index] = element;
         return oldValue;
     }
     
     //公共add方法
     public boolean add(E e) {
         modCount++;
         add(e, elementData, size);
         return true;
     }
     //插入到指定index的实现
     public void add(int index, E element) {
         rangeCheckForAdd(index);
         modCount++;
         final int s;
         Object[] elementData;
         if ((s = size) == (elementData = this.elementData).length)
             elementData = grow();
         //向后移一位
         System.arraycopy(elementData, index,
                          elementData, index + 1,
                          s - index);
         elementData[index] = element;
         size = s + 1;
     }
     //调用了这个add方法
     private void add(E e, Object[] elementData, int s) {
         if (s == elementData.length)
             elementData = grow();
         elementData[s] = e;
         size = s + 1;
     }
     
     //remove方法
     public E remove(int index) {
         Objects.checkIndex(index, size);
         final Object[] es = elementData;
     
         @SuppressWarnings("unchecked") E oldValue = (E) es[index];
         fastRemove(es, index);
     
         return oldValue;
     }
     private void fastRemove(Object[] es, int i) {
         modCount++;
         final int newSize;
         if ((newSize = size - 1) > i)
             System.arraycopy(es, i + 1, es, i, newSize - i);
         es[size = newSize] = null;
     }
     
     //addAll方法
     public boolean addAll(Collection<? extends E> c) {
         Object[] a = c.toArray();
         modCount++;
         int numNew = a.length;
         if (numNew == 0)
             return false;
         Object[] elementData;
         final int s;
         if (numNew > (elementData = this.elementData).length - (s = size))
             elementData = grow(s + numNew);
         System.arraycopy(a, 0, elementData, s, numNew);
         size = s + numNew;
         return true;
     }
     public boolean addAll(int index, Collection<? extends E> c) {
         rangeCheckForAdd(index);
     
         Object[] a = c.toArray();
         modCount++;
         int numNew = a.length;
         if (numNew == 0)
             return false;
         Object[] elementData;
         final int s;
         if (numNew > (elementData = this.elementData).length - (s = size))
             elementData = grow(s + numNew);
     
         int numMoved = s - index;
         if (numMoved > 0)
             System.arraycopy(elementData, index,
                              elementData, index + numNew,
                              numMoved);
         System.arraycopy(a, 0, elementData, index, numNew);
         size = s + numNew;
         return true;
     }
     
     //去除gap
     protected void removeRange(int fromIndex, int toIndex) {
         if (fromIndex > toIndex) {
             throw new IndexOutOfBoundsException(
                 outOfBoundsMsg(fromIndex, toIndex));
         }
         modCount++;
         shiftTailOverGap(elementData, fromIndex, toIndex);
     }
     
     /** Erases the gap from lo to hi, by sliding down following elements. */
     private void shiftTailOverGap(Object[] es, int lo, int hi) {
         System.arraycopy(es, hi, es, lo, size - hi);
         for (int to = size, i = (size -= hi - lo); i < to; i++)
             es[i] = null;
     }
     
     //Iterator的实现
     private class Itr implements Iterator<E> {
         int cursor;       // index of next element to return
         int lastRet = -1; // index of last element returned; -1 if no such
         int expectedModCount = modCount;
     
         // prevent creating a synthetic constructor
         Itr() {}
     
         public boolean hasNext() {
             return cursor != size;
         }
     
         public E next() {
             checkForComodification();
             int i = cursor;
             if (i >= size)
                 throw new NoSuchElementException();
             Object[] elementData = ArrayList.this.elementData;
             if (i >= elementData.length)
                 throw new ConcurrentModificationException();
             cursor = i + 1;
             return (E) elementData[lastRet = i];
         }
     
         public void remove() {
             if (lastRet < 0)
                 throw new IllegalStateException();
             checkForComodification();
     
             try {
                 ArrayList.this.remove(lastRet);
                 cursor = lastRet;
                 lastRet = -1;
                 expectedModCount = modCount;
             } catch (IndexOutOfBoundsException ex) {
                 throw new ConcurrentModificationException();
             }
         }
     
         @Override
         public void forEachRemaining(Consumer<? super E> action) {
             Objects.requireNonNull(action);
             final int size = ArrayList.this.size;
             int i = cursor;
             if (i < size) {
                 final Object[] es = elementData;
                 if (i >= es.length)
                     throw new ConcurrentModificationException();
                 for (; i < size && modCount == expectedModCount; i++)
                     action.accept(elementAt(es, i));
                 // update once at end to reduce heap write traffic
                 cursor = i;
                 lastRet = i - 1;
                 checkForComodification();
             }
         }
     
         final void checkForComodification() {
             if (modCount != expectedModCount)
                 throw new ConcurrentModificationException();
         }
     }
     ```

     **谈谈你对ArrayList的理解：**

     **`Collection<? extends E> c`**：

     `Collection<? extends E>`是Java中泛型通配符的一个使用示例

  3. `LinkedList`：

  **List<List<Integer>> res = new ArrayList<>();的底层逻辑，内部List是如何分配内存的？**

- `Queue`：

  1. `LinkedList`：
  2. `PriorityQueue`：

- `Set`：

  1. `HashSet`：
  2. `TreeSet`：

##### Map:

- `HashMap`：

  HashMap是如何分配空间的？

  HashMap的内部实现？

  HashMap的底层逻辑？

- `TreeMap`：

##### 双端队列`Deque`

双端队列实现了两端进出，可替代栈和队列，继承自`Queue`接口，实现类**`LinkedList,ArrayDeque,LinkedBlockingDeque`**

|      |       头        |       头       |       尾       |      尾       |
| :--: | :-------------: | :------------: | :------------: | :-----------: |
| 插入 |  `addFirst()`   | `offerFirst()` |  `addLast()`   | `offerLast()` |
| 删除 | `removeFirst()` | `pollFirst()`  | `removeLast()` | `pollLast()`  |
| 检查 |  `getFirst()`   |  `peekFirst`   |  `getLast()`   | `peekLast()`  |

##### 优先队列：

#### 网络编程（java.net）：

##### TCP（Transmission Control Protocol）：基于字节流

##### UDP（User Datagram Protocol）：UDP是一种无连接的传输层协议，提供简单但是不可靠的消息传输服务。

特点

- **无连接**：UDP在传输数据之前不需要建立连接，直接发送数据。
- **不可靠传输**：UDP不保证数据的可靠到达，即数据可能丢失或顺序可能错乱。
- **快速传输**：由于UDP协议简单，处理速度快，没有建立连接的延迟，适合对传输速度要求较高的场景。
- **支持一对多通信**：UDP支持发送单个数据包到多个接收者（广播或组播）。

#### 并发&多线程(todo:2.13/16/17)

[（2023最新Java进阶学习路线【涵盖初，中，高级程序员以及架构师所有内容】）_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1QB4y1v7Si?p=2&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

线程切换的概念：CPU保存现场，执行新线程，回复现场，继续执行原线程的这样一个过程

##### 线程：

线程优先级：

Java中，JVM中一个线程对应操作系统中一个线程

用户线程：

内核线程：

**超线程：**一个ALU对应多个PC寄存器的组合，例如四核八线程

##### CAS

compare and swap

`AtomicInteger`：看看底层实现

```java
AtomicInteger
```

cas如何实现：

1. 读取当前值E
2. 计算结果值V
3. 比较E和当前值N
4. 相等则修改为V
5. 如果不相等则其他线程将其修改，需要重新读取计算
6. ABA问题：其他线程修改数次后和原值相同，解决方法：给值增加版本号、
7. **cas的底层实现：**`lock cmpxchg`

##### 对象在内存中的布局：

```java
Object o = new Object();
System.out.println(ClassLayout.parseInstance(o).toPrintable());
synchronized (o){
      System.out.println(ClassLayout.parseInstance(o).toPrintable());
}//执行块中代码时，对o上锁
```

```
java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           58 0d 00 00 (01011000 00001101 00000000 00000000) (3416)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

java.lang.Object object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           d0 f4 ff 79 (11010000 11110100 11111111 01111001) (2046817488)
      4     4        (object header)                           1b 00 00 00 (00011011 00000000 00000000 00000000) (27)
      8     4        (object header)                           58 0d 00 00 (01011000 00001101 00000000 00000000) (3416)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
```

JVM连个参数：`UseCompressedClassPointer``UseCompressedOops`分别是使用类压缩指针，和使用普通对象压缩指针，未压缩8字节64位，压缩后4个字节32位。

普通对象在内存中的结构：

1. 对象头（markword）：8字节

   JDK8

   ![image-20240214154443183](notepics/image-20240214154443183.png)

2. 类型指针（class pointer）：4字节

3. 实例数据（instance data）：

4. 对齐（padding）：对齐到8字节的倍数，方便读取

##### 锁的升级过程以及Markword内容：

1. new出来一个对象`Object o = new Object()`，无锁态。
2. 调用`o.hashCode()`等方法，则把hashCode写到markword中
3. 使用`synchronized(o)`则偏向锁，markword存储线程ID。ps:偏向锁有延迟
4. 如果有线程进行竞争，则撤销偏向锁，线程在自己的线程栈中生成`LockRecord`，这个锁记录中包含了锁对象的Mark Word的拷贝，用CAS操作将markword设置成指向自己这个线程的LR的指针，
5. 如果竞争加剧（有线程超过10次自旋，或者自旋线程数超过cpu核心数的一半），升级位重量级锁，linux mutex，线程挂起，进入等待队列

##### 锁消除（Lock eliminate）&锁粗化（Lock coarsening）

`StringBuffer`和`StringBuilder`的区别：

1. `StringBuffer`中的方法`append`是同步的(`synchronized`)

   ```java
   public void add(String str1,String str2){
       StringBuffer sb = new StringBuffer();
       sb.append(str1).append(str2);
   }
   //当sb这个引用旨在add方法中使用，不可能被其他线程引用，因此sb是不可能共享的资源，JVM会自动消除StringBuffer对象内部的锁
   ```

```java
public String test(String str){
    int i = 0;
    StringBuffer sb = new StringBuffer();
    while(i<100){
        sb.append(str);
        i++;
    }
    return sb.toString;
}
```

JVM会检测到这样一连串的操作都对同一个对象加锁，此时就加一次锁。

##### `synchronized`的实现过程：

synchronized对对象加锁是在对象的markword中记录，那么对方法代码块加锁呢？

当`synchronized`修饰非静态方法时，锁定的是调用该方法的对象实例。

当`synchronized`修饰代码块时，需要指定一个锁对象

当`synchronized`修饰静态方法时，锁定的是这个类的Class对象

1. java代码：`synchronized`
2. class代码：monitorenter monitorexit：这两个指令分别用于进入和退出监视器（Monitor），监视器是实现`synchronized`同步的底层机制。每个使用`synchronized`的对象都关联着一个监视器，当线程执行到`monitorenter`指令时，它将尝试获取监视器的所有权；当执行到`monitorexit`指令时，它将释放监视器。如果一个线程已经持有了监视器，其他任何线程都无法通过`monitorenter`成功获取监视器，直到持有监视器的线程执行`monitorexit`释放监视器。
3. 执行过程中自动升级
4. lock comxchg

##### `synchroniezd`和`ReentrantLock`有哪些区别？

##### 重入锁：



##### 缓存行：

MESI Cache一致性协议：Cache line 有四种状态 Modified,Exclusive,Shared,Invalid（x86）

##### Volatile：

阻止指令重排序：在运行程序时，为了提高性能，编译器和处理器可能会对指令序列进行重新排序。`volatile`可以部分防止指令重排，确保在`volatile`变量读写操作前后的程序执行顺序不被重排，从而避免在并发情况下可能出现的数据不一致问题。

**JSR内存屏障：**

1. **LoadLoad Barriers**：放在两个读操作之间，确保前一个读操作的结果对后一个读操作可见。

2. **StoreStore Barriers**：放在两个写操作之间，确保前一个写操作对后一个写操作可见。

3. **LoadStore Barriers**：放在读操作后和写操作前，确保读操作的结果在写操作发生之前可见。

4. **StoreLoad Barriers**：是最重要的一种屏障，放在写操作后和读操作前，确保写操作的结果对后续的读操作可见

   对`volatile`变量的写操作会插入StoreStore和StoreLoad屏障，而对`volatile`变量的读操作会插入LoadLoad和LoadStore屏障。

**volatile如何解决指令重排序?**

1. volatile i
2. ACC_VOLATILE
3. JVM的内存屏障：
4. hotspot实现：

保证线程可见性：当一个变量被声明为`volatile`之后，线程对这个变量的读取都会从主存中进行，对这个变量的写入也会立即同步回主存，这保证了一个线程对这个变量值的修改对其他线程是立即可见的。

虽然`volatile`关键字确保了变量的可见性和防止指令重排，但它并不具备互斥性

##### ThreadLocal:

线程本地变量，每个线程独立拥有，线程存在则变量存在

`ThreadLocal`变量并不是存储在`ThreadLocal`对象本身，而是存储在访问该`ThreadLocal`的线程的`Thread`对象内部。具体而言，每个`Thread`对象内部有一个`ThreadLocal.ThreadLocalMap`类型的字段`threadLocals`，该map存储`ThreadLocalMap.Entry`类型，该Entry使用弱引用。

为什么要用弱引用？

防止内存泄漏，ThreadLocal对象被两个变量引用：一个是new这个对象时的强引用`ThreadLocal<String> tl = new ThreadLocal<>();`另一个是调用set方法后，存储的Entry中的key对这个对象的弱引用。当tl不再引用这个对象时，key的引用就被回收。但是在这个Map中的那条记录无法被回收，需要手动。

```java
static class Entry extends WeakReference<ThreadLocal<?>> {
            /** The value associated with this ThreadLocal. */
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }
```

ThreadLocal源码分析：



java中的四种引用：

1. 强引用（Normal Reference）：栈里的m指向堆里的对象

2. 软引用：当堆中内存足够时，不回收，内存不够分配的时候，回收软引用指向的对象。

   ```java
   SoftReference<byte[]> sr = new SoftReference<>(new byte[10]);
   byte[] by = sr.get()
   ```

3. 弱引用：垃圾回收期gc只要发现有弱引用的就回收，

4. 虚引用：get不到，用于管理堆外内存

##### ThreadPoolExecutor:

为什么要使用线程池？

**线程池的使用方式：**

- 构建ThreadPoolExecutor对象即可
- 执行runnable 使用execute方法
- 执行callable 使用submit方法，有返回值

JDK中已经提供了Executors，提供了很多封装好的线程池

**核心参数**：

- 核心线程数（corePoolSize）：当提交
- 最大线程数（maximumPoolSize）：
- 存活时间（keepAliveTime）：非核心线程的空闲时间
- 单位（unit）：存活时间的单位
- 任务队列（workQueue）：
- 线程工厂（ThreadFactory）：
- 拒绝策略（RejectedExecutionHandler）：

**执行流程：**

#### JVM(todo:2.14)

[2023版最新JVM调优实战，深入理解JVM虚拟机，马士兵讲解JVM教程！_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1fe411A7JQ/?spm_id_from=333.337.top_right_bar_window_custom_collection.content.click&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

[【狂神说Java】JVM快速入门篇_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1iJ411d7jS/?spm_id_from=333.788.top_right_bar_window_custom_collection.content.click&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

##### JVM内存分配：

1. 栈：方法运行时使用的内存，比如main方法运行，进入方法栈中执行
2. 堆：存储对象或者数组，new来创建的，都存储在堆内存
3. 元空间：存储可以运行的class文件
4. 本地方法栈：JVM在使用系统调用时使用
5. 寄存器：给cpu使用

**多线程的时候如何分配**

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

##### 对象在内存中的布局：

使用工具JOL (Java Object Layout)

```java
Object o = new Object();
System.out.println(ClassLayout.parseInstance(o).toPrintable());
synchronized (o){
      System.out.println(ClassLayout.parseInstance(o).toPrintable());
}//执行块中代码时，对o上锁
```



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

#### 设计模式

##### 单例模式：

###### 1. 懒汉式（线程不安全）

```
javaCopy codepublic class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

这种实现方式最基本，但在多线程环境下不能正常工作。

###### 2. 懒汉式（线程安全）

```
javaCopy codepublic class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

通过添加`synchronized`关键字使得在多线程环境下能够工作，但降低了效率。

###### 3. 饿汉式（静态常量）

```
javaCopy codepublic class Singleton {
    private static final Singleton instance = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return instance;
    }
}
```

这种方式基于类加载机制，保证了单例只会被创建一次。它是线程安全的，但可能会导致资源浪费。

###### 4. 双重检查（Double-Check）

```
javaCopy codepublic class Singleton {
    private static volatile Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

这种方式既保证了懒加载，又通过双重检查和`volatile`保证了线程安全和性能。

**DCL为什么需要`volatile`？**

因为new方法在底层实现时基本分为三步骤：分配内存，执行初始化方法，链接，如果不使用volatile关键字，则有可能发生指令重排序，将半初始化状态的对象链接到变量上，当另一个线程来获取实例时，则会出现使用半初始化对象的结果。

###### 5. 静态内部类

```
javaCopy codepublic class Singleton {
    private Singleton() {}
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

利用了类加载机制保证初始化实例时只有一个线程，既实现了线程安全，又避免了同步带来的性能影响。

###### 6. 枚举

```
javaCopy codepublic enum Singleton {
    INSTANCE;
    public void whateverMethod() {
    }
}
```

使用枚举方式是实现单例模式的最佳方法，它不仅能避免多线程同步问题，还能防止反序列化重新创建新的对象



### 算法

[2、算法基本概念_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1zv411j7QX?p=5&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

#### 快排：

#### KMP算法：

#### 贪心：[095764-01.pdf (tsinghua.edu.cn)](http://www.tup.tsinghua.edu.cn/upload/books/yz/095764-01.pdf)

#### 广度优先遍历：

广度优先遍历时如何记载结点的层数？

#### 矩阵：

##### 矩阵中位置的数学关系：

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

##### Spring三级缓存：

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

#### Servlet:

[01-servlet与http的简单介绍_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1Zw411Z77e?p=2&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

##### servlet简介：

Servlet是运行在Web服务器或应用服务器上的程序，它用于处理客户端请求并生成动态内容。

Servlet的主要特点和功能包括：

- **生命周期管理**：Web容器（如Tomcat）负责管理Servlet的生命周期，包括初始化（调用`init`方法）、处理请求（调用`service`方法或`doGet`、`doPost`等方法）和销毁（调用`destroy`方法）。
- **请求处理**：Servlet可以接收客户端发送的请求，并通过请求对象（`HttpServletRequest`）访问请求信息，然后生成响应（通过`HttpServletResponse`对象）。
- **多线程支持**：Servlet容器会为每个请求创建一个新的线程，使得Servlet能够同时处理多个请求。
- **会话管理**：Servlet提供了会话管理机制，允许跟踪用户会话状态。

##### Http协议：

超文本传输协议(Hyper Text Transfer Protocol)

特点：

1. **简单性**：HTTP 协议简单，易于理解和使用。客户端发送请求到服务器，服务器回送响应，这个过程非常直观。
2. **无连接性**：HTTP 是一个无连接的协议。默认情况下，HTTP 请求一次被处理后，连接就被关闭，节省了服务器资源。这意味着服务器不需要维持客户状态，从而简化了服务器的设计。这种模式被称为“无状态协议”。
3. **无状态性**：无状态是指协议对于事务处理没有记忆能力。服务器不知道客户端是什么状态。虽然这样简化了服务器的设计，但在实际应用中，我们通常通过在请求中添加Cookies来维持状态。
4. **支持多种数据类型**：HTTP 不仅支持传输文本，还支持图片、视频、音频等多种类型的数据。通过使用MIME类型，在HTTP头部对资源类型进行描述，使得HTTP能够处理和传输多种格式的文档。
5. **可扩展性**：HTTP 允许自定义请求头和响应头，因此可以根据需要扩展功能。例如，通过自定义头部，可以实现客户端缓存、认证、内容协商等功能。
6. **支持B/S和C/S模式**：HTTP 不仅支持在浏览器（客户端）和服务器之间的通信（B/S架构），也可以在自定义的客户端和服务器之间进行通信（C/S架构）。
7. **支持缓存处理**：通过HTTP头信息中的缓存控制指令，可以控制文档在客户端和代理服务器上的缓存处理，有效减少带宽使用，提高访问速度。
8. **支持安全扩展（HTTPS）**：虽然HTTP本身不包含加密机制，但可以通过SSL/TLS协议加密HTTP通信，形成HTTPS，确保数据传输的安全性。

请求行报文格式：

![](notepics/image-20240216165618293.png)

响应行报文格式：

![](notepics/image-20240216165930649.png)

**http的交互过程**

1. 建立连接：

   三次握手：syn，ack+syn，ack

2. 发送请求：

3. 关闭连接

   四次分手：经过四次才能释放两端的资源

GET和POST请求方式的区别：

1. get请求参数是直接显示在地址栏的，而post在地址栏不显示
2. get方式不安全，post安全
3. get参数有长度限制，post参数没有长度限制

##### tomcat：

**自定义tomcat实现：**

Servlet简介：

servlet是一种web服务器端编程技术，是一个实现了特殊接口的Java类，由支持Servlet的web服务器调用和启动运行，一个Servlet负责对应的一个或一组URL访问请求

Servlet流程：

Servlet生命周期：

1. **加载和实例化** (`init` 方法)：
   - **加载**：Servlet 容器启动时或当 Servlet 被首次请求时，容器加载 Servlet 类。
   - **实例化**：加载后，Servlet 容器创建 Servlet 类的实例。
   - **初始化**：实例化后，容器调用 Servlet 的 `init` 方法。这个方法只会被调用一次。`init` 方法通常用于一次性的启动逻辑，例如加载配置或初始化资源（如数据库连接）。`init` 方法接收一个 `ServletConfig` 对象，包含了 Servlet 的初始化参数。
2. **请求处理** (`service` 方法)：
   - 对于每个请求，Servlet 容器会创建一个新的 `HttpServletRequest` 对象和 `HttpServletResponse` 对象，并调用 Servlet 的 `service` 方法。`service` 方法会根据 HTTP 请求的类型（GET、POST、PUT、DELETE等）调用对应的方法（如 `doGet`、`doPost`等）。这意味着 `service` 方法可能会被调用多次，每次请求都是如此。
3. **销毁** (`destroy` 方法)：
   - 当 Servlet 容器关闭或需要从容器中移除 Servlet 时，它会调用 `destroy` 方法。这个方法也只会被调用一次。`destroy` 方法可以用来释放 Servlet 占用的资源，如关闭数据库连接或清理临时文件等。

**`doGet`,`doPost`,`Service`方法的区别**

**request对象：**

tomcat会把请求转换到`HttpServletRequest`中

**Cookie：**

http是一个无状态协议，当一个客户端向服务端发送请求，在服务器返回响应后，连接就关闭了，服务器端不再保留连接信息，那么当客户端发送的多次请求需要相同的参数时，怎么办？

Cookie是一种在客户端保持http状态信息的技术，cooke是浏览器访问服务器的某个资源是，web服务器在响应头中存储传给浏览器的数据。cookie中保存的是key-value的形式

```java
Cookie cookie = new Cookie("00001","value");
cookie.setPath("/xxx");
cookie.setMaxAge(3*24*3600);
response.addCookie(cookie);
Cookie[] cookies = request.getCookies();
```

**Session：**

Session的机制：

1. **用户首次访问服务器**：当用户第一次访问Web应用服务器时，服务器会根据需要决定是否创建一个Session。
2. **创建Session**：如果需要，服务器会为该用户创建一个唯一的Session ID（通常是一个长字符串），并将此ID与用户的状态信息一起存储在服务器上。
3. **发送Session ID给客户端**：创建Session后，服务器会将Session ID通过响应发送给客户端，通常是通过设置Cookie在客户端浏览器存储Session ID。有时候，也可以通过URL重写等其他方式传递Session ID。
4. **客户端存储Session ID**：客户端浏览器会存储Session ID（如果是通过Cookie方式的话），并在之后的每次请求中自动将其发送给服务器。
5. **服务器识别Session ID**：在后续的请求中，服务器会从请求中读取Session ID，通过这个ID查找之前存储的用户状态信息，从而知道是哪个用户发起的请求。
6. **持续交互**：服务器根据Session中存储的状态信息来处理用户的请求，直到Session过期或被显式销毁，比如用户登出。

Session的基本操作:

```java
//request为jakarta的包中HttpServletRequest
HttpSession session = request.getSession();
//设置session的失效时长
session.setMaxInactiveInterval(5);
//强制session失效：
session.invalidate();
//session设置参数
session.setAttribute("key","value");

```

**ServletContext和ServletConfig：**

运行在JVM上的每一个web应用都有一个与之对应的Servlet上下文，提供ServletContext接口用来表示Servlet上下文，ServletContext可以被所有servlet访问

servletContext的基本使用

```java
ServletContext servletContext = this.getServletContext();
ServletContext servletContext = this.getServletConfig().getServletContext();
ServletContext servletContext = request.getSession().getServletContext();

//设置属性值
servletContext.setAttribute("key","value");
```

servletConfig的基本使用

可以用来获取web.xml中的servlet配置信息

```java

```

**JSP：**

![image-20240218182502844](notepics/image-20240218182502844.png)

编译器指令

1. page

2. include

3. taglib

   ```jsp
   <%@ page contentType="text/html;charset=GBK"%>
   <%@ include file="相对位置"%>
   <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
   ```

**EL表达式：**

```jsp
${expression}
```

**JSTL标签库：**

**过滤器和监听器：**

过滤器能够对web请求和web响应的头属性和内容体进行操作的特殊web组件`extends Filter`

监听器监听request对象或者response对象`extends ServletRequestListener`

![image-20240218190116031](notepics/image-20240218190116031.png)

#### Ajax：

Asynchronous JavaScript and XML 在无需重新加载整个网页的情况下，能够更新部分网页

#### Nginx

##### 跨域：

跨域指的是页面所在域名和异步请求域名不一致

#### 风险控制

#### 缓存

#### 数据库Mysql（2.18）

[也就马士兵能把MySQL基础+优化+SQL+索引+锁机制+引擎+底层原理等MySQL数据库讲的如此简单明了！_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1Y64y1i7kM/?spm_id_from=333.337.search-card.all.click&vd_source=f67d6aae55af8412bb2b00a8e38c78b8)

##### 基础语法

```mysql
# select全部
SELECT * FROM `table_name`;
# select列名
SELECT `column_name` AS `xxx`,`column_name` FROM `table_name`;
# select处理空值
SELECT `column_to_calculate`*12+(CASE WHEN `column_to_calculate` IS NULL THEN 0 ELSE `column_to_calculate` END) FROM `table_name`;
# select去重
SELECT DISTINCT `column_name` FROM `table_name`;

# where
where id = 10;
where id <> 10;# 不等于
where id>= 1 and id<=100;
where `date` > '1980-02-01';
# 模糊查询
LIKE '%A%' # 0个或多个
LIKE '_A_' # 一个

# order by
ORDER BY `xxx`;
# 倒序
ORDER BY `AAA` DESC,`xxx` DESC; # 先前面排完，相同的用后面的排

# group by
SELECT MAX(sal) FROM emp GROUP BY `column_name`;

# having 是对分组进行限制

```

常用函数

[MySQL :: MySQL 8.0 Reference Manual :: 14 Functions and Operators](https://dev.mysql.com/doc/refman/8.0/en/functions.html)

组函数：

查找多条数据，输出一条数据

```mysql

```

**常见面试题：**

**分页查询：**

```sql
# mysql
SELECT ename,sal FROM empl limit 5; # 第一页
SELECT ename,sal FROM empl LIMIT 5,5; # 第二页
```

**DDL语句：**

Data Defination Langure 

联合主键：两个或多个字段联合起来作为主机那

**DML语句** CRUD

**索引：**mysql常用索引：B树（B+树）和Hash

**存储引擎是什么？**



InnoDB，MyISAM使用B+树

MEMORY 使用Hash

InnoDB是否支持Hash？InnoDB可以使用自适应hash，用户没办法干预

*为什么选择B+树？*

hash索引的缺点：

1. 利用hash存储的话需要将所有的数据文件添加到内存，当数据量非常大时，将所有数据加载到内存中的哈希表可能会导致内存不足
2. **不支持范围查询**：与树形索引结构（如B树）不同，哈希索引不适合执行范围查询操作，因为哈希函数的输出并不保持键之间的顺序关系。

*二叉树和红黑树为什么不行？*

无论是二叉树还是红黑树，都会因为树的深度造成io次数变多，影响数据读取的效率。

**扰动函数：**

**磁盘预读：**页 4KB

**前缀索引：**它基于字段值的前缀（即字符串的开始部分）来创建索引，而不是整个字段值。前缀索引可以减少索引占用的空间并提高查询效率

```sql
CREATE INDEX idx_name_prefix ON table_name (name(10));
```

MyISAM和InnoDB的区别：

MyISAM的B+树的叶子结点中存储的是一个地址，地址指向数据文件中的的数据

InnoDB的B+树的叶子结点中直接存储数据

**注意：**InnoDB是通过B+树结构对主键创建索引，然后叶子结点中存储记录，**如果没有主键，那么会选择唯一键，如果没有唯一键**，则会生成一个6字节的`row_id`来作为主键。如果创建索引的键是其他字段，那么在叶子结点中存储的是该记录的主键，然后通过主键索引找到对应的记录；

**聚簇索引和非聚簇索引：**

**聚簇索引**：聚簇索引将数据表中的行直接存储在索引的叶节点中。这意味着表数据按照索引键的顺序物理存储。每个表只能有一个聚簇索引，因为数据只能按照一种顺序存储。

- **数据排序**：数据按照索引键的顺序进行物理排序，这可以提高范围查询的效率。
- **快速访问**：由于索引结构直接包含了表中的数据，聚簇索引可以加快数据的访问速度。
- **空间效率**：聚簇索引不需要额外的空间来存储指向数据行的指针，因为数据直接存储在索引的叶节点上。
- **插入速度影响**：由于需要保持数据的物理顺序，插入和删除操作可能导致数据页分裂，影响性能。

**非聚簇索引**：非聚簇索引，又称为辅助索引或二级索引，不改变表中数据的物理存储顺序。它们通过索引键和一个指向数据行的指针（通常是行ID或数据页的地址）来构建索引

- **独立的存储结构**：非聚簇索引在物理上独立于数据表，索引结构包含键值和指向实际数据行的指针。
- **支持多个索引**：一个表可以拥有多个非聚簇索引，每个索引可以基于不同的列或列组合创建。
- **额外空间需求**：非聚簇索引需要额外的存储空间来保存索引结构及其指向数据行的指针。
- **访问速度**：查询非聚簇索引可能需要两次磁盘访问：一次访问索引找到数据行的指针，另一次访问数据表来获取数据。

**回表：**一个查询使用非聚簇索引来查找数据时，它**首先在索引中查找满足查询条件的条目**。由于非聚簇索引的叶节点**不直接包含数据行本身**，而是包含了指向实际数据行的指针（在InnoDB中通常是主键的值），所以在找到索引条目后，数据库需要执行一个额外的步骤：使用这些指针去数据表中检索完整的数据行。这个从索引指针到数据行的检索过程就称为"回表"。

**索引覆盖：**如果查询可以完全由非聚簇索引中的列来满足，那么就可以避免回表操作。这种索引称为覆盖索引。

索引覆盖最常见于以下几种查询场景：

- **选择特定列**：查询只需要表中的少数几个列，而这些列已经被包含在一个索引中。
- **聚合查询**：如计数（COUNT）、求和（SUM）、平均值（AVG）等操作，只需对索引中的列进行操作。
- **排序和分组**：当查询包含ORDER BY或GROUP BY子句时，如果所涉及的列已经在索引中，可以直接利用索引进行排序或分组，无需额外的数据表访问。

**索引下推：**索引下推优化技术特别指的是**将查询的一部分条件（如过滤条件）应用到索引扫描操作中，**而不是在检索了所有可能的行之后再应用这些条件。在没有索引下推的情况下，数据库可能首先使用索引找到所有可能的匹配行，然后再将查询条件应用到这些行上，以过滤出真正符合条件的结果。

**最左匹配：**最左匹配原则指的是在使用复合索引进行查询时，查询条件需要从索引的最左侧列开始匹配，并且按照索引中定义的列的顺序进行。

如，假设有一个复合索引是基于以下三个列创建的：`(A, B, C)`。根据最左匹配原则，以下是几种可以有效利用这个索引的查询条件的例子：

1. `WHERE A = value`：这个查询可以充分利用索引，因为它从索引的最左侧列开始。
2. `WHERE A = value AND B = value`：这个查询同样可以有效利用索引，因为它按照索引列的顺序来。
3. `WHERE A = value AND B = value AND C = value`：这个查询可以最大限度地利用索引。

然而，如果查询条件跳过了最左侧的列，比如只有`WHERE B = value`或者`WHERE C = value`，则无法充分利用该复合索引。

*组合索引是如何存储的？*

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

