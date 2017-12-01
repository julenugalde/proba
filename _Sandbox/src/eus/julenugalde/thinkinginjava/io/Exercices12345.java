package eus.julenugalde.thinkinginjava.io;

import java.io.*;
import java.util.ArrayList;

public class Exercices12345 {
	public static void main(String[] args) {
		try {
			if (args.length == 2) {
				File file = new File(args[0]);
				String search = args[1];
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				ArrayList<String> alLines = new ArrayList<String>();
				String line;
				while((line=br.readLine()) != null) {
					alLines.add(line);
				}
				br.close();
				
				System.out.println("The word " + search + " will be searched in " + file.getName());
				BufferedWriter bw = new BufferedWriter(new FileWriter("c:/Temp/output.txt"));
				for (int i=alLines.size(); i>0; i--) {
					line = "Line #" + i + ": \"" + alLines.get(i-1) + "\"";
					bw.write(line + "\n");
					if (line.indexOf(search) != -1)  {
						System.out.println(search + " found in line " + i);
					}
				}	
				bw.close();
			}
			else {
				System.out.println("The file name and the word to find must be passed as arguments.");
			}
		} catch(FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.err.println("I/O exception: " + e.getLocalizedMessage());
		}
	}
}
