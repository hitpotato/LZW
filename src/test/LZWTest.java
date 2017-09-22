package test;

/**
 * File: LZWTest.java
 * Created by longchen on 2/3/17.
 */
import static org.junit.Assert.*;
import model.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LZWTest {

	@Test
	public void testWhenEncodeEmpty() {
		String before = "";
		List<String> encoded = LZW.encoder(before);

	}

	@Test(expected = IllegalCharacterException.class)
	public void testWhenStringContainsIllegalCharacter() {
		String before = "ABCFDa";
		List<String> encoded = LZW.encoder(before);
	}

	@Test(expected = IllegalCharacterException.class)
	public void testWhenStringContainsIllegalCharacter2() {
		String before = "aABCFD";
		List<String> encoded = LZW.encoder(before);

	}

	@Test(expected = IllegalCharacterException.class)
	public void testWhenStringContainsIllegalCharacter3() {
		String before = "AB12891CFD";
		List<String> encoded = LZW.encoder(before);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testWhenEncodeContainsIllegalCharacter() {
		List<String> encoded = new ArrayList<String>();
		encoded.add("0");
		encoded.add("19");
		encoded.add("10");
		String decoded = LZW.decoder(encoded);
	}

	@Test
	public void testSingleCharacter() {
		String before = "A";
		List<String> encoded = LZW.encoder(before);
		System.out.println(encoded);
		String decoded = LZW.decoder(encoded);
		assertTrue(before.equals(decoded));
	}

	@Test
	public void testEncoder1() {
		String before = "ABC";
		List<String> encoded = LZW.encoder(before);
		System.out.println(encoded);
		String decoded = LZW.decoder(encoded);
		assertTrue(before.equals(decoded));
	}

	@Test
	public void testEncoder2() {
		String before = "ABCDEFGHUTY";
		List<String> encoded = LZW.encoder(before);
		System.out.println(encoded);
		String decoded = LZW.decoder(encoded);
		assertTrue(before.equals(decoded));
	}

	@Test
	public void testEncoder3() {
		String before = "ABCABCABCABABABCABCBACABCBAABCBABC";
		List<String> encoded = LZW.encoder(before);
		System.out.println(encoded);
		String decoded = LZW.decoder(encoded);
		assertTrue(before.equals(decoded));

	}

	@Test
	public void testDecoder1() {
		List<String> encoded = new ArrayList<String>();
		String decoded = LZW.decoder(encoded);
		assertTrue(decoded.equals(""));

	}

	@Test
	public void testDecoder2() {
		List<String> encoded = new ArrayList<String>();
		encoded.add("0");
		encoded.add("1");
		encoded.add("10");
		String decoded = LZW.decoder(encoded);
		assertTrue(decoded.equals("ABC"));

	}

	@Test
	public void testDecoder3() {
		List<String> encoded = new ArrayList<String>();
		encoded.add("0");
		encoded.add("1");
		encoded.add("10");
		String decoded = LZW.decoder(encoded);
		assertTrue(decoded.equals("ABC"));

	}

}
