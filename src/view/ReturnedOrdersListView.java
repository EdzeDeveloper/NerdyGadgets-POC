package view;

import javax.swing.*;
import model.Return;
import view.components.ListPanel;
import java.util.ArrayList;
import java.awt.event.ActionListener;

public class ReturnedOrdersListView extends JFrame{
	private ArrayList<Return> returnedOrders;
	private ArrayList<String> testStringArrayList = new ArrayList();
	private ListPanel myList;
	public ReturnedOrdersListView(){
			try {
				testStringArrayList.add("Data" );
				testStringArrayList.add("Wil ik de index of het ID?" );
				testStringArrayList.add("idex " );
				testStringArrayList.add("spoepls " );
				testStringArrayList.add("henk pot vis " );
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			} 

			JFrame frame = new JFrame( "Create list for use" );
			MainMenu menu = new MainMenu(frame);
			// add list data functie
			myList = new ListPanel(testStringArrayList, "bekijk geretourneerde bestelling");

			JPanel listPanel = new JPanel();
			listPanel.add( myList );
			
			frame.getContentPane().add( listPanel );
			frame.setSize(800, 800);
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			frame.pack();
			frame.setVisible(true);
		}

		public void setReturnedOrders(ArrayList<Return> returnedOrders) {
			this.returnedOrders = returnedOrders;
		}
		// set buttonListener in jpanel vanuit controller doorsluisen.
		public void addGoToButtonListener(ActionListener listenForSearchButton){
			myList.addGoToButtonListener(listenForSearchButton);
		}

		public Object getSelectedProduct() {
			return myList.getSelectedProduct();
		}
}


