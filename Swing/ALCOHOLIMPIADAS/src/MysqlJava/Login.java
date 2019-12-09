package MysqlJava;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login {

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
	public Login() {
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
		loginlbl.setBounds(123, 11, 174, 64);
		login.getContentPane().add(loginlbl);
		
		nombretxt = new JTextField();
		nombretxt.setBounds(190, 104, 131, 20);
		login.getContentPane().add(nombretxt);
		nombretxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(59, 107, 99, 17);
		login.getContentPane().add(lblNewLabel);
		
		contratxt = new JPasswordField();
		contratxt.setBounds(190, 152, 131, 20);
		login.getContentPane().add(contratxt);
		contratxt.setColumns(10);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.setBounds(73, 207, 89, 23);
		loginbtn.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					ConexionFrame cf = new ConexionFrame();
					cf.setVisible(true);
			}
		});
		login.getContentPane().add(loginbtn);
		
		
		JButton exitbtn = new JButton("Cerrar");
		exitbtn.setBounds(261, 207, 89, 23);
		exitbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		login.getContentPane().add(exitbtn);
		
		JLabel label = new JLabel("Contraseña");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(59, 155, 99, 17);
		login.getContentPane().add(label);
		
		
	}

}
