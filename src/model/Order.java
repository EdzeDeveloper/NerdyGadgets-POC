package model;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
	private int bestellingID, persoonID, adresID;

	private String status;

	private Date bestelDatum;
	private Date leverDatum;

	private ArrayList<Product> besteldeProducten;

	public Order() {
		this.besteldeProducten = new ArrayList<Product>(); // initiate with empty list.
	}

	public Order(int bestellingID, int persoonID, int adresID, String status, Date bestelDatum, Date leverDatum) {
		this.bestellingID = bestellingID;
		this.persoonID = persoonID;
		this.adresID = adresID;

		this.status = status;

		this.bestelDatum = bestelDatum;
		this.leverDatum = leverDatum;
	}

	public int getBestellingID() { 
		return bestellingID; 
	}

	public void setBestellingID(int bestellingID) { 
		 this.bestellingID = bestellingID; 
	}

	public int getPersoonID() {
		return persoonID; 
   	}

	public void setPersoonID(int persoonID) { 
		this.persoonID = persoonID; 
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

	public ArrayList<Product> getBesteldeProducten() {
		return besteldeProducten;
	}

	public void setBesteldeProducten(ArrayList<Product> besteldeProducten) {
		this.besteldeProducten = besteldeProducten;
	}

	@Override
	public String toString() {
		return Integer.toString(bestellingID);
	}
}