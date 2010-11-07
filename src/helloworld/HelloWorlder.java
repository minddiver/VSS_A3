/**
 * 05.11.2010
 * Wladimir Danilov
 * Jevgeni Zelenkov
 * 
 * Aufgabe 3.1
 * Implementirung des "HelloWorld" als Thread.
 * Nach dem Start wird einmalig eine HelloWorld-Meldung ausgegeben,
 * der Name der Instanz ist dabei vorangestellt.
 */


package helloworld;

public class HelloWorlder implements Runnable {
	private String name;
	
	/**
	 * Konstruktor
	 * @param name Name der Instanz.
	 */
	public HelloWorlder(String name) {
		this.name = name;
	}
	
	/**
	 * Gibt die HelloWorld-Meldung auf den Konsolenstream aus und 
	 * führt flush() aus.
	 */
	public void greet() {
		System.out.println(name + " greets the World!");
		System.out.flush();
	}

	/**
	 * Ruft greet() auf und terminiert.
	 */
	@Override
	public void run() {
		greet();
	}
}
