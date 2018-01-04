package eus.julenugalde.thinkinginjava.designpatterns;

import java.util.HashMap;
import java.util.Map;

public class TrashFactory {
	private static final Map<TrashType,Trash> trashMap = new HashMap<TrashType,Trash>();
	
	static {
		trashMap.put(TrashType.ALUMINIUM, new Aluminium());
		trashMap.put(TrashType.CARDBOARD, new Cardboard());
		trashMap.put(TrashType.GENERIC, new GenericTrash());
		trashMap.put(TrashType.GLASS, new Glass());
		trashMap.put(TrashType.PAPER, new Paper());
	}
	
	public static Trash acceptTrash(Info info) {
		try {
			return (trashMap.get(info.trashType)).accept(info);
		} catch (Exception e) {
			System.err.println(info.trashType+" trash type not valid: " + e.getLocalizedMessage());
			return null;
		}
	}
	
	public static int getNumValidTrashTypes() {
		return trashMap.size();
	}
}
