package eus.julenugalde.sandbox.designpatterns.factory;

/** Clase auxiliar que simula la imagen decodificada, independientemente del formato
 * de la imagen original */
public class DecodedImage {
	private String image;
	
	/** Constructor al que se le pasa el nombre de la imagen original
	 * 
	 * @param image Nombre de la imagen
	 */
	public DecodedImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return image + " has been decoded";
	}
}
