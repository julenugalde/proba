package eus.julenugalde.thinkinginjava.collections;

import java.util.Arrays;
import java.util.Random;

import eus.julenugalde.thinkinginjava.collections.sorting.*;

public class TestArrays {
	public static void main(String[] args) {
		//testAsList();
		testBinarySearchChar();
		testBinarySearchCard();	
		
	}
	
	private static void testBinarySearchCard() {
		CardDeck deck = new CardDeck();
		
		Card[] cardArray = deck.toArray();
		
		print(cardArray);
		Arrays.sort(cardArray, new CardComparator());
		print(cardArray);
		
		buscarElemento(cardArray, new Card(2, Suit.CLUBS));
		buscarElemento(cardArray, new Card(5, Suit.COINS));
		buscarElemento(cardArray, new Card(1, Suit.CLUBS));
		buscarElemento(cardArray, new Card(11, Suit.SWORDS));
		
	}

	private static void testBinarySearchChar() {
		String sCaracteres = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
		char[] cCaracteres = sCaracteres.toCharArray();
		
		Character[] arrayChars = new Character[500];
		Random random = new Random();
		for (int i=0; i<arrayChars.length; i++) {
			arrayChars[i] = new Character(cCaracteres[random.nextInt(cCaracteres.length)]);
		}
		print(arrayChars);
		Arrays.sort(arrayChars);
		//print(arrayChars);
		
		buscarElemento(arrayChars, 'j');
		buscarElemento(arrayChars, 'Ñ');
		buscarElemento(arrayChars, '7');
		buscarElemento(arrayChars, 'X');
		
	}

	@SuppressWarnings("unused")
	private static void testAsList() {
		String[] dias = {"anteayer", "ayer", "hoy", "mañana"};
		String[] masDias = {"anteayer", "ayer", "hoy", "mañana", "pasado mañana", 
			"anteayer", "ayer", "hoy", "mañana", "pasado mañana"};
		java.util.List<String> listaDias = Arrays.asList(dias);
		System.out.println(listaDias.size() + " dias en la lista");
		listaDias = java.util.Arrays.asList(masDias);
		System.out.println(listaDias.size() + " dias en la lista");
	}

	public static <T> void buscarElemento (T[] array, T buscado) {
		int posicion = 0;
		posicion = Arrays.binarySearch(array, buscado);
		if (posicion < 0) {
			System.out.println("Elemento '" + buscado + "' no encontrado");
		}
		else {
			System.out.println("Elemento '" + buscado + "' en posición " + posicion);
		}
	}
	
	public static <T> void print(T[] array) {
		System.out.print(array.length + " elementos en el array: { ");
		for (T elem : array) {
			System.out.print("\"" + elem.toString() + "\", ");
		}
		System.out.println("}");
	}
}
