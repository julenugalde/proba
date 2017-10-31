package eus.julenugalde.sandbox.designpatterns.factory;

/** Interface que proporciona la abstracción para decodificar distintos tipos de archivos */
public interface ImageReader {
	DecodedImage getDecodeImage();
}
