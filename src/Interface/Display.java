package Interface;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;

import javax.swing.JFrame;

public class Display {
  public void show() {
  
    JFrame frame = new JFrame("5e Character");
  
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //...create emptyLabel...
    frame.getContentPane().add(new Label(), BorderLayout.CENTER);
  //4. Size the frame.
    frame.pack();

    //5. Show it.
    frame.setVisible(true);
  }
}
