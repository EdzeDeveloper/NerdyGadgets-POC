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

    public int getAdresID() {
        return adresID;
    }
}
