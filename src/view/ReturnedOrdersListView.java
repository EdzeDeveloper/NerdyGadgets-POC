package view;

import java.awt.*;
import javax.swing.*;

import view.panels.ListPanel;

public class ReturnedOrdersListView extends JFrame{
	private ListPanel listPanel;

	public ReturnedOrdersListView(){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
					} 
					JFrame frame = new JFrame("Producten lijst");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.add(new JScrollPane(listPanel));
					frame.pack();
					for (int i = 0; i < 12; i++) {
						listPanel.addNewPanel("42042");
					}
					frame.setVisible(true);
			}
		});
	}

  public void getReturnProductList(String string){
		for (int i = 0; i < 12; i++) {
			listPanel.addNewPanel(string);
		}
	}
	
}

