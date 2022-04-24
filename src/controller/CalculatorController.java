package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.CalculatorModel;
import view.CalculatorView;


public class CalculatorController {
	
	private CalculatorView theView;
	private CalculatorModel theModel;
	
	public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
		this.theView = theView;
		this.theModel = theModel;

		this.theView.addCalculateListener(new CalculateListener());
	}
	
	class CalculateListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			int firstNumber, secondNumber = 0;
			try{
        // probeer van d evieuw de firstNumber en second number te pakken
				firstNumber = theView.getFirstNumber();
				secondNumber = theView.getSecondNumber();
				
        // wanneer je de getallen hebt ga naar het model met de nummers
				theModel.addTwoNumbers(firstNumber, secondNumber);
				
        //haal het resultaat op door de getCalculationValue functie.
				theView.setCalcSolution(theModel.getCalculationValue());
			
			}

			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				theView.displayErrorMessage("You Need to Enter 2 Integers");
				
			}
			
		}
		
	}
	
}