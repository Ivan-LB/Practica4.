import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.lang.StringBuilder;
import java.awt.event.*;

class Logica extends JFrame implements ActionListener{
  int rdmCategoria;
  int rdmPalabra;
  String s;
  String nuevo;
  String original;
  Random aleatorio = new Random(System.currentTimeMillis());
  ArrayList<String> arrayPeliculas = new ArrayList<String>();
  ArrayList<String> arrayFrutas = new ArrayList<String>();
  ArrayList<String> arrayPaises = new ArrayList<String>();
  ArrayList<String> arrayBebidas = new ArrayList<String>();
  ArrayList<String> arrayComidas = new ArrayList<String>();
  ArrayList<String> arrayColores = new ArrayList<String>();
  ArrayList<String> arrayInstrumentos = new ArrayList<String>();
  ArrayList<String> arrayArtistas = new ArrayList<String>();
  ArrayList<ArrayList<String>> arrayCategoriasActivas = new ArrayList();
  JPanel panel;
  JLabel lblPalabra;
  JLabel lblRespuesta;
  JTextField txtRespuesta;
  JButton btnRespuesta;

  public Logica(ArrayList<String> categorias){
    panel = new JPanel();
    panel.setLayout(null);
    panel.setBackground(Color.orange);
    lblPalabra = new JLabel("");
    lblPalabra.setBounds(0,0,250,30);
    txtRespuesta = new JTextField();
    txtRespuesta.setBounds(50,150,250,30);
    btnRespuesta = new JButton("Presiona");
    btnRespuesta.setBounds(150,75,75,30);
    btnRespuesta.addActionListener(this);
    lblRespuesta = new JLabel("");
    lblRespuesta.setBounds(0,40,250,30);
    System.out.println(lblRespuesta.getText().length());

    for(int i=0; i<categorias.size();i++){
      if(categorias.get(i)=="Peliculas"){
        arrayPeliculas = Archivo.leerTodo("D:/Practica4/Categorias/Peliculas.txt");
        arrayCategoriasActivas.add(arrayPeliculas);
      }else if(categorias.get(i)=="Frutas"){
        arrayFrutas = Archivo.leerTodo("D:/Practica4/Categorias/Frutas.txt");
        arrayCategoriasActivas.add(arrayFrutas);
      }else if(categorias.get(i)=="Paises"){
        arrayPaises = Archivo.leerTodo("D:/Practica4/Categorias/Paises.txt");
        arrayCategoriasActivas.add(arrayPaises);
      }else if(categorias.get(i)=="Bebidas"){
        arrayBebidas = Archivo.leerTodo("D:/Practica4/Categorias/Bebidas.txt");
        arrayCategoriasActivas.add(arrayBebidas);
      }else if(categorias.get(i)=="Comidas"){
        arrayComidas = Archivo.leerTodo("D:/Practica4/Categorias/Comidas.txt");
        arrayCategoriasActivas.add(arrayComidas);
      }else if(categorias.get(i)=="Colores"){
        arrayColores = Archivo.leerTodo("D:/Practica4/Categorias/Colores.txt");
        arrayCategoriasActivas.add(arrayColores);
      }else if(categorias.get(i)=="Instrumentos"){
        arrayInstrumentos = Archivo.leerTodo("D:/Practica4/Categorias/Instrumentos.txt");
        arrayCategoriasActivas.add(arrayInstrumentos);
      }else if(categorias.get(i)=="Artistas"){
        arrayArtistas = Archivo.leerTodo("D:/Practica4/Categorias/Artistas.txt");
        arrayCategoriasActivas.add(arrayArtistas);
      }
    }
    rdmCategoria = aleatorio.nextInt(categorias.size());
    rdmPalabra = aleatorio.nextInt(arrayCategoriasActivas.get(rdmCategoria).size());
    s = arrayCategoriasActivas.get(rdmCategoria).get(rdmPalabra);
    lblPalabra.setText(s);
    for(int i=0; i<s.length(); i++) {
      lblRespuesta.setText(lblRespuesta.getText()+" ");
    }
    original = lblRespuesta.getText();

    panel.add(lblPalabra);
    panel.add(lblRespuesta);
    panel.add(txtRespuesta);
    panel.add(btnRespuesta);

    this.add(panel);
    this.setTitle(" Prueba ");
    this.setSize(700,700);
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }
  public void actionPerformed(ActionEvent event){
		if(event.getSource() == this.btnRespuesta){
      Boolean test = new Boolean(false);
      int coincidencia = 0;
      String temporal = txtRespuesta.getText();
      char temp = temporal.charAt(0);
			for(int i=0; i<s.length(); i++){
        if(temp == (s.charAt(i))){
          nuevo = new StringBuilder(original).replace(i,i+1,temporal).toString();
          lblRespuesta.setText(lblRespuesta.getText()+nuevo);
          System.out.println(lblRespuesta.getText().length());
          coincidencia++;
        }
      }
      if(coincidencia!=0){
        System.out.println("Coincidio: "+coincidencia);
      }else{
        System.out.println("No coincidio");
      }
		}
  }
}
