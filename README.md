android中AOP编程的应用 
==

1、AOP的概念：（摘自[维基百科](https://zh.wikipedia.org/wiki/%E9%9D%A2%E5%90%91%E4%BE%A7%E9%9D%A2%E7%9A%84%E7%A8%8B%E5%BA%8F%E8%AE%BE%E8%AE%A1)）
--

面向侧面的程序设计（aspect-oriented programming，AOP，又译作面向方面的程序设计、观点导向编程、剖面导向程序设计）是计算机科学中的一个术语，指一种程序设计范型。该范型以一种称为侧面（aspect，又译作方面）的语言构造为基础，侧面是一种新的模块化机制，用来描述分散在对象、类或函数中的横切关注点（crosscutting concern）。侧面的概念源于对面向对象的程序设计的改进，但并不只限于此，它还可以用来改进传统的函数。与侧面相关的编程概念还包括元对象协议、主题（subject）、混入（mixin）和委托。<br>

2、AOP在Android中的应用点：
--

aop是一种面向切面的编程思想，目的在于从切面插入自己需要的代码，在不破坏、不耦合原有代码逻辑的基础上，实现相关的功能，在Android中的应用场景非常丰富，如：数据统计、日志记录、用户行为统计、应用性能统计、数据校验、行为拦截，等等应用场景。

3、在Android中，如何实现AOP编程：
--

aop编程的实现技术方案有很以下几种：<br>
* 在编码期切入；<br>
* 在编译期修改源代码；<br>
* 在运行期字节码加载前修改字节码；<br>
* 在运行期字节码加载后动态创建代理类的字节码；<br>

aop各种实现技术方案对比：

|类别        | 原理           | 优点  |缺点  |
| ------------- |:-------------:| -----:| -----:|
| 在编码期切入| 通过静态代理切入 | 业务逻辑跟切入代码分离 |针对性强 |
| 在编译期修改源代码 | 在编译期，切面直接以字节码的形式 <br> 编译到目标字节码文件中 |对系统无性能影响 |灵活性不够|
| 在运行期字节码加载前修改字节码 | 自定义类加载器，在加载类的时候对字节码进行修改  | 可以对所有类进行织入|  |
| 在运行期字节码加载后动态创建代理类的字节码 | 类加载后，为接口生成动态代理（动态代理）、或者为<br>目标类动态生成子类（动态字节码生成（CGLIB)）  | (动态代理)相对于静态AOP更加灵活<br>(CGLIB)没有接口也可以织入| (动态代理)<br>切入的关注点需要实现接口。<br>对系统有一点性能影响<br>(CGLIB)<br>扩展类的实例方法为final时，则无法进行织 |

通过调研和对比， 在Android中，目前比较成熟的方案：
* 静态代理；
* Aspectj框架： 编译器对字节码进行切入修改。

4、Aspectj框架入门介绍：
### 环境搭建:
aspectj的环境搭建步骤比较复杂，这里参见了https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx项目(该插件目前有些问题未修复)，对gradle插件进行了封装；
使用步骤：
在项目的build.gradle文件中加入如下代码:

