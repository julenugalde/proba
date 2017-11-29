package eus.julenugalde.thinkinginjava.io;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

public class TestFilterIOStream {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		FilterOutputStream fos = System.out;
		FilterInputStream fis = (FilterInputStream) System.in;
		DataInputStream dis = new DataInputStream(fis);
		DataOutputStream dos = new DataOutputStream(System.out);
				
		byte[] values = {71, 72, 73, 74, 75, 76, 77, 78, 79, 80};
		
		try {
			/*
			fos.write(values);
						while (fis.available() < 10);
			fis.read(values, 0, 10);
			fos.write('\n');
			fos.write(values);
			*/
			/*
			if (dis.readInt() > 0) 
				System.out.println("positivo");
			else 
				System.out.println("negativo");
				*/
			/*
			dos.writeUTF("сссс~Ђь");
			dos.writeByte(65);
			dos.writeChar('\n');
			dos.writeLong(1234567890123456789L);
			*/
			
			PipedOutputStream pos = new PipedOutputStream();
			PipedInputStream pis = new PipedInputStream(pos);
			
			System.out.println("los bytes van al pipe");
			pos.write(values);
			byte[] buffer = new byte[100];
			int leidos = pis.read(buffer);
			System.out.print(leidos + " bytes leidos del pipe: "); 
			fos.write(buffer);
			System.out.println();
			pis.close();
			pos.close();
			
			String fileName = "c:/Temp/lorem.txt";
			LineNumberReader lnr = new LineNumberReader(new FileReader(new File(fileName)));
			lnr.readLine();
			lnr.readLine();
			lnr.readLine();
			lnr.readLine();
			System.out.println("Numero de linea: " + lnr.getLineNumber());
			lnr.close();
			
			PrintStream ps = new PrintStream(new BufferedOutputStream(new FilterOutputStream(
					new FileOutputStream(new File("c:/temp/test.txt")))));
			ps.print("prueba");
			ps.close();
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		
	}
}
