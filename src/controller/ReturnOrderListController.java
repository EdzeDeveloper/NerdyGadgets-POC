package controller;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ReturnedOrdersListView;

public class ReturnOrderListController {
  private ReturnedOrdersListView view;
  
  public ReturnOrderListController(ReturnedOrdersListView view) throws SQLException {
    this.view = view;
    this.view.addGoToButtonListener(new ReturnedOrderListGoToButtonListener());
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
