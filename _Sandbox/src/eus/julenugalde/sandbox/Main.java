package eus.julenugalde.sandbox;
import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.*;
import java.time.Month;
import java.util.*;
import java.util.Date;

import eus.julenugalde.sandbox.*;
import eus.julenugalde.sandbox.arboles.ArbolBinario;
import eus.julenugalde.sandbox.arboles.Nodo;
import eus.julenugalde.sandbox.complejos.Complejo;
import eus.julenugalde.sandbox.empresa.Empleado;
import eus.julenugalde.sandbox.empresa.PuestosEmpresa;
import eus.julenugalde.sandbox.empresa.SortedSetEmpleados;
import eus.julenugalde.sandbox.jerarquiaclases.A;
import eus.julenugalde.sandbox.jerarquiaclases.B;
import eus.julenugalde.sandbox.jerarquiaclases.C;

@SuppressWarnings ("unused")
public class Main {
	public static void main (String args []) {
		//testParseoISS();
		//testClassScanner();
		//testFlags();
		testHerencias();
		//testClaseVector();
		//testLeerTeclado();
		//testCadenasTexto();
		//testInterfaces();
		//testArchivos();
		//testSerializacion();
		//testNetwork();
		//testArbolBinario();
		//testFormatos();
		//testSortedSet();
		//testHashMap();
		//testIterators();
		//testEnums();
		//testMySQL();
		
	}
	
	/** Test para probar las conexiones a una base de datos MySQL, usando una DB pública */
	private static void testMySQL() {
		try {
			String user = "julen";
			
			//Leer contraseña del teclado
			System.out.print("Password: ");
			BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
			String password = br.readLine();
			
			Class.forName("com.mysql.jdbc.Driver");    //Registra el driver
			String url = "jdbc:mysql://localhost:3306/sakila";
			Connection con = DriverManager.getConnection(
					url+"?verifyServerCertificate=false&useSSL=true", 	//usa SSL pero sin verificación	
					user, password);									//del certificado
			
			String sql = "SELECT actor_id, first_name, last_name FROM actor where first_name=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "julia");	//también existe setInt(), setDouble(), setBigDecimal(), etc.
			ResultSet rs = stmt.executeQuery();    
			
			System.out.println("ID\tNOMBRE\tAPELLIDO");
			while (rs.next()) {
				System.out.println(rs.getInt("actor_id") + "\t" + rs.getString("first_name") + 
						"\t" + rs.getString("last_name"));
			}
			
			con.close();
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found: " + e.getMessage());;
		} catch (SQLException e) {
			System.err.println("SQL exception: " + e.getMessage());
		} catch (IOException ioex) {
			System.err.println("Error: " + ioex.getMessage());
		/*} catch (IllegalAccessException e) {
			System.err.println("Illegal access: " + e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();*/
		}		
	}

	/** Pruebas con enum, usando PuestosEmpresa */
	private static void testEnums() {
		PuestosEmpresa[] listaPuestos = PuestosEmpresa.values();
		
		//Modificacion de uno de los atributos
		System.out.println("Sueldo medio " + listaPuestos[0].toString() + ": " + 
				listaPuestos[0].sueldoMedio());
		listaPuestos[0].setSueldoMaximo(250000);
		System.out.println("Sueldo medio " + listaPuestos[0].toString() + ": " + 
				listaPuestos[0].sueldoMedio());
		
		//Clase EnumMap
		EnumMap<PuestosEmpresa, String>mapaTratamientos = 
				new EnumMap<PuestosEmpresa, String>(PuestosEmpresa.class);
		mapaTratamientos.put(PuestosEmpresa.DIRECTOR, "Señor director");
		mapaTratamientos.put(PuestosEmpresa.JEFE_DEPARTAMENTO, "Estimado señor");
		mapaTratamientos.put(PuestosEmpresa.TRABAJADOR, "Oye");
		mapaTratamientos.put(PuestosEmpresa.BECARIO, "Pringadillo");
		
		//Ver los métodos de la enumeración
		for (PuestosEmpresa puesto : listaPuestos)
			System.out.println("El puesto con nombre "  + puesto.toString() + " esta en la posicion " + 
					puesto.ordinal() + " y tiene como sueldo medio " + puesto.sueldoMedio() + "€");
		//PuestosEmpresa kk = PuestosEmpresa.valueOf("DIRECTOR");		
	}

	/** Pruebas con clases que implementan los interfaces Iterator y ListIterator */
	private static void testIterators() {
		ArrayList<String> listaNombres = new ArrayList<String>();
		listaNombres.add("alfa");
		listaNombres.add("beta");
		listaNombres.add("gamma");
		listaNombres.add("delta");
		listaNombres.add("epsilon");
		listaNombres.add("zeta");
		listaNombres.add("eta");
		verLista(listaNombres.listIterator());		
		
		ListIterator<String> iterador = listaNombres.listIterator();
		if (iterador.hasNext())
			System.out.println(iterador.nextIndex() + " - " + iterador.next());
		if (iterador.hasNext()) iterador.next();
		if (iterador.hasNext()) iterador.next();
		if (iterador.hasPrevious())
			System.out.println(iterador.previousIndex() + " - " + iterador.previous());
		iterador.set("HULK");
		
		verLista(listaNombres.listIterator());
	}
	
	private static void verLista (ListIterator<String> iterador) {
		System.out.println("INDICE\tVALOR");
		while (iterador.hasNext())
			System.out.println(iterador.nextIndex() + "\t" + iterador.next());
	}

	/** Pruebas con las clases de Java Collections Framework */
	private static void testHashMap() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		HashMap<Integer, Empleado> mapaHashEmpleados = new HashMap<Integer, Empleado>();
		try {
			Empleado myEmpleado = new Empleado(
					"Alice", sdf.parse("1970-01-23"), 25000, PuestosEmpresa.TRABAJADOR);
			mapaHashEmpleados.put(myEmpleado.hashCode(), myEmpleado);
			myEmpleado = new Empleado(
					"Charlie", sdf.parse("1998-11-30"), 10000, PuestosEmpresa.BECARIO);
			mapaHashEmpleados.put(myEmpleado.hashCode(), myEmpleado);
			myEmpleado = new Empleado(
					"Bob", sdf.parse("1981-02-05"), 100000, PuestosEmpresa.DIRECTOR);
			mapaHashEmpleados.put(myEmpleado.hashCode(), myEmpleado);
			myEmpleado = new Empleado(
					"Dan", sdf.parse("1966-03-04"), 32000, PuestosEmpresa.TRABAJADOR);
			mapaHashEmpleados.put(myEmpleado.hashCode(), myEmpleado);
			myEmpleado = new Empleado(
					"Ronald", sdf.parse("1978-04-05"), 30000, PuestosEmpresa.TRABAJADOR);
			mapaHashEmpleados.put(myEmpleado.hashCode(), myEmpleado);
			myEmpleado = new Empleado(
					"Lance", sdf.parse("1992-08-15"), 12000, PuestosEmpresa.BECARIO);
			mapaHashEmpleados.put(myEmpleado.hashCode(), myEmpleado);
			myEmpleado = new Empleado(
					"Charlie", sdf.parse("1981-03-04"), 23000, PuestosEmpresa.TRABAJADOR);
			mapaHashEmpleados.put(myEmpleado.hashCode(), myEmpleado);
			myEmpleado = new Empleado(
					"Abraham", sdf.parse("1977-10-12"), 55000, PuestosEmpresa.JEFE_DEPARTAMENTO);
			mapaHashEmpleados.put(myEmpleado.hashCode(), myEmpleado);	
			
			MessageFormat mensaje = new MessageFormat(
					"Hay {0} elementos en el HashMap, con las siguientes claves: {1}\n");
			Object[] argumentos = {mapaHashEmpleados.size(), mapaHashEmpleados.keySet().toString()};
			System.out.print(mensaje.format(argumentos));
			
			int claveEliminar = 2125039532;
			myEmpleado = (Empleado)mapaHashEmpleados.get(claveEliminar);
			mapaHashEmpleados.remove(claveEliminar, myEmpleado);
			argumentos = new Object[] {mapaHashEmpleados.size(), mapaHashEmpleados.keySet().toString()};
			System.out.print(mensaje.format(argumentos));
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	/**Pruebas con una clase que implementa SortedSet */
	private static void testSortedSet() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		try {
			SortedSetEmpleados empleados = new SortedSetEmpleados(new Empleado(
					"Alice", sdf.parse("1970-01-23"), 25000, PuestosEmpresa.TRABAJADOR));
			empleados.add(new Empleado(
					"Charlie", sdf.parse("1998-11-30"), 10000, PuestosEmpresa.BECARIO));
			empleados.add(new Empleado(
					"Bob", sdf.parse("1981-02-05"), 100000, PuestosEmpresa.DIRECTOR));
			empleados.add(new Empleado(
					"Dan", sdf.parse("1966-03-04"), 32000, PuestosEmpresa.TRABAJADOR));
			empleados.add(new Empleado(
					"Ronald", sdf.parse("1978-04-05"), 30000, PuestosEmpresa.TRABAJADOR));
			empleados.add(new Empleado(
					"Lance", sdf.parse("1992-08-15"), 12000, PuestosEmpresa.BECARIO));
			empleados.add(new Empleado(
					"Abraham", sdf.parse("1977-10-12"), 55000, PuestosEmpresa.JEFE_DEPARTAMENTO));
			empleados.add(new Empleado(
					"Charlie", sdf.parse("1981-03-04"), 23000, PuestosEmpresa.TRABAJADOR));
			System.out.println(empleados.listarEmpleados());
			
			/*Empleado aux = new Empleado("Bob", sdf.parse("1981-2-5"), 25001, Puesto.DIRECTOR);
			//DEBUG System.out.println (empleados.contains(aux));
			empleados.remove(aux);*/
			
			List<Empleado> lista = new ArrayList<Empleado>();
			lista.add(new Empleado("Bob", sdf.parse("1981-2-5"), 25001, PuestosEmpresa.DIRECTOR));
			lista.add(new Empleado("Michael", sdf.parse("191-12-4"), 12000, PuestosEmpresa.BECARIO));
			//empleados.remove(lista.get(1));
			empleados.removeAll(lista);
			
			System.out.println(empleados.listarEmpleados());
			
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	/** Pruebas con formatos de cadenas, números, fechas y mensajes */
	private static void testFormatos() {
		//Usando StringBuilder
		String formato = "Nombre: %s. Edad: %d. PI=%f%n";
		String nombre = "John Doe";
		int edad = 55;
		StringBuilder sb = new StringBuilder();
		Formatter fmt = new Formatter(sb, Locale.US);
		fmt.format(formato, nombre, edad, Math.PI);
		System.out.print(sb.toString());
		fmt.close();
		
		//Con método static de String
		Calendar c = new GregorianCalendar(1980, 1, 20);
		String s = String.format("Duke's Birthday: %1$tB %1$te, %1$tY", c);
		System.out.println(s);
				
		//Formateo de números con DecimalFormat
		double numeros[] = {100000/3, Math.PI, 6.023*Math.pow(10, 23), 1E8, 123};
		DecimalFormat dfFormato = new DecimalFormat("#,##0.##¤",
				new DecimalFormatSymbols(new Locale("en", "US")));
			
		System.out.print("en-US --> ");	//Formato en-US
		for (int i=0; i<numeros.length; i++)
			System.out.print (dfFormato.format(numeros[i]) + "\t");
		System.out.println();
				
		dfFormato.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("es", "ES")));
		System.out.print("es-ES --> "); //Formato es-ES
		for (int i=0; i<numeros.length; i++)
			System.out.print (dfFormato.format(numeros[i]) + "\t");		
		System.out.println();
		
		//Formateo con NumberFormat
		NumberFormat nfFormato = NumberFormat.getPercentInstance(new Locale("es", "ES"));
		for (int i=0; i<numeros.length; i++)
			System.out.print(nfFormato.format(numeros[i]) + "\t");
		System.out.println();
		
		//Formatos de fecha
		Date dHoy = new Date();
		SimpleDateFormat sdfFormato = new SimpleDateFormat("EEEE', 'd' de 'MMMM' de 'Y',' HH:mm:ss zzzz",
				DateFormatSymbols.getInstance(new Locale("es", "ES")));
		System.out.println(sdfFormato.format(dHoy));
		sdfFormato.setTimeZone(TimeZone.getTimeZone("Europe/Brussels"));
		System.out.println(sdfFormato.format(dHoy));
		
		/*Locale[] lLocales = SimpleDateFormat.getAvailableLocales();
		for (Locale l : lLocales)
			System.out.println("Lenguaje: " + l.getDisplayLanguage() + ", país: " + 
					l.getDisplayCountry() + ", variante: " + l.getDisplayVariant() + 
					", nombre: " + l.getDisplayName());
		String[] sZonasHorarias = TimeZone.getAvailableIDs();
		for (String sZona : sZonasHorarias)
			System.out.println(sZona);*/
		
		//Formatos de mensajes.\
		Object[] argumentos = {new Long(123), "c:\"", new Complejo(2,3), 
				new Nodo<String>("contenido nodo"), new A().getX()};
		MessageFormat mfFormato = new MessageFormat("Hay {0} archivos en {1}, incluyendo a {2} con " + 
				"su nodo {3} que contiene {4}");
		System.out.println(mfFormato.format(argumentos));
		
	}

