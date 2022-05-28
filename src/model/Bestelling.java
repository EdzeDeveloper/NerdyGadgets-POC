package model;

import java.sql.Date;
import java.util.ArrayList;

public class Bestelling {
	private int bestellingID, persoonID, leverAdresId;
	
	private String status;
  private Date bestelDatum, leverDatum;
	
  private ArrayList<Product> besteldeProducten;

	public Bestelling(){
    besteldeProducten = new ArrayList<>();
  }

  public ArrayList<Product> getBesteldeProducten() {
    return besteldeProducten;
  }

  public void setBesteldeProducten(ArrayList<Product> besteldeProducten) {
    this.besteldeProducten = besteldeProducten;
  }

  public Date getLeverDatum() {
    return leverDatum;
  }

  public void setLeverDatum(Date leverDatum) {
    this.leverDatum = leverDatum;
  }

  public Date getBestelDatum() {
    return bestelDatum;
  }

  public void setBestelDatum(Date bestelDatum) {
    this.bestelDatum = bestelDatum;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getLeverAdresId() {
    return leverAdresId;
  }

  public void setLeverAdresId(int leverAdresId) {
    this.leverAdresId = leverAdresId;
  }

  public int getPersoonID() {
    return persoonID;
  }

  public void setPersoonID(int persoonID) {
    this.persoonID = persoonID;
  }

  public int getBestellingID() {
    return bestellingID;
  }

  public void setBestellingID(int bestellingID) {
    this.bestellingID = bestellingID;
  }

  @Override
  public String toString() {
  return String.valueOf(bestellingID);
  }
	
}
