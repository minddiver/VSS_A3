package producer_consumer;

import java.util.ArrayList;
import java.util.List;


public class Consumer extends Thread {
	private String name;
	private List<Pint> counter;
	private Pint pint;
	private ArrayList<Long> dataMonitor;
	private long t;
	
	
	public Consumer(String name, List<Pint> counter, ArrayList<Long> dataMonitor) {
		this.name = name;
		this.counter = counter;
		this.pint = null;
		this.dataMonitor = dataMonitor;
		this.t = System.currentTimeMillis();
//		this.pMonitor = pMonitor;
//		this.cMonitor = cMonitor;
	}
	
	private void printMess(String message) {
		System.out.println(message);
//		System.out.flush();
	}

	@Override
	public void run() {
		
		while (!Thread.interrupted()) {
//			if (System.currentTimeMillis() - 1000 > t) {
//				this.interrupt();
//			}
			// Prüfen ob das Bier nicht leer ist
			if (pint == null || pint.getSips() < 1) {
				if (pint != null) {
//					printMess(name + ": " + pint.getResponse());
				}
//				printMess(name + " wartet in der Schlange vor dem Tresen auf ein neues Bier.");
				// Versuche Zugriff auf den Tresen zu bekommen
				synchronized (dataMonitor) {
					// Prüfen, ob der Tresen nicht leer ist
					if (counter.size() > 0) {
						// Das Bier nehmen
						pint = counter.get(0);
						counter.remove(0);
						dataMonitor.set(1, dataMonitor.get(1)+1);
						printMess(name + " hat ein neues Bier gekriegt. (" + counter.size() + " Biere übrig.) ");
					}
					else {
						printMess(name + " ärgert sich, dass der Tresen leer ist.");
						try {
							dataMonitor.notifyAll();
							dataMonitor.wait();
						} catch (Exception e) {this.interrupt();}
					}
				}
			}
			else {
				// Bier trinken
				pint.sip();
//				Thread.yield();
			}
			
			try {
				Thread.sleep(10);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
