import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;


class InicioSesion extends JFrame implements ActionListener{
  /* Inicializacion de variables */
  JButton btnLogin;
  JButton btnSignUp;
  JPanel panel;
  JLabel lblV;

	public InicioSesion()
	{
    /* Asignacion variables */
    panel = new JPanel();
    panel.setLayout(null);
    Color cGris = new Color(211,211,211);
    Color cAzul = new Color(175,175,225);
    panel.setBackground(cGris);

    lblV= new JLabel(new ImageIcon("./Imagenes/inicio.png"));
    lblV.setBounds(110,30,70,120);

    btnLogin = new JButton("Iniciar Sesion");
    btnLogin.setBounds(120,120,150,30);
    btnLogin.setBorder(null);
    btnLogin.setBackground(cAzul);
    btnLogin.setVerticalAlignment(SwingConstants.CENTER);
    btnLogin.addActionListener(this);

    btnSignUp = new JButton("Registrarse ");
    btnSignUp.setBounds(150,190,100,30);
    btnSignUp.setBorder(null);
    btnSignUp.setBackground(cAzul);
    btnSignUp.setVerticalAlignment(SwingConstants.CENTER);
    btnSignUp.addActionListener(this);

    panel.add(btnLogin);
    panel.add(btnSignUp);

    this.add(panel);
    this.setTitle(" Inicio ");
		this.setSize(300,300);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
    this.setResizable(false);

	}
  public void actionPerformed(ActionEvent event){
  		if(event.getSource() == this.btnLogin){
  			this.dispose();
        Login l = new Login();

  		}else if(event.getSource() == this.btnSignUp){
        this.dispose();
        SignUp su = new SignUp();
      }
  }
}
