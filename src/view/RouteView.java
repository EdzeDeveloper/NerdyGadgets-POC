package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.*;    
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Order;
import repository.AdresRepository;

public class RouteView extends JPanel{
	private JPanel routeView;
	private JPanel leftRouteView;
	private JPanel rightRouteView;
	private JSplitPane splitPane;
  private JLabel aantalBestellingenLabel;
  private JTextField aantalBestellingenTextField;
  private JButton calculateRouteButton;
  private DefaultTableModel routeDefaultTableModel;
  private JTable routJTable;

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
	
		//create table beforehand
		String colums[]={"BestellingID","Adres"};    
		routeDefaultTableModel = new DefaultTableModel(colums, 0);
		routJTable = new JTable(routeDefaultTableModel);
		add(new JScrollPane(routJTable));

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


  public void createRouteList(ArrayList<Order> routeList) throws SQLException {
    System.out.print(routeList);
    JLabel ListTitle = new JLabel("Beste route op basis van Nearest Neighbor");
		rightRouteView.add(ListTitle);
    AdresRepository adresRepo = new AdresRepository();
		for (int i = 0; i < routeList.size(); i++)   
		{
			// insert data into tableModel as rows
			Object[] data = {routeList.get(i).getBestellingID(), adresRepo.find(routeList.get(i).getAdresID()).getStraatnaam() + " " + adresRepo.find(routeList.get(i).getAdresID()).getPostcode() + adresRepo.find(routeList.get(i).getAdresID()).getWoonplaats()};
			routeDefaultTableModel.addRow(data);
		}	
    // add list to Right Jpanel
		rightRouteView.add(routJTable);

		// accept.setVisible(true);
	
		rightRouteView.revalidate();
  }
}


