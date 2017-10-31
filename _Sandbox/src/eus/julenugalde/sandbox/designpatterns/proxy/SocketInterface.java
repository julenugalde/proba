package eus.julenugalde.sandbox.designpatterns.proxy;

/** Interface que asegura la compatibilidad entre el wrapper y el target */
public interface SocketInterface {
	String readLine();
	void writeLine(String str);
	void dispose();
}
