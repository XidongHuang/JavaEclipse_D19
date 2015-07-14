package tony.java.exe;

public class Person {

public String name;
	private int age;
//	private String nation;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Person() {
	super();
	System.out.println("fsadf");
}

	public Person(String name) {
		super();
		this.name = name;
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	public void show(){
		System.out.println("I am ");
		
	}
	 
	public void display(String str){
		System.out.println("My nationalist "+ str);
		
	}
	
}
