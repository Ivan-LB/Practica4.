import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Login extends JFrame implements ActionListener{
  JPanel panel;
  JLabel lblUsername;
  JLabel lblPassword;
  JTextField txtUsername;
  JPasswordField txtPassword;
  JButton btnLogin;
  Boolean iguales = new Boolean(false);
  int numUser = 0;
  int asegurar;

  public Login(){

    panel = new JPanel();
    panel.setLayout(null);
    panel.setBackground(Color.orange);
    lblUsername= new JLabel("Usuario: ");
    lblUsername.setBounds(0,25,100,30);
    lblPassword = new JLabel("Clave: ");
    lblPassword.setBounds(0,85,100,30);
    txtUsername = new JTextField();
    txtUsername.setBounds(110,25,250,30);
    txtPassword = new  JPasswordField();
    txtPassword.setBounds(110,85,250,30);
    btnLogin = new JButton("Ingresar");
    btnLogin.setBounds(160,175,100,30);
    btnLogin.addActionListener(this);

    panel.add(lblUsername);
    panel.add(lblPassword);
    panel.add(txtUsername);
    panel.add(txtPassword);
    panel.add(btnLogin);

    this.add(panel);
    this.setTitle(" Login ");
    this.setSize(400,300);
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setLocationRelativeTo(null);

  }
  public void actionPerformed(ActionEvent event){
    if(event.getSource() == this.btnLogin){
      try{
        File usuarios = new File("D:/Practica4/UsersPasswords/Users.txt");
        File claves = new File ("D:/Practica4/UsersPasswords/Passwords.txt");

        FileReader fr1 = new FileReader(usuarios);
        FileReader fr2 = new FileReader(claves);

        BufferedReader bf1 = new BufferedReader(fr1);
        BufferedReader bf2 = new BufferedReader(fr2);

        String sCadena1 = bf1.readLine();
        String sCadena2 = bf2.readLine();

        while ((sCadena1!=null)||(sCadena2!=null)){
          if(sCadena1.equals(txtUsername.getText())){
            if(sCadena2.equals(txtPassword.getText())){
             this.dispose();
             iguales = true;
             MenuJuego mj = new MenuJuego(numUser);
            }
          }
          sCadena1 = bf1.readLine();
          sCadena2 = bf2.readLine();
          if(iguales.equals(false)){
            numUser++;
          }
        }
        if(iguales.equals(false)){
          System.out.println(iguales);
          JOptionPane.showMessageDialog(null,"Verifique que escribio correctamente su usuario y contraseña");
        }
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
