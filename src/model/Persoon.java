package model;

public class Persoon {
    private int persoonID, adresID;

    private String voornaam, achternaam, email;

    public Persoon(int persoonID, int adresID, String voornaam, String achternaam, String email) {
        this.persoonID = persoonID;
        this.adresID = adresID;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
    }

    public Persoon(){

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

    public void setAdresID(int adresID) {
        this.adresID = adresID;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}