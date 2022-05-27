package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

import model.Order;
import model.Return;
import repository.OrderRepository;
import repository.ReturnRepository;
import view.ReturnProductsView;

import javax.swing.*;

public class ReturnProductsController {
	
	private ReturnProductsView returnProductsView;
	
	public ReturnProductsController(ReturnProductsView returnProductsView) throws SQLException {
		this.returnProductsView = returnProductsView;

		this.returnProductsView.addSearchButtonListener(new ReturnListener());
		this.returnProductsView.addNextListener(new ReturnListener());
	}
	
	class ReturnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			String name = button.getText();

			OrderRepository orderRepository = new OrderRepository();
			ReturnRepository returnRepository = new ReturnRepository();

			if (Objects.equals(name, "Zoek bestelde producten")) {
				searchButtonHandler(orderRepository);
			}

			if (Objects.equals(name, "Volgende")) {
				try {
					nextButtonHandler(orderRepository, returnRepository);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		
	}

	private void searchButtonHandler(OrderRepository orderRepository) {
		int orderNumber;

		try{
			// probeer van de view het ordernummer te lezen.
			orderNumber = returnProductsView.getOrderNumber();

			// wanneer je het ordernummer hebt, haal de order op uit de database.
			Order order = orderRepository.find(orderNumber, true);

			//haal het resultaat op door de getCalculationValue functie.
			returnProductsView.setOrder(order);
			System.out.println(order);

		}

		catch(NumberFormatException ex){
			System.out.println(ex);

			returnProductsView.displayErrorMessage("Dit is geen geldig ordernummer. Alleen getallen zijn toegestaan.");

		} catch (SQLException e2) { // @TO-DO this isn't working?
			// When ordernumber can't be found.
			returnProductsView.displayErrorMessage("Ordernummer is niet gevonden. Sorry.");

			e2.printStackTrace();
		}
	}

	private void nextButtonHandler(OrderRepository orderRepository, ReturnRepository returnRepository) throws SQLException {
		Map<Integer, JTextField> productsToReturn = returnProductsView.getProductsToReturn();
		Integer orderID = returnProductsView.getOrderNumber();

		Return returnInstance = new Return(orderID, "reden");

		productsToReturn.forEach((productID, quantityField) -> {
			int quantity = Integer.parseInt( quantityField.getText() );
			returnInstance.addReturnedProduct(productID, quantity);
		});

		System.out.println(returnInstance.getReturnedProducts());

		returnRepository.create(returnInstance);

	}
}