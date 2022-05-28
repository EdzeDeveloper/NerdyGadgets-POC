package view;

import java.awt.Color;
// import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;    
import javax.swing.*;

public class RouteView extends JPanel{
	private JPanel routeView;
	private JPanel leftRouteView;
	private JPanel rightRouteView;
	private JSplitPane splitPane;
  private JLabel aantalBestellingenLabel;
  private JTextField aantalBestellingenTextField;
  private JButton calculateRouteButton;

	public RouteView() {
		// new panels
		routeView = new JPanel();
		leftRouteView = new JPanel();
		rightRouteView = new JPanel();

    //new labels
    aantalBestellingenLabel = new JLabel("Aantal bezorgingen :");

    //new textfields
    aantalBestellingenTextField = new JTextField(6);

		//set layout
		routeView.setLayout(new BoxLayout(routeView, BoxLayout.X_AXIS));
	
		// //create table beforehand
		// String colums[]={"ProductID","Naam","Prijs"};    
		// tableModel = new DefaultTableModel(colums, 0);
		// table = new JTable(tableModel);
		// add(new JScrollPane(table));

		// new splitpane
		splitPane = new JSplitPane();

    //add create items for left view
    calculateRouteButton = new JButton("Bereken route");
    leftRouteView.add(aantalBestellingenLabel);
    leftRouteView.add(aantalBestellingenTextField);
    leftRouteView.add(calculateRouteButton);

    splitPane.setLeftComponent(leftRouteView);
		splitPane.setRightComponent(rightRouteView);
		// splitPane.setRightComponent(new JScrollPane(returnList));

	
		//add items to views6
		  calculateRouteButton.setVisible(true);
      routeView.add(splitPane);
      routeView.revalidate();
	}


	public void setCalculateRouteListener(ActionListener actionListener) {
		calculateRouteButton.addActionListener(actionListener);
	}

	// public void createResultView(Order bestelling) {
	// 	JLabel bestellingID = new JLabel("RetourID = " + Integer.toString(bestelling.getBestellingID()));
	// 	resultViewPanel.add(bestellingID);

	// 	for (int i = 0; i < bestelling.getBesteldeProducten().size(); i++)   
	// 	{
	// 		// insert data into tableModel as rows
	// 		Object[] data = {bestelling.getBesteldeProducten().get(i).getProductID(), bestelling.getBesteldeProducten().get(i).getProductNaam(), bestelling.getBesteldeProducten().get(i).getPrijs()};
	// 		tableModel.addRow(data);
	// 	}	
	// 	resultViewPanel.add(table);
	// 	resultViewPanel.add(accept);
	// 	resultViewPanel.add(decline);
	// 	resultViewPanel.add(retourNotRecieved);
	// 	accept.setVisible(true);
	// 	decline.setVisible(true);
	// 	retourNotRecieved.setVisible(true);
	// 	resultViewPanel.revalidate();
	// }

	// public void emptyResultViewPanel() {
	// 	table.removeAll();
	// 	tableModel.setRowCount(0);
	// 	accept.setVisible(false);
	// 	decline.setVisible(false);
	// 	retourNotRecieved.setVisible(false);
	// 	resultViewPanel.removeAll();
	// 	resultViewPanel.revalidate();
	// }

	public void displayErrorMessage(String errorMessage){
		
		JOptionPane.showMessageDialog(this, errorMessage);
		
	}

  public JPanel getRouteViewPanel() {
    return routeView;
  }

  public int getAmountOfOrderNumber(){
		return Integer.parseInt(aantalBestellingenTextField.getText());
	}
}


