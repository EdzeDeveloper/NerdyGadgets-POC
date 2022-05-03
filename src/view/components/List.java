package view.components;

import javax.swing.*;
import java.util.Vector;
import java.awt.*;

public class List extends JPanel {
  private JList list;
  private JPanel headerPanel;
  private JLabel header;

  public List(Vector vectorList) {
    setLayout( new BorderLayout() );
    list = new JList();
    JScrollPane scroll = new JScrollPane( list );
    add( scroll, BorderLayout.CENTER );
    list.setPreferredSize(new Dimension(300, 300));
    list.setListData( vectorList );
  }

  public List(Vector vectorList, String listHeaderText) {
    setLayout( new BorderLayout() );
    list = new JList();
    headerPanel = new JPanel();
    headerPanel.setLayout( new BorderLayout() );
    headerPanel.setBorder( BorderFactory.createEtchedBorder() );
    JScrollPane scroll = new JScrollPane( list );
    add( scroll, BorderLayout.CENTER );
    header = new JLabel( listHeaderText, JLabel.CENTER );
    headerPanel.add( header );
    add( headerPanel, BorderLayout.NORTH );
    list.setPreferredSize(new Dimension(300, 300));
    list.setListData( vectorList );
  }
}