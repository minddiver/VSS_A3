/**
 * 05.11.2010
 * Wladimir Danilov
 * Jevgeni Zelenkov
 * 
 * Aufgabe 3.1
 * 
 * Die ausführbare Main-Klasse. main() erwartet ein Argument,
 * der die Anzahl der Instanzen angibt, die gestartet werden sollen.
 */

package helloworld;

import java.util.ArrayList;


public class Main {

	/**
	 * @param args Anzahl der zu startenden Instanzen.
	 */
	public static void main(String[] args) {
		int count = 0;
		ArrayList<Thread> greeters = new ArrayList<Thread>();
		if (args.length == 1) {
			count = Integer.parseInt(args[0]);
		}
		if (count > 0) {
			for (int i=1;i<=count;i++) {
				greeters.add(new Thread(new HelloWorlder("HelloWorlder" + String.valueOf(i))));
			}
		}
		
		for (Thread g: greeters) {
			g.start();
		}
	}

}
