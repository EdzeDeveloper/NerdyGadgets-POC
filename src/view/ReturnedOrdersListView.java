package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSplitPane;

import model.Product;
import model.Return;

public class ReturnedOrdersListView extends JPanel{
	private JList returnList;
	private JPanel productPanel;
	private JPanel retourOrdersPanel;
	private JPanel resultViewPanel;
  private JLabel returnLabel;
	private JPanel productNameLabel;
	private JSplitPane splitPane;
	private int selectedBestellingID;
	private JButton accept;
	private JButton decline;

	// setting all components to be used
	public ReturnedOrdersListView() {
		retourOrdersPanel = new JPanel();
		resultViewPanel = new JPanel();
		retourOrdersPanel.setLayout(new BoxLayout(retourOrdersPanel, BoxLayout.X_AXIS));
		resultViewPanel.setLayout(new BoxLayout(resultViewPanel, BoxLayout.Y_AXIS));
		resultViewPanel.setBackground(Color.red);
		productPanel = new JPanel();
		productNameLabel = new JPanel();

		returnLabel = new JLabel();
		splitPane = new JSplitPane();
		accept = new JButton("Accept");
		decline = new JButton("Decline");
	}


	// wanneer een list binnen komt
	public void setListModel(DefaultListModel<Return> defaultListmodel) {
		returnList = new JList(defaultListmodel);

		splitPane.setLeftComponent(new JScrollPane(returnList));
		resultViewPanel.add(returnLabel);
		splitPane.setRightComponent(resultViewPanel);

		retourOrdersPanel.add(splitPane);
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
	public JLabel getReturnListLabel() {
		return returnLabel;
	}
	
	public JButton getDecline() {
		return decline;
	}

	public void addDecline() {
		resultViewPanel.add(decline);
	}
	
	public void addDeclineListener(ActionListener actionListener) {
		decline.addActionListener(actionListener);
		resultViewPanel.add(decline);
	}

	public JButton getAccept() {
		return accept;
	}

	public void addAcceptListener(ActionListener actionListener) {
		accept.addActionListener(actionListener);
		resultViewPanel.add(accept);
	}
	public void addAccept() {
		resultViewPanel.add(accept);
	}


	// public void setProductList(ArrayList<Product> productArrayList) {
	// 	System.out.print("test");
	// 	DefaultListModel<Product> DefaultListModelProducts = new DefaultListModel<>();
	// 	productList = new JList(DefaultListModelProducts);
	// 	productList.setBackground(Color.blue);

	// 	splitPane.setLeftComponent(new JScrollPane(returnList));

	// 	resultViewPanel.add(productList);
	// 	resultViewPanel.revalidate();
	// 	resultViewPanel.repaint();
	// }

	public void addLabels(ArrayList<Product> productArrayList) {
	
			productPanel.setName("test");
		// for(int i = 0; i < productArrayList.size(); i++) {
		// 	// productPanel =	new JPanel(productArrayList.get(i).getProductNaam());
		// 	JLabel productName = new JLabel(productArrayList.get(i).getProductNaam());
		// 	productPanel.add(productName);
		// }
		resultViewPanel.add(productPanel);
	}
}


