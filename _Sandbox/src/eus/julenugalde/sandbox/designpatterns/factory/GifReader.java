package eus.julenugalde.sandbox.designpatterns.factory;

/** Implementación concreta de ImageReader para imágenes GIF */
public class GifReader implements ImageReader {
	private DecodedImage decodedImage;
	
	public GifReader (String image) {
		this.decodedImage = new DecodedImage(image);
	}
	
	@Override
	public DecodedImage getDecodeImage() {
		return decodedImage;
	}
}