	/** pruebas de un arbol binario generico */
	private static void testArbolBinario() {
		Nodo<Complejo> nodoRaiz = new Nodo<Complejo>(new Complejo(1,2));
		ArbolBinario<Complejo> arbol = new ArbolBinario<Complejo>(nodoRaiz);
		
		arbol.addHijo(new Nodo<Complejo>(new Complejo(5.1,7)), 
				nodoRaiz, ArbolBinario.Orientacion.IZQUIERDA);
		arbol.addHijo(new Nodo<Complejo>(new Complejo(-1.2,3)), 
				nodoRaiz, ArbolBinario.Orientacion.DERECHA);
		arbol.addHijo(new Nodo<Complejo>(new Complejo(-2.3,0)), 
				nodoRaiz.getIzquierda(), ArbolBinario.Orientacion.IZQUIERDA);
		arbol.addHijo(new Nodo<Complejo>(new Complejo(-2.4,-7.3)), 
				nodoRaiz.getIzquierda(), ArbolBinario.Orientacion.DERECHA);
		arbol.addHijo(new Nodo<Complejo>(new Complejo(5,0)), 
				nodoRaiz.getDerecha(), ArbolBinario.Orientacion.DERECHA);
		arbol.addHijo(new Nodo<Complejo>(new Complejo(-10.6,3555)), 
				nodoRaiz.getDerecha().getDerecha(), ArbolBinario.Orientacion.IZQUIERDA);
		arbol.addHijo(new Nodo<Complejo>(new Complejo(2.7,3555)), 
				nodoRaiz.getDerecha().getDerecha(), ArbolBinario.Orientacion.DERECHA);
		arbol.addHijo(new Nodo<Complejo>(new Complejo(8,3555)), 
				nodoRaiz.getDerecha().getDerecha().getDerecha(), ArbolBinario.Orientacion.IZQUIERDA);
		arbol.addHijo(new Nodo<Complejo>(new Complejo(9.9, 0.)), 
				nodoRaiz.getDerecha().getDerecha().getDerecha(), ArbolBinario.Orientacion.DERECHA);
		arbol.addHijo(new Nodo<Complejo>(new Complejo(-10.101010,3555)), 
				nodoRaiz.getDerecha().getDerecha().getDerecha().getDerecha(), 
				ArbolBinario.Orientacion.DERECHA);
		
		arbol.mostrarArbol(ArbolBinario.Recorrido.POSTORDEN);
	}


