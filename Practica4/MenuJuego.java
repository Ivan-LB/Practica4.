import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

class MenuJuego extends JFrame implements ActionListener{
	JPanel panel;
	int numUser;
	JButton btnPuntaje;
	JButton btnIniciar;
	JCheckBox opciones[] = new JCheckBox[8];
	String categorias[] = {"Peliculas","Frutas","Paises","Bebidas","Comidas","Colores","Instrumentos","Artistas"};
	Boolean prueba = new Boolean(false);
	ArrayList<String> arrayCategorias = new ArrayList<String>();
	ArrayList<String> arrayScore = new ArrayList<String>();

	public MenuJuego(int numUser){
		this.numUser=numUser;
		arrayScore = Archivo.leerTodo("D:/Practica4/UsersPasswords/Puntajes.txt");
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.orange);
		for(int i=0, y=0; i<4; i++, y+=30){
			opciones[i] = new JCheckBox(categorias[i]);
			opciones[i].setBounds(0,y,100,30);
			panel.add(opciones[i]);
		}
		for(int i=4, y=0; i<8; i++, y+=30){
			opciones[i] = new JCheckBox(categorias[i]);
			opciones[i].setBounds(110,y,120,30);
			panel.add(opciones[i]);
		}

		btnPuntaje = new JButton("Mostrar Puntajes");
		btnPuntaje.setVerticalAlignment(SwingConstants.CENTER);
		btnPuntaje.setBounds(0,380,150,50);
		btnPuntaje.addActionListener(this);

		btnIniciar = new JButton("Comenzar Juego");
		btnIniciar.setVerticalAlignment(SwingConstants.CENTER);
		btnIniciar.setBounds(340,380,150,50);
		btnIniciar.addActionListener(this);

		panel.add(btnPuntaje);panel.add(btnIniciar);

		this.add(panel);
    this.setTitle(" Categorias ");
    this.setSize(500,500);
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setLocationRelativeTo(null);

	}
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == this.btnIniciar){
			for (int i=0; i<8; i++) {
				prueba = opciones[i].isSelected();
				if(prueba.equals(true)){
					arrayCategorias.add(categorias[i]);
				}
			}
			Logica l = new Logica(arrayCategorias);
			this.dispose();
		}else if (event.getSource() == this.btnPuntaje ){
			JOptionPane.showMessageDialog(null, "Su puntaje mas alto es: " + arrayScore.get(numUser));
		}
  }
}
