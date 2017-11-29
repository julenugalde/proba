package eus.julenugalde.thinkinginjava.io;

import java.io.File;
import java.io.FilenameFilter;

public class SourceFileFilter implements FilenameFilter {
	private final String[] extensions = {"java", "c", "cpp", "jsp", "m"};
	
	@Override
	public boolean accept(File dir, String name) {
		for (int i=0; i<extensions.length; i++) {
			if (name.endsWith("." + extensions[i])) 
				return true;
		}
		return false;
	}

}
