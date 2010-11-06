package producer_consumer;

public class Pint {
	private String response;
	private int sips;
	
	public Pint(int sips, String response) {
		this.sips = sips;
		this.response = response;
	}
	
	public String getResponse() {
		return new String(this.response);
	}
	
	public int getSips() {
		return this.sips;
	}
	
	public void sip() {
		if (sips > 0)
			sips--;
		else
			System.out.println("Oops, das Bier ist leer!");
	}
}
