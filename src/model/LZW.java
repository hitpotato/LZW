package model;

/**
 * File: LZW.java
 * Created by longchen on 2/1/17.
 * 
 */
import java.util.*;

public class LZW {
	/**
	 * Encode a string to a list of 1s and 0s.
	 * 
	 * @throws IllegalCharacterException
	 */
	public static List<String> encoder(String inputString) throws IllegalCharacterException {

		// Input check
		if (inputString.length() == 0)
			return null;

		for (int i = 0; i < inputString.length(); i++) {
			if (inputString.charAt(i) < 'A' || inputString.charAt(i) > 'Z')
				throw new IllegalCharacterException();
		}

		// Initialize the dictionary
		int dictSize = 26;
		Map<String, Integer> dictionary = new HashMap<String, Integer>();

		for (int i = 0; i < 26; i++)
			dictionary.put("" + (char) (i + 65), i);

		// Begin encode run time O(n), memory complexity O(n)
		String preString = "";
		List<Integer> result = new ArrayList<Integer>();
		List<String> result_binary = new ArrayList<String>();

		for (char curChar : inputString.toCharArray()) {
			String curString = preString + curChar;
			if (dictionary.containsKey(curString))
				preString = curString;
			else {
				dictionary.put(curString, dictSize++);
				result.add(dictionary.get(preString));
				result_binary.add(Integer.toBinaryString(dictionary.get(preString)));
				preString = "" + curChar;
			}
		}

		if (!preString.equals(""))
			result_binary.add(Integer.toBinaryString(dictionary.get(preString)));
		return result_binary;
	}

	/**
	 * Decode a list of 0s and 1s to a string.
	 * 
	 * @throws IllegalArgumentException
	 */
	public static String decoder(List<String> encode) {
		// Input check
		if (encode == null || encode.size() == 0)
			return "";
		for (String str : encode) {
			for (int i = 0; i < str.length(); i++) {
				if (!(str.charAt(i) == '0' || str.charAt(i) == '1'))
					throw new IllegalArgumentException();
			}

		}
		// Initialize the dictionary
		int dictSize = 26;
		Map<Integer, String> dictionary = new HashMap<Integer, String>();

		for (int i = 0; i < 26; i++)
			dictionary.put(i, "" + (char) (i + 65));

		// Begin decode run time O(n), memory complexity O(n)
		List<Integer> encode_int = new ArrayList<Integer>();

		for (String str : encode)
			encode_int.add(Integer.parseInt(str, 2));

		String preString = "" + (char) ((int) encode_int.remove(0) + 65);
		StringBuffer result = new StringBuffer(preString);

		for (int index : encode_int) {
			String curString;
			if (dictionary.containsKey(index))
				curString = dictionary.get(index);
			else if (index == dictSize)
				curString = preString + preString.charAt(0);
			else
				continue;
			result.append(curString);
			dictionary.put(dictSize++, preString + curString.charAt(0));
			preString = curString;
		}
		return result.toString();
	}
}