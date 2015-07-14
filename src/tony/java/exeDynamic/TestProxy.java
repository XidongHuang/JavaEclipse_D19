package tony.java.exeDynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Handler;

//动态代理的使用
interface Subject{
	void action();
}
//被代理类
class RealSubject implements Subject{
	public void action(){
		System.out.println("I am going to be run by an agency");
	}
}

class MyInvocationHandler implements InvocationHandler{
	Object obj;//实现了接口的被代理类的对象的声明
	//作用1. 给被代理类的对象实例化 2.返回一个代理类的对象
	public Object blind(Object obj){
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}
	//当通过代理类的对象发起对被重写的方法的调用时，都会转换为对如下的invoke方法的调用
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//method方法的返回值时returnVal
		Object returnVal = method.invoke(obj, args);
		
		return returnVal;
	}
	
}

//代理类



public class TestProxy {
	public static void main(String[] args) {
		//1.被代理类的对象
		RealSubject rs = new RealSubject();
		//2.创建一个InvacationHandler接口的类的对象
		MyInvocationHandler mi = new MyInvocationHandler();
		//3.调用blind()方法，动态地返回一个同样实现了real所在类实现的接口Subject的代理类的对象。
		Object obj = mi.blind(rs);
		Subject sub = (Subject)obj;//此时的sub就是代理类的对象
		
		sub.action();
		
		//再举个例子
		NikeClothFacotry nf = new NikeClothFacotry();
		ClothFactory proxyCloth = (ClothFactory)mi.blind(nf);//proxyCloth即为代理类的对象
		proxyCloth.productCloth();
		
		
	}
}