repositories {
    ...
    maven { url 'https://jitpack.io' }
}
dependencies {
    ...
 classpath 'com.github.alfredxl:android-aspectj:0.9.0'
}
在application模块中的build.gradle文件中加入:
apply plugin: 'android-aspectjx'
aspectj介绍: (详见：https://www.eclipse.org/aspectj/doc/released/adk15notebook/index.html)
aspectj术语
名称
描述
JPoint
代码可注入的点，比如一个方法的调用处或者方法内部、“读、写”变量等。
Pointcut
用来描述 JPoint 注入点的一段表达式，比如：调用 Animal 类 fly 方法的地方，call(* Animal.fly(..))。
Advice
常见的有 Before、After、Around 等，表示代码执行前、执行后、替换目标代码，也就是在 Pointcut 何处注入代码。
Aspect
Pointcut 和 Advice 合在一起称作 Aspect。
Pointcut语法:
符号
描述
*
表示任何数量的字符,除了(.)
.. 
表示任何数量的字符包括任何数量的(.)
+
描述指定类型的任何子类或者子接口
!
一 元操作符:
||、&& 
二 元操作符

主要例子:
例子
解析
*Account 
使用Account名称结束的类型,如SavingsAccount和CheckingAccount
java.*.Date 
类型Date在任何直接的java子包中,如java.util.Date和java.sql.Date
java..* 
任何在java包或者所有子包中的类型,如java.awt和java.util或者java.awt.event 和java.util.logging
javax..*Model+ 
所有javax包或者子包中以Model结尾的类型和其所有子类,如TableModel,TreeModel。
!vector 
所有除了Vector的类型
Vector || Hashtable 
Vector或者Hashtable类型
java.util.RandomAccess+ 
RandomAccess的所有子类
void Account.set*(*) 
Account中以set开头,并且只有一个参数类型，无返回值的方法
void Account.*()  
Account中所有的没有参数的void 方法
public * Account.*()  
Account中所有没有参数的public 方法
public * Account.*(..)  
Account中所有的public 方法
* Account.*(..)  
Account中的所有方法,包括private方法
!public * Account.*(..)  
所有的非public 方法
* Account+.*(..)  
所有的方法,包括子类的方法
* java.io.Reader.read(..) 
所有的read方法
* java.io.Reader.read(char[],..) 
所有以read(char[])开始的方法,包括read(char[])和read(char[],int,int)
* javax..*.add*Listener(EventListener+)  
命名以add开始,以Listener结尾的方法,参数中为EventListener或子类
* *.*(..) throws RemoteException  
抛出RemoteException的所有方法

Pattern规则
名称
描述
MethodPattern :  
[!] [@Annotation] [public,protected,private] [static] [final] 返回值类型 [类名.]方法名(参数类型列表) [throws 异常类型]
ConstructorPattern: 
 [!] [@Annotation] [public,protected,private] [final] [类名.]new(参数类型列表) [throws 异常类型]
FieldPattern:  
[!] [@Annotation] [public,protected,private] [static] [final] 属性类型 [类名.]属性名

TypePattern:  
其他 Pattern 涉及到的类型规则也是一样，可以使用 '!'、''、'..'、'+'，'!' 表示取反，'' 匹配除 . 外的所有字符串，
'*' 单独使用事表示匹配任意类型，'..' 匹配任意字符串，'..' 单独使用时表示匹配任意长度任意类型，'+' 匹配其自身及子类，
还有一个 '...'表示不定个数
JPoint语法:
语法
描述
execution(MethodPattern)
方法执行
call(MethodPattern)
方法调用
execution(ConstructorPattern)
构造函数执行
call(ConstructorPattern)
构造函数被调用
staticinitialization(TypePattern)
static 块初始化
get(FieldPattern)
属性读操作
set(FieldPattern)
属性写操作
handler(TypePattern)
异常处理
adviceexecution()
所有 Advice 执行
间接选择JPonit语法：
语法
描述
within(TypePattern):  
TypePattern表示某个包或者类中包含的JPoint，可以使用通配符；
withincode(ConstructorPattern|MetodPattern):  
表示某个类的构造函数或方法中涉及到的JPoint；
cflow(pointcuts): 
比如cflow(call Animal.fly): 标识调用Animal.fly函数时所包含的JPoint，包含fly的call这个JPoint本身；
cflowbelow(pointcuts): 
比如cflowbelow(call Animal.fly): 标识调用Animal.fly函数时所包含的JPoint，但不包含fly的call这个JPoint本身；
this(Type): 
JPoint所在的对象是否满足instanceof Type条件（注：不能使用通配符，与within类似，但within包含内部类， 而this不包含)；
target(Type): 
与this相对，表示Pointcut所在对象是否满足instanceof Type条件(注：不能使用通配符);
args(TypeSignature):
Constructor Signature|Method Signature的参数类型，比如args(int,..)表示第一个参数是int, 后面的参数个数和类型不限；
if(BooleanExpression): 
满足表达式的 Join Point，表达式只能使用静态属性、Pointcuts 或 Advice 暴露的参数、thisJoinPoint 对象。

Advice语法:
语法
描述
Before(Pointcut):
 在执行JPoint之前；
After(Pointcut): 
在执行JPonit之后;
Aroud(Pointcut): 
替换原需要执行的代码，如需执行原代码，需调用joinPoint.proceed()方法，不可与Before和After同时使用
AfterReturning(Pointcut): 
JPoint 为方法调用且正常 return 时，不指定返回类型时匹配所有类型
AfterThrowing(Pointcut): 
JPoint 为方法调用且抛出异常时，不指定异常类型时匹配所有类型

