package model;

public class Product {
	private int productID;

	private String productNaam;

	public Product() {
	}

	public Product(int productID, String productNaam) {
		this.productID = productID;
		this.productNaam = productNaam;
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