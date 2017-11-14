package MiniC.TreeDrawer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DrawerFrame extends JFrame {
  public static final long serialVersionUID = 1L;
  public DrawerFrame (JPanel panel) {
    setSize(300, 200);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize();
    int screenHeight = d.height;
    int screenWidth = d.width;
    setTitle("MiniC Compiler AST");
    setSize(screenWidth / 2, screenHeight / 2);
    setLocation(screenWidth / 4, screenHeight / 4);
    // Image img = tk.getImage("icon.gif");
    // setIconImage(img);

    addWindowListener(
      new WindowAdapter() {
        public void windowClosing (WindowEvent e) {
      	  System.exit(0);
        }
      }
    );
    Container contentPane = getContentPane();
    contentPane.add(new JScrollPane(panel));
  }
}
