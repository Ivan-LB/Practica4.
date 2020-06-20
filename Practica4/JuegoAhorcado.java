import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;

class JuegoAhorcado extends JFrame implements KeyListener{
  BufferedImage imagen;
  BufferedImage subImagen;
  Monito monito;
  int indiceX = 0;

  public JuegoAhorcado(){
    try {
      imagen = ImageIO.read(new File("./Imagenes/SpritesFinal.png"));
    }catch (Exception e) {
      System.out.println("Error al cargar la imagen");
    }
    subImagen = imagen.getSubimage(0,0,126,350);
    monito = new Monito(subImagen);

    this.add(monito);

    this.setTitle("Prueba");
    this.setBounds(500,400,500,400);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    this.addKeyListener(this);
  }
  public void keyPressed(KeyEvent e)
	{
		//System.out.println("Tecla presionada = "+e.getKeyCode());

    int t = e.getKeyCode();
    Point pos = monito.getLocation();
		int x = (int)pos.getX();
		int y = (int)pos.getY();
		if(t==68)
		{
      // x = x+5;
			// indiceX = ((indiceX + 1) % 5) * 126;
      indiceX+=126;
      monito.imagen = imagen.getSubimage(indiceX,0,126,350);
      monito.revalidate();
      System.out.println("Se presiono");

      // if(indiceX==756) {
      //   indiceX=0;
      // }

      monito.repaint();
		}
    //monito.setLocation(x,y);
	}

	public void keyReleased(KeyEvent e)
	{
		//System.out.println("Tecla liberada.");
	}

	public void keyTyped(KeyEvent e)
	{
		//System.out.println("Tecla en el buffer.");

	}
}
