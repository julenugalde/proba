package eus.julenugalde.thinkinginjava.collections.sorting;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Random;

@SuppressWarnings("serial")
public class CardDeck implements Serializable {
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(NUM_CARDS + " cards in the deck: {");
		for (int i=0; i<NUM_CARDS; i++) {
			sb.append(get(i).toString() + ", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append("}");
		return sb.toString();
	}
}
