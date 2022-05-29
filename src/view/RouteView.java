package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.*;    
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Order;
import model.Route;
import repository.AdresRepository;

public class RouteView extends JPanel{
	private JPanel routeView;
	private JPanel leftRouteView;
	private JPanel rightRouteView;
	private JSplitPane splitPane;
  private JLabel aantalBestellingenLabel;
  private JTextField aantalBestellingenTextField;
  private JButton calculateRouteButton;
  private JButton calculateRouteButtonFor2Opt;
  private DefaultTableModel routeDefaultTableModel;
  private DefaultTableModel routeDefaultTableModelFor2Opt;
  private JTable routeJTable;
  private JTable tableFor2Opt;
	private ArrayList<Order> routeListFor2Opt;
	private Route route;

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
		rightRouteView.setLayout(new BoxLayout(rightRouteView, BoxLayout.Y_AXIS));
	
		//create table beforehand
		String colums[]={"BestellingID","Adres"};    
		routeDefaultTableModel = new DefaultTableModel(colums, 0);
		routeDefaultTableModelFor2Opt = new DefaultTableModel(colums, 0);
		routeJTable = new JTable(routeDefaultTableModel);
		tableFor2Opt = new JTable(routeDefaultTableModelFor2Opt);
		

		// new splitpane
		splitPane = new JSplitPane();

    //add create items for left view

    calculateRouteButton = new JButton("Bereken route");
    calculateRouteButtonFor2Opt = new JButton("Bereken 2-opt");
    leftRouteView.add(aantalBestellingenLabel);
    leftRouteView.add(aantalBestellingenTextField);
    leftRouteView.add(calculateRouteButton);
    leftRouteView.add(calculateRouteButtonFor2Opt);

    splitPane.setLeftComponent(leftRouteView);
		splitPane.setRightComponent(rightRouteView);
		// splitPane.setRightComponent(new JScrollPane(returnList));

	
		//add items to views6
		  calculateRouteButtonFor2Opt.setVisible(false);
		  calculateRouteButton.setVisible(true);
      routeView.add(splitPane);
      routeView.revalidate();
	}


	public void setCalculateRouteListener(ActionListener actionListener) {
		calculateRouteButton.addActionListener(actionListener);
	}
	public void setCalculateFor2OptRouteListener(ActionListener actionListener) {
		calculateRouteButtonFor2Opt.addActionListener(actionListener);
	}

	public void emptyResult() {
		resetTable();
		rightRouteView.removeAll();
		routeView.revalidate();
	}
	public void resetTable() {
		routeJTable.removeAll();
		tableFor2Opt.removeAll();
		routeDefaultTableModel.setRowCount(0);
		routeDefaultTableModelFor2Opt.setRowCount(0);
		rightRouteView.removeAll();
		rightRouteView.revalidate();
		routeView.revalidate();
	}

	public void displayErrorMessage(String errorMessage){
		
		JOptionPane.showMessageDialog(this, errorMessage);
		
	}

  public JPanel getRouteViewPanel() {
    return routeView;
  }

  public JTextField getAmountOfOrderNumber(){
		return aantalBestellingenTextField;
	}


  public void createRouteList(ArrayList<Order> routeList, Route route) throws SQLException {
		resetTable();
		routeListFor2Opt = routeList;
		this.route = route;
		JLabel aantalKMJLabel = new JLabel("Completed Nearest Neighbor, route is: " + Math.round(route.getAantalkm()/1000) + " km");
    AdresRepository adresRepo = new AdresRepository();
		for (int i = 0; i < routeList.size(); i++)   
		{
			// insert data into tableModel as rows
			Object[] data = {routeList.get(i).getBestellingID(), adresRepo.find(routeList.get(i).getAdresID()).getStraatnaam() + " " + adresRepo.find(routeList.get(i).getAdresID()).getHuisnummer() + ", " + adresRepo.find(routeList.get(i).getAdresID()).getPostcode() + ", " + adresRepo.find(routeList.get(i).getAdresID()).getWoonplaats()};
			routeDefaultTableModel.addRow(data);
		}	
    // add list to Right Jpanel
		rightRouteView.add(aantalKMJLabel);
		rightRouteView.add(new JScrollPane(routeJTable));

		// accept.setVisible(true);
		calculateRouteButtonFor2Opt.setVisible(true);
		rightRouteView.revalidate();
  }


	public void create2optList(ArrayList calculateRouteNearestNeigbor, Route route) throws SQLException {
		JLabel aantalKMJLabel = new JLabel("Completed 2-Opt, route is: " + Math.round(route.getAantalkm()/1000) + " km");
    AdresRepository adresRepo = new AdresRepository();
		for (int i = 0; i < routeListFor2Opt.size(); i++)   
		{
			// insert data into tableModel as rows
			Object[] data = {routeListFor2Opt.get(i).getBestellingID(), adresRepo.find(routeListFor2Opt.get(i).getAdresID()).getStraatnaam() + " " + adresRepo.find(routeListFor2Opt.get(i).getAdresID()).getHuisnummer() + ", " + adresRepo.find(routeListFor2Opt.get(i).getAdresID()).getPostcode() + ", " + adresRepo.find(routeListFor2Opt.get(i).getAdresID()).getWoonplaats()};
			routeDefaultTableModelFor2Opt.addRow(data);
		}	
    // add list to Right Jpanel
		rightRouteView.add(aantalKMJLabel);
		rightRouteView.add(new JScrollPane(tableFor2Opt));

		// accept.setVisible(true);
		calculateRouteButtonFor2Opt.setVisible(false);
		rightRouteView.revalidate();
	}


  public ArrayList getCurrentRoute() {
    return routeListFor2Opt;
  }

	public Route getRoute() {
		return route;
	}
}


