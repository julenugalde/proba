/**
 * 
 */
package eus.julenugalde.sandbox.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eus.julenugalde.sandbox.complejos.Complejo;

/** Clase de test para probar JUnit
 */
class TestComplejo {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeAll - Setupbeforeclass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterAll - Teardownafterclass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("@BeforeEach - setup");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		System.out.println ("@AfterEach - teardown");
	}

	/**
	 * Test method for {@link eus.julenugalde.sandbox.complejos.Complejo#sumar(eus.julenugalde.sandbox.complejos.Complejo, 
	 * eus.julenugalde.sandbox.complejos.Complejo)}.
	 */
	@Test
	void testSumarComplejoComplejo() {
		Complejo comp1 = new Complejo (2, 7);
		Complejo comp2 = new Complejo (5, -1);
		assertEquals(new Complejo(7,6), Complejo.sumar(comp1, comp2), 
				"El resultado de la suma de complejos es incorrecto");	
		
	}

	/**
	 * Test method for {@link eus.julenugalde.sandbox.complejos.Complejo#sumar(eus.julenugalde.sandbox.complejos.Complejo)}.
	 */
	@Test
	void testSumarComplejo() {
		Complejo comp1 = new Complejo (2, 7);
		Complejo comp2 = new Complejo (5, -1);
		comp2.sumar(comp1);
		assertEquals(new Complejo(7,6), comp2, "El resultado de la suma de complejos es incorrecto");
	}
	
	/**
	 * Test method for {@link eus.julenugalde.sandbox.complejos.Complejo#restar(eus.julenugalde.sandbox.complejos.Complejo)}.
	 */
	@Test
	void testRestarComplejo() {
		Complejo comp1 = new Complejo (2, 7);
		Complejo comp2 = new Complejo (5, -1);
		comp2.restar(comp1);
		assertEquals(new Complejo(3,-8), comp2, "El resultado de la resta de complejos es incorrecto");
	}	
	
	/**
	 * Test method for {@link eus.julenugalde.sandbox.complejos.Complejo#multiplicar(eus.julenugalde.sandbox.complejos.Complejo)}.
	 */
	@Test
	void testMultiplicarComplejo() {
		Complejo comp1 = new Complejo (3, 2);
		Complejo comp2 = new Complejo (1, 4);
		comp2.multiplicar(comp1);
		assertEquals(new Complejo(-5,14), comp2, "El resultado de la multiplicación de complejos es incorrecto");
	}
	
	/**
	 * Test method for {@link eus.julenugalde.sandbox.complejos.Complejo#dividir(eus.julenugalde.sandbox.Divisible)}.
	 */
	@Test
	void testDividir() {
		Complejo comp1 = new Complejo (5, 5);
		Complejo comp2 = new Complejo (1, 0);
		int test=1;
		String mensajeError = "El resultado de la división es incorrecto. Test #";
		assertEquals(new Complejo(5, 5), comp1.dividir(comp2), mensajeError + test++);
		
		comp1.setReal(3);
		comp1.setImag(5);
		comp2.setReal(2);
		comp2.setImag(6);
		assertEquals(new Complejo(0.9, -0.2), comp1.dividir(comp2), mensajeError + test++);
		
		comp1.setReal(6);
		comp1.setImag(-2);
		comp2.setReal(5);
		comp2.setImag(7);
		//double real = 0.216216216216216216216216216216216;
		//double imag = -0.702702702702702702702702702702702;
		double real = (double)16/(double)74;
		double imag = -(double)52/(double)74;
		assertEquals(new Complejo(real, imag), comp1.dividir(comp2), mensajeError+ test++);
		
	}

	/**
	 * Test method for {@link eus.julenugalde.sandbox.complejos.Complejo#inverso()}.
	 */
	@Test
	void testInverso() {
		int test=1;
		String mensajeError = "El resultado de la inversión es incorrecto. Test #";
		Complejo comp = new Complejo(2, 2);
		assertEquals(new Complejo(0.25, -0.25), comp.inverso(), mensajeError + test++);
	}

}
