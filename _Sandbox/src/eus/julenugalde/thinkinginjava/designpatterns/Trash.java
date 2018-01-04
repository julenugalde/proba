package eus.julenugalde.thinkinginjava.designpatterns;

import java.util.Iterator;
import java.util.Vector;

public abstract class Trash {
	private Info info;
	
	abstract double getValue();
	abstract Trash accept(Info info);
	
	protected Trash(Info info) {
		this.info = info;
	}
	
	public Info getInfo() {
		return info;
	}
	
	public void setInfo(Info info) {
		this.info = info;
	}
	
	static double sumValue(Vector<Trash> trashBin) {
		double total = 0.0f;
		Iterator<Trash> iterator = trashBin.iterator();
		Trash current;
		while(iterator.hasNext()) {
			current = iterator.next();
			/*System.out.println("Weight of " + current.getClass().getName() + 
					" = " + current.getWeight());*/
			total += (current.info.weight * current.getValue());
		}
		//System.out.println("Total value = " + total);
		return total;
	}
}
