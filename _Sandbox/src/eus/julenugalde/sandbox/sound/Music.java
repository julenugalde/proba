package eus.julenugalde.sandbox.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class Music {
	public static final int SAMPLE_RATE = 16 * 1024;	//16KHz
	
	
	public static void main(String[] args) {
		for (Note n : Note.values()) {
			System.out.print("Note " + n.name() + ": ");
			System.out.printf("%.2f", n.frequency());
			System.out.println("Hz");
		}
				
		try {
			final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
			SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
			
			sdl.open(af, SAMPLE_RATE);
			sdl.start();
						
			//Song song = new Song("c:/Temp/jingle_bells.txt");
			//double quarterTime = 350d;	//Quarter note time in milisecons
			//Song song = new Song("c:/Temp/for_elise.txt");
			//double quarterTime = 800d;	//Quarter note time in milisecons
			Song song = new Song("c:/Temp/ode_to_joy.txt");
			double quarterTime = 450d;	//Quarter note time in milisecons
			playSong(sdl, song, quarterTime);
			
			sdl.drain();
			sdl.close();
			
			
		} catch (Exception e) {
			System.err.println("Error " + e.getClass().getSimpleName() + ": " + e.getMessage());
		}		
	}
	
	public static void playSong(SourceDataLine line, Song song, double quarterTime) {
		Sound s = new Sound(Note.SILENCE, quarterTime, SAMPLE_RATE);
	
		for (int i=0; i<song.getNumNotes(); i++) {
			if (!s.getNote().equals(song.getNote(i)))
				s.setNote(song.getNote(i));
			//Song's durations expressed in quarters, Sound's durations in ms
			if (s.getDuration() != (song.getDuration(i) * quarterTime))
				s.setDuration(quarterTime * song.getDuration(i));
			s.play(line);
		}
	}
}
