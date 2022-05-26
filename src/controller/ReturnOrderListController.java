package controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Return;
import model.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ReturnedOrdersListView;

public class ReturnOrderListController {
  private ReturnedOrdersListView view;
	private ArrayList<Return> testArrayList = new ArrayList<Return>();

  
  public ReturnOrderListController(ReturnedOrdersListView returnOrderListView) throws SQLException {

		ResultSet getAllPersonData = DBConnection.select("Select * from retour limit 10");
    while(getAllPersonData.next()){
      int bestellindID = getAllPersonData.getInt("bestellingID");
      String reden = getAllPersonData.getString("reden");
      int retourID = getAllPersonData.getInt("retourID");

			Return Return = new Return(bestellindID, reden, retourID);

			testArrayList.add(Return);
    }

    view = returnOrderListView;
		// view.addReturnedOrdersToList(ReturnRepository.getAllReturnedOrders());
		view.addReturnedOrdersToList(testArrayList);
		// System.out.print(ReturnRepository.getAllReturnedOrders());
    view.addGoToButtonListener(new ReturnedOrderListGoToButtonListener());
  }

  class ReturnedOrderListGoToButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				view.getSelectedProduct();
			} catch (Exception e1) {
			}
		}
	}
}
