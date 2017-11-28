package eus.julenugalde.thinkinginjava.chapter08.sorting;

import java.util.Enumeration;
import java.util.Vector;

public class SortedVector<E extends Sortable>{
	private boolean isSorted = false;
	private Vector<E> vector;
	int iteracion = 0;
	
	public SortedVector () {
		vector = new Vector<E>();
	}
	
	public Enumeration<E> enumeration() {
		return java.util.Collections.enumeration(vector);
	}
	
	public void add (E element) {
		vector.addElement(element);
		isSorted = false;
	}
	
	public boolean isSorted() {
		return isSorted;
	}
	
	public int size() {
		return vector.size();
	}
	
	public void sort() {
		quickSort(0, vector.size()-1);
		isSorted = true;
	}
	
	public E get(int index) {
		return vector.get(index);
	}
	
	private void quickSort(int left, int right) {
		if (right > left) {
			E obj1 = vector.elementAt(right);
			int i = left-1;
			int j = right;
			while(true) {
				while(obj1.lessThan(vector.elementAt(++i), obj1));
				while (j>0) {
					if (obj1.lessThanOrEqualTo(vector.elementAt(--j), obj1)) {
						break;	//out of while
					}
				}
				if (i >= j) break;
				swap(i, j);
			}
			swap(i, right);
			quickSort(left, i-1);
			quickSort(i+1, right);
		}
	}
	
	private void swap(int loc1, int loc2) {
		E tmp = vector.elementAt(loc1);
		vector.setElementAt(vector.elementAt(loc2), loc1);
		vector.setElementAt(tmp, loc2);
	}
	
	@SuppressWarnings("unchecked")
	public E[] toArray() {
		if (vector.size() == 0) return null;
		E[] array =  (E[]) java.lang.reflect.Array.newInstance(vector.get(0).getClass(), vector.size());;
		for (int i = 0; i < vector.size(); i++) {
	        array[i] = vector.get(i);
	    }
		return array;
	}
}
