import java.io.*;
import java.lang.reflect.*;
import java.text.*;
import java.util.*;

public class Main {
	public static void main (String args []) {
		//testParseoISS();
		//testClassScanner();
		//testFlags();
		//testHerencias();
		//testClaseVector();
		//testLeerTeclado();
		//testCadenasTexto();
		testInterfaces();
	}
	
	/** pruebas con implementacion de interfaces y sobrecarga de metodos*/
	private static void testInterfaces() {
		Complejo comp1 = new Complejo(1, 1);
		Complejo comp2 = new Complejo(-1, 1);
		Complejo comp3 = new Complejo(-1, -1);
		Complejo comp4 = new Complejo(1, -1);
		
		//char angulo = '\u299F';
		char angulo = '<';		
		System.out.println(comp1.toString() + " = " + comp1.getModulo() + angulo + comp1.getArgumento() + 
				" (" + comp1.hashCode() + ")");
		System.out.println(comp2.toString() + " = " + comp2.getModulo() + angulo + comp2.getArgumento() + 
				" (" + comp2.hashCode() + ")");
		System.out.println(comp3.toString() + " = " + comp3.getModulo() + angulo + comp3.getArgumento() + 
				" (" + comp3.hashCode() + ")");
		System.out.println(comp4.toString() + " = " + comp4.getModulo() + angulo + comp4.getArgumento() + 
				" (" + comp4.hashCode() + ")");
		
		System.out.println(comp3.compareTo(comp4));
		
		Complejo[] arrayRandom = Complejo.getRandomArray(50, 10, 823);
		for (int i=0; i<arrayRandom.length; i++) 
			System.out.println(i + ": " + arrayRandom[i].toString());
	}

	/** pruebas con clases String, StringBuilder y StringTokenizer */
	private static void testCadenasTexto() {
		String cadena = "I must not fear.\r\n" + 
				"\r\n" + 
				"Fear is the mind-killer.\r\n" + 
				"Fear is the little-death that brings total obliteration.\r\n" + 
				"\r\n" + 
				"I will face my fear.\r\n" + 
				"I will permit it to pass over me and through me.\r\n" + 
				"\r\n" + 
				"And when it has gone past I will turn the inner eye to see its path.\r\n" + 
				"Where the fear has gone there will be nothing.\r\n" + 
				"\r\n" + 
				"Only I will remain.";
		//System.out.println(cadena.toUpperCase(new Locale("en", "gb")));
		System.out.println(cadena.substring(19, 75).concat("abcdefg").toUpperCase());
		
		char caracter = 'x';
		int posicion = cadena.indexOf(caracter);
		if (posicion == -1)
			System.err.println("No encontrado");
		else
			System.out.println("Posicion: " + posicion);
			
		byte[] bytesCadena = cadena.getBytes();
		for (int i=0; i<bytesCadena.length; i++) {
			System.out.println(i + " -> " + byteStringFormat(bytesCadena[i]) + " (" + 
					String.valueOf(bytesCadena[i]) + " '" + (char)bytesCadena[i] + "')");
		}
		
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder(cadena);
			
		System.out.println("sb1: length=" + sb1.length() + ", capacity=" + sb1.capacity());
		System.out.println("sb2: length=" + sb2.length() + ", capacity=" + sb2.capacity());
		
		posicion = 0;
		String cadenaBuscada = "ear";
		System.out.print("Posiciones de " + cadenaBuscada + ": ");
		while (posicion < sb2.length()) {
			posicion = sb2.indexOf(cadenaBuscada, posicion);
			if (posicion == -1) 
				break;
			System.out.print ((posicion++) + " ");
		}
		System.out.println();
		
		StringTokenizer st = new StringTokenizer(cadena);
		System.out.println("Separacion por tokens:");
		posicion = 0;
		while (st.hasMoreTokens()) {
			System.out.println((posicion++) + ": \"" + st.nextToken() + "\"");
		}		
	}

	/** snippet para leer una línea del teclado con BufferedReader */
	private static void testLeerTeclado() {
		String cadena;
		
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		try {
			cadena = br.readLine();
			System.out.println("Cadena leida: \"" + cadena +  "\"");
		} catch (IOException ioex) {
			System.err.println("Error: " + ioex.getMessage());
		}
	}

	/** pruebas con la clase Vector y enumeraciones */
	private static void testClaseVector() {
		Vector<String> comunidadAnillo = new Vector<String>();
		comunidadAnillo.addElement("Frodo");
		comunidadAnillo.addElement("Sam");
		comunidadAnillo.addElement("Pippin");
		comunidadAnillo.addElement("Merry");
		comunidadAnillo.addElement("Aragorn");
		comunidadAnillo.addElement("Legolas");
		comunidadAnillo.addElement("Boromir");
		comunidadAnillo.addElement("Gimli");
		comunidadAnillo.addElement("Gandalf");
		
		/*Class objeto = comunidadAnillo.getClass();
		objeto.getName();
		Method[] metodos = objeto.getMethods();
		for (int i=0; i<metodos.length; i++) {
			System.out.print(metodos[i].getName() + "(");
			Parameter[] parametros = metodos[i].getParameters();
			for (int j=0; j<parametros.length; j++)
				System.out.print(parametros[j].getName() + " ");
			System.out.println(")");
		}*/
		
		/*for (String integrante : comunidadAnillo)
			System.out.print(integrante + " ");
		System.out.println();*/		
		Enumeration<String> enumComunidad = comunidadAnillo.elements();
		while (enumComunidad.hasMoreElements())
			System.out.println(enumComunidad.nextElement());
		
		System.out.println("Tamaño original: " + comunidadAnillo.size());
		int posicion = 7;
		System.out.println("Elemento en posicion " + posicion + " : " + comunidadAnillo.elementAt(posicion));
		//Muere Boromir
		comunidadAnillo.remove("Boromir");
		System.out.println("Tamaño actual: " + comunidadAnillo.size());
		System.out.println("Elemento en posicion " + posicion + " : " + comunidadAnillo.elementAt(posicion));
		
	}

	/** pruebas de como se visualizan las variables en herencias de clases */
	private static void testHerencias() {
		A objA = new A();
		B objB = new B();
		C objC = new C();
		System.out.println("x en A: " + objA.x);
		System.out.println("x en B: " + objB.x);
		System.out.println("x en C: " + objC.x);
		objC.metodoPrueba();
		
	}

	/** test flags */
	private static void testFlags() {
		System.out.print("Introducir número entero entre -127 y 127: ");
		Scanner sc = new Scanner(System.in);
		String cadena = sc.nextLine();
		
		try {
			int entero = Integer.parseInt(cadena);
			if (entero < -127)
				System.err.println("Error: el número no puede ser menor que -127");
			else if (entero > 127)
				System.err.println("Error: el número no puede ser mayor que 127");
			else {
				byte dato = new Byte(cadena);
				System.out.println("Representacion de " + entero + " en binario: " + byteStringFormat(dato));
				
				byte temp = (byte) (dato>>1 & 0xFF);
				System.out.println ("Desplazamiento de una posicion a la derecha: " + String.valueOf(temp) + 
						" (" + byteStringFormat(temp) + ")");
				temp = (byte) (dato>>2 & 0xFF);
				System.out.println ("Desplazamiento de dos posiciones a la derecha: " + String.valueOf(temp) + 
						" (" + byteStringFormat(temp) + ")");
				temp = (byte) (dato<<3 & 0xFF);
				System.out.println ("Desplazamiento de tres posiciones a la izquierda: " + String.valueOf(temp) + 
						" (" + byteStringFormat(temp) + ")");
				byte mascara = (byte) 0x0A;
				temp = (byte)((dato & mascara) & 0xFF);
				System.out.println ("Operacion AND con la máscara " + byteStringFormat(mascara) + " : " + 
						String.valueOf(temp) + " (" + byteStringFormat(temp) + ")");
				temp = (byte)((dato | mascara) & 0xFF);
				System.out.println ("Operacion OR con la máscara " + byteStringFormat(mascara) + " : " + 
						String.valueOf(temp) + " (" + byteStringFormat(temp) + ")");
				temp = (byte)((dato ^ mascara) & 0xFF);
				System.out.println ("Operacion XOR con la máscara " + byteStringFormat(mascara) + " : " + 
						String.valueOf(temp) + " (" + byteStringFormat(temp) + ")");
				
				temp = (byte) ((~dato) & 0xFF);
				System.out.println ("Complemento a 1: " + String.valueOf(temp) + " (" + byteStringFormat(temp) + ")");
				
			}
		} catch (NumberFormatException nfex) {
			System.err.println ("Error en el formato del número: " + nfex.getMessage());
			
		} catch (IllegalArgumentException iaex) {
			System.err.println("Error: " + iaex.getMessage());
		} finally {sc.close();}
		
	}
	
