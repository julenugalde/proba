package eus.julenugalde.sandbox.sound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;


public class Song {
	private Note[] notes;
	private double[] durationsInQuarters;
	
	public Song(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader (new File(fileName)));
		int numLines = 0;
		
		//first iteration to check the number of lines
		while (br.readLine() != null)
			numLines++;
		br.close();
		
		System.out.println("dEBUG: " + numLines + " lines in the file");
		if (numLines == 0) {
			notes = null;
			durationsInQuarters = null;
		}
		else {
			notes = new Note[numLines];
			durationsInQuarters = new double[numLines];
		
			br = new BufferedReader(new FileReader (new File(fileName)));
			StringTokenizer st;
			for (int i=0; i<numLines; i++) {
				st = new StringTokenizer(br.readLine(), ",");
				notes[i] = Note.valueOf(st.nextToken());
				durationsInQuarters[i] = Double.parseDouble(st.nextToken());
			}
		}
	}
	
	public int getNumNotes() {
		return notes.length;
	}
	
	public Note getNote (int index) throws IndexOutOfBoundsException {
		if (index >= notes.length)
			throw new IndexOutOfBoundsException();
		return notes[index];
	}
	
	public double getDuration(int index) throws IndexOutOfBoundsException {
		if (index >= durationsInQuarters.length)
			throw new IndexOutOfBoundsException();
		return durationsInQuarters[index];
	}
}
