package eus.julenugalde.sandbox.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class RegexExamples {
	public static boolean validarDNI(String cadena) {
		String patron = "^[0-9]{8}[- ]?[A-Z]$";
		//return cadena.matches(patron);
		Pattern pattern = Pattern.compile(patron);
		return pattern.matcher(cadena).matches();
	}
	
	public static boolean validarColorRGBHex(String cadena) {
		return cadena.matches("^#?([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
	}
	
	/**
	 * Comprobar si el nombre de usuario tiene entre 3 y 16 caracteres alfanuméricos
	 * mas - o _
	 */
	public static boolean validarNombreUsuario(String cadena) {
		return cadena.matches("^[a-z0-9_-]{3,16}$");
	}
	
	public static boolean validarEMail(String cadena) {
		return cadena.matches("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$");
	}
	
	public static boolean validarURL(String cadena) {
		return cadena.matches(
				"^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$");
	}
	
	public static void main(String[] args) {
		String cadena;
        BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		
        while (true) {
			try {
				//Validar nombre usuario
				System.out.print("Introducir nombre de usuario: ");
				cadena = br.readLine();
			    System.out.print("La cadena \"" + cadena +  "\" ");
			    if (!validarNombreUsuario(cadena)) {
			    	System.out.print("NO ");
			    }
			    System.out.println("corresponde con un nombre de usuario válido.");
				
			    //Validar e-mail
			    System.out.print("Introducir e-mail: ");
				cadena = br.readLine();
			    System.out.print("La cadena \"" + cadena +  "\" ");
			    if (!validarEMail(cadena)) {
			    	System.out.print("NO ");
			    }
			    System.out.println("corresponde con una dirección de e-mail válida.");
			    
			    
				//Validar DNI
			    System.out.print("Introducir DNI: ");
				cadena = br.readLine();
			    System.out.print("La cadena \"" + cadena +  "\" ");
			    if (!validarDNI(cadena)) {
			    	System.out.print("NO ");
			    }
			    System.out.println("corresponde con un DNI válido.");
			    
			    //Validar URL
			    System.out.print("Introducir URL: ");
				cadena = br.readLine();
			    System.out.print("La cadena \"" + cadena +  "\" ");
			    if (!validarURL(cadena)) {
			    	System.out.print("NO ");
			    }
			    System.out.println("corresponde con una URL válida.");
			    
			    //Validar color RGB hex
			    System.out.print("Introducir color en formato #RGB (componentes en hexadecimal): ");
				cadena = br.readLine();
			    System.out.print("La cadena \"" + cadena +  "\" ");
			    if (!validarColorRGBHex(cadena)) {
			    	System.out.print("NO ");
			    }
			    System.out.println("corresponde con un color válido.");
			} catch (IOException ioex) {
			    System.err.println("Error: " + ioex.getMessage());
			}

		}
	}
}