	/** Método que devuelve una cadena de 8 caracteres con la representación en binario de un byte
	 * 
	 * @param entero Número entre -127 y 127 a representar
	 * @return Cadena de caracteres con la representación del número en 8 dígitos binarios
	 * @throws IllegalArgumentException
	 */
	public static String byteStringFormat (byte entero) throws IllegalArgumentException {
		if (entero<-127 | entero>127)
			throw new IllegalArgumentException ("El número está fuera de rango");
		//System.out.println("debug: " + Integer.toBinaryString(entero));
		StringBuilder sb = new StringBuilder(Integer.toBinaryString(entero));
		if (sb.length() > 8) 
			sb.delete(0, sb.length()-8);
		//System.out.println("debug: " + sb.length());
		while (sb.length() < 8)
			sb.insert(0, "0");
		return sb.toString();
	}
	
	/** pruebas de la clase Scanner que permiten leer una entrada de texto desde el teclado */
	public static void testClassScanner () {
		System.out.print("introducir dato: ");
		Scanner sc = new Scanner(System.in);
		String datoUsuario = sc.nextLine();
		System.out.println("se han leido " + datoUsuario.length() + " caracteres: " + datoUsuario);
		sc.close();
	}
	
	/** pruebas para parsear la cadena de caracteres que devuelve el correo de spot ISS para convertirlo en algo 
	 * que se pueda almacenar en el calendario
	 */
	public static void testParseoISS () {
		String cadena = 
				"Time: Mon Aug 19/11:12 PM, Visible: 1 min, Max Height: 70 degrees, Appears: NW, Disappears: NNW";
		StringTokenizer str = new StringTokenizer (cadena, ",");				
		String campo, fecha = "";
		StringBuilder descripcion = new StringBuilder("ISS ");
		GregorianCalendar inicio = new GregorianCalendar();				
		GregorianCalendar fin = new GregorianCalendar();
		int minutos = 0;
		
		while (str.hasMoreElements()) {
			campo = (String)str.nextElement();
			if (campo.startsWith("Time:")) {
				fecha = campo.replace("Time: ", "");
				System.out.println("TIME --> \"" + fecha + "\"");
			}
			else if (campo.startsWith(" Visible:")) {				
				campo = campo.replace(" Visible: ", "");
				campo = campo.replace("<", "");
				campo = campo.replace(" min", "");
				try {
					minutos = Integer.parseInt(campo);
				} catch (NumberFormatException ex) {}
				descripcion.append(campo + " min ");
				System.out.println("VISIBLE --> \"" + campo + "\"");
			}
			else if (campo.startsWith(" Max Height:")) {
				campo = campo.replace(" Max Height: ", "");
				campo = campo.replace(" degrees", "º");
				descripcion.append(campo + " ");
				System.out.println("MAX HEIGHT --> \"" + campo + "\"");
			}
			else if (campo.startsWith(" Appears:")) {
				campo = campo.replace(" Appears: ", "");
				descripcion.append(campo + "-");
				System.out.println("APPEARS --> \"" + campo + "\"");
			}
			else if (campo.startsWith(" Disappears:")) {
				campo = campo.replace(" Disappears: ", "");
				descripcion.append(campo);
				System.out.println("DISAPPEARS --> \"" + campo + "\"");
			}			
		}
		
		System.out.println("DESCRIPCION --> \"" + descripcion.toString() + "\"");
		System.out.println("MINUTOS --> " + minutos);		
		SimpleDateFormat sdf = new SimpleDateFormat ("EEE MMM dd'/'KK:mm aa yyyy", Locale.getDefault());
		inicio = new GregorianCalendar();		
		fin = new GregorianCalendar();
		
		try {
			inicio.setTime(sdf.parse(fecha + " " + inicio.get(GregorianCalendar.YEAR)));			
			fin.setTimeInMillis(inicio.getTimeInMillis() + (minutos*60*1000));
			System.out.println("INICIO: " + inicio.getTime().toString());
			System.out.println("FIN: " + fin.getTime().toString());
			
		} catch (ParseException e) {
			System.err.println("Error parseo: " + e.getLocalizedMessage());
		}
	}
	
}



