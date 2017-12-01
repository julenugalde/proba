package eus.julenugalde.thinkinginjava.io;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

@SuppressWarnings("unused")
public class TestIOStreams {
	public static void main(String[] args) {
		//testStringReader();
		//testDataInputStream(); 
		//testStringTokenizer();
		testRedirectStandardIO();
	}

	private static void testRedirectStandardIO() {
		try {
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(new File("c:/Temp/lorem1.txt")));
			PrintStream out = new PrintStream(new BufferedOutputStream(
					new FileOutputStream(new File("c:/Temp/log.txt"))));
			System.setIn(in);
			System.setOut(out);
			System.setErr(out);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String read;
			int count = 0;
			while (br.ready()) {
				read = br.readLine();
				System.out.println("Line #" + (++count) + ": \"" + read + "\"\n");
			}
			
			br.close();
			out.close();
			in.close();
			
		} catch (IOException ioex) {
			System.err.println("I/O error: " + ioex.getMessage());
		}
	}

	private static void testStringTokenizer() {
		try {
			FileReader fr = new FileReader(new File("c:/Temp/classpath.csv"));
			StringBuilder sb = new StringBuilder();
			char[] buffer = new char[1024];
			int read;
			while ((read=fr.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, read));
			}
			StringTokenizer st = new StringTokenizer(sb.toString(), ";");
			ArrayList<String> classpath = new ArrayList<String>();

			while (st.hasMoreTokens()) {
				classpath.add(st.nextToken());
			}
			
			for (String s : classpath) {
				System.out.println("Classpath entry: " + s);
			}
			
			fr.close();			
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.err.println("I/O error");
		}
	}

	private static void testDataInputStream() {

		DataInputStream dis;
		try {
			int count = 0;
			int read;
			char[] separator = {System.getProperty("line.separator").charAt(0), 
					System.getProperty("line.separator").charAt(1)};
			dis = new DataInputStream(new BufferedInputStream(
					new FileInputStream(new File("c:/temp/lorem1.txt"))));
			System.out.print("<line>");
			while (dis.available() > 0) {
				read = dis.readByte();
				if (((char)read != separator[0]) & ((char)read != separator[1])) {
					System.out.print((char)read);
					if (++count == 80) {
						count = 0;
						System.out.print("</line>\n<line>");
					}
				}
			}
			System.out.print("</line>");
			dis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testStringReader() {
		String cadena = "esto es una prueba de cadena\nincluye un salto de línea";
		/*
		java.io.StringBufferInputStream sbis = new java.io.StringBufferInputStream(cadena);
		int c;
		while ((c = sbis.read()) != -1) {
			System.out.print((char)c);
		}*/
		StringReader sr = new StringReader(cadena);
		int c;
		try {
			while ((c = sr.read()) != -1) {
				System.out.print((char)c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println();
			sr.close();
		}
	}

}
