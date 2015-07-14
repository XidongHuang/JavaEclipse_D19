package tony.java.exeDynamic;

//一. 静态代理模式
//接口
interface ClothFactory{
	void productCloth();
}
//被代理类
class NikeClothFacotry implements ClothFactory{
	
	@Override
	public void productCloth(){
		
		System.out.println("Nike products cloths");
	}
	
}
//代理类
class ProxyFactory implements ClothFactory{
	ClothFactory cf;
	
	public ProxyFactory(ClothFactory cf){
		this.cf = cf;
	}
	
	public void productCloth(){
		System.out.println("Agent works for you");
		cf.productCloth();
	}
	
}





public class TestClothProduct {
	public static void main(String[] args) {
		NikeClothFacotry nc = new NikeClothFacotry();
		ProxyFactory pf = new ProxyFactory(nc);
		pf.productCloth();
		
		
		
	}
}
