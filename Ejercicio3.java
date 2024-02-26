package retoUd5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {

	static String userDir = "user.dir";
	static File directorioActual = new File(System.getProperty(userDir));
	static String nombreFichero = "tres.dat";


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

	//Este programa crea el fichero tres.dat y almacena los numeros positivos que se introduzcan por consola hasta que se introduzca uno negativo
	public static void main(String[] args) {

		//Se crea el fichero binario llamado tres.dat, si este no existe ya
		try {

			if(!comprobarExiste(nombreFichero, directorioActual)) {

				FileWriter fw=new FileWriter(directorioActual + File.separator + nombreFichero);
				System.out.println("Se ha creado el archivo: " + "'" + nombreFichero + "'" );

				fw.close();

			} else {
				System.out.println("El archivo: " + "'" + nombreFichero + "'" + " no se ha creado porque ya existía en el directorio " + userDir);
			}

			//Se crea el objeto para poder introducir datos binarios
			FileOutputStream oos=new FileOutputStream(nombreFichero);

			//Se pide al usuario que introduzca numeros positivos, si introduce un numero negativo termina el proceso
			Scanner numSc = new Scanner(System.in);

			System.out.println("Introduzca un número positivo y pulse Enter para continuar. Para finalizar el proceso escriba un número negativo");
			int numero = 0;

			while(numero >= 0) {

				numero = numSc.nextInt();

				if(numero >= 0) {

					oos.write(numero);
					System.out.println("Se ha introducido el número: " + numero);

				} else {

					System.out.println("Fin de la entrada de números");

				}
			}

			oos.close();
			numSc.close();


		} catch (IOException e) {
			System.out.println(e.getMessage());		
		}

		//Se lee por consola el contenido del archivo tres.dat para comprobar que es correcto
		if (comprobarExiste(nombreFichero, directorioActual)) {

			try {

				//ObjectInputStream ois = new ObjectInputStream(new FileInputStream(directorioActual + File.separator + nombreFichero)); 
				FileInputStream ois = new FileInputStream(directorioActual + File.separator + nombreFichero); 

				int c;
				String linea;

				System.out.println("El contenido del archivo " + "'" + nombreFichero + "'" + " es el siguiente: ");

				while((c=ois.read())!=-1) {
					System.out.print(c + " ");
				}

				ois.close();

			} catch (IOException e) {
				System.out.println(e.getMessage());		
			}

		}
	}
}
