package producer_consumer;

import java.util.ArrayList;

public class Main extends Thread {
private boolean started = false;
private ArrayList<Long> dataMonitor = new ArrayList<Long>();
private int producersCount;
private int consumersCount;
private long t;

private ArrayList<Pint> counter = new ArrayList<Pint>();
private ArrayList<Thread> barkeepers = new ArrayList<Thread>();
private ArrayList<Thread> consumers = new ArrayList<Thread>();

	private Main(int producers, int consumers) {
		this.producersCount = producers;
		this.consumersCount = consumers;
		this.t = System.currentTimeMillis();
	}

	/**
	 * @param 1. Argument: Anzahl der Barkeeper
	 * 2. Argument: Anzahl der Barbesucher
	 */
	public static void main(String[] args) {
		if (args.length != 2)
			throw new RuntimeException("Illegale Anzahl der Argumente");
		int countBarKeepers = Integer.parseInt(args[0]);
		int countDrinkers = Integer.parseInt(args[1]);
		
		Main m = new Main(countBarKeepers, countDrinkers);
		m.start();
	}
	
	/**
	 * Erzeugt und startet die Erzeuger und die Verbraucher.
	 */
	private void prepare() {
		
		dataMonitor.add(0L);
		dataMonitor.add(0L);
		
		for (int i=1;i<=producersCount;i++) {
			printMess("Main erzeugt Barkeeper" + String.valueOf(i) + ".");
			barkeepers.add(new Producer("Barkeeper" + String.valueOf(i), counter, dataMonitor));
			printMess("Barkeeper" + String.valueOf(i) + " wurde erzeugt.");
		}
		
		for (int i=1;i<=consumersCount;i++) {
			printMess("Main erzeugt Säufer" + String.valueOf(i) + ".");
			consumers.add(new Consumer("Trinker" + String.valueOf(i), counter, dataMonitor));
			printMess("Säufer" + String.valueOf(i) + " wurde erzeugt.");
		}
		
		for (int i=0;i<barkeepers.size();i++) {
			printMess("Barkeeper" + (i+1) + " wird gestartet.");
//			barkeepers.get(i).setDaemon(true);
			barkeepers.get(i).start();
			printMess("Barkeeper" + (i+1) + " wurde gestartet.");
		}
		
		for (int i=0;i<consumers.size();i++) {
			printMess("Säufer" + (i+1) + " wird gestartet.");
//			consumers.get(i).setDaemon(true);
			consumers.get(i).start();
			printMess("Säufer" + (i+1) + " wurde gestartet.");
		}
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			if (!started) {
				started = true;
				prepare();
			}
			Thread.yield();
			if (System.currentTimeMillis() - 1500 > t) {
				synchronized (dataMonitor) {
					printMess(dataMonitor.get(0) + "Biere produziert.");
					printMess(dataMonitor.get(1) + "Biere getrunken.");
				}
				try {
					this.interrupt();
				} catch (Throwable e) {}
				System.exit(1);
			}
		}
	}
	
	
	private static void printMess(String message) {
		System.out.println(message);
		System.out.flush();
	}

}
