package eus.julenugalde.sandbox.designpatterns.singleton;

/** Ejemplo del patrón de diseño Singleton 
 * Extraido de {@link https://sourcemaking.com/design_patterns/singleton/java/2} 
 */
public class ThreadSafeSingleton {
	private static volatile ThreadSafeSingleton instance;
	private String atributo;
	
	private ThreadSafeSingleton () {
		this.atributo = "";
	}
	
	public static ThreadSafeSingleton getInstance() {
		if (instance == null) {
			synchronized (ThreadSafeSingleton.class) {
				if (instance == null)
					instance = new ThreadSafeSingleton();
			}
		}
		return instance;
	}
	
	public String getAtributo() {return atributo;}
	public void setAtributo(String atributo) {this.atributo = atributo;}
}
