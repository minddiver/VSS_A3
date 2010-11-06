package producer_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Producer extends Thread {
	private List<Pint> counter;
	private String name;
	private ArrayList<Long> dataMonitor;
	private long t;

	public Producer(String name, List<Pint> counter, ArrayList<Long> dataMonitor) {
		this.name = name;
		this.counter = counter;
		this.dataMonitor = dataMonitor;
		this.t = System.currentTimeMillis();
	}
	
	private void printMess(String message) {
		System.out.println(message);
//		System.out.flush();
	}
	
	public void fillBeer() {
		Random rndBeers = new Random();
		int beers;
		beers = rndBeers.nextInt(10) + 1;	// 1 bis 5 Bier
//		beers = 1;
//		printMess(name + " zapft jetzt " + beers + " Biere.");
		for (int i=0;i<beers;i++) {
			counter.add(new Pint(5,"Rüps!"));
		}
		dataMonitor.set(0, dataMonitor.get(0)+beers);
		printMess(name + " hat " + beers + " Biere gezapft. " + counter.size() + " Biere auf dem Tresen.");
	}

	@Override
	public void run()  {
		int beers = 0;
		while (!Thread.interrupted()) {
//			if (System.currentTimeMillis() - 1000 > t) {
//				this.interrupt();
//			}
//			printMess(name + " wartet bis die Kunden ihn das Bier zapfen lassen.");
			synchronized (dataMonitor) {
				beers = counter.size();
				if (beers < 1) {
					fillBeer();
					dataMonitor.notifyAll();
				}
				else {
					try {
						dataMonitor.wait();
					} catch (Exception e) {this.interrupt();}
//					Thread.yield();
				}
//				monitor.notifyAll();
			}
			
//			try { Thread.sleep(10); }
//			catch (Exception e) { }
		}
	}
	
	
}
