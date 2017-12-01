package eus.julenugalde.thinkinginjava.io;

import java.io.File;
import java.io.FilenameFilter;

public class SourceFileList {
	public static void main(String[] args) {
		File path = new File(
				System.getProperty("user.dir") + System.getProperty("file.separator") + "res" + 
				System.getProperty("file.separator") + "src");
		System.out.println("Initial directory: " + path.getAbsolutePath());
		
		String[] list = path.list();
		printFileNames(list, "All files");
		
		list = path.list(new SourceFileFilter());
		printFileNames(list, "Source files");
		
		list = path.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.length() < 7) return true;
				return false;
			}});
		printFileNames(list, "File names smaller that 7 characters");
		
		File archivo = new File("c:/temp/lorem.txt");
		archivo.renameTo(new File("c:/temp/lorem1.txt"));
		
	}
	
	private static void printFileNames(String[] list, String message) {
		System.out.print(message  + ": ");
		for (String file : list) {
			System.out.print(file + ", ");
		}
		System.out.println();
	}
}
