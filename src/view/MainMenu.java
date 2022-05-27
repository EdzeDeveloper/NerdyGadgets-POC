package view;

import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu extends JFrame{
  JMenuBar menuBar;

	//menu's
	JMenu productMenu;
	
	//menu items
	JMenuItem returnedOrderListProductsviewMenuItem;
	JMenuItem routeViewMenuItem;
	JMenuItem returnProductsViewMenuItem;

  public MainMenu (JFrame frame) {
		menuBar = new JMenuBar();

		ImageIcon returnIcon = new ImageIcon("../test.png");

		productMenu = new JMenu("Product");
	  	returnProductsViewMenuItem = new JMenuItem("Retour aanmelden");
		returnedOrderListProductsviewMenuItem = new JMenuItem("Bekijk geretourneerde producten");
		routeViewMenuItem = new JMenuItem("Ga naar route");

	  	productMenu.add(returnProductsViewMenuItem);
	  	productMenu.add(returnedOrderListProductsviewMenuItem);
		productMenu.add(routeViewMenuItem);

		menuBar.add(productMenu);
		
		frame.setJMenuBar(menuBar);
	}

	public void addMenuItemListeners(ActionListener addMenuItemListener){
		returnProductsViewMenuItem.addActionListener(addMenuItemListener);
		returnedOrderListProductsviewMenuItem.addActionListener(addMenuItemListener);
		routeViewMenuItem.addActionListener(addMenuItemListener);
	}

	public JMenuItem getReturnProductsViewMenuItem() {
		return returnProductsViewMenuItem;
	}
	
	public JMenuItem getReturnedOrderListProductsViewMenuItem() {
		return returnedOrderListProductsviewMenuItem;
	}

	public JMenuItem getRouteViewMenuItem() {
		return routeViewMenuItem;
	}
}