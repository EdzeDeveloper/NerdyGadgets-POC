package model;


public class Adres {

    private int adresId;
    private String straatnaam;
    private int huisnummer;
    private String huisletter;
    private String postcode;
    private String woonplaats;
    private int X;
    private int Y;

    public Adres(){

    }

    public Adres(int adresId, String straatnaam, int huisnummer, String huisletter, String postcode, String woonplaats, int x, int y) {
        this.adresId = adresId;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.huisletter = huisletter;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        X = x;
        Y = y;
    }

    public int getAdresId() {
        return adresId;
    }

    public void setAdresId(int adresId) {
        this.adresId = adresId;
    }


    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }


    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }


    public String getHuisletter() {
        return huisletter;
    }

    public void setHuisletter(String huisletter) {
        this.huisletter = huisletter;
    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }


    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }


    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

}