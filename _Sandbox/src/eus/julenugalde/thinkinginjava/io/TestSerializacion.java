package eus.julenugalde.thinkinginjava.io;

import java.io.*;

import eus.julenugalde.thinkinginjava.collections.sorting.CardDeck;

@SuppressWarnings("unused")
public class TestSerializacion {
	public static void main(String[] args) {
		//testSerializable();
		testExternalizable();
	}

	private static void testExternalizable() {
		ConnectionData con = new ConnectionData("julen", "asdfg", "localhost", 1234);
		System.out.println("Before serialization --> " + con.toString());
		
		try {
			//Serialize
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(new File("c:/Temp/connection.out")));
			oos.writeObject(con);
			oos.close();
			
			//Deserialize
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(new File("c:/Temp/connection.out")));
			Object obj = ois.readObject();
			ois.close();
			
			System.out.println("\nDeserialized object class: " + obj.getClass().getName());
			con = (ConnectionData)obj;
			System.out.println("After serialization --> " + con.toString());
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getLocalizedMessage());
		} catch (IOException e) {
			System.err.println("I/O Exception: " + e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found: " + e.getLocalizedMessage());
		}
	}

	private static void testSerializable() {
		CardDeck deck = new CardDeck();
		System.out.println("Before serialization --> " + deck.toString());
		
		try {
			//Serialize
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(new File("c:/Temp/deck.out")));
			oos.writeObject(deck);
			oos.close();
			
			//Deserialize
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(new File("c:/Temp/deck.out")));
			Object obj = ois.readObject();
			ois.close();
			
			System.out.println("\nDeserialized object class: " + obj.getClass().getName());
			deck = (CardDeck)obj;
			System.out.println("After serialization --> " + deck.toString());
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getLocalizedMessage());
		} catch (IOException e) {
			System.err.println("I/O Exception: " + e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found: " + e.getLocalizedMessage());
		}
	}
}
