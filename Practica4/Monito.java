import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

class Monito extends JPanel{
	BufferedImage imagen;

	public Monito(BufferedImage imagen){
		this.imagen = imagen;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imagen,0,0,126,350,null);
	}
}
