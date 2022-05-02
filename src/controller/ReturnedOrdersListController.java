package controller;

import model.ReturnedOrders;
import view.ReturnedOrdersListView;

public class ReturnedOrdersListController {
  private ReturnedOrdersListView view;
  private ReturnedOrders model;
  
  public ReturnedOrdersListController(ReturnedOrdersListView ReturnProductListView, ReturnedOrders ReturnProductListModel) {
    view = ReturnProductListView;
    model = ReturnProductListModel;

    
    System.out.println("ik ben nu in de controller en doehet ");

  }

  class ReturnProductListListener implements ActionListener {

		// public void listItemClicked(ActionEvent e) {
		// 	try {
		// 		returnId = theView.getReturnId();
    //     // send naar pagina van ditmar, met returnID zodat ditmar info kan ophalen met het id.
		// 	} catch(error e) {
		// 		System.out.println(e);
		// 		theView.displayErrorMessage("Error something went wrong");
		// 	}
  }
}
