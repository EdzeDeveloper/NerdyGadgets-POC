package model;


public class Adres {

  private long adresId;
  private String straatnaam;
  private long huisnummer;
  private String huisletter;
  private String postcode;
  private String woonplaats;
  private long X;
  private long Y;


  public long getAdresId() {
    return adresId;
  }

  public void setAdresId(long adresId) {
    this.adresId = adresId;
  }


  public String getStraatnaam() {
    return straatnaam;
  }

  public void setStraatnaam(String straatnaam) {
    this.straatnaam = straatnaam;
  }


  public long getHuisnummer() {
    return huisnummer;
  }

  public void setHuisnummer(long huisnummer) {
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


  public long getX() {
    return X;
  }

  public void setX(long X) {
    this.X = X;
  }


  public long getY() {
    return Y;
  }

  public void setY(long Y) {
    this.Y = Y;
  }

}
