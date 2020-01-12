package control;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class Login {
	static Metodos method = new Metodos ();
	private boolean check= false;
	private JFrame login;
	private JTextField nombretxt;
	private JPasswordField contratxt;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.login.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public  Login() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		
		login = new JFrame();
		login.setBounds(100, 100, 450, 300);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.getContentPane().setLayout(null);
		
		JLabel loginlbl = new JLabel("LOGIN");
		loginlbl.setHorizontalAlignment(SwingConstants.CENTER);
		loginlbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		loginlbl.setBounds(111, 10, 174, 64);
		login.getContentPane().add(loginlbl);
		
		nombretxt = new JTextField();
		nombretxt.setBounds(190, 85, 131, 20);
		login.getContentPane().add(nombretxt);
		nombretxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(59, 85, 99, 17);
		login.getContentPane().add(lblNewLabel);
		
		contratxt = new JPasswordField();
		contratxt.setBounds(190, 136, 131, 20);
		login.getContentPane().add(contratxt);
		contratxt.setColumns(10);
		
		
		Object marca=true, vuelta = false;
		vuelta = method.comprobarCheck();
		HashMap<Integer, String> datos = null;
		datos = new HashMap<Integer, String>();
		datos.put(0, "");
		datos.put(1, "");
		if( marca == vuelta) {
			File file = new File("src/control/data.txt"); 
			  
			BufferedReader br;
			  
			  
			try {
				br = new BufferedReader(new FileReader(file));
				String a;
				String st; 
				st = br.readLine();
				 int cont=0;
				StringTokenizer tokens=new StringTokenizer(st);
				
				while(tokens.hasMoreTokens()){
						 a=tokens.nextToken().toString();
						datos.put(cont, a);
						
						cont++;
			       }
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			  
			 
			
		}
		String name = datos.get(0);
		nombretxt.setText(name);
		String pass = datos.get(1);
		contratxt.setText(pass);
		
		JCheckBox check1 = new JCheckBox("Recuerdame");
        check1.setBounds(296,224,150,30);
        login.getContentPane().add(check1);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.setBounds(69, 185, 89, 23);
		loginbtn.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
					check=method.checkUsername(nombretxt.getText().toString(), contratxt.getText().toString());
					if (check==true) {
						if(check1.isSelected()==true) {
							 try {
							FileOutputStream fos = new FileOutputStream("src/control/data.txt");
					        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
					        BufferedWriter out = new BufferedWriter(osw);
					        String text = nombretxt.getText()+" "+contratxt.getText();
					       
								out.write(text);
								out.flush();
						        fos.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Error al Logearse", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
					        
						
						}
						}
						VentanaPrinci cf;
						try {
							cf = new VentanaPrinci();
							cf.setVisible(true);
							login.setVisible(false);
							
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			}
		});
		login.getContentPane().add(loginbtn);
		
		
		JButton exitbtn = new JButton("Cerrar");
		exitbtn.setBounds(263, 185, 89, 23);
		exitbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		login.getContentPane().add(exitbtn);
		
		JLabel label = new JLabel("Contrasena");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(59, 136, 99, 17);
		login.getContentPane().add(label);
		
		
	}
	
	
	

}

