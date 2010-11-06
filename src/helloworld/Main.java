package helloworld;

import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 0;
		ArrayList<HelloWorlder> greeters = new ArrayList<HelloWorlder>();
		// TODO Auto-generated method stub
		if (args.length == 1) {
			count = Integer.parseInt(args[0]);
		}
		if (count > 0) {
			for (int i=1;i<=count;i++) {
				greeters.add(new HelloWorlder("Greeter" + String.valueOf(i)));
			}
		}
		
		for (HelloWorlder g: greeters) {
			g.run();
		}
	}

}
