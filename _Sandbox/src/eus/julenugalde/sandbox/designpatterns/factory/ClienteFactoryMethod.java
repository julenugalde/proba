package eus.julenugalde.sandbox.designpatterns.factory;

/** Clase de prueba para probar el patr�n de dise�o Factory. 
 * Extraido de {@link https://sourcemaking.com/design_patterns/factory_method/java/1}
 * 
 */
public class ClienteFactoryMethod {

	/**
	 * M�todo main, al que hay que pasar como argumento la imagen a decodificar
	 * @param args args[0] es el nombre de la imagen (.gif, .jpg, .jpeg)
	 */
	public static void main(String[] args) {
		DecodedImage decodedImage;
		ImageReader reader = null;
		String image = args[0];
        assert image != null;
		
        //formato en funci�n de la extensi�n del fichero
		String format = image.substring(image.indexOf('.') + 1, (image.length()));
        
        if (format.equals("gif")) {
        	reader = new GifReader(image);
        }
        else if (format.equals("jpg") | format.equals("jpeg")) {
        	reader = new JpegReader(image);        	
        }
        
        assert reader != null;	//Dar� error si el formato nos es uno de los soportados
        
        //Se llamar� a un m�todo u otro en funci�n de la clase
        decodedImage = reader.getDecodeImage();
        System.out.println(decodedImage.toString());
	}

}
