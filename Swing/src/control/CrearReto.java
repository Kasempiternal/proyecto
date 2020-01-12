package control;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;

public class CrearReto  extends JFrame{
	static Metodos method = new Metodos ();
	private JFrame frame;
	private JTextField Fname;
	private JPasswordField Fpass;
	private JComboBox comboBox;
	private VentanaPrinci  vp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearReto window = new CrearReto();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	class ComboItem
	{
	    private String key;
	    private String value;

	    public ComboItem(String key, String value)
	    {
	        this.key = key;
	        this.value = value;
	    }

	    @Override
	    public String toString()
	    {
	        return key;
	    }

	    public String getKey()
	    {
	        return key;
	    }

	    public String getValue()
	    {
	        return value;
	    }
	}
	/**
	 * Create the application.
	 */
	public  CrearReto() throws ClassNotFoundException, SQLException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws ClassNotFoundException, SQLException{
		
		this.setBounds(100, 100, 373, 255);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		Fname = new JTextField();
		Fname.setBounds(142, 56, 130, 26);
		this.getContentPane().add(Fname);
		Fname.setColumns(10);
		
		Fpass = new JPasswordField();
		Fpass.setBounds(142, 128, 130, 26);
		this.getContentPane().add(Fpass);
		Fpass.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre Reto:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(23, 60, 106, 16);
		this.getContentPane().add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_3 = new JLabel("Contraseña:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(23, 132, 94, 16);
		this.getContentPane().add(lblNewLabel_3);
		
		JLabel lblCrearUsuario = new JLabel("Crear Reto");
		lblCrearUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCrearUsuario.setBounds(124, 11, 106, 34);
		this.getContentPane().add(lblCrearUsuario);
		
		
		 comboBox = new JComboBox();
		comboBox.setBounds(152, 93, 85, 27);
		this.getContentPane().add(comboBox);
		
		
		comboBox.addItem(new ComboItem("1", "1"));
		comboBox.addItem(new ComboItem("2", "2"));
		comboBox.addItem(new ComboItem("3", "3"));
		comboBox.addItem(new ComboItem("4", "4"));
		comboBox.addItem(new ComboItem("5", "5"));
		comboBox.addItem(new ComboItem("6", "6"));
		comboBox.addItem(new ComboItem("7", "7"));
		comboBox.addItem(new ComboItem("8", "8"));
		comboBox.addItem(new ComboItem("9", "9"));
		comboBox.addItem(new ComboItem("10", "10"));
		comboBox.addItem(new ComboItem("11", "11"));
		comboBox.addItem(new ComboItem("12", "12"));
		comboBox.addItem(new ComboItem("13", "13"));
		comboBox.addItem(new ComboItem("14", "14"));
		comboBox.addItem(new ComboItem("15", "15"));
		comboBox.addItem(new ComboItem("16", "16"));
		
		
		JButton btnCrearUsuario = new JButton("Crear Reto");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String x = String.valueOf(comboBox.getSelectedItem());
				method.crearReto(Fname.getText(),comboBox.getSelectedIndex(), Fpass.getText());
				
			}
		});
		
		btnCrearUsuario.setBounds(44, 165, 117, 29);
		this.getContentPane().add(btnCrearUsuario);
		
		JButton btnActualizar = new JButton("ActualizarDatos ");
		btnActualizar.setBounds(191, 165, 137, 29);
		getContentPane().add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vp.modificar();

			}
		});
		
		
		
		
		
		
		
	}
	public void windowClosing(WindowEvent e) {  
	    dispose();  
	} 
}
