package controller;
import java.sql.SQLException;
import java.util.ArrayList;

import repository.ReturnRepository;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ReturnedOrdersListView;

public class ReturnOrderListController {
  private ReturnedOrdersListView view;
	private ArrayList<String> testArrayList = new ArrayList<String>();
  
  public ReturnOrderListController(ReturnedOrdersListView returnOrderListView) throws SQLException {
    view = returnOrderListView;
		testArrayList.add("returned order 1");
		testArrayList.add("returned order 2");
		testArrayList.add("returned order 3");
		testArrayList.add("returned order 4");
		testArrayList.add("returned order 5");
		testArrayList.add("returned order 6");
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
