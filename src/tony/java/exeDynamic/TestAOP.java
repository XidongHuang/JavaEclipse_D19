package tony.java.exeDynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Human{
	void info();
	void fly();
}


class SuperMan implements Human{
	
	public void info(){
		System.out.println("I am superman!");
	}
	
	public void fly(){
		System.out.println("I can fly!");
	}
	
	
	
}

class HumanUtil{
	public void method(){
		System.out.println("== Method 1 ==");
	}
	
	public void method2(){
		System.out.println("=== Method 2 ===");
	}
	
}



class MyInvocationHandler1 implements InvocationHandler{

	Object obj;
	
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		HumanUtil h = new HumanUtil();
		h.method();
		Object returnVal= method.invoke(obj, args);
		h.method2();
		
		return returnVal;
	}
	
	
	
}

//动态地创建一个代理类的对象
class MyProxy{
	public static Object getProxyInstance(Object obj){
		MyInvocationHandler1 handler = new MyInvocationHandler1();
		handler.setObj(obj);
		
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
		
		
	}
}



public class TestAOP {
	public static void main(String[] args) {
		
		SuperMan sm = new SuperMan();
		Object obj = MyProxy.getProxyInstance(sm);
		Human hu = (Human)obj;
		hu.info();
		System.out.println();
		hu.fly();
		
		//--------------------
		System.out.println();
		NikeClothFacotry ncf =new NikeClothFacotry();
		Object obj1 =MyProxy.getProxyInstance(ncf);
		ClothFactory cf = (ClothFactory)obj1;
		cf.productCloth();
		
		
	}
	
	
}



