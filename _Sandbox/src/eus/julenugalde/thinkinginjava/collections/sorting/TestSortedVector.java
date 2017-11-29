package eus.julenugalde.thinkinginjava.collections.sorting;

import java.util.Random;

public class TestSortedVector {

	public static void main(String[] args) {
		testDeck();
		testNCards();		
	}
	
	private static void testDeck() {
		CardDeck deck = new CardDeck();
		
		//Print unsorted and then sorted
		deck.print(System.err);
		Card[] array = deck.toArray();
		System.out.println("Array of length " + array.length + " obtained.");
		System.out.println("The element #4 is the " + array[3].toString() + 
				" and #7 is the " + array[6].toString() + ".");
		
		deck.sort();
		deck.print(System.out);
	}

	private static void testNCards() {
		int numberCards = 500;
		//Create a set of cards
		Random random = new Random();
		Suit[] suits = Suit.values();
		SortedVector<Card> cards = new SortedVector<Card>();
		Card currentCard;
		for (int i=0; i<numberCards; i++) {
			currentCard = new Card(random.nextInt(12)+1, suits[random.nextInt(4)]);
			cards.add(currentCard);
		}
		
		//Print unsorted and then sorted
		printCards(cards);
		cards.sort();
		printCards(cards);
	}

	public static void printCards(SortedVector<Card> cards) {
		for (int i=0; i<cards.size(); i++) {
			System.out.println(i + " - " + cards.get(i));
		}
	}
}
