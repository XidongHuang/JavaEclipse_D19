package tony.java.exe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class TestReflection {
	
	//如何获取Class的实例(3种)
	@Test
	public void test4() throws ClassNotFoundException{
		//1. 调用运行时类本身的 .class属性
		Class clazz = Person.class;
		System.out.println(clazz.getName());
		
		Class clazz2 = String.class;
		System.out.println(clazz2.getName());
		
		//2. 通过运行时类的对象获取
		Person p = new Person();
		Class clazz3 = p.getClass();
		System.out.println(clazz3.getName());
		
		//3.通过Class的静态方法获取, 通过此方式，体会以下，反射的动态性
		String className = "tony.java.exe.Person";
		
		Class clazz4 = Class.forName(className);
		System.out.println(clazz4);
		
		//4. (了解)通过类的加载器
		ClassLoader cL = this.getClass().getClassLoader();
		Class clazz5 = cL.loadClass(className);
		System.out.println(clazz5.getName());
	}
	
	
	
	/*
	 * java.lang.Class：是反射的源头
	 * 我们创建了一个类，通过编译(javac.exe), 生成对应的.class文件。之后我们使用java.exe加载(JVM的类加载器完成的) 
	 * 此 .class文件，此.class文件加载到内存以后，就是一个运行时类，存放在缓存区。那么这个运行时类本身就是一个Class的实例！
	 * 1. 每一个运行时类只加载一次！
	 * 2. 有了Class的实例以后，我们才可以进行如下的操作：
	 * 		1) 创建对应的运行时类的对象
	 * 		2) 获取对应的运行时类的完整结构(属性，方法，构造器，内部类，父类，所在的包，异常，注解...)
	 * 		3) 调用对应的运行时类的指定的结构(属性，方法，构造器)
	 * 		4) 反射的应用:动态代理
	 * 
	 */
	@Test
	public void test3(){
		Person p= new Person();
		Class clazz = p.getClass();//通过运行时类的对象，调用其getClass(),返回其运行时类。
		System.out.println(clazz);
	}
	
	
	//有了反射，可以通过反射创建一个类的对象，并调用其中的结构
	@Test
	public void test2() throws Exception{
//		@SuppressWarnings{}
		Class clazz = Person.class;
		
		
		//1. 创建clazz对应的运行时类Person类的对象
		Person p = (Person)clazz.newInstance();
		System.out.println(p);
		
		//2.通过反射调用运行时类的指定属性
		//2.1
		Field f1 = clazz.getField("name");
//		f1.setAccessible(true);
		f1.set(p, "liudehua");
		System.out.println(p);
		
		//2.2
		Field f2 = clazz.getDeclaredField("age");
		f2.setAccessible(true);
		f2.set(p, 20);
		System.out.println(p);
		
		//3.通过反射调用运行时类的指定的方法
		Method m1 = clazz.getMethod("show");
		m1.invoke(p);
		
		Method m2 = clazz.getMethod("display", String.class);
		m2.invoke(p, "China");
		
		
	}
	
	
}
