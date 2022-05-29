package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.Order;
import model.Product;

public class ReturnProductsView extends JPanel{

	private Order order = new Order();

	private JPanel returnPanel;
	private JPanel productsGridPanel;

	private JLabel productsLabel;
	private JLabel textLabel = new JLabel("Ordernummer:");
	private JTextField orderNumber  = new JTextField(10);
	private JButton searchButton = new JButton("Zoek bestelde producten");

	private JButton nextButton = new JButton("Retour aanmelden");

	private Map<Integer, JTextField> productsToReturn = new HashMap<>();

	private JTextArea reasonArea = new JTextArea("Reden van retour", 6, 1);

	public ReturnProductsView(){
		
		// Sets up the view and adds the components

		productsGridPanel = new JPanel();
		productsLabel = new JLabel("Geef aantal producten aan");

		
		returnPanel = new JPanel();
		returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.X_AXIS));

		returnPanel.add(textLabel);
		returnPanel.add(orderNumber);
		returnPanel.add(searchButton);

		returnPanel.add(nextButton);
		nextButton.setVisible(false);
		
		this.add(returnPanel);
		
	}

	public String getReason(){
		return reasonArea.getText();
	}
	
	public int getOrderNumber(){
		return Integer.parseInt(orderNumber.getText());
	}

	public Map<Integer, JTextField> getProductsToReturn(){
		return productsToReturn;
	}

	public void resetProductsToReturn() {
		productsToReturn.clear();
	}

	public void setOrder(Order order) {
		
		this.order = order;

		productsGridPanel.setLayout(new BoxLayout(productsGridPanel, BoxLayout.Y_AXIS));

		productsLabel.setFont(productsLabel.getFont().deriveFont(Font.BOLD, 14f));

		productsGridPanel.add(productsLabel);
		productsGridPanel.add(Box.createVerticalStrut(20));

		emptyReturnPanel();

		// With 2 columns for label and input.
		for (Product p : order.getBesteldeProducten()) {
			productsGridPanel.add(new JLabel(p.getProductNaam()));

			productsToReturn.put(p.getProductID(), new JTextField("0", 1));
			productsGridPanel.add( productsToReturn.get( p.getProductID() ) );

			productsGridPanel.add(Box.createVerticalStrut(10));
		}

		productsGridPanel.add( reasonArea );

		//Add the panel to our frame.
		this.add(new JSeparator());
		this.add(productsGridPanel);

		nextButton.setVisible(true);

		repaint();
	}

	public void emptyReturnPanel() {
		productsGridPanel.removeAll();
		returnPanel.revalidate();
	}

	
	public void setTextLabel(String text){
		textLabel.setText(text);
	}
	
	// If the searcheButton is clicked execute a method
	// in the Controller named actionPerformed
	public void addSearchButtonListener(ActionListener listenForSearchButton){
		searchButton.addActionListener(listenForSearchButton);
	}

	public void addNextListener(ActionListener listenForNextButton){
		nextButton.addActionListener(listenForNextButton);
	}
	
	// Open a popup that contains the error message passed
	
	public void displayErrorMessage(String errorMessage){
		
		JOptionPane.showMessageDialog(this, errorMessage);
		
	}
	
}