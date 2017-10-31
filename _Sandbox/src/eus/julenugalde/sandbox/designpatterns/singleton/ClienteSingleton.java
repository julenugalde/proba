package eus.julenugalde.sandbox.designpatterns.singleton;

public class ClienteSingleton {

	public static void main(String[] args) {
		ThreadSafeSingleton tss1 = ThreadSafeSingleton.getInstance();
		tss1.setAtributo("primera instancia");
		ThreadSafeSingleton tss2 = ThreadSafeSingleton.getInstance();
		tss2.setAtributo("segunda instancia");
		
		System.out.println("tss1 - " + tss1.getAtributo());
		System.out.println("tss2 - " + tss2.getAtributo());
		
	}

}
