package model;

import java.sql.SQLException;

public class Product {
	private int productID;

	private String productNaam;
	private String prijs;

	public Product() {
	}

	public String getPrijs() {
		return prijs;
	}

	public void setPrijs(String prijs) {
		this.prijs = prijs;
	}

	public Product(int productID, String productNaam) {
		this.productID = productID;
		this.productNaam = productNaam;
	}

	public Product(int productID, String productNaam, String prijs) {
		this.productID = productID;
		this.productNaam = productNaam;
		this.prijs = prijs;
	}

	public int getProductID() { 
		return productID; 
	}

	public void setProductID(int productID) { 
		 this.productID = productID; 
	}

	public String getProductNaam() { 
		return productNaam; 
	}

	public String setProductNaam(String productNaam) { 
		return this.productNaam = productNaam; 
	}

	@Override
	public String toString() {
		return productNaam;
	}
}