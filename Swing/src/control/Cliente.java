//importamos los paquetes
package control;

import javax.swing.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.io.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


//clase main en laque colocamos el marco
public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoCliente mimarco=new MarcoCliente();
		//esto hace que la ventana no se pueda cambiar de tama�o
		mimarco.setResizable(false);mimarco.setTitle("Ventana Cliente");
		
		
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

//escribimos ala alamina
class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(800,500,480,550);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		
		addWindowListener(new EnvioOnline());
		}	
	
}
//------------------------------------Envio online se�al ----------------------------------------------
class EnvioOnline extends WindowAdapter{
	
	
	
	public void windowOpened(WindowEvent e) {
		
		try {
			//creamos socket
			Socket misocket = new Socket("192.168.0.156", 9999);
			//creamos la instancia de paquete datos
			PaqueteEnvio datos=new PaqueteEnvio();
			//usamos el setMensaje para enviarle esta se�al
			datos.setMensaje(" online");
			//con un objectoutputstream desmigamos el objeto
			ObjectOutputStream paquete_datos = new ObjectOutputStream(misocket.getOutputStream());
			//y lo escribimos
			paquete_datos.writeObject(datos);
			//cerramos socket
			misocket.close();
			
		}
		//Controlamos excepcion
		catch(Exception e2) {
			
			System.out.println(e2.getMessage());
		}
		
	}
	
}
//---------------------------------------------------------------------------------------------------

//escribimos la lamina rellenandola
class LaminaMarcoCliente extends JPanel implements Runnable{
	
	public LaminaMarcoCliente(){
		
		String nick_usuario=JOptionPane.showInputDialog("Nick: ");
		//controla si el nick esta en blanco o vacio, y en ese caso sale
		if (nick_usuario==null || (nick_usuario!=null && ("".equals(nick_usuario)))) {
			
			System.exit(0);
			
		}
		
		JLabel n_nick=new JLabel("Nick:");
		
		add(n_nick);
		
		nick =new JLabel();
		
		nick.setText(nick_usuario);
		
		add(nick);
	
		JLabel texto=new JLabel("Online:");
		
		add(texto);
		
		ip =new JComboBox();
		
		//ip.addItem("172.16.5.9");
		
		//ip.addItem("192.168.0.155");
		
	
		
		
		add(ip);
		
		campochat = new JTextArea(25,40);
		//le desavilitamos la edicion al campo
		campochat.setEditable(false);
		//le a�adimos el scroll al campo
		scroll = new JScrollPane(campochat);
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
		add(scroll);		
	
		campo1=new JTextField(40);
		
		add(campo1);
	
		miboton=new JButton("Enviar");
		
		EnviaTexto mievento = new EnviaTexto();
		
		miboton.addActionListener(mievento);
		
		add(miboton);	
		//creamos el hilo
		Thread mihilo=new Thread(this);
		//lo arrancamos
		mihilo.start();
		
	}
	
	//esta clase es la encargada de enviar el texto al server al clicar en el boton
	private class EnviaTexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			// TODO Auto-generated method stub
			
			campochat.append("\n Yo:"+campo1.getText()+" para "+ip.getSelectedItem().toString());
			
			
			try {
				//creamos el socket con la ipv4 del server
				Socket misocket = new Socket("192.168.0.156", 9999);
				//creamos el objeto
				PaqueteEnvio datos=new PaqueteEnvio();
				//coojemos los datos de los campos
				datos.setNick(nick.getText());
				
				datos.setIp(ip.getSelectedItem().toString());
				
				datos.setMensaje(campo1.getText());
				//creamos el objectoutputstream
				ObjectOutputStream paquete_datos = new ObjectOutputStream(misocket.getOutputStream());
				//escribimos
				paquete_datos.writeObject(datos);
				//cerramos socket
				misocket.close();
				//borramos el campo 1 que es la barra de escribir
				campo1.setText("");
				
				//DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
				
				//flujo_salida.writeUTF(campo1.getText());
				
			//	flujo_salida.close();
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
		
	}
		
		
		//instanciamos estos objetos
	private JTextField campo1;
	
	private JComboBox ip;
	
	private JLabel nick;
	
	private JTextArea campochat;
	
	private JButton miboton;
	
	private JScrollPane scroll;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			//creamos el socket para la lectura
			ServerSocket servidor_cliente = new ServerSocket(9090);
			
			Socket cliente;
			
			PaqueteEnvio paqueteRecibido;
			
			//Creamos la clase Player con la ruta
			Player apl;

		      
			//creamos un bucle infinito para que siempre este a la escucha
			while(true) {
				
				//aceptamos la conexion
				cliente = servidor_cliente.accept();
				//creamos una object inputstream para desmigar el paquete que nos viene
				ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());
				//lo leemos
				paqueteRecibido = (PaqueteEnvio) flujoentrada.readObject();
				// si el contenido es diferente a " online" lo mostramos en el area de texto
				if(!paqueteRecibido.getMensaje().equals(" online")) {
					
					campochat.append("\n"+paqueteRecibido.getNick()+": "+paqueteRecibido.getMensaje());
					//ejecutamos la clase para que nos lance el sonido
					apl = new Player(new FileInputStream("sound\\tic.mp3"));
					apl.play();
					apl.close();
					
					
					
				}else {
				 // sino es la se�al de conexion de un nuevo usuario por lo que lo mostramos
				campochat.append("\n Usuarios online: " + paqueteRecibido.getIps());
				//creamos un arraylist
				ArrayList <String> IpsMenu = new ArrayList <String>();
				//usamos el metodo getIps para coger el darto
				IpsMenu=paqueteRecibido.getIps();
				// borramos el conbobox
				ip.removeAllItems();
				//le a�adimos el item de grupo
				ip.addItem("Grupo");
				// en un bucle for each leeemos el array list y a�adimos uno a uno en el combobox como hemos hecho anteriormente con grupo
				for(String z:IpsMenu) {
					
					ip.addItem(z);
					
				}
				
				}
				
			}
			
		}
		catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	
}

//clase paqueteenvio con  sus atributos, entre ellos un array list, y cada atributo con su correspondiente
//get y set
class PaqueteEnvio implements Serializable{
	
	private String nick,ip,mensaje;
	
	private ArrayList <String> Ips;
	
	

	public ArrayList<String> getIps() {
		return Ips;
	}

	public void setIps(ArrayList<String> ips) {
		Ips = ips;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
	
}