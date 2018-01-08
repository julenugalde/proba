/**
 * 
 */
package eus.julenugalde.sandbox.regex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author inzil
 *
 */
class RegexExamplesTest {

	/**
	 * Test method for {@link eus.julenugalde.sandbox.regex.RegexExamples#validarDNI(java.lang.String)}.
	 */
	@Test
	void testValidarDNI() {
		assertTrue(RegexExamples.validarDNI("12345678E"));
		assertTrue(RegexExamples.validarDNI("12345678-E"));
		assertTrue(RegexExamples.validarDNI("12345678 E"));
		assertFalse(RegexExamples.validarDNI("12345678"));
		assertFalse(RegexExamples.validarDNI("abc"));
	}

	/**
	 * Test method for {@link eus.julenugalde.sandbox.regex.RegexExamples#validarColorRGBHex(java.lang.String)}.
	 */
	@Test
	void testValidarColorRGBHex() {
		assertTrue(RegexExamples.validarColorRGBHex("#000000"));
		assertTrue(RegexExamples.validarColorRGBHex("ad5568"));
		assertTrue(RegexExamples.validarColorRGBHex("#FF44bc"));
		assertTrue(RegexExamples.validarColorRGBHex("345"));
		assertFalse(RegexExamples.validarColorRGBHex("white"));
		assertFalse(RegexExamples.validarColorRGBHex("E56Y66"));
		assertFalse(RegexExamples.validarColorRGBHex("abcde"));
		assertFalse(RegexExamples.validarColorRGBHex("BLACK"));
	}

	/**
	 * Test method for {@link eus.julenugalde.sandbox.regex.RegexExamples#validarNombreUsuario(java.lang.String)}.
	 */
	@Test
	void testValidarNombreUsuario() {
		assertTrue(RegexExamples.validarNombreUsuario("julen"));
		assertTrue(RegexExamples.validarNombreUsuario("julen_456"));
		assertFalse(RegexExamples.validarNombreUsuario("julenugalde123456789"));
		assertFalse(RegexExamples.validarNombreUsuario("julen~ugalde"));
		assertFalse(RegexExamples.validarNombreUsuario("julen ugalde"));
	}

	/**
	 * Test method for {@link eus.julenugalde.sandbox.regex.RegexExamples#validarEMail(java.lang.String)}.
	 */
	@Test
	void testValidarEMail() {
		assertTrue(RegexExamples.validarEMail("julen@mail.com"));
		assertTrue(RegexExamples.validarEMail("julen.ugalde@mail.com"));
		assertFalse(RegexExamples.validarEMail("julen~ugalde@mail.com"));
		assertFalse(RegexExamples.validarEMail("julen"));
		assertFalse(RegexExamples.validarEMail("julen@mail"));
		assertFalse(RegexExamples.validarEMail("julen@mail.longdomain"));

		
	}

	/**
	 * Test method for {@link eus.julenugalde.sandbox.regex.RegexExamples#validarURL(java.lang.String)}.
	 */
	@Test
	void testValidarURL() {
		assertTrue(RegexExamples.validarURL("http://www.google.com"));
		assertTrue(RegexExamples.validarURL("https://en.wikipedia.org/wiki/Computer"));
		assertTrue(RegexExamples.validarURL("www.wikipedia.org/pagina.html"));
		assertFalse(RegexExamples.validarURL("www.test~~pagina.com"));
		assertFalse(RegexExamples.validarURL("123456"));

	}

}
