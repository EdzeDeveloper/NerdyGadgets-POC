package view;

import javax.swing.*;

import view.components.List;

import java.util.Vector;

public class ReturnedOrdersListView extends JFrame{
	public ReturnedOrdersListView(){
			try {

				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			} 

			JFrame frame = new JFrame( "Create list for use" );
			

			// bespreken olcan!!: generiek maken voor verschillende data
			Vector v = new Vector();
			for ( int i = 0; i < 10; i++ ) {
					v.add( "Data " + i );
			}

			// add list data functie
			List myList = new List(v, "titel");

			JPanel listPanel = new JPanel();
			listPanel.add( myList );
			
			frame.getContentPane().add( listPanel );
			frame.setSize(600, 600);
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			frame.pack();
			frame.setVisible(true);
		}
}

