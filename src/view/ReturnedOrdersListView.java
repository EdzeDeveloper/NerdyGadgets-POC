package view;

import javax.swing.*;
import view.components.ListPanel;
import java.util.ArrayList;
import java.awt.event.ActionListener;

public class ReturnedOrdersListView extends JPanel{
	private ArrayList returnedOrders;
	// private static ArrayList<String> testStringArrayList = new ArrayList();
	private ListPanel myList;
	private JPanel listPanel;

	public ReturnedOrdersListView() {
		listPanel = new JPanel();
		}
		
		public void addReturnedOrdersToList(ArrayList arrayList) {
			// add list data functie
			returnedOrders = arrayList;
			myList = new ListPanel(returnedOrders, "bekijk geretourneerde bestelling");
			listPanel.add( myList );
		}
		public JPanel getListPanel() {
			// add list data functie
			return listPanel;
		}

		// set buttonListener in jpanel vanuit controller doorsluisen.
		public void addGoToButtonListener(ActionListener listenForSearchButton){
			myList.addGoToButtonListener(listenForSearchButton);
		}

		public Object getSelectedProduct() {
			return myList.getSelectedProduct();
		}
}


