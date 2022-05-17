package model;


public class Route {

  private long routeId;
  private long aantalkm;
  private long graphID;
  private String aantalsteden;
  private graphModel graphModel;
  private int[] deliveryOrder;

  public Route(int numberOfNodes){
    graphModel = new graphModel(numberOfNodes);
  }

  public long getRouteId() {
    return routeId;
  }

  public void setRouteId(long routeId) {
    this.routeId = routeId;
  }

  public long getAantalkm() {
    return aantalkm;
  }

  public void setAantalkm(long aantalkm) {
    this.aantalkm = aantalkm;
  }

  public String getAantalsteden() {
    return aantalsteden;
  }

  public void setAantalsteden(String aantalsteden) {
    this.aantalsteden = aantalsteden;
  }

  public long getGraphID() {
    return graphID;
  }

  public void setGraphID(long graphID) {
    this.graphID = graphID;
  }

  public model.graphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(model.graphModel graphModel) {
    this.graphModel = graphModel;
  }

  public int[] getDeliveryOrder() {
    return deliveryOrder;
  }

  public void setDeliveryOrder(int[] deliveryOrder) {
    this.deliveryOrder = deliveryOrder;
  }
}
