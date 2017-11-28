package eus.julenugalde.thinkinginjava.chapter08.sorting;

public enum Suit implements Comparable<Suit> {
	COINS ("Coins", "Oros", 1),
	CUPS ("Cups", "Copas", 2),
	SWORDS ("Swords", "Espadas", 3),
	CLUBS ("Clubs", "Bastos", 4);
	
	private String englishName;
	private String spanishName;
	private int order;
	
	Suit(String englishName, String spanishName, int order) {
		this.englishName = englishName;
		this.spanishName = spanishName;
		this.order = order;
	}
	
	public String getEnglishName() {
		return englishName;
	}
	
	public String getSpanishName() {
		return spanishName;
	}
	
	public final int compareSuit(Suit o) {
		if (this.order == o.order) return 0;
		if (this.order > o.order) return 1;
		return -1;
	}
}
