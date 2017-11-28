package eus.julenugalde.thinkinginjava.chapter08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import eus.julenugalde.thinkinginjava.chapter08.sorting.*;

public class TestCollections {
	public static void main(String[] args) {
		CardDeck deck = new CardDeck();
		List<Card> listaCartas = new ArrayList<Card>();
		for (int i=0; i<deck.size(); i++) {
			listaCartas.add(deck.get(i));
		}
		//print(listaCartas);
		Collections.sort(listaCartas);
		//print(listaCartas);
		System.out.println("Maximo: " + Collections.max(listaCartas).toString());
		System.out.println("Minimo: " + Collections.min(listaCartas).toString());
		
		//List<Card> listaSincronizada = Collections.synchronizedList(listaCartas);
		
		
	}

	@SuppressWarnings("unused")
	private static void print(List<Card> deck) {
		Iterator<Card> iterator = deck.iterator();
		int position = 0;
		while (iterator.hasNext()) {
			System.out.println(++position + " --> " + iterator.next().toString());
		}
	}
}