	/** Pruebas de conexiones de red */
	private static void testNetwork() {		
		//Lectura de una direccion
		try {
			java.net.URL direccion = new java.net.URL("http://www.google.com");
			BufferedReader br = new BufferedReader(new InputStreamReader(direccion.openStream()));
			System.out.println(br.readLine());
			br.close();
		} catch (java.net.MalformedURLException muex) {
			System.err.println(muex.getMessage());
		} catch (IOException ioex) {
			System.err.println(ioex.getMessage());
		}		
	}

	/** Pruebas de serializacion/deserializacion de objetos usando la clase Complejo */
	private static void testSerializacion() {
		//Creamos unos cuantos números complejos y alguna que otra variable
		Complejo[] complejos = Complejo.getRandomArray(20, 555, 853);
		String cadena = "Esto es una cadena de caracteres";
		int entero = 18;
		
		File archivo = new File("c:/Temp/complejos_serializados.txt");
		ObjectOutputStream oos;
		ObjectInputStream ois;
		
		try {
			//Serializamos en un archivo 
			oos = new ObjectOutputStream(new FileOutputStream(archivo));
			oos.writeUTF(cadena);
			oos.writeInt(entero);
			oos.writeObject(complejos);
			oos.close();
			
			//Deserializamos y comparamos
			ois = new ObjectInputStream(new FileInputStream(archivo));
			String cadenaLeida = ois.readUTF();
			int enteroLeido = ois.readInt();
			Complejo[] complejosLeidos = (Complejo[])ois.readObject();
			ois.close();
			
			if (cadena.compareTo(cadenaLeida) != 0)
				System.err.println("Error en la serializacion de la cadena");			
			if (enteroLeido != entero)
				System.err.println("Error en la serializacion del entero");
			for (int i=0; i<complejos.length; i++) {
				System.out.println("Original: " + complejos[i].toString() + " / Leido: " + 
						complejosLeidos[i].toString());
			}
		}
		catch (IOException ioex) {
			System.err.println("Error: " + ioex.getMessage());
		}
		catch (ClassNotFoundException cnfex) {
			System.err.println("Error: " + cnfex.getMessage());
		}
		
	}

	/** pruebas de lectura y escritura de archivos */
	private static void testArchivos() {
		File archivo = new File ("c:/Temp/lorem.txt");
		
		//Lectura de archivo con FileReader
		/*char[] buffer = new char[100];
		try {
			FileReader fr = new FileReader(archivo);
			System.out.println("Codificación: " + fr.getEncoding());
			int leidos = 0;
			System.out.println("Contenido del archivo:");
			while ((leidos = fr.read(buffer)) > 0)
				System.out.print(new String(buffer, 0, leidos));
			fr.close();
		}
		catch (IOException ioex) {
			System.err.println("Error: " + ioex.getMessage());			
		}*/
		
		//Lectura de archivo con LineNumberReader
		/*try {
			LineNumberReader lnr = new LineNumberReader(new FileReader(archivo));
			while (lnr.ready()) {
				System.out.println("Linea " + lnr.getLineNumber() + " leida: \"" + lnr.readLine() + "\"");
			}
			lnr.close();
		}
		catch (IOException ioex) {
			System.err.println("Error de I/O: " + ioex.getMessage());
		}*/
		
		//Prueba de clase que extiende a FilterReader
		/*try {
			LectorFiltrado lf = new LectorFiltrado(new FileReader (archivo));
			int leidos;
			char[] buffer = new char[100];
			while ((leidos = lf.read(buffer)) > 0) {
				System.out.println ("Leidos " + leidos + " caracteres: \"" + 
						new String (buffer, 0, leidos) + "\"");		
			}
			lf.close();
		}
		catch (IOException ioex) {
			System.err.println("Error de I/O: " + ioex.getMessage());
		}*/
		
		//Prueba escritura de archivos
		/*try {
			File archivoEscritura = new File ("c:/Temp/numeros.txt");
			if (!archivoEscritura.exists())
				archivoEscritura.createNewFile();
			
			PrintWriter pr = new PrintWriter(archivoEscritura, StandardCharsets.UTF_8.name());
			final int NUM_ENTEROS = 200;
			Random rand = new Random();
			pr.write("Lista de " + NUM_ENTEROS + " enteros aleatorios entre 1 y 100" + 
					System.lineSeparator());
			for (int i=0; i<NUM_ENTEROS; i++)
				pr.write((rand.nextInt(100)+1) + "\t");
			pr.write("\n");
			pr.close();
		}
		catch (IOException ioex) {
			System.err.println("Error de I/O: " + ioex.getMessage());
		}*/
		
		//Prueba acceso aleatorio
		try {
			RandomAccessFile raf = new RandomAccessFile(archivo, "rws");
			raf.seek(550);	//Va a la posicion 50
			raf.writeBytes("REEMPLAZMOS EL CONTENIDO CON ESTE TEXTO");
			
			raf.close();
		}
		catch (IOException ioex) {
			System.err.println("Error de I/O: " + ioex.getMessage());
		}
	}

	/** pruebas con implementacion de interfaces y sobrecarga de metodos*/
	private static void testInterfaces() {
		Complejo comp1 = new Complejo(2, 3);
		Complejo comp2 = new Complejo(1);
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
		
		try {			
			System.out.println(comp1.toString() + " / " + comp2.toString() + " = " 
					+ comp1.dividir(comp2));
			System.out.println("1 / " + comp3.toString() + " = " + comp3.inverso());
		}
		catch (ArithmeticException aex) {
			System.err.println(aex.getMessage());
		}
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
		C objC = new C();
		System.out.println ("Valor variable static: " + objC.getNumStatic());
		A objA = new A();
		B objB = new B();
		System.out.println("x en A: " + objA.getX());
		System.out.println("x en B: " + objB.getX());
		System.out.println("x en C: " + objC.getX());
		objC.metodoPrueba();
		
		A[] matriz = new A[3];
		matriz[0] = new A();
		matriz[1] = new B();
		matriz[2] = new C();
		
		for (int i=0; i<matriz.length; i++) {
			System.out.println(i + ": "); 
			matriz[i].metodoA();
			matriz[i].metodoRedef();
		}
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
	 * @throws IllegalArgumentException Se lanza excepción si el número está fuera del rango
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



