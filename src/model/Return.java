package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Return {
	private int bestellingID, retourID;
	
	private String reden;

	//returnedProducts<productID, quantity>
	private Map<Integer, Integer> returnedProducts = new HashMap<>();

	public Return() {
		this.returnedProducts = new HashMap<>(); // initiate with empty list.
	}

	public Return(int bestellingID, String reden, int retourID) {
		this.bestellingID = bestellingID;
		this.reden = reden;
		this.retourID = retourID;
	}

	//Method overloading to accept a Return without ID. We sue this in create statements.
	public Return(int bestellingID, String reden) {
		this.bestellingID = bestellingID;
		this.reden = reden;
	}

	public void setRetourID(int retourID) { 
		this.retourID = retourID; 
	}

	public int getRetourID() {
		return retourID;
	}

	public void setReden(String reden) {
		this.reden = reden; 
   	}

	public String getReden() {
		return reden;
	}

	public void setBestellingID(int bestellingID) { 
		this.bestellingID = bestellingID; 
	}

	public int getBestellingID() { 
		return bestellingID; 
	}

	public Map<Integer, Integer> getReturnedProducts() {
		return returnedProducts;
	}

	public void setReturnedProducts(Map<Integer, Integer> returnedProducts) {
		this.returnedProducts = returnedProducts;
	}

	public void addReturnedProduct(int productID, int quantity) {
		returnedProducts.put(productID, quantity);
	}

	@Override
	public String toString() {
		return Integer.toString(retourID);
	}
}