package control;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class VentanaPrinci extends JFrame {
	static Metodos method = new Metodos ();
    private DefaultTableModel model;
	private JFrame frame;
	private JTable table;
	static Connection conn;
	static BaseDatosConexion bd = new BaseDatosConexion();
	private int fila = 0, id_columna=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrinci window = new VentanaPrinci();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrinci() throws ClassNotFoundException, SQLException {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		tableChanged();

	}

	
	
	public void tableChanged( ) {
	this.setBounds(100, 100, 550, 600);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.getContentPane().setLayout(null);

	JPanel contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setContentPane(contentPane);
	table = new JTable();
	table.setBounds(131, 93, 1, 1);
	contentPane.add(table);

	contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
	
	
	JButton retobtn = new JButton("Ver Retos");
	retobtn.setBounds(73, 207, 89, 23);

	contentPane.add(retobtn);

	retobtn.addActionListener(new ActionListener()

	{

		@Override
		public void actionPerformed(ActionEvent e) {

			VentanaRetos reto = null;
			try {
				reto = new VentanaRetos();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			reto.setVisible(true);
			reto.setSize(550, 600);
			setVisible(false);
			
			
		}
		
		
	});

	JButton loginbtn = new JButton("Borrar Usuario");
	loginbtn.setBounds(73, 207, 89, 23);

	contentPane.add(loginbtn);

	loginbtn.addActionListener(new ActionListener()

	{

		@Override
		public void actionPerformed(ActionEvent e) {

			 try {
				 String id=table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
			id_columna = Integer.parseInt(id);
			System.out.println("llega");
			method.deleteUser(id_columna);
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
				
			 
			
				
				TablaNueva();
			//if(table.getSelectedRow() == 0) {
				
			//}
		}
		
		
	});
	
	
	
	JButton updatebtn = new JButton("Actualizar Datos");
	loginbtn.setBounds(73, 207, 89, 23);

	contentPane.add(updatebtn);

	ActionListener ac = new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
			String id=table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
			System.out.println(id);
			id_columna = Integer.parseInt(id);
			int pos = id_columna;
			String name=table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
			String type=table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
			int type_columna = Integer.parseInt(type);
			String pass=table.getModel().getValueAt(table.getSelectedRow(), 3).toString();
			User u = new User(id_columna,name,type_columna,pass);
			System.out.println("llega");
			method.updateUser(u,pos);
			
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
			TablaNueva();
		}
	};
	updatebtn.addActionListener(ac);

	
	JButton crearbtn = new JButton("Crear Usuario");
	crearbtn.setBounds(73, 207, 89, 23);

	contentPane.add(crearbtn);

	crearbtn.addActionListener(new ActionListener()
	

	{
			public void actionPerformed(ActionEvent e) {
				CrearUsuario use = null;
				try {
					use = new CrearUsuario();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				use.setVisible(true);
				
				TablaNueva();
			};
			
	});
	JButton sv = new JButton("Servidor Chat");
	crearbtn.setBounds(73, 207, 89, 23);

	contentPane.add(sv);
	
	sv.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MarcoServidor ms = new MarcoServidor();
			ms.setVisible(true);
			
			
			
		}
	});
	JButton chat = new JButton("Cliente Chat");
	crearbtn.setBounds(73, 207, 89, 23);

	contentPane.add(chat);
	
	chat.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
			
			
			MarcoCliente mc = new MarcoCliente();
			mc.setVisible(true);
		}
	});
	TablaNueva();
	
}
	public void modificar() {
		String id=table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
		System.out.println(id);
		id_columna = Integer.parseInt(id);
		int pos = id_columna;
		String name=table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
		String type=table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
		int type_columna = Integer.parseInt(type);
		String pass=table.getModel().getValueAt(table.getSelectedRow(), 3).toString();
		User u = new User(id_columna,name,type_columna,pass);
		System.out.println("llega");
		method.updateUser(u,pos);
	}
	
	public void TablaNueva() {
		Connection conn = null;
		try {
			conn = bd.Conectar();
		} catch (BDExcepcion e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		String sqltable = "SELECT * FROM User";
		Statement stm;
		 model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Tipo");
		model.addColumn("Pass");

		table.setModel(model);

		String[] datos = new String[4];
		try {
			conn = bd.Conectar();
			stm = conn.createStatement();

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
		} catch (BDExcepcion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void windowClosing(WindowEvent e) {  
	    dispose();  
	} 
	
	

}
