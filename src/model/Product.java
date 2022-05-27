package model;

public class Product {
    private int productID;

    private String productNaam, prijs;

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
}