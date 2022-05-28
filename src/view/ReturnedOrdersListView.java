package view;

import java.awt.Color;
import java.util.ArrayList;

import java.awt.*;    
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Order;
import model.Product;
import model.Return;

public class ReturnedOrdersListView extends JPanel{
	private JList returnList;
	private JPanel retourOrdersPanel;
	private JPanel resultViewPanel;
	private JSplitPane splitPane;
	private int selectedBestellingID;
	private JButton accept;
	private JButton decline;
	private JButton retourNotRecieved;
	private JTable table;
	private DefaultTableModel tableModel;

	// setting all components to be used
	public ReturnedOrdersListView() {
		// new panels
		retourOrdersPanel = new JPanel();
		resultViewPanel = new JPanel();
		resultViewPanel.setPreferredSize(new Dimension(350, 350));
		resultViewPanel.setBorder(BorderFactory.createLineBorder(Color.pink));

		//set layout
		retourOrdersPanel.setLayout(new BoxLayout(retourOrdersPanel, BoxLayout.X_AXIS));
		resultViewPanel.setLayout(new BoxLayout(resultViewPanel, BoxLayout.Y_AXIS));
	
		//create table beforehand
		String colums[]={"ProductID","Naam","Prijs"};    
		tableModel = new DefaultTableModel(colums, 0);
		table = new JTable(tableModel);
		add(new JScrollPane(table));

		// new splitpane
		splitPane = new JSplitPane();

		// new buttons and set them 
		accept = new JButton("Retour in goede orde ontvangen");
		decline = new JButton("Retour afwijzen");
		retourNotRecieved = new JButton("Retour niet ontvangen");
		accept.setVisible(false);
		decline.setVisible(false);
		retourNotRecieved.setVisible(false);
	}

	// wanneer een list binnen komt
	public void setListModel(DefaultListModel<Return> defaultListmodel) {
		returnList = new JList(defaultListmodel);

		splitPane.setLeftComponent(new JScrollPane(returnList));
		splitPane.setRightComponent(resultViewPanel);

		retourOrdersPanel.add(splitPane);
		retourOrdersPanel.revalidate();
	}

	public JPanel getListPanel() {
		return retourOrdersPanel;
	}

	public Object getSelectedValue() {
		return returnList.getSelectedValue();
	}

	public int getSelectedBestellingID() {
		return selectedBestellingID;
	}
	public JList getReturnList() {
		return returnList;
	}

	public void addDeclineListener(ActionListener actionListener) {
		decline.addActionListener(actionListener);
		resultViewPanel.add(decline);
	}

	public void addAcceptListener(ActionListener actionListener) {
		accept.addActionListener(actionListener);
		resultViewPanel.add(accept);
	}
	public void addRetourRecievedListener(ActionListener actionListener) {
		retourNotRecieved.addActionListener(actionListener);
		resultViewPanel.add(retourNotRecieved);
	}

	public void createResultView(Order bestelling) {
		JLabel bestellingID = new JLabel("RetourID = " + Integer.toString(bestelling.getBestellingID()));
		resultViewPanel.add(bestellingID);

		for (int i = 0; i < bestelling.getBesteldeProducten().size(); i++)   
		{
			// insert data into tableModel as rows
			Object[] data = {bestelling.getBesteldeProducten().get(i).getProductID(), bestelling.getBesteldeProducten().get(i).getProductNaam(), bestelling.getBesteldeProducten().get(i).getPrijs()};
			tableModel.addRow(data);
		}	
		resultViewPanel.add(table);
		resultViewPanel.add(accept);
		resultViewPanel.add(decline);
		resultViewPanel.add(retourNotRecieved);
		accept.setVisible(true);
		decline.setVisible(true);
		retourNotRecieved.setVisible(true);
		resultViewPanel.revalidate();
	}

	public void emptyResultViewPanel() {
		table.removeAll();
		tableModel.setRowCount(0);
		accept.setVisible(false);
		decline.setVisible(false);
		retourNotRecieved.setVisible(false);
		resultViewPanel.removeAll();
		resultViewPanel.revalidate();
	}

	public void setReturnListListener(ListSelectionListener vieuwReturnedItemsEventListener) {
		returnList.getSelectionModel().addListSelectionListener(vieuwReturnedItemsEventListener);
	}

	public void displayErrorMessage(String errorMessage){
		
		JOptionPane.showMessageDialog(this, errorMessage);
		
	}
}


