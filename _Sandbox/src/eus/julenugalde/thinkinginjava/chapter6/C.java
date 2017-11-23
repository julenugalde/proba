package eus.julenugalde.thinkinginjava.chapter6;

@SuppressWarnings("unused")
public class C extends A {
	public C(int i) {
		super(i);
	}

	private B objB;
	
	public static void main(String[] args) {
		C objC = new C(25);
	}
}
