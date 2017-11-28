package eus.julenugalde.thinkinginjava.chapter08.sorting;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		if (o1.equals(o2)) return 0;
		if (o1.lessThan(o1, o2)) return -1;
		return 1;
	}

}
