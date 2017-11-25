package eus.julenugalde.sandbox.sound;

import javax.sound.sampled.SourceDataLine;

public class Sound {
	/** Array with sound data */
	private byte[] noteBytes;
	/** Specific note to be played in this sound*/
	Note note;
	/** Duration of the sound in miliseconds*/
	double ms;
	/** Sampling rate in samples/second */
	int sampleRate;
	
	public Sound(Note note, double ms, int sampleRate) {
		this.note = note;
		this.ms = ms;
		this.sampleRate = sampleRate;
		noteBytes = getNoteBytes();
	}
	
	public int play(SourceDataLine line) 
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
		System.out.print(this.note.name() + "-");
		return line.write(noteBytes, 0, noteBytes.length);		
	}
	
	public double getDuration() {
		return ms;
	}
	
	public Sound setDuration(double ms) {
		this.ms = ms;
		noteBytes = getNoteBytes();
		return this;
	}
	
	public Note getNote() {
		return note;
	}
	
	public Sound setNote(Note note) {
		this.note = note;
		noteBytes = getNoteBytes();
		return this;
	}
	
	private byte[] getNoteBytes() {
		int numSamples = (int) Math.round(ms * sampleRate / 1000);
		byte[] data = new byte[numSamples];
		double period = (double)sampleRate / note.frequency();
		double angle;
		/*System.out.println("Sample period = " + sampleRate + "samples/s / " + note.frequency() + 
				"Hz = " + period + "samples/cycle");*/
		for (int i=0; i<data.length; i++) {
			angle = 2.0 * Math.PI * i / period;
			data[i] = (byte)(Math.sin(angle) * 127f);
		}		
		return data;
	}
	

}
