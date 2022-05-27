package model;

public class Return {
	private int bestellingID, retourID;
	
	private String reden;
	
	public Return(){}

	public Return(int bestellingID, String reden, int retourID) {
		this.bestellingID = bestellingID;
		this.reden = reden;
		this.retourID = retourID;
	}

	public void setRetourID(int retourID) { 
		this.retourID = retourID; 
	}

	public void setReden(String reden) {
		this.reden = reden; 
   	}

	public void setBestellingID(int bestellingID) { 
		this.bestellingID = bestellingID; 
	}

	public int getRetourID() { 
		return retourID; 
	}

	public String getReden() {
		return reden; 
   	}

	public int getBestellingID() { 
		return bestellingID; 
	}

	@Override
	public String toString() { 
			return "Retour bestelling : " + Integer.toString(bestellingID); 
	}    
}