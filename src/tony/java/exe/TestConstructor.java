package tony.java.exe;

import org.junit.Test;

public class TestConstructor {
	
	
	@Test
	public void test1() throws Exception{
		String className = "tony.java.exe.Person";
		Class clazz = Class.forName(className);
		
		//创建对应的运行时类的对象：是哟和那个newInstance()，实际上就是调用类运行时类的空参的构造器。
		//要想能够创建成功：a)要求对应的运行时类要有空参的构造器 b)构造器的权限修饰符的权限要足够。
		Object obj = clazz.newInstance();
		Person p = (Person)obj;
		System.out.println(p);
		
		
	}
}
