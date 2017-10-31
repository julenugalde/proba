package eus.julenugalde.sandbox.designpatterns.factory;

/** Interface que proporciona la abstracci�n para decodificar distintos tipos de archivos */
public interface ImageReader {
	DecodedImage getDecodeImage();
}
