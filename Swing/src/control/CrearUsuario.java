package control;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CrearUsuario  extends JFrame{
	static Metodos method = new Metodos ();
	private JFrame frame;
	private JTextField Fname;
	private JTextField Fpass;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearUsuario window = new CrearUsuario();
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
	public  CrearUsuario() throws ClassNotFoundException, SQLException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws ClassNotFoundException, SQLException{
		
		this.setBounds(100, 100, 373, 255);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		Fname = new JTextField();
		Fname.setBounds(142, 67, 130, 26);
		this.getContentPane().add(Fname);
		Fname.setColumns(10);
		
		Fpass = new JTextField();
		Fpass.setBounds(142, 143, 130, 26);
		this.getContentPane().add(Fpass);
		Fpass.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre usuario:");
		lblNewLabel_1.setBounds(24, 72, 106, 16);
		this.getContentPane().add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_3 = new JLabel("Contraseña:");
		lblNewLabel_3.setBounds(34, 148, 94, 16);
		this.getContentPane().add(lblNewLabel_3);
		
		JLabel lblCrearUsuario = new JLabel("Crear Usuario");
		lblCrearUsuario.setBounds(142, 21, 106, 34);
		this.getContentPane().add(lblCrearUsuario);
		
		
		 comboBox = new JComboBox();
		comboBox.setBounds(150, 105, 85, 27);
		this.getContentPane().add(comboBox);
		
		
		comboBox.addItem(new ComboItem("Usuario", "0"));
		comboBox.addItem(new ComboItem("Admin", "1"));
		
		JButton btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String x = String.valueOf(comboBox.getSelectedItem());
				method.crearUsuario(Fname.getText(),comboBox.getSelectedIndex(), Fpass.getText());
				
			}
		});
		
		btnCrearUsuario.setBounds(85, 181, 117, 29);
		this.getContentPane().add(btnCrearUsuario);
		
		
		
		
		
		
	}
	public void windowClosing(WindowEvent e) {  
	    dispose();  
	} 
}
