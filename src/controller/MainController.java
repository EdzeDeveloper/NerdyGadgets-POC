package controller;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import view.MainMenu;
import view.ReturnProductsView;
import view.ReturnedOrdersListView;
import view.RouteView;

import java.awt.Color;

public class MainController {
  // init controllers  
  private ReturnOrderListController returnOrderListController;
  private ReturnProductsController returnProductsController;
  private RouteViewController routeViewController;

  // default view
  private JFrame mainFrame;
  private MainMenu mainMenu;
  private JMenuItem returnedOrderListProductsViewMenuItem;
  private JMenuItem routeViewMenuItem;
  private JMenuItem returnProductsViewMenuItem;

  //view components
  private ReturnedOrdersListView returnOrderListView;
  private ReturnProductsView returnProductsView;
  private RouteView routeView;

  //initialize views panels
  JPanel panelController = new JPanel();
  JPanel returnOrderListViewJpanel;
  JPanel returnProductsViewJpanel;
  JPanel routeViewPanel;
  CardLayout cardLayout = new CardLayout();
  
  public MainController() throws SQLException {
    mainFrame = new JFrame("NerdyGadgets");
    // set the views
    initializeViewPanels();
    
    returnOrderListController = new ReturnOrderListController(returnOrderListView, mainFrame);
    returnOrderListViewJpanel = returnOrderListView.getListPanel();

    // init controllers
    returnProductsController = new ReturnProductsController(returnProductsView);
    routeViewController = new RouteViewController(routeView);

    // add all panels to the panel controller
    panelController.add(returnProductsView, "returnProducts");
    panelController.add(returnOrderListViewJpanel, "returnOrderList");
    panelController.add(routeViewPanel, "viewRoute");

    // get main menu with button listeners
    mainMenu = new MainMenu(mainFrame);
    mainMenu.addMenuItemListeners(new addMenuItemListener());
    returnedOrderListProductsViewMenuItem = mainMenu.getReturnedOrderListProductsViewMenuItem();
    returnProductsViewMenuItem = mainMenu.getReturnProductsViewMenuItem();
    routeViewMenuItem = mainMenu.getRouteViewMenuItem();

    mainFrame.add(panelController);
    mainFrame.pack();
    mainFrame.setSize(800, 800); 
    mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    mainFrame.setVisible(true);
  }

  class addMenuItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
        if(e.getSource()==returnProductsViewMenuItem) {
          cardLayout.show(panelController, "returnProducts");
          // empty views when switching
          returnOrderListView.emptyResultViewPanel();
        }
        if(e.getSource()==returnedOrderListProductsViewMenuItem) {
          cardLayout.show(panelController, "returnOrderList");
          // empty views when switching
          returnProductsView.emptyReturnPanel();
        }
        if(e.getSource()==routeViewMenuItem) {
          cardLayout.show(panelController, "viewRoute");
          // empty views when switching
          returnProductsView.emptyReturnPanel();
          returnOrderListView.emptyResultViewPanel();
        }
      }
    }

  public void resetViews(Component comp) {
      panelController.remove(comp);
      panelController.revalidate();
      panelController.repaint();
  }

  public void initializeViewPanels() {
    // set panel controller
    panelController.setLayout(cardLayout);
    // set views
    returnProductsView = new ReturnProductsView();
    returnOrderListView = new ReturnedOrdersListView();
    routeView = new RouteView();
    //set panels
    returnOrderListViewJpanel = new ReturnedOrdersListView();
    routeViewPanel = routeView.getRouteViewPanel();
  }
}

