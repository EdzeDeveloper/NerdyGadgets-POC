package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame implements ActionListener{
  JMenuBar menuBar;
	JMenu productMenu;
	JMenu contactMenu;
	JMenu helpMenu;
	JMenuItem returnProducts;
	JMenuItem viewReturnedProducts;
	JMenuItem exitItem;
	//ImageIcon eenImage;

  public MainMenu (JFrame frame) {
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		//eenImage = new ImageIcon("eenImage.png");
		
		menuBar = new JMenuBar();
		
		productMenu = new JMenu("Product");
		
		returnProducts = new JMenuItem("Retour aanmelden");
		viewReturnedProducts = new JMenuItem("Bekijk geretourneerde producten");
		
		returnProducts.addActionListener(this);
		viewReturnedProducts.addActionListener(this);
		
		productMenu.add(returnProducts);
		productMenu.add(viewReturnedProducts);
		
		menuBar.add(productMenu);
		
		frame.setJMenuBar(menuBar);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==returnProducts) {
			System.out.println("*beep boop* je wilt naar de product retourneer pagina");
		}
		if(e.getSource()==viewReturnedProducts) {
			System.out.println("*beep boop* je wilt de lijst van geretourneerde producten zien");
		}
		if(e.getSource()==exitItem) {
			System.exit(0);
		}
	}
}