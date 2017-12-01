package eus.julenugalde.thinkinginjava.io;

import java.util.zip.*;
import java.io.*;
import static net.mindview.util.Print.*;

public class ZipCompress {
	public static void main(String[] args) throws IOException {
		FileOutputStream f = new FileOutputStream("c:/Temp/test.zip");
		CheckedOutputStream csum = new CheckedOutputStream(f, new CRC32());
		ZipOutputStream zos = new ZipOutputStream(csum);
		BufferedOutputStream out =	new BufferedOutputStream(zos);
		zos.setComment("A test of Java Zipping");
		
		String[] files = {"c:/Temp/1.docx", "c:/Temp/classpath.csv", "c:/Temp/intercal.txt", 
				"c:/Temp/lorem1.txt", "c:/Temp/numeros.txt"};
		for(String s : files) {
			print("Writing file " + s);
			File file = new File(s);
			BufferedReader in = new BufferedReader(new FileReader(file));
			zos.putNextEntry(new ZipEntry(file.getName()));
			int c;
			while((c = in.read()) != -1)
				out.write(c);
			in.close();
			out.flush();
		}
		out.close();
		// Checksum valid only after the file has been closed!
		print("Checksum: " + csum.getChecksum().getValue());
		
		// Now extract the files:
		print("Reading file");
		FileInputStream fi = new FileInputStream("c:/Temp/test.zip");
		CheckedInputStream csumi = new CheckedInputStream(fi, new CRC32());
		ZipInputStream in2 = new ZipInputStream(csumi);
		BufferedInputStream bis = new BufferedInputStream(in2);
		ZipEntry ze;
		while((ze = in2.getNextEntry()) != null) {
			print("Reading file " + ze);
			int x;
			while((x = bis.read()) != -1)
				System.out.write(x);
		}
		if(args.length == 1)
			print("Checksum: " + csumi.getChecksum().getValue());
		bis.close();
		// Alternative way to open and read Zip files:
		/*ZipFile zf = new ZipFile("c:/Temp/test.zip");
		Enumeration e = zf.entries();
		while(e.hasMoreElements()) {
			ZipEntry ze2 = (ZipEntry)e.nextElement();
			print("File: " + ze2);
			// ... and extract the data as before
		}*/
		
	}
} 
