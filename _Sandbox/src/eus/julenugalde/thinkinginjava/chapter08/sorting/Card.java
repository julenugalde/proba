package eus.julenugalde.thinkinginjava.chapter08.sorting;

public class Card implements Sortable {
	private int number;
	private Suit suit;
	
	public Card(int number, Suit suit) throws IllegalArgumentException {
		if ((number < 0) | (number>12)) 
			throw new IllegalArgumentException("Invalid card number");
		this.number = number;
		this.suit = suit;		
	}
	
	public String toString() {
		String temp;
		switch(number) {
		case 1:
			temp = "Ace";
			break;
		case 10:
			temp = "'Sota'";
			break;
		case 11:
			temp = "Knight";
			break;
		case 12:
			temp = "King";
			break;
		default:
			temp = String.valueOf(number);
		}
		
		return temp + " of " + suit.getEnglishName().toLowerCase();
	}

	@Override
	public boolean lessThanOrEqualTo(Object obj1, Object obj2) {
		if ((obj1 == null) | !(obj1 instanceof Card)) return false;
		if ((obj2 == null) | !(obj2 instanceof Card)) return false;
		Card card1 = (Card)obj1;
		Card card2 = (Card)obj2;
		if (card1.suit.compareTo(card2.suit) == 0) {	//Same suit
			if (card1.number == card2.number) {	//Equal cards
				return true;
			}
			else {
				return lessThan(obj1, obj2);
			}
		}
		return lessThan(obj1, obj2);
	}
	
	@Override
	public boolean lessThan(Object obj1, Object obj2) {
		if ((obj1 == null) | !(obj1 instanceof Card)) return false;
		if ((obj2 == null) | !(obj2 instanceof Card)) return false;
		Card card1 = (Card)obj1;
		Card card2 = (Card)obj2;
		if (card1.suit.compareTo(card2.suit) == 0) {	//Same suit
			if (card1.number == card2.number) {	//Equal cards
				return false;
			}
			else {
				return card1.number < card2.number;
			}
		}
		return card1.suit.compareSuit(card2.suit) < 0;
	}
	
	public static void main(String[] args) {
		System.out.println("testing comparison methods");
		Card c1 = new Card(1, Suit.CLUBS);
		Card c2 = new Card(4, Suit.CLUBS);
		System.out.println(c1.toString() + " <= " + c2 + " = " + c1.lessThanOrEqualTo(c1, c2));
		System.out.println(c1.toString() + " < " + c2 + " = " + c1.lessThan(c1, c2));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Card)) return false;
		Card card = (Card)obj;
		return ((card.number==this.number) & (card.suit.equals(this.suit)));
	}
	
	@Override
	public int hashCode() {
		int numSuit = suit.ordinal();
		return (numSuit*100) + number;
	}
}
