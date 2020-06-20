import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

class Escenario extends JFrame{
  ImageIcon image_icon;
  JuegoAhorcado ja;
  String escenario = "./Imagenes/Escenario_626x885.jpg";

  public Escenario(/*ArrayList<String> categorias*/){
    this.setFocusable(true);
    this.setBackground(Color.blue);

    image_icon = new ImageIcon(this.getClass().getResource(escenario));
    //ja = new JuegoAhorcado();
  }
  public void paint(Graphics g) {
    super.paint(g);

    Graphics2D g2d = (Graphics2D)g;
    g2d.drawImage(image_icon.getImage(), 0, 0, this);

    Toolkit.getDefaultToolkit().sync();
    g.dispose();
  }

}
