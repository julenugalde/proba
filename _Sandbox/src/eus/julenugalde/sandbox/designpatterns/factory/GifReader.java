package eus.julenugalde.sandbox.designpatterns.factory;

/** Implementaci�n concreta de ImageReader para im�genes GIF */
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
