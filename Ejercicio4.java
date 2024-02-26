package retoUd5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Ejercicio4 {
	
	static String userDir = "user.dir";
	static File directorioActual = new File(System.getProperty(userDir));
	static String nombreFichero = "fichero.dat";
	
	//Este metodo comprueba si ya existe el archivo o directorio pasado por parametro en el directorio actual
	private static boolean comprobarExiste(String nombreArchivo, File rutaAComprobar) {
		boolean comprobar = false;

		String[] listadoArchivos = rutaAComprobar.list();

		for (int i = 0; i < listadoArchivos.length; i++) {

			if(listadoArchivos[i].equals(nombreArchivo)) {				
				comprobar = true;				
			}
		}	
		return comprobar;
	}

	public static void main(String[] args) {

		//Se crea el fichero persona.dat, si este no existe ya
		try {

			if(!comprobarExiste(nombreFichero, directorioActual)) {

				FileWriter fw=new FileWriter(directorioActual + File.separator + nombreFichero);
				System.out.println("Se ha creado el archivo: " + "'" + nombreFichero + "'" );

				fw.close();

			} else {
				System.out.println("El archivo: " + "'" + nombreFichero + "'" + " no se ha creado porque ya existía en el directorio " + userDir);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());		
		}
		
		//creamos tres instancias del objeto personaEjercicio4
		personaEjercicio4 persona1 = new personaEjercicio4("Antonio", "García Pérez", 32, "Madrid");
		personaEjercicio4 persona2 = new personaEjercicio4("Rosa", "Benito López", 29, "Córdoba");
		personaEjercicio4 persona3 = new personaEjercicio4("Laura", "Sánchez Gil", 55, "Badajoz");
		
		//escribimos los datos en el fichero
		try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(directorioActual + File.separator + nombreFichero))) {

			//escribimos en un fichero
			oos.writeObject(persona1);
			oos.writeObject(persona2);
			oos.writeObject(persona3);
			
			System.out.println("Se han escrito los datos de las personas en el archivo: " + nombreFichero);

			
		} catch (IOException e) {

			System.out.println(e.getMessage());

		}

	}

}
