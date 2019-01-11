package LP;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
//import java.util.logging.*;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import LN.clsBD;
import LN.clsGestor;

/**
 * Clase para crear la pantalla de inicio de sesi�n. En ella el usuario tendr� la posibilidad de iniciar sesi�n o de crearse una 
 * cuenta en caso de no tenerla. Al crearse una cuenta nueva el usuario recibir� un email de confirmaci�n. 
 * @author Gabriela Garaizabal, Maarten Handels y Laura Llorente
 *
 */
public class frmInicioSesion extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
//	private static Logger logger = Logger.getLogger(frmInicioSesion.class.getName());
	
	private JLabel lblInicio;
	private JLabel lblUsu; 
	private JLabel lblRegistro;
	private JLabel lblContra; 
	private JLabel lblNombre;
	private JLabel lblApe;
	private JLabel lblCorreo;
	private JLabel lblUsu2;
	private JLabel lblContra2;
	private JLabel lblFecha; 
	
	private JTextField txtUsu;
	private JTextField txtNombre;
	private JTextField txtApe;
	public static JTextField txtCorreo;
	public static JTextField txtUsu2;
	
	
	private JPasswordField contraField; 
	private  JPasswordField contraField2; 
	
	private JButton btnEntrar;
	private JButton btnRegistrar; 
	
	private String nombre; 
	private String apellido;
	private String email;
	private String nombreUsu;
	private String contrasenya;
	
	private String usuario;
	private String contra; 
	
	private Image imagenFondo;
	private URL fondo;
	
	private InputMap map; 
	
	private clsGestor gestor; 

	/**
	 * En este metodo se encuentran todos los elementos necesarios para crear la pantalla de inicio de sesion. 
	 */
	public frmInicioSesion() 
	{	
		setTitle("Inicio de Sesi�n");
		setSize(460,550);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
		//Inserci�n de imagen
//		ImageIcon img = new ImageIcon("src/LN/logo.png");
//		JLabel fondo = new JLabel(img);	
//		JLabel fondo4 = new JLabel (img);
//		fondo.setBounds(-10,-20,100,100);
//		fondo4.setBounds(360,440,100,100);
//		panel.add(fondo);
//		panel.add(fondo4);
//		fondo.setVisible(true);
		
		//Inserci�n de la imagen del fondo en el panel 
	    fondo = this.getClass().getResource("/LN/Imagen_Fondo.png");
	    imagenFondo = new ImageIcon(fondo).getImage();
	    Container contenedor = getContentPane();
	    contenedor.add(panel);
	    panel.setLayout(null);
	    
	    //Atributos para insertar la hora y la fecha 
	    Calendar cal = Calendar.getInstance(); 
		String fecha; 
		Font font = new Font("Century Gothic",Font.BOLD,13); 
		
		fecha = cal.get(Calendar.DATE) +"/"+ cal.get(Calendar.MONTH) +"/"+cal.get(Calendar.YEAR); 
		
		lblFecha = new JLabel(); 
		lblFecha.setText(fecha);
		lblFecha.setBounds(360, 5, 100, 30);
		lblFecha.setFont(font);
		lblFecha.setForeground(Color.WHITE);
		panel.add(lblFecha);
	    		
		//Fuentes de letra para la pantalla 
		Font f1 = new Font("Century Gothic",Font.BOLD,22);
		Font f2 = new Font("Century Gothic",Font.BOLD,15); 
		Font f3 = new Font("Century Gothic",Font.BOLD,13); 
		
		//Creaci�n de la pantalla 
		lblInicio = new JLabel("INICIAR SESI�N"); 
		lblInicio.setFont(f1);
		lblInicio.setBounds(160,10,220,40);
		lblInicio.setForeground(Color.white);
		panel.add(lblInicio);
				
		lblUsu = new JLabel("Usuario");
		lblUsu.setFont(f2);
		lblUsu.setBounds(20,50,100,50);
		lblUsu.setForeground(Color.white);
		panel.add(lblUsu); 
		
		txtUsu = new JTextField(); 
		txtUsu.setBounds(110, 65, 300, 25);
		txtUsu.setFont(f3);
		panel.add(txtUsu);
		
		lblContra = new JLabel ("Contrase�a"); 
		lblContra.setFont(f2);
		lblContra.setBounds(20,100,300,30);
		lblContra.setForeground(Color.white);
		panel.add(lblContra);
		
		contraField = new JPasswordField();
		contraField.setBounds(110, 105, 300, 25);
		contraField.setFont(f2);
		contraField.requestFocus();
		panel.add(contraField);
		
		map = new InputMap(); 
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0,false),"pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		btnEntrar = new JButton ("Entrar"); 
		btnEntrar.setToolTipText("Pulse este bot�n si desea entrar al reproductor de m�sica");
		btnEntrar.setInputMap(0, map);
		btnEntrar.setFont(f2);
		btnEntrar.setBounds(200, 150, 100, 30);
		panel.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{				
//						logger.log(Level.INFO, "Comienzo inicio sesi�n");
						
						if(txtUsu.getText().isEmpty() || contraField.getPassword()==null)
						{
							JOptionPane.showMessageDialog(null,"Te faltan campos de informaci�n por rellenar","INICIO SESI�N",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							
							if (clsBD.comprUsuario(txtUsu.getText()) == true)
							{
								usuario = txtUsu.getText();
								
								if (clsBD.comprContra(txtUsu.getText(),String.valueOf(contraField.getPassword())) == true)
								{
								contra = new String (contraField.getPassword());
								JOptionPane.showMessageDialog(null, "Bienvenido a GML music","INICIO SESI�N",JOptionPane.INFORMATION_MESSAGE);
								frmReproductor pagina = new frmReproductor(); //LLEVAR A LA PANTALLA PRINCIPAL
								pagina.GUI();
								}
								else
								{
									JOptionPane.showMessageDialog(null,"La contrase�a introducida es incorrecta","INICIO SESI�N",JOptionPane.INFORMATION_MESSAGE);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null,"Ese nombre de usuario no esta registrado en la aplicacion","INICIO SESI�N",JOptionPane.INFORMATION_MESSAGE);
							}
					}
				}});	

		lblRegistro = new JLabel("REGISTRARSE"); 
		lblRegistro.setFont(f1);
		lblRegistro.setBounds(185,200,180,40);
		lblRegistro.setForeground(Color.white);
		panel.add(lblRegistro);
		
		lblNombre = new JLabel ("Nombre");
		lblNombre.setFont(f2);
		lblNombre.setBounds(20,240,100,50);
		lblNombre.setForeground(Color.white);
		panel.add(lblNombre); 
		
		txtNombre = new JTextField(); 
		txtNombre.setBounds(110, 255, 300, 25);
		txtNombre.setFont(f3);
		panel.add(txtNombre);
		
		lblApe = new JLabel ("Apellido");
		lblApe.setFont(f2);
		lblApe.setBounds(20,280,100,50);
		lblApe.setForeground(Color.white);
		panel.add(lblApe);
		
		txtApe = new JTextField(); 
		txtApe.setBounds(110, 295, 300, 25);
		txtApe.setFont(f3);
		panel.add(txtApe);
		
		lblCorreo = new JLabel ("Correo");
		lblCorreo.setFont(f2);
		lblCorreo.setBounds(20,320,100,50);
		lblCorreo.setForeground(Color.white);
		panel.add(lblCorreo);
		
		txtCorreo = new JTextField(); 
		txtCorreo.setBounds(110, 335, 300, 25);
		txtCorreo.setFont(f3);
		panel.add(txtCorreo);
		
		lblUsu2 = new JLabel ("Usuario");
		lblUsu2.setFont(f2);
		lblUsu2.setBounds(20,360,100,50);
		lblUsu2.setForeground(Color.white);
		panel.add(lblUsu2);
		
		txtUsu2 = new JTextField(); 
		txtUsu2.setBounds(110, 375, 300, 25);
		txtUsu2.setFont(f3);
		panel.add(txtUsu2);
		
		lblContra2 = new JLabel ("Contrase�a");
		lblContra2.setFont(f2);
		lblContra2.setBounds(20,400,100,50);
		lblContra2.setForeground(Color.white);
		panel.add(lblContra2);
		
		contraField2 = new JPasswordField();
		contraField2.setBounds(110,415,300,25);
		contraField2.setFont(f2);
		contraField2.requestFocus();
		panel.add(contraField2);
		
		btnRegistrar = new JButton ("Registrar"); 
		btnRegistrar.setFont(f2);
		btnEntrar.setToolTipText("Pulse este bot�n si desea crearse una cuenta");
		btnRegistrar.setBounds(200, 460, 100, 30);
		panel.add(btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				gestor = new clsGestor();
				
				if(txtNombre.getText().isEmpty() || txtApe.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtUsu2.getText().isEmpty() || contraField2.getPassword()==null )
				{
					JOptionPane.showMessageDialog(null,"Te faltan campos de informaci�n por rellenar","INICIO SESI�N",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					nombre = txtNombre.getText();
					apellido = txtApe.getText();
					
					if (clsBD.comprUsuario(txtUsu2.getText()) == false)
					{
						nombreUsu = txtUsu2.getText();
						contrasenya = new String(contraField2.getPassword());
						
						Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
						 
						email = txtCorreo.getText();
						 
						Matcher mather = pattern.matcher(email);
					    if (mather.find() == true) 
					    {				
					    	clsBD.a�adirUsuario(nombre, apellido, email, nombreUsu, contrasenya);
					    	//gestor.correo(email);
					    	
//					    	gestor.enviarCorreo(email);
					    	JOptionPane.showMessageDialog(null,"Su registro se ha realizado satisfactoriamente","INICIO SESI�N",JOptionPane.INFORMATION_MESSAGE);
					    	
					    	dispose();
					    	JOptionPane.showMessageDialog(null, "Bienvenido a GML music","INICIO SESI�N",JOptionPane.INFORMATION_MESSAGE);
					    	//Llama a la pantalla frmReproductor
					        frmReproductor player = new frmReproductor();
					        player.GUI();
					    } 
					    else 
					    {
							JOptionPane.showMessageDialog(null,"El correo ingresado no es v�lido","INICIO SESI�N",JOptionPane.ERROR_MESSAGE);
								
							txtCorreo.setText(null);
							txtCorreo.requestFocus();
					    }
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Su Nombre de usuario ya est� ocupado","INICIO SESI�N",JOptionPane.INFORMATION_MESSAGE);
						
						txtUsu2.setText(null);
						txtUsu2.requestFocus();
					}
					
				
				}
			}
		});
	}
	
	/**
	 * M�todo para a�adir la imagen de fondo.
	 */
	public JPanel panel = new JPanel()
	{
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g)
	    {
	    	g.drawImage(imagenFondo, 0, 0, getWidth(),getHeight(),this);
	    }
	};
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}