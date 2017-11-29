package eus.julenugalde.thinkinginjava.exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestResumption {
	public static void main(String[] args) {
boolean exit = false;
int number;
long result;
while (!exit) {
	try {
		number =  getNumber();
		result = factorial(number);
		System.out.println(number  + "! = " + result);
		exit = true;
	} catch (IllegalArgumentException e) {
		System.out.println("Error: " + e.getMessage());
	}
}
	}
	
	private static long factorial(int number) throws IllegalArgumentException {
		if (number < 2) {
			throw new IllegalArgumentException("Argument must be higher than 2");
		}
		else {
			long result = 1;
			for (int i=number; i>1; i--) {
				result *= i;
			}
			return result;
		}
	}
	
	private static int getNumber()  {
        Scanner keyboard = new Scanner(System.in);
        boolean isValid = false;
        int num=-1;
        while (isValid == false) {
            System.out.print("Please enter an integer higher than 2 --> ");
            try {
            	num = keyboard.nextInt();
                isValid = true;
            } catch (InputMismatchException ex) {
                System.out.println("Wrong input. Ony integer input will be processed.");
                //clean up the garbage input
                keyboard.nextLine();
            }finally{
                keyboard.close();
            }
        }
        return num;
	}
}
