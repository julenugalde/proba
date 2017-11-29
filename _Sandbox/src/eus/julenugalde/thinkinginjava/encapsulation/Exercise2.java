package eus.julenugalde.thinkinginjava.encapsulation;

public class Exercise2 {
	protected int i = 0;
	
	protected void f() {
		System.out.println("Protected method");
	}
	
	public static void main(String[] args) {
		OtherClass objOther = new OtherClass();
		Exercise2 objEx2 = new Exercise2();
		objOther.internal(objEx2);
	}
}


class OtherClass {
	OtherClass() {
		//Manipulate an Exercise2 object created in the constructor
		Exercise2 obj = new Exercise2();
		obj.f();
		obj.i = 1;
	}
	
	void internal(Exercise2 object) {
		//Manipulate the Exercise2 object passed as parameter
		object.i = 1;
		System.out.println("i=" + object.i);
		object.f();
	}
}