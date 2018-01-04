package eus.julenugalde.sandbox.designpatterns.observer;

public class CharObserver extends Observer {
	
	public CharObserver(Subject subject) {
		this.subject = subject;
		subject.addObserver(this);
		System.out.println("observador char registrado");
	}
	
	@Override
	public void update() {
		System.out.print("\t'" + (char)(subject.getCurrentValue()) + "'");
	}

}
