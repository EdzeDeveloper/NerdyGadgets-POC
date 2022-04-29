package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import model.Order;
import model.Product;


public class ReturnView extends JFrame{

	private Order order = new Order();

	private JLabel textLabel = new JLabel("Ordernummer:");
	private JTextField orderNumber  = new JTextField(10);
	private JButton searchButton = new JButton("Zoek bestelde producten");

	private JLabel listLabel  = new JLabel("Kies uw producten die geretourneerd moeten worden:");
	private JList list = new JList<Product>();

	private JButton nextButton = new JButton("Volgende");

	
	public ReturnView(){
		
		// Sets up the view and adds the components
		
		JPanel ReturnPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		
		ReturnPanel.add(textLabel);
		ReturnPanel.add(orderNumber);
		ReturnPanel.add(searchButton);
		
		ReturnPanel.add(listLabel);
		listLabel.setVisible(false);
		ReturnPanel.add(list);

		ReturnPanel.add(nextButton);
		nextButton.setVisible(false);
		
		this.add(ReturnPanel);
		
		// End of setting up the components --------
		
	}
	
	public int getOrderNumber(){
		
		return Integer.parseInt(orderNumber.getText());
		
	}

	public void setOrder(Order order) {
		this.order = order;

		//Map order products to a workable set/ array for our list. {"pName1", pName2, etc..}
		ArrayList<String> products = new ArrayList<String>();
		for (Product p : order.getBesteldeProducten()) {
			products.add(p.getProductNaam());
		}

		listLabel.setVisible(true);
		this.list.setListData(products.toArray());
		nextButton.setVisible(true);

		repaint();
	}

	
	public void setTextLabel(String text){
		
		textLabel.setText(text);
		
	}
	
	// If the searcheButton is clicked execute a method
	// in the Controller named actionPerformed
	
	public void addSearchButtonListener(ActionListener listenForSearchButton){
		
		searchButton.addActionListener(listenForSearchButton);
		
	}
	
	// Open a popup that contains the error message passed
	
	public void displayErrorMessage(String errorMessage){
		
		JOptionPane.showMessageDialog(this, errorMessage);
		
	}
	
}