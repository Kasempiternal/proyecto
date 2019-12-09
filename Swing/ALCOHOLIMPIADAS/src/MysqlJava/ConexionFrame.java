package MysqlJava;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.PanelPeer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;
import java.awt.Font;

public class ConexionFrame extends JFrame {


	  	private DefaultTableModel tablas;
	
	  private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver" ;
	  private static final String URL = "jdbc:mysql://localhost:3306/Alcoholimpiadas" ;
	  private static final String USUARIO = "root" ;
	  private static final String CONTRASEÑA = "123456789" ;
	  private JTextField IDtxt;
	  private JTextField Tipotxt;
	  private JTextField Nombretxt;
	  private JTextField Passtxt;
	  private JTable table;
	  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConexionFrame window = new ConexionFrame();
					window.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConexionFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane panelDePestanas = new JTabbedPane(JTabbedPane.TOP);
		  panelDePestanas.setBounds(5, 5, 575, 450);
		  contentPane.add(panelDePestanas);
		  
		  
		  //PANEL 1:
		  
		  
		  JPanel panel1 = new JPanel();
		  panelDePestanas.addTab("Registro", panel1);
		  panel1.setLayout(null);
		  
		  JLabel Idlbl = new JLabel("ID:");
		  Idlbl.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		  Idlbl.setBounds(60, 85, 69, 28);
		  panel1.add(Idlbl);
		  
		  JLabel Tipolbl = new JLabel("Tipo:");
		  Tipolbl.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		  Tipolbl.setBounds(60, 197, 69, 28);
		  panel1.add(Tipolbl);
		  
		  JLabel Nombrelbl = new JLabel("Nombre:");
		  Nombrelbl.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		  Nombrelbl.setBounds(297, 85, 69, 28);
		  panel1.add(Nombrelbl);
		  
		  JLabel Passlbl = new JLabel("Pass:");
		  Passlbl.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		  Passlbl.setBounds(297, 197, 69, 28);
		  panel1.add(Passlbl);
		  
		 
		  IDtxt = new JTextField(20);
		  IDtxt.setBounds(139, 84, 98, 28);
		  panel1.add(IDtxt);
		  
		  Tipotxt = new JTextField(20);
		  Tipotxt.setBounds(137, 197, 100, 26);
		  panel1.add(Tipotxt);
		  
		  Nombretxt = new JTextField(20);
		  Nombretxt.setBounds(386, 85, 98, 26);
		  panel1.add(Nombretxt);
		  
		  Passtxt = new JTextField(20);
		  Passtxt.setBounds(386, 197, 98, 26);
		  panel1.add(Passtxt);
		  
		 
		  
		    JButton agregarbtn = new JButton("Agregar");
		    agregarbtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		    agregarbtn.setBounds(60, 311, 98, 38);
		  panel1.add(agregarbtn);
		  agregarbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
					try {
						Class.forName(CONTROLADOR);
						conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);		
						System.out.println("CONEXION OK");
						
						String nombre = Nombretxt.getText();
						int tipo = Integer.parseInt(Tipotxt.getText());
						String pass = Passtxt.getText();
								
						
						String sqlUpdate = "INSERT INTO user (username,user_type,user_pass) VALUES ('"+ nombre +"','"+tipo+"','"+pass+"')";
						Statement stm = conn.createStatement();
						stm.executeUpdate(sqlUpdate);
						
						JOptionPane.showMessageDialog(null, "Usuario agregado correctamente" );
						
						
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, "El usuario ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
						
				}
				// TODO Auto-generated method stub
				
		});
	
		  
		 /* JButton buscarbtn = new JButton("Buscar");
		  buscarbtn.setBounds(236, 326, 89, 23);
		  panel1.add(buscarbtn);
		  buscarbtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Connection conn = null;
						try {
							Class.forName(CONTROLADOR);
							conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);		
							System.out.println("CONEXION OK");
							
							String nombre = Nombretxt.getText();
							int tipo = Integer.parseInt(Tipotxt.getText());
							String pass = Passtxt.getText();
									
							
							String sqlUpdate = "SELECT * FROM user WHERE username  = '"+ nombre +"' AND user_type = '"+ tipo +"' AND user_pass = '"+ pass +"'";
							Statement stm = conn.createStatement();
							stm.executeQuery(sqlUpdate);
							
														
							//JOptionPane.showMessageDialog(null, "Usuario encontrado" , "USUARIO:", JOptionPane.PLAIN_MESSAGE);
							
							
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null, "El usuario no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
							
					}
					// TODO Auto-generated method stub
					
			});*/
		  
		  JButton eliminarbtn = new JButton("Eliminar");
		  eliminarbtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		  eliminarbtn.setBounds(239, 311, 98, 38);
		  panel1.add(eliminarbtn);
		  
		
		  eliminarbtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Connection conn = null;
						try {
							Class.forName(CONTROLADOR);
							conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);		
							System.out.println("CONEXION OK");
							
							String nombre = Nombretxt.getText();
							int tipo = Integer.parseInt(Tipotxt.getText());
							String pass = Passtxt.getText();
									
							
							String sqlUpdate = "DELETE FROM user  WHERE username  = '"+ nombre +"' AND user_type = '"+ tipo +"' AND user_pass = '"+pass+"'";
							Statement stm = conn.createStatement();
							stm.executeUpdate(sqlUpdate);
							
							JOptionPane.showMessageDialog(null,"Usuario eliminado correctamente");
							
							
						}catch(Exception e1){
							JOptionPane.showMessageDialog(null,"Error al eliminar el usuario", null, JOptionPane.ERROR_MESSAGE);
						}
							
					}
					// TODO Auto-generated method stub
					
			}); 
		  JButton salirbtn = new JButton("Cerrar");
		  salirbtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		  salirbtn.setBounds(422, 311, 98, 38);
		  panel1.add(salirbtn);
		  
		  JLabel registrolbl = new JLabel("REGISTRO DE USUARIOS");
		  registrolbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
		  registrolbl.setHorizontalAlignment(SwingConstants.CENTER);
		  registrolbl.setBounds(150, 11, 298, 28);
		  panel1.add(registrolbl);
		  salirbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			System.exit(0);	
			}
		});
		  
		  
		  //PANEL 2:
		  
		  
		  JPanel panel2 = new JPanel();
		  panelDePestanas.addTab("Usuarios", null, panel2, null);
		  
		  table = new JTable();
		  table.setBounds(131, 93, 1, 1);
		  panel2.add(table);
		  
		  panel2.add(new JScrollPane(table), BorderLayout.CENTER);
		 
		  
			Connection conn = null;
		  try {
			Class.forName(CONTROLADOR);
			conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);		
			System.out.println("CONEXION OK");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		  
		  
		  String sqltable = "SELECT * FROM user";
		  Statement stm;
		  DefaultTableModel model = new DefaultTableModel();
		  
		  model.addColumn("ID");
		  model.addColumn("Nombre");
		  model.addColumn("Tipo");
		  model.addColumn("Pass");
		  
		  table.setModel(model);
		  
		  String[] datos = new String[4];
		  try {
			stm = Conectar().createStatement();
			
			ResultSet result = stm.executeQuery(sqltable);
			
			while (result.next()) {
				datos[0] = result.getString(1);
				datos[1] = result.getString(2);
				datos[2] = result.getString(3);
				datos[3] = result.getString(4);
				model.addRow(datos);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		  

		  
			
		  
		  
		 
		  
	}
	
	
	//CONEXION A LA BASE DE DATOS
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection Conectar() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);		
			System.out.println("CONEXION OK");
		}catch(SQLException e){
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
		return conn;
	}
}
