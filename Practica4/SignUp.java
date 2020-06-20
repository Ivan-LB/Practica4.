import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Dimension;

class SignUp extends JFrame implements ActionListener{
  /* Declarar Variables */
  JPanel panel;
  Font font = new Font("arial",Font.BOLD,25);
  JLabel lblUser;
  JLabel lblPassword;
  JTextField txtUserNew;
  JCheckBox visible;
  JPasswordField txtPasswordNew;
  JButton btnListo;
  JButton btnBack;
  JToggleButton btnVisible;
  ArrayList<String> usuarios = new ArrayList<String>();
  int flag = 0;

  public SignUp(){
      /* Ajustar Variables */
      panel = new JPanel();
      panel.setLayout(null);
  		panel.setBackground(Color.orange);
      usuarios = Archivo.leerTodo("D:/Practica4/UsersPasswords/Users.txt");
      lblUser = new JLabel("Usuario: ");
      lblUser.setBounds(5,25,75,30);
      lblUser.setFont(font);
      lblPassword = new JLabel("Clave: ");
      lblPassword.setBounds(5,85,75,30);
      lblPassword.setFont(font);
      txtUserNew = new JTextField();
      txtUserNew.setBounds(100,25,200,30);
      txtUserNew.setFont(font);
      txtPasswordNew = new  JPasswordField();
      txtPasswordNew.setEchoChar('*');
      txtPasswordNew.setFont(font);
      txtPasswordNew.setBounds(100,85,200,30);
      btnListo = new JButton("Registrar");
      btnListo.setBounds(5,175,100,30);
      btnListo.addActionListener(this);
      btnBack = new JButton("Volver al menu");
      btnBack.setBounds(110,175,200,30);
      btnBack.addActionListener(this);
      btnVisible = new JToggleButton();
      btnVisible.setIcon(new ImageIcon(((new ImageIcon("./Imagenes/VisibleOff.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
      btnVisible.setSelectedIcon(new ImageIcon(((new ImageIcon("./Imagenes/VisibleOn.png")).getImage()).getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
      btnVisible.setBounds(310,85,30,30);
      ItemListener itemListener = new ItemListener(){
        public void itemStateChanged(ItemEvent ItemEvent){
          int state = ItemEvent.getStateChange();
          if(state == ItemEvent.SELECTED){
            txtPasswordNew.setEchoChar((char)0);
          }else{
            txtPasswordNew.setEchoChar('*');
            txtPasswordNew.setFont(font);
          }
        }
      };
      btnVisible.addItemListener(itemListener);

      panel.add(lblUser);
      panel.add(lblPassword);
      panel.add(txtUserNew);
      panel.add(txtPasswordNew);
      panel.add(btnListo);
      panel.add(btnBack);
      panel.add(btnVisible);

      this.add(panel);
      this.setTitle(" Registro ");
  		this.setSize(400,300);
  		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
  		this.setVisible(true);
  		this.setLocationRelativeTo(null);
  }
  public void actionPerformed(ActionEvent event){
  	if(event.getSource() == this.btnListo){
      for (int i=0;i<usuarios.size(); i++) {
        if(txtUserNew.getText().equals(usuarios.get(i))) {
          JOptionPane.showMessageDialog(null,"El usuario ya existe intente con otro");
          flag++;
        }
      }
      if(flag==0){
        Archivo.CrearArchivo(txtUserNew.getText(),"Users.txt");
        Archivo.CrearArchivo(txtPasswordNew.getText(),"Passwords.txt");
        Archivo.CrearArchivo("0","Puntajes.txt");
        JOptionPane.showMessageDialog(null, "Usuario Registrado con exito...");
    	}
    }else if(event.getSource() == this.btnBack){
      this.dispose();
      InicioSesion f = new InicioSesion();
    }
  }
}
