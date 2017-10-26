import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class LectorFiltrado extends FilterReader {

	protected LectorFiltrado(Reader in) {
		super(in);
	}
	
	@Override
	/**
	 * Sobrecarga del método read que convierte todas las vocales en X
	 */
	public int read(char[] cbuf) throws IOException {
		int leidos = super.read(cbuf);
		for (int i=0; i<leidos; i++) {
			switch (cbuf[i]) {
			case 'a': case 'e': case 'i': case 'o': case 'u':
				cbuf[i] = 'X';
				break;
			}
		}
		return leidos;
	}
	
	@Override
	public void close() throws IOException {
		System.out.println("Cerrando lector...");
		super.close();
	}
}
