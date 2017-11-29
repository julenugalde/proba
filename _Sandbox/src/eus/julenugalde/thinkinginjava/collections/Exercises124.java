package eus.julenugalde.thinkinginjava.collections;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

@SuppressWarnings("unused")
public class Exercises124 {
	public static void main(String[] args) {
		Vector<Gerbil> vectorGerbils = new Vector<Gerbil>();
		for (int i=0; i<20; i++) {
			vectorGerbils.add(new Gerbil(i+1));
		}
		
		/* exercise 1
		for (int i=0; i<vectorGerbils.size(); i++) {
			vectorGerbils.elementAt(i).hop();
		}*/
		
		/* exercise 2
		Enumeration<Gerbil> enumeration = Collections.enumeration(vectorGerbils);
		while (enumeration.hasMoreElements()) {
			enumeration.nextElement().hop();
		}*/
		
		// exercise 4
		Hashtable<String, Gerbil> mapGerbils = new Hashtable<String,Gerbil>();
		Gerbil g;
		for (int i=0; i<vectorGerbils.size(); i++) {
			g = vectorGerbils.get(i);
			mapGerbils.put(g.toString(), g);
		}
		
		Enumeration<String> gerbilKeys = mapGerbils.keys();
		String key;
		while (gerbilKeys.hasMoreElements()) {
			key = gerbilKeys.nextElement();
			System.out.print(key + " --> ");
			((Gerbil)mapGerbils.get(key)).hop();
		}
	}
}
