package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MiInterfaz implements Interfaz {

	@Override
	public Map<Integer, String> getDatosFile() {
		// TODO Auto-generated method stub
		
		
		File file = new File("src/control/data.txt"); 
		Map<Integer, String> datos = null;
		datos = new HashMap<Integer, String>();
		datos.put(0, "");
		datos.put(1, "");
		  
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
		return datos;
	}

}
