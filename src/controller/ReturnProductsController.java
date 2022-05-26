package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import model.Order;
import repository.OrderRepository;
import view.ReturnView;


public class ReturnProductsController {
	
	private ReturnView returnView;
	
	public ReturnProductsController(ReturnView returnView) throws SQLException {
		this.returnView = returnView;

		this.returnView.addSearchButtonListener(new ReturnListener());
	}
	
	class ReturnListener implements ActionListener{
		private OrderRepository orderRepository;

		public void actionPerformed(ActionEvent e) {
			this.orderRepository = new OrderRepository();

			int orderNumber;
			try{
        		// probeer van de view het ordernummer te lezen.
				orderNumber = returnView.getOrderNumber();
				
        		// wanneer je het ordernummer hebt, haal de order op uit de database.
				Order order = orderRepository.find(orderNumber);
				
        		//haal het resultaat op door de getCalculationValue functie.
				returnView.setOrder(order);
			
			}

			catch(NumberFormatException ex){
				System.out.println(ex);
				
				returnView.displayErrorMessage("Dit is geen geldig ordernummer. Alleen getallen zijn toegestaan.");
				
			} catch (SQLException e2) { // @TO-DO this isn't working?
				// When ordernumber can't be found.
				returnView.displayErrorMessage("Ordernummer is niet gevonden. Sorry.");

				e2.printStackTrace();
			}
			
		}
		
	}
	
}