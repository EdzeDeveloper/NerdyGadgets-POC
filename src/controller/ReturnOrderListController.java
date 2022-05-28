package controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Bestelling;
import model.Return;
import view.ReturnedOrdersListView;
import repository.BestellingRepository;
import repository.ReturnRepository;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ReturnOrderListController {
  private ReturnedOrdersListView view;
  private JList returnList;
	private BestellingRepository<Bestelling> bestellingRepo;
	
  
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
		bestellingRepo = new BestellingRepository();

		
		// set toekomstige button listeners voor het goed/afkeuren van producten
		view.addAcceptListener(new vieuwReturnedItemsEventListener());
		view.addDeclineListener(new vieuwReturnedItemsEventListener());

		//listener voor de geselecteerde item
		view.setReturnListListener(new returnListListener());
  }
	class vieuwReturnedItemsEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.print(e.getActionCommand());
		
		}

	}
	// add listener to selected item
	class returnListListener implements ListSelectionListener {

		public void valueChanged (ListSelectionEvent e) {

			
			if (!e.getValueIsAdjusting()) {//This line prevents double events

				Return R = (Return) returnList.getSelectedValue();
				Bestelling geselecteerdeBestelling;
				// probeer een bestelling met producten op te halen
				try {
					geselecteerdeBestelling = bestellingRepo.find(R.getRetourID());
					view.emptyResultViewPanel();
					// returnLabel.setText("Bestelling ID: " + R.getBestellingID());
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