aspectj示例:
execution(MethodPattern)（JPoint方法执行）
@Aspect
public class OtherJpoint {

@Before("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
public void onViewClickListener(JoinPoin joinPoint) throws Throwable {
if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            Object object = joinPoint.getArgs()[0];
 if (object instanceof TextView) {
                Log.d("aopLog", "clickViewName: " + ((TextView) object).getText().toString());
 }
        }
 }
}
该切入点执行的效果为：在所有实现View的点击事件的onClick方法内部代码执行之前打印View的信息
execution(ConstructorPattern)（JPoint构造器执行）
@Before("execution(com.aspectj.demo.bean.Person.new(..))")
public void onStartPageConstructor(JoinPoint joinPoint) throws Throwable {
    Log.d("aopLog", "this:" + joinPoint.getThis().toString());
}
该切入点执行的效果为：在所有创建Person对象的时候，执行Person对象构造函数之前打印该对象的信息；
注意： 构造函数执行， 没有返回值类型， 且函数名只能是new
call(MethodPattern)  (JPoint方法调用)
@Before("call(* com.aspectj.demo.StartPage.setButtonOneData(..))")
public void onCallStartPagesetButtonOneData(JoinPoint joinPoint) throws Throwable {
if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
        Object object = joinPoint.getArgs()[0];
 Log.d("aopLog", "button_text: " + object.toString());
 }
}
该切入点的效果为：在所有调用com.aspectj.demo.StartPage.setButtonOneData(..)该方法的代码之前打印传递给该方法的型参
staticinitialization(TypePattern)  (JPoint类初始化)
@Before("staticinitialization(com.aspectj.demo.bean.Person)")
public void onStartPageStaticinitialization(JoinPoint joinPoint) throws Throwable {
    Log.d("aopLog", "staticinitialization_this:" + joinPoint.getStaticPart().toString());
}
该切入点的效果为：在Person初始化之前打印信息
get(FieldPattern)  (JPoint属性读操作)
@Before("get(* com.aspectj.demo.bean.Person.*)")
public void getPersonField(JoinPoint joinPoint) throws Throwable {
    Log.d("aopLog", "getPersonField:" + joinPoint.getStaticPart().toString());
}
该切入点的效果为，在所有获取Person对象属性的代码之前插入打印信息
set(FieldPattern)  (JPoint属性写操作)
@Before("set(* com.aspectj.demo.bean.Person.*)")
public void setPersonField(JoinPoint joinPoint) throws Throwable {
    Log.d("aopLog", "setPersonField:" + joinPoint.getStaticPart().toString());
}
该切入点的效果为，在所有设置Person对象属性值的代码之前插入打印信息
handler(TypePattern)  (JPoint例外处理执行)
private fun showToast(message: String) {
if (message.isNotEmpty()) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
try {
throw IllegalArgumentException()
    } catch (e: IllegalArgumentException) {

    }
}
@Before("handler(java.lang.IllegalArgumentException)")
public void handlerIllegalArgumentException(JoinPoint joinPoint) throws Throwable {
    Log.d("aopLog", "handlerIllegalArgumentException:" + joinPoint.getStaticPart().toString());
}
该切入点的效果为，在所有编译时匹配的IllegalArgumentException异常，将在异常抛出前，打印信息；
注意： handler只支持Before，而且只能匹配编译期异常捕获，并且不能捕获该异常的子类。
within(TypePattern)  
@Pointcut("within(com.aspectj.demo.StartPage)")
public void withinStartPage() {
}

@Pointcut("call(* android.widget.Toast.show(..))")
public void showToast() {
}

@Before("withinStartPage() && showToast()")
public void withinStartPageAndShowToast(JoinPoint joinPoint) throws Throwable {
    Log.d("aopLog", "withinStartPageAndShowToast:" + joinPoint.getStaticPart().toString());
}
该切入点的效果为，在StartPage类中调用Toast方法之前打印相关信息

withincode(MethodPattern) 
@Pointcut("withincode(* com.aspectj.demo.StartPage.showToast(..))")
public void inStartPageShowToast() {
}
@Pointcut("call(* android.widget.Toast.show(..))")
public void showToast() {
}
@Before("inStartPageShowToast() && showToast()")
public void inStartPageShowToastAndShowToast(JoinPoint joinPoint) throws Throwable {
    Log.d("aopLog", "inStartPageShowToastAndShowToast: " + joinPoint.getSignature().getName());
}
该切入点的效果为：在com.aspectj.demo.StartPage.showToast()方法中调用了Toast方法之前打印相关信息

注解
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface ActivityOnCreate {
}
@Pointcut("execution(@com.aspectj.aspectjaplay.activitylife.ActivityOnCreate * *(..))")
public void ActivityOnCreate() {
}

@Around("ActivityOnCreate()")
public void ActivityOnCreate(ProceedingJoinPoint joinPoint) throws Throwable {
long startTime = System.currentTimeMillis();
 joinPoint.proceed();
 long endTime = System.currentTimeMillis();
 String key = joinPoint.getSignature().getName();
 Log.d("aopLog", "execution_onActivityMethodAround: " + joinPoint.getThis().getClass().getSimpleName() + "_" + key + ":" + (endTime - startTime));
}
该切入点的效果为，在使用了ActivityOnCreate 注解的目标方法执行前进行拦截并实行相关操作

总结：
本篇介绍，对AOP及Aspectj的介绍仅仅只涉及到到入门知识，更输入的学习，欢迎大家一起加入。
