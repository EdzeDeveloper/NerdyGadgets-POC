package view;

import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu extends JFrame{
  JMenuBar menuBar;

	//menu's
	JMenu productMenu;
	
	//menu items
	JMenuItem returnOrderProductsViewMenuItem;
	JMenuItem returnedOrderListProductsviewMenuItem;
	JMenuItem routeViewMenuItem;

  public MainMenu (JFrame frame) {
		
		menuBar = new JMenuBar();

		ImageIcon returnIcon = new ImageIcon("../test.png");

		productMenu = new JMenu("Product");
		returnOrderProductsViewMenuItem = new JMenuItem("Retour aanmelden", returnIcon);
		returnedOrderListProductsviewMenuItem = new JMenuItem("Bekijk geretourneerde producten");
		routeViewMenuItem = new JMenuItem("Ga naar route");


		productMenu.add(returnOrderProductsViewMenuItem);
		productMenu.add(returnedOrderListProductsviewMenuItem);
		productMenu.add(routeViewMenuItem);
		
		menuBar.add(productMenu);
		
		frame.setJMenuBar(menuBar);
	}

	public void addMenuItemListeners(ActionListener addMenuItemListener){
		returnOrderProductsViewMenuItem.addActionListener(addMenuItemListener);
		returnedOrderListProductsviewMenuItem.addActionListener(addMenuItemListener);
		routeViewMenuItem.addActionListener(addMenuItemListener);
	}

	public JMenuItem getReturnOrderProductsViewMenuItem() {
		return returnOrderProductsViewMenuItem;
	}
	
	public JMenuItem getReturnedOrderListProductsViewMenuItem() {
		return returnedOrderListProductsviewMenuItem;
	}

	public JMenuItem getRouteViewMenuItem() {
		return routeViewMenuItem;
	}
}