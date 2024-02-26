package retoUd5;

import java.util.Scanner;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio1 {

	//Se crean los metodos necesarios de tipo static:

	//Este metodo comprueba si ya existe el archivo o directorio pasado por parametro en el directorio actual
	private static boolean comprobarExiste(String nombreArchivo) {
		boolean comprobar = false;

		File directorio = new File(System.getProperty("user.dir"));
		String[] listadoArchivos = directorio.list();

		for (int i = 0; i < listadoArchivos.length; i++) {


			if(listadoArchivos[i].equals(nombreArchivo)) {				
				comprobar = true;				
			}

		}	

		return comprobar;
	}

	//Este metodo se encarga de abrir y cerrar flujos para escribir del 1 al 10 en el fichero que recibe por parametro
	private static boolean escribirEnFichero(String nombreArchivo) {
		boolean bandera = false;
		FileWriter out = null;

		try {
			out = new FileWriter(nombreArchivo);

			for(int i = 0;i<=10;i++) {
				out.write(String.valueOf(i) + '\n');
			}

			out.close();
			bandera = true;

		} catch (IOException ex) {
			System.out.println("Error E/S.");
		}

		return bandera;

	}

	//Este metodo se encarga de abrir y cerrar flujos para leer el fichero que recibe por parametro y muestra su informacion en consola 
	private static boolean leerDeFichero(String rutaArchivo) {
		boolean bandera = false;	
		String contenido = "";
		int index;


		try {
			FileReader in = null;
			in = new FileReader(rutaArchivo);

			while((index=in.read())!=-1) {
				contenido+=(char)index;
			}

			System.out.println("El contenido del fichero es: " + contenido);

			in.close();
			bandera = true;

		} catch(FileNotFoundException e) {
			System.out.println("Archivo no encontrado.");

		} catch(IOException e) {
			System.out.println("Error E/S.");
		} 

		return bandera;
	}

	//Este metodo se encarga de recibir un fichero origen y un fichero destino, y de hacer una copia de la información del origen al destino
	private static boolean duplicarFicheros(File  archivoOrigen, File archivoDestino) {
		boolean bandera = false;
		FileReader in = null;
		FileWriter out = null;

		try {
			in = new FileReader(archivoOrigen);
			out = new FileWriter(archivoDestino);
			int index;

			while((index=in.read())!=-1) {
				out.write(index);
			}
			bandera = true;

			in.close();
			out.close();

		} catch(IOException e) {
			System.out.println("Error E/S.");
		} 

		return bandera;
	}

	//Este metodo borra el fichero pasado por parametro
	private static boolean borrarFichero(File nombreArchivo) {
		boolean bandera = false;

		nombreArchivo.delete(); 
		System.out.println("Se ha borrado: " + "'" + nombreArchivo + "'.");

		bandera= true;

		return bandera;
	}

	//En la clase main se suceden las instrucciones del ejercicio 1
	public static void main(String[] args) {
		String nombreFichero1="";
		String nombreFichero2="";

		//a) Se crean los canales para introducir el texto por teclado
		Scanner nombre1 = new Scanner(System.in);
		Scanner nombre2 = new Scanner(System.in);

		//Se repite la consulta del nombre hasta que el fichero1 tenga al menos 3 caracteres
		while (nombreFichero1.length() < 3) {			

			//Se solicita al usuario el primer nombre de fichero 
			System.out.print("Introduzca el nombre del primer fichero (debe tener al menos 3 caracteres): ");
			nombreFichero1 = nombre1.next();

		}

		//Se repite la consulta del nombre hasta que el fichero2 tenga al menos 3 caracteres
		while (nombreFichero2.length() < 3) {			

			//Se solicita al usuario el segundo nombre de fichero 
			System.out.print("Introduzca el nombre del segundo fichero (debe tener al menos 3 caracteres): ");
			nombreFichero2 = nombre2.next();

		}


		//se cierran los canales de entrada
		nombre1.close();
		nombre2.close();

		//se muestran en pantalla los nombres de archivo introducidos
		System.out.println("El nombre " + "'" + nombreFichero1 + "'"  + " es válido para el fichero nº1.");
		System.out.println("El nombre " + "'" + nombreFichero2 + "'"  + " es válido para el fichero nº2.");

		//b) Se obtiene la ruta del directorio actual
		String directorioActual = System.getProperty("user.dir");
		System.out.println("El directorio actual es: " + directorioActual);

		//c) Se construye la ruta relativa al fichero nº1 y nº2
		String rutaCompletaFich1 = directorioActual + File.separator + nombreFichero1;
		String rutaCompletaFich2 = directorioActual + File.separator + nombreFichero2;
		
		//se crean los objetos File para poder pasarle los metodos propios posteriormente
		File fichero1 = new File(rutaCompletaFich1);
		File fichero2 = new File(rutaCompletaFich2);

		//se abre el canal de escritura del archivo
		FileWriter out = null;

		//d) Se crea el fichero nº1 junto a su manejo de excepciones
		try {

			if(!comprobarExiste(nombreFichero1)) {
				out = new FileWriter(rutaCompletaFich1);

				System.out.println("Se ha creado el archivo: " + "'" + nombreFichero1 + "'.");

				//se cierran el canal de salida
				out.close();			
				
			} else {
				System.out.println("Este fichero ya existe en el directorio: " + "'" + nombreFichero1 + "'.");
			}

		} catch(IOException e) {
			System.out.println("Error E/S.");
		} 

		//Se crea el fichero nº2 junto a su manejo de excepciones
		try {

			if(!comprobarExiste(nombreFichero2)) {
				out = new FileWriter(rutaCompletaFich2);

				System.out.println("Se ha creado el archivo: " + "'" + nombreFichero2 + "'.");

				//se cierran el canal de salida
				out.close();
			} else {
				
				System.out.println("Este fichero no existe en el directorio: " + "'" + nombreFichero2 + "'.");
				
			}


		} catch(IOException e) {
			System.out.println("Error E/S.");
		} 

		//e) Se graban datos en el fichero1, primero se comprueba si el archivo existe
		if(comprobarExiste(nombreFichero1)) {
			escribirEnFichero(nombreFichero1);

			System.out.println("Se han escrito los números del 1 al 10 en el archivo: " + "'" + nombreFichero1 + "'.");
			
		} else {
			
			System.out.println("Este fichero ya existe en el directorio: " + "'" + nombreFichero1 + "'.");
			
		}

		//f) Se escriben los datos grabados previamente en la consola
		if(comprobarExiste(nombreFichero1)) {
			
			leerDeFichero(nombreFichero1);
			
		} else {
			
			System.out.println("Este fichero ya existe en el directorio: " + "'" + nombreFichero1 + "'.");
			
		}

		//g) Se mostraran diferentes datos del fichero en consola
		if(comprobarExiste(nombreFichero1)) {

			String rutaDirectorioPadre1 = fichero1.getParent();
			long pesoFichero1 = rutaCompletaFich1.length();
			boolean ficheroEsDirectorio1 = fichero1.isDirectory();
			boolean permisoLectura1 = fichero1.canRead();
			boolean permisoEscritura1 = fichero1.canWrite();	
			boolean permisoEjecucion1 = fichero1.canExecute();
			boolean estaOculto1 = fichero1.isHidden();

			System.out.println("----- DATOS DEL FICHERO 1 -----" + '\n');
			System.out.println("Nombre: " + nombreFichero1);
			System.out.println("Ruta absoluta: " + rutaCompletaFich1);
			System.out.println("Ruta directorio padre: " + rutaDirectorioPadre1);
			System.out.println("Tamaño: " + pesoFichero1 + "bytes");

			if (ficheroEsDirectorio1) {
				System.out.println("Es un directorio." );
			} else {
				System.out.println("Es un fichero." );
			}

			System.out.println("Permiso de lectura: " + permisoLectura1);
			System.out.println("Permiso de escritura: " + permisoEscritura1);
			System.out.println("Permiso de ejecución: " + permisoEjecucion1);
			System.out.println("Está oculto: " + estaOculto1 );
			System.out.println('\n' + "----- ---------- -----");

		} else {
			
			System.out.println("El archivo no existe.");
			
		}

		//h) Se copia el contenido del archivo 1 al archivo 2
		duplicarFicheros(fichero1 ,fichero2);
		System.out.println("Se ha copiado el contenido del archivo 1: " + "'" + nombreFichero1 + "'" + " al archivo 2: " + "'" + nombreFichero2  + "'.");

		//i) Se borra el archivo 1
		borrarFichero(fichero1);

		//j) Se lee desde pantalla el contenido del archivo 2
		if(comprobarExiste(nombreFichero2)) {
			
			leerDeFichero(nombreFichero2);
			
		} else {
			
			System.out.println("Este fichero no existe en el directorio: " + "'" + nombreFichero2 + "'.");
			
		}

		//k) Se crea un directorio nuevo en caso de que no exista previamente
		String nombreDirectorio = "dirEjer1";

		if(!comprobarExiste(nombreDirectorio)) {

			File dirEjer1 = new File(System.getProperty("user.dir") + File.separator + nombreDirectorio);			
			dirEjer1.mkdir();

			System.out.println("Se ha creado el directorio: " + "'" + nombreDirectorio + "'.");

		} else {

			System.out.println("El directorio: " + "'" + nombreDirectorio + "'" + " ya existía.");

		}


	} 

}
