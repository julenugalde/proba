package eus.julenugalde.thinkinginjava.chapter08;

import eus.julenugalde.thinkinginjava.chapter08.sorting.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestSets {
	public static void main(String[] args) {
		CardDeck deck = new CardDeck();
		
		Set<Card> setCards = new HashSet<Card>();
		for (int i=0; i<deck.size(); i++) {
			setCards.add(deck.get(i));
		}
		Iterator<Card> iterator = setCards.iterator();
		System.out.print("Current deck: ");
		while (iterator.hasNext()) {
			System.out.print(iterator.next().toString() + ", ");
		}
		System.out.println();
		
		Card card;
		card = new Card(7, Suit.COINS);
		if (setCards.add(card)) {	//Repetida
			System.out.println("Card '" + card.toString() + "' added.");
		}
		else {
			System.out.println("ERROR: card '" + card.toString() + "' could not be added");
		}
	}
}
