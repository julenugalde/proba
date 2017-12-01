package eus.julenugalde.thinkinginjava.io;

import java.io.File;

public class TestCompresion {
	public static void main(String[] args) {	
		File dst = new File("c:/Temp/test.gzip");
		File src = new File("c:/Temp/lorem1.txt");
		long checksum = Compression.gzipCompress(src, dst);
		if (checksum != -1) {
			System.out.println("sucess! checksum=" + hexFormat(checksum));
		}
		else {
			System.err.println("fail");
		}
		
		System.out.println("Checksum '" + src.getName() + "' = 0x" + 
				hexFormat(Compression.getChecksum(dst)));
		;
		System.out.println("Checksum '" + dst.getName() + "' = 0x" + 
				hexFormat(Compression.getChecksum(src)));
		
	}
	
	public static String hexFormat(long checksum) {
		return String.format("%08X", checksum & 0xFFFFFFFF);
	}
}
