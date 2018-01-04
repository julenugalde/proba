package eus.julenugalde.thinkinginjava.designpatterns;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Vector;

public class Recycle {
	
	public static void main(String[] args) {
		//First the trash is put in a common container
		Vector<Trash> trashBin = new Vector<Trash>();
		for (int i=0; i<100; i++) {
			trashBin.addElement(getRandomTrash(10));
		}
		//printTrash(trashBin);
		
		//Order the trash in separate bins
		Vector<Trash> glassBin = new Vector<Trash>();
		Vector<Trash> paperBin = new Vector<Trash>();
		Vector<Trash> aluminiumBin = new Vector<Trash>();
		Vector<Trash> cardboardBin = new Vector<Trash>();
		Vector<Trash> genericTrashBin = new Vector<Trash>();
		
		Iterator<Trash> iterator = trashBin.iterator();
		Trash trashPiece;
		while (iterator.hasNext()) {
			trashPiece = iterator.next();
			if (trashPiece instanceof Aluminium)
				aluminiumBin.addElement(trashPiece);
			else if (trashPiece instanceof Paper) 
				paperBin.addElement(trashPiece);
			else if (trashPiece instanceof Glass)
				glassBin.addElement(trashPiece);
			else if (trashPiece instanceof Cardboard)
				cardboardBin.addElement(trashPiece);
			else if (trashPiece instanceof GenericTrash)
				genericTrashBin.addElement(trashPiece);
			else
				System.err.println("Error: " + trashPiece.toString() + " could not be catalogued");
		}
		
		printTrash(aluminiumBin);
		printTrash(paperBin);
		printTrash(glassBin);
		printTrash(cardboardBin);
		printTrash(genericTrashBin);
	}
	
	private static Trash getRandomTrash(double maxWeight) {
		TrashType[] types = TrashType.values();
		int index = (int)(Math.random() * types.length);
		assert ((index >=0) && (index < types.length));
		Info info = new Info(types[index], Math.random()*maxWeight, 0);
		return TrashFactory.acceptTrash(info);
	}
	

	
	public static void printTrash(Vector<Trash> bin) {
		Iterator<Trash> it = bin.iterator();
		Trash current;
		DecimalFormat twoDecimals = new DecimalFormat("0.00");
		System.out.println("Contents of the bin (total value=" + 
				twoDecimals.format(Trash.sumValue(bin)) + "€):");
		while (it.hasNext()) {
			current = it.next();
			System.out.println(" * " + twoDecimals.format(current.getInfo().weight)  + "kg. of " +
					current.getClass().getSimpleName());
		}
	}

}
