package helloworld;

public class HelloWorlder implements Runnable {
String name;
	public HelloWorlder(String name) {
		this.name = name;
	}
	
	public void greet() {
		System.out.println(name + " greets the World!");
	}

	@Override
	public void run() {
		greet();
	}
}
