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
import repository.ReturnRepository;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ReturnOrderListController {
  private ReturnedOrdersListView view;
  private JList returnList;
	private OrderRepository<Order> bestellingRepo;
	private Order geselecteerdeBestelling;
	private int currentSelectedRetourId = 0;
	
  
  public ReturnOrderListController(ReturnedOrdersListView returnOrderListView, JFrame mainframe) throws SQLException {
		DefaultListModel<Return> DefaultListModelReturnedOrders = new DefaultListModel<>();
		
		//haal alle geretourneerde orders op
		ReturnRepository returnRepo = new ReturnRepository();
		ResultSet returnOrdersResultSet  = returnRepo.findAll();
		while (returnOrdersResultSet.next()) {
			Return returnInstance = new Return();
			returnInstance.setBestellingID(returnOrdersResultSet.getInt("bestellingID"));
			returnInstance.setReden(returnOrdersResultSet.getString("reden"));
			returnInstance.setRetourID(returnOrdersResultSet.getInt("retourID"));
			DefaultListModelReturnedOrders.addElement(returnInstance);
		}
    view = returnOrderListView;
		view.setListModel(DefaultListModelReturnedOrders);	

		returnList = view.getReturnList();
		// get bestellingen repository
		bestellingRepo = new OrderRepository();

		
		// set toekomstige button listeners voor het goed/afkeuren van producten
		view.addAcceptListener(new vieuwReturnedItemsEventListener());
		view.addDeclineListener(new vieuwReturnedItemsEventListener());

		//listener voor de geselecteerde item
		view.setReturnListListener(new returnListListener());
  }
	class vieuwReturnedItemsEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if("Accept".equals(e.getActionCommand())) {
				if (currentSelectedRetourId > 0) {
					try {
						geselecteerdeBestelling.setStatusToRecieved();
						bestellingRepo.update(geselecteerdeBestelling);
						view.displayErrorMessage("Order is in goede orde aangekomen.");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						view.displayErrorMessage("Er is iets mis gegaan met het aanpassen van de status van de order.");
						e1.printStackTrace();
					}
					System.out.print("ik wil wat accepteren");
				}
			}
			if("Decline".equals(e.getActionCommand())) {
				System.out.print("ik wil wat Decline");
			}
		}

	}
	// add listener to selected item
	class returnListListener implements ListSelectionListener {

		public void valueChanged (ListSelectionEvent e) {

			
			if (!e.getValueIsAdjusting()) {//This line prevents double events

				Return R = (Return) returnList.getSelectedValue();
				currentSelectedRetourId = R.getRetourID();
				// probeer een Order met producten op te halen
				try {
					System.out.print(R.getRetourID());
					geselecteerdeBestelling = bestellingRepo.findAndSetOrder(R.getRetourID());
					view.emptyResultViewPanel();
					// returnLabel.setText("Order ID: " + R.getBestellingID());
					view.createResultView(geselecteerdeBestelling);

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
