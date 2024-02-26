package retoUd5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {

	static String userDir = "user.dir";
	static String directorioActual = System.getProperty(userDir);
	static String dirNombre = "dirEjer2";
	static String directorioNuevo = System.getProperty(userDir)+ File.separator + dirNombre;
	static File dirPadre = new File(System.getProperty(userDir));
	static File dirNuevo = new File(System.getProperty(userDir) + File.separator + dirNombre);

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

		//a) Se muestra el directorio actual por consola
		System.out.print("El directorio actual es: "+ directorioActual + '\n');

		//Se crea un directorio nuevo, primero se comprueba si existe
		if ( !comprobarExiste(dirNombre, dirPadre)) {

			dirNuevo.mkdir();
			System.out.print("Se ha creado el directorio: " + "'" + dirNombre + "'" + '\n');


		} else {
			System.out.print("El directorio con el nombre: " + "'" + dirNombre + "'" + " no se ha creado porque ya existía." + '\n');
		}

		//Se crean dos ficheros dentro del nuevo directorio, primero se comprueba que no existian antes
		String fichero1Nom = "uno.txt";
		String fichero2Nom = "dos.txt";
		FileWriter out = null;

		if (!comprobarExiste(fichero1Nom, dirNuevo)) {			
			try {

				File fichero1 = new File(dirNuevo + File.separator + fichero1Nom);
				out = new FileWriter(fichero1);	
				System.out.print("El archivo con el nombre: " + "'" + fichero1Nom + "'" + " se ha creado en el directorio "+ "'" + dirNuevo + "'" +'\n');

				out.close();

			} catch (IOException e) {
				e.printStackTrace();
			}			

		} else {

			System.out.print("El archivo con el nombre: " + "'" + fichero1Nom + "'" + " no se ha creado porque ya existía en el directorio "+ "'" + dirNuevo + "'" +'\n');

		}


		if (!comprobarExiste(fichero2Nom, dirNuevo)) {
			try {
				File fichero2 = new File(dirNuevo + File.separator + fichero2Nom);
				out = new FileWriter(fichero2);	
				System.out.print("El archivo con el nombre: " + "'" + fichero2Nom + "'" + " se ha creado en el directorio "+ "'" + dirNuevo + "'" +'\n');

				out.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

			System.out.print("El archivo con el nombre: " + "'" + fichero2Nom + " no se ha creado porque ya existía en el directorio "+ "'" + dirNuevo + "'"+'\n');

		}

		//b) se abre el fichero 2 para escritura		
		try {

			if (comprobarExiste(fichero2Nom, dirNuevo)) {

				BufferedWriter outBW = new BufferedWriter(new FileWriter(dirNuevo + File.separator + fichero2Nom));

				//Se pide al usuario que introduzca un nombre y pulse Enter para introducir otro. El proceso finaliza al escribir '-'
				Scanner nombres = new Scanner(System.in);

				System.out.println("Introduzca un nombre y pulse Enter para continuar. Para finalizar el proceso escriba '-'");
				String escrito = "";

				while(!escrito.equals("-")) {

					escrito = nombres.next();

					if(!escrito.equals("-")) {						

						outBW.write(escrito);
						outBW.newLine();
						outBW.flush();

						System.out.println("--- Se ha guardado el nombre: " + escrito + " ---");

					} else {

						System.out.println('\n' + "--- Se ha terminado de introducir datos en el archivo ---");
					}

				}

				System.out.println("Los nombres introducidos se han guardado en el archivo: " + "'" + fichero2Nom + "'" );

				outBW.close();
				nombres.close();
				
			}	else {
				System.out.print("El archivo con el nombre: " + "'" + fichero2Nom + "'" + " no existe en "+ "'" + dirNuevo + "'" +'\n');
			}

		} catch (IOException ex) {
			System.out.println("Error E/S.");
		}

		//e) Se lee el archivo 2 y se muestra en pantalla
		try {

			if (comprobarExiste(fichero2Nom, dirNuevo)) {
				BufferedReader inBR = new BufferedReader(new FileReader(fichero2Nom));

				String linea;
				System.out.print("El contenido del archivo: " + "'" + fichero2Nom + "'" + " es el siguiente: "+ '\n');
				
				while((linea=inBR.readLine())!=null) {
					System.out.println(linea);
				}

				inBR.close();
				
			} else {
				System.out.print("El archivo con el nombre: " + "'" + fichero2Nom + "'" + " no existe en "+ "'" + dirNuevo + "'" +'\n');
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println("Error E/S");
		}


	}

}
