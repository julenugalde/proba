package eus.julenugalde.sandbox.designpatterns.factory;

/** Implementaci�n concreta de ImageReader para im�genes JPEG */
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
