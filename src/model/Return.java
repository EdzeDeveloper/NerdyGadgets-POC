package model;

public class Return {
	private int bestellingID, retourID;
	
	private String reden;

	public int getRetourID() { 
		return retourID; 
	}

	public String getReden() {
		return reden; 
   	}

	public int getBestellingID() { 
		return bestellingID; 
	}

	public void setRetourID(int returnID) { 
		this.retourID = returnID; 
	}

	public void setReden(String reden) {
		this.reden = reden;
   	}

	public void setBestellingID(int bestellingID) { 
		this.bestellingID = bestellingID; 
	}
	
}