package controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Order;
import model.Return;
import view.ReturnedOrdersListView;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.ReturnRepository;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ReturnOrderListController {
  private ReturnedOrdersListView view;
  private JList returnList;
	private OrderRepository<Order> bestellingRepo;
	private ProductRepository productRepo;
	private ReturnRepository returnRepo;
	private Return geselecteerdeRetour;
	private int currentSelectedRetourId = 0;
	private DefaultListModel<Return> DefaultListModelReturnedOrders;
	
  
  public ReturnOrderListController(ReturnedOrdersListView returnOrderListView, JFrame mainframe) throws SQLException {
		DefaultListModelReturnedOrders = new DefaultListModel();
		
		//haal alle geretourneerde orders op
		returnRepo = new ReturnRepository();
		productRepo = new ProductRepository();
		getDefaultListModel();
    view = returnOrderListView;
		view.setListModel(DefaultListModelReturnedOrders);	

		returnList = view.getReturnList();
		// get bestellingen repository
		bestellingRepo = new OrderRepository();

		
		// set toekomstige button listeners voor het goed/afkeuren van producten
		view.addAcceptListener(new vieuwReturnedItemsEventListener());
		view.addDeclineListener(new vieuwReturnedItemsEventListener());
		view.addRetourRecievedListener(new vieuwReturnedItemsEventListener());

		//listener voor de geselecteerde item
		view.setReturnListListener(new returnListListener());
  }

	private void getDefaultListModel() throws SQLException {
		ResultSet returnOrdersResultSet  = returnRepo.findAll();
		while (returnOrdersResultSet.next()) {
			Return returnInstance = new Return();
			returnInstance.setBestellingID(returnOrdersResultSet.getInt("bestellingID"));
			returnInstance.setReden(returnOrdersResultSet.getString("reden"));
			returnInstance.setRetourID(returnOrdersResultSet.getInt("retourID"));
			DefaultListModelReturnedOrders.addElement(returnInstance);
		}
	}
	class vieuwReturnedItemsEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Order selectedOrder = null;
			try {
				selectedOrder = bestellingRepo.find(geselecteerdeRetour.getBestellingID());
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if("Retour in goede orde ontvangen".equals(e.getActionCommand())) {
				if (currentSelectedRetourId > 0) {
					try {
						selectedOrder.setStatusToReturnRecieved();
						bestellingRepo.update(selectedOrder);
						returnRepo.delete(currentSelectedRetourId);
						resetAndGetNewList();
						view.displayErrorMessage("Product status is gewijzigd naar : Retour ontvangen");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						view.displayErrorMessage("Er is iets mis gegaan met het aanpassen van de status van de order.");
						e1.printStackTrace();
					}
				}
			}
			if("Retour afwijzen".equals(e.getActionCommand())) {
				try {
					selectedOrder.setStatusToReturnDeclined();
					bestellingRepo.update(selectedOrder);
					returnRepo.delete(currentSelectedRetourId);
					view.displayErrorMessage("Product status is gewijzigd naar : afgewezen");
					resetAndGetNewList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					view.displayErrorMessage("Er is iets mis gegaan met het aanpassen van de status van de order.");
					e1.printStackTrace();
				}
			}
			if("Retour niet ontvangen".equals(e.getActionCommand())) {
				try {
					selectedOrder.setStatusToReturnNOTRecieved();
					bestellingRepo.update(selectedOrder);
					returnRepo.delete(currentSelectedRetourId);
					view.displayErrorMessage("Product status is gewijzigd naar : niet aangekomen");
					resetAndGetNewList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					view.displayErrorMessage("Er is iets mis gegaan met het aanpassen van de status van de order.");
					e1.printStackTrace();
				}
			}
		}

	}
	// add listener to selected item
	class returnListListener implements ListSelectionListener {

		public void valueChanged (ListSelectionEvent e) {

			
			if (!e.getValueIsAdjusting()) {//This line prevents double events

				Return R = (Return) returnList.getSelectedValue();
				if (returnList.getSelectedValue()!=null) {
					currentSelectedRetourId = R.getRetourID();
					// probeer een Order met producten op te halen
					try {
						geselecteerdeRetour = returnRepo.findAndSetReturn(R.getRetourID());
						view.emptyResultViewPanel();
						// returnLabel.setText("Order ID: " + R.getBestellingID());
						view.createResultView(geselecteerdeRetour);

						//inistiate product information panel info.

						// System.out.print(geselecteerdeBestelling);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}

	public void resetList() {
		returnList.clearSelection();
	}

	public void resetAndGetNewList() throws SQLException {
		DefaultListModelReturnedOrders.removeAllElements();
		getDefaultListModel();
		view.revalidate();
	}
}
