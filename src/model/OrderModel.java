package model;

import java.sql.Date;

public class OrderModel {
	private int bestellingID, persoonID, adresID;

	private String status;

	private Date bestelDatum;
	private Date leverDatum;

	public int getBestellingID() { 
		return bestellingID; 
	}

	public int getPersoonID() {
		return persoonID; 
   	}

	public int getAdresID() { 
		return adresID; 
	}

	public String getStatus() { 
		return status; 
	}
	
	public Date getBestelDatum() { 
		return bestelDatum; 
	}

	public Date getLeverDatum() { 
		return leverDatum; 
	}
}