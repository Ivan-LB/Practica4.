import java.io.*;
import java.util.*;

class Archivo {

	public static ArrayList<String> leerTodo(String nombreArchivo){
		ArrayList<String> contenido = new ArrayList<String>();
		String cadena = new String();
		try(
			FileInputStream fis = new FileInputStream(nombreArchivo);
			DataInputStream din = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(din));
			){
			cadena=br.readLine();
			while(cadena!=null){
			contenido.add(cadena);
			cadena=br.readLine();
		}
		br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return contenido;
	}
	public static void CrearArchivo(String contenidol, String ComplementoRuta){
		try {
			String ruta = "D:/Practica4/UsersPasswords/"+ComplementoRuta;
      File file = new File(ruta);
      // Si el archivo no existe es creado
      if (!file.exists()) {
          file.createNewFile();
      }else{
				FileWriter fw = new FileWriter(file,true);
	      BufferedWriter bw = new BufferedWriter(fw);
	      bw.write("\n"+contenidol);
	      bw.close();
			}
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	}
}
