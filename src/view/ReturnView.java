package view;

import java.awt.event.ActionListener;

import javax.swing.*;

import model.Order;
import model.Product;


public class ReturnView extends JFrame{

	private Order order = new Order();

	private JLabel textLabel = new JLabel("Ordernummer:");
	private JTextField orderNumber  = new JTextField(10);
	private JButton searchButton = new JButton("Zoek bestelde producten");
	private JList list = new JList(order.getBesteldeProducten().toArray());

	
	public ReturnView(){
		
		// Sets up the view and adds the components
		
		JPanel ReturnPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		
		ReturnPanel.add(textLabel);
		ReturnPanel.add(orderNumber);
		ReturnPanel.add(searchButton);
		ReturnPanel.add(list);
		
		this.add(ReturnPanel);
		
		// End of setting up the components --------
		
	}
	
	public int getOrderNumber(){
		
		return Integer.parseInt(orderNumber.getText());
		
	}

	public void setOrder(Order order) {
		this.order = order;
		System.out.println(((Product) order.getBesteldeProducten().get(0)).getProductNaam());
		//@TO-DO: Add products to list so customer can choose between products to return.
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