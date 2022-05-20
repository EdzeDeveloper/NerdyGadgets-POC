package view.components;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;

public class ListPanel extends JPanel {
  JButton goToButton;
  JList list;
  public ListPanel(ArrayList arrayList, String buttonText) {
    goToButton = new JButton(buttonText);
    setLayout( new BorderLayout() );
    list = new JList(arrayList.toArray());
    list.setVisibleRowCount(10);
    JScrollPane scroll = new JScrollPane( list );
    add( scroll, BorderLayout.CENTER );
    add( goToButton, BorderLayout.SOUTH);
    list.setPreferredSize(new Dimension(700, 700));
  }

  public ListPanel(ArrayList arrayList, String buttonText, String listHeaderText) {
    setLayout( new BorderLayout() );
    goToButton = new JButton(buttonText);
    list = new JList(arrayList.toArray());
    list.setVisibleRowCount(10);
    JPanel headerPanel = new JPanel();
    headerPanel.setLayout( new BorderLayout() );
    headerPanel.setBorder( BorderFactory.createEtchedBorder() );
    JScrollPane scroll = new JScrollPane( list );
    add( scroll, BorderLayout.CENTER );
    JLabel header = new JLabel( listHeaderText, JLabel.CENTER );
    headerPanel.add( header );
    add( headerPanel, BorderLayout.NORTH );
    add( goToButton, BorderLayout.SOUTH);
    list.setPreferredSize(new Dimension(700, 700));
    JButton seeItemButton = new JButton();
    add(seeItemButton, BorderLayout.SOUTH);
  }

  public void setButtonListener() {

  }

  public void addGoToButtonListener(ActionListener addGoToButtonListener){
		goToButton.addActionListener(addGoToButtonListener);
	}

  public Object getSelectedProduct() {
    // System.out.print(list.getSelectedValue());
    System.out.println(list.getSelectedIndex() + 1);
    return list.getSelectedValue();
  }

  // public Object getListItemFromIndex(int index) {
  //   // return list.get(index);
  // }
}