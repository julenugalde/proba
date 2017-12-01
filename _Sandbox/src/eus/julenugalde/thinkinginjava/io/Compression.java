package eus.julenugalde.thinkinginjava.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPOutputStream;

public class Compression {
	private static final int BUFFER_SIZE = 4096;
	public static long gzipCompress(File src, File dst) {
		try {
			GZIPOutputStream zipOut = new GZIPOutputStream(new FileOutputStream(dst));
			OutputStream os = (OutputStream)zipOut;
			CheckedOutputStream cos = new CheckedOutputStream(os, new Adler32());
			BufferedReader br = new BufferedReader(new FileReader(src));
			String line;
			byte[] feed = System.getProperty("line.separator").getBytes();
			while (br.ready()) {
				line = br.readLine();
				/*zipOut.write(line.getBytes(), 0, line.length());
				zipOut.write(feed, 0, feed.length);*/
				cos.write(line.getBytes(), 0, line.length());
				cos.write(feed, 0, feed.length);
			}
			
			br.close();
			zipOut.close();
			return cos.getChecksum().getValue();
		
		} catch (FileNotFoundException e) {
			System.err.println("Error: file not found");
			return -1;
		} catch (IOException e) {
			System.err.println("I/O exception: " + e.getLocalizedMessage());
			return -1;
		}
	}
	
	public static long getChecksum(File file) {
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			CheckedInputStream cis = new CheckedInputStream(is, new CRC32());
			byte[] buffer = new byte[BUFFER_SIZE];
			while (cis.read(buffer, 0, BUFFER_SIZE) != -1) {
				// debug System.out.println("checksum=" + cis.getChecksum().getValue());
			}
			long checksum = cis.getChecksum().getValue();
			is.close();
			cis.close();
			return checksum;
		} catch (FileNotFoundException e) {
			System.err.println("Error: file not found");
			return -1;
		} catch (IOException e) {
			System.err.println("I/O exception: " + e.getLocalizedMessage());
			return -1;
		}
		
	}
}
