package controller;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import view.MainMenu;
import view.ReturnedOrdersListView;

import java.awt.Color;

public class MainController {
  // init controllers  
  private ReturnOrderListController returnOrderListController;

  // default view
  private JFrame mainFrame;
  private MainMenu mainMenu;
  private JMenuItem returnOrderProductsViewMenuItem;
  private JMenuItem returnedOrderListProductsViewMenuItem;
  private JMenuItem routeViewMenuItem;

  //view components
  private ReturnedOrdersListView returnOrderListView;

  //initialize views
  JPanel panelController = new JPanel();
  JPanel returnOrderListViewJpanel;
  JPanel panelSecond = new JPanel();
  JPanel routeView = new JPanel();
  CardLayout cardLayout = new CardLayout();;
  
  public MainController() throws SQLException {
    mainFrame = new JFrame("NerdyGadgets");
   
    panelController.setLayout(cardLayout);

    //initialize return order list view
    returnOrderListView = new ReturnedOrdersListView();
    
    returnOrderListController = new ReturnOrderListController(returnOrderListView);
    returnOrderListViewJpanel = returnOrderListView.getListPanel();

    panelSecond.setBackground(Color.GREEN);
    routeView.setBackground(Color.YELLOW);
    
    // add all panels to the panel controller
    panelController.add(panelSecond, "startPagina");
    panelController.add(returnOrderListViewJpanel, "returnOrderList");
    panelController.add(routeView, "viewRoute");

    // get main menu with button listeners
    mainMenu = new MainMenu(mainFrame);
    mainMenu.addMenuItemListeners(new addMenuItemListener());
    returnOrderProductsViewMenuItem = mainMenu.getReturnOrderProductsViewMenuItem();
    returnedOrderListProductsViewMenuItem = mainMenu.getReturnedOrderListProductsViewMenuItem();
    routeViewMenuItem = mainMenu.getRouteViewMenuItem();
    //show first panel.

    mainFrame.add(panelController);
    mainFrame.setSize(800, 800); 
    mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    mainFrame.setVisible(true);
  }

  class addMenuItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
      if(e.getSource()==returnOrderProductsViewMenuItem) {
        System.out.println("*beep boop* je wilt naar de product retourneer pagina");
        cardLayout.show(panelController, "startPagina");
      }
      if(e.getSource()==returnedOrderListProductsViewMenuItem) {
        System.out.println("*beep boop* je wilt de lijst van geretourneerde producten zien");
        cardLayout.show(panelController, "returnOrderList");
      }
      if(e.getSource()==routeViewMenuItem) {
        System.out.println("*beep boop* je wilt de lijst van geretourneerde producten zien");
        cardLayout.show(panelController, "viewRoute");
      }
    }
  }
}

