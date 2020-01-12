//importamos los paquetes
package control;

import javax.swing.*;


import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;


//clase main en laque colocamos el marco
public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		//esto hace que la ventana no se pueda cambiar de tama�o
		mimarco.setResizable(false);
		
		mimarco.setTitle("Ventana Servidor");
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}
//escribimos ala alamina y la rellenamos
class MarcoServidor extends JFrame implements Runnable {
	
	public MarcoServidor(){
		
		setBounds(1200,500,480,550);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		//desavilitamos la edicion del area de texto
		areatexto.setEditable(false);
		// y le a�adimos un scroll
		scroll = new JScrollPane(areatexto);
		
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
		add(scroll);		
		
		
		setVisible(true);
		//creamos el hilo
		Thread mihilo = new Thread (this);
		//lo lanzamos
		mihilo.start();
		
		}
	//creamos los objetos de swing
	private	JTextArea areatexto;
	
	private JScrollPane scroll;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//creamos un server socket que se conecte al puerto 9999
			ServerSocket servidor = new ServerSocket(9999);
			//instanciamos variables y un array list
			String nick, ip, mensaje;
			
			ArrayList<String> listaIp = new ArrayList<String>();
		//y la clase paquete envio
			PaqueteEnvio paquete_recibido;
			
			//creamos un bucle infinito para que siempre este a la escucha
			while(true) {
				//aceptamos la conexion
			Socket misocket = servidor.accept();
			//creamos una object inputstream para desmigar el paquete que nos viene del cliente
			ObjectInputStream paquete_datos = new ObjectInputStream(misocket.getInputStream());
			//lo leemos
			paquete_recibido = (PaqueteEnvio) paquete_datos.readObject();
			//lo desmigamos en variables con los metodos get
			nick = paquete_recibido.getNick();
			
			ip = paquete_recibido.getIp();
			
			mensaje = paquete_recibido.getMensaje();
			
			//DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());
			
			//String mensaje_texto = flujo_entrada.readUTF();
			
			//areatexto.append("\n"+mensaje_texto);
			
			
			// diferenciamos los mensaje si es dimerente de " online" el mensaje
			 if(!mensaje.equals(" online")) {
				 //y si el id es diferente de "Grupo"
				 if(!ip.equals("Grupo")) {
					// mostramos el texto
					 areatexto.append("\n"+ nick +": "+mensaje+" para "+ip);
				//creamos el socket con la ip recivida para enviar el mensaje
					 Socket enviaDestinatario = new Socket(ip, 9090);
				//creamos el object stream 
					 ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
				//escribimos en el
					 paqueteReenvio.writeObject(paquete_recibido);
				//cerramos todo
					 paqueteReenvio.close();
				
					 enviaDestinatario.close();
				
					 misocket.close();
				
				
				 }
				 
				 else {//en caso de ser "Grupo" hacemos exactamente lo mismo que antes menos por una cosa, primero mostramos texto
					 areatexto.append("\n Grupo-->"+ nick +": "+mensaje+" para grupo");
					 //creamos un bucle foreach en el que enviamos a cada ip del array el mensaje
					 for(String z:listaIp) {
						 //el socket lo creamos con z 
						 	Socket enviaDestinatario = new Socket(z, 9090);
						 	
						 	nick = "Grupo--->"+nick;
						 	//le decimos que concatene el nick con el texto grupo para que el cliente sepa de donde viene el mensaje y que njo es algro privado
						 	paquete_recibido.setNick(nick);
							//objetos de salida
							ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
							//escribimos
							paqueteReenvio.writeObject(paquete_recibido);
							//cerramos todo
							paqueteReenvio.close();
							
							enviaDestinatario.close();
							
							misocket.close();
						 
					 }
					 
				 	
				 	
				 }
			
			
			}
			
			else {// si el texto recibido seria " online" seria una peticion de conexion
				
				//-----------------DETECTA ONLINE--------------------
				//cojemos la ip de el mensajero
				InetAddress localizacion =misocket.getInetAddress();
				//la almacenamos
				String IpRemota = localizacion.getHostAddress();
				
				//System.out.println("Online "+IpRemota);
				//la a�adimos al array list
				listaIp.add(IpRemota);
				//escribimos en el objeto con el metodo setIps
				paquete_recibido.setIps(listaIp);
				//creamos el bucle foreach
				for(String z:listaIp) {
					//mostramos los usuarios conectados en el array
					areatexto.append("\n Usuario conectado: "+z);
					//creamos el socket para cada destinatario y enviamos la informacion
					Socket enviaDestinatario = new Socket(z, 9090);
					
					ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
					
					paqueteReenvio.writeObject(paquete_recibido);
					
					paqueteReenvio.close();
					
					enviaDestinatario.close();
					
					misocket.close();
					
				}
				
				//---------------------------------------------------
				
			} 
				
				
			
			
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
