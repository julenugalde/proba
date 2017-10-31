package eus.julenugalde.sandbox.designpatterns.factory;

/** Implementación concreta de ImageReader para imágenes JPEG */
public class JpegReader implements ImageReader {
	private DecodedImage decodedImage;
	
	public JpegReader (String image) {
		this.decodedImage = new DecodedImage(image);
	}
	
	@Override
	public DecodedImage getDecodeImage() {
		return decodedImage;
	}

}
