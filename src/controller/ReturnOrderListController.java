package controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import model.Bestelling;
import model.Return;
import view.ReturnedOrdersListView;
import repository.BestellingRepository;
import repository.ReturnRepository;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ReturnOrderListController {
  private ReturnedOrdersListView view;
  
  public ReturnOrderListController(ReturnedOrdersListView returnOrderListView, JFrame mainframe) throws SQLException {
		DefaultListModel<Return> DefaultListModelReturnedOrders = new DefaultListModel<>();
		
		//haal alle geretourneerde orders op
		ReturnRepository returnRepo = new ReturnRepository();
		ResultSet returnOrdersResultSet  = returnRepo.getAll();
		while (returnOrdersResultSet.next()) {
			Return returnInstance = new Return();
			returnInstance.setBestellingID(returnOrdersResultSet.getInt("bestellingID"));
			returnInstance.setReden(returnOrdersResultSet.getString("reden"));
			returnInstance.setRetourID(returnOrdersResultSet.getInt("retourID"));
			DefaultListModelReturnedOrders.addElement(returnInstance);
		}
    view = returnOrderListView;
		view.setListModel(DefaultListModelReturnedOrders);	

		JList returnList = view.getReturnList();
		JLabel returnLabel = view.getReturnListLabel();
		// get bestellingen repository
		BestellingRepository<Bestelling> bestellingRepo = new BestellingRepository();

		
		// set toekomstige button listeners voor het goed/afkeuren van producten
		view.addAcceptListener(new vieuwReturnedItemsEventListener());
		view.addDeclineListener(new vieuwReturnedItemsEventListener());

		//listener voor de geselecteerde item
		returnList.getSelectionModel().addListSelectionListener(e -> {
			Return R = (Return) returnList.getSelectedValue();
			Bestelling geselecteerdeBestelling;
			// probeer een bestelling met producten op te halen
			try {
				geselecteerdeBestelling = bestellingRepo.get(R.getRetourID());
				view.addLabels(geselecteerdeBestelling.getBesteldeProducten());
				returnLabel.setText("Bestelling ID: " + R.getBestellingID());

				//voeg buttons toe aan de panel van de producten lijst.
				view.addAccept();
				view.addDecline();

				// System.out.print(geselecteerdeBestelling);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});


  }
	class vieuwReturnedItemsEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.print(e.getActionCommand());
		
		}

	}
}
