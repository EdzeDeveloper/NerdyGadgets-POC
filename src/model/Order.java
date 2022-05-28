package model;

import java.sql.Date;
import java.util.ArrayList;

public class Order {
	private int bestellingID, persoonID, adresID;

	private String status;

	private Date bestelDatum;
	private Date leverDatum;

	private ArrayList<Product> besteldeProducten;

	public static final String STATUS_ORDERED = "Besteld";
	public static final String STATUS_SHIPPED = "Verzonden";
	public static final String STATUS_RECEIVED = "Ontvangen";
	public static final String STATUS_RETURN_REGISTERED = "Retour aangemeld";
	public static final String STATUS_RETURN_ACCEPTED = "retour geaccepteerd";

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

	public void setStatusToOrdered() {
		this.status = STATUS_ORDERED;
	}
	public void setStatusToShipped() {
		this.status = STATUS_SHIPPED;
	}
	public void setStatusToRecieved() {
		this.status = STATUS_RECEIVED;
	}
	public void setStatusToReturnRegistered() {
		this.status = STATUS_RETURN_REGISTERED;
	}
	public void setStatusToReturnAccepted() {
		this.status = STATUS_RETURN_ACCEPTED;
	}

	@Override
	public String toString() {
		return Integer.toString(bestellingID);
	}

  public void setStatus(String status) {
		this.status = status;
  }

  public void setBestelDatum(Date besteldatum) {
		this.bestelDatum = besteldatum;
  }

  public void setLeverDatum(Date leverdatum) {
		this.leverDatum = leverdatum;
  }

  public void setLeverAdresId(int adresID) {
		this.adresID = adresID;
  }
}