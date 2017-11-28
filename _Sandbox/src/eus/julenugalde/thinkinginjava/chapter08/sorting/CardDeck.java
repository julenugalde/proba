package eus.julenugalde.thinkinginjava.chapter08.sorting;

import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Random;

public class CardDeck {
	private SortedVector<Card> deck;
	private Random random;
	private int MAX_NUMBER = 12;
	private int NUM_SUITS = Suit.values().length;
	private int NUM_CARDS = MAX_NUMBER * NUM_SUITS;
	
	public CardDeck(long randomnessSeed) {
		random = new Random(randomnessSeed);
		fillDeck();
	}
	
	public CardDeck() {
		random = new Random();
		fillDeck();
	}
	
	public void print(PrintStream out) {
		for (int i=0; i<deck.size(); i++) {
			out.println(i + " - " + deck.get(i));
		}
	}
	
	private void fillDeck() {
		boolean[][] flags = new boolean[NUM_SUITS][MAX_NUMBER];
		Suit[] suits = Suit.values();
		int currentNumber, currentSuit;
		deck = new SortedVector<Card>();
		for (int i=0; i<NUM_CARDS; i++) {
			do {
				currentNumber = random.nextInt(MAX_NUMBER);
				currentSuit = random.nextInt(NUM_SUITS);
			} while (flags[currentSuit][currentNumber]);
			deck.add(new Card(currentNumber+1, suits[currentSuit]));
			flags[currentSuit][currentNumber] = true;
		}
	}
	
	public void sort() {
		deck.sort();
	}
	
	public Card[] toArray() {
		return deck.toArray();
	}
	
	public int size() {
		return NUM_CARDS;
	}
	
	public Card get(int index) {
		return (Card)deck.get(index);
	}
	
	public Enumeration<Card> enumeration() {
		return (Enumeration<Card>)deck.enumeration();
	}
	
	public boolean remove(Card card) {
		
		return false;
	}
}
