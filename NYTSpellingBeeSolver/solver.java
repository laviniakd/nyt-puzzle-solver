//Lavinia Dunagan, 2018
//solves the New York Times Spelling Bee puzzle

import java.util.*;
import java.io.*;

public class solver {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		System.out.println("Input the middle letter: ");
		String middle = ("" + in.nextLine().charAt(0)).toLowerCase();
		
		char[] alphabet = new char[26];
		for (int i = 0; i < 26; i++) {
			alphabet[i] = 'a' + i;
		}	
		
		List<String> badLetters = makeBadLetters(in, alphabet);
		
		List<String> words = generate(middle, badLetters);
		int num = words.size();
		System.out.println("There are " + num + " valid words. Here they are: ");
		for (int i = 0; i < num; i++) {
			System.out.println(words.get(i));
		}
	}
	
	public static List<String> makeBadLetters(Scanner in, char[] alphabet) {
		System.out.println("Now input the other six letters: ");
		String aux = in.nextLine().toLowerCase();
		
		while (aux.length() != 6) {
			System.out.print("Try again!");
			aux = in.nextLine();
		}
		
		List<String> goodLetters = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			goodLetters.add("" + aux.charAt(i));
		}
		
		List<String> badLetters = new ArrayList<String>();
		for (int i = 0; i < alphabet.length; i++) {
			String n = "" + alphabet[i];
			if (!goodLetters.contains(n) && !n.equals(middle)) {
				badLetters.add(n);
			}
		}
		
		return badLetters;
	}
	
	public static List<String> generate(String middle, List<String> badLetters) 
										throws FileNotFoundException {
		List<String> words = new ArrayList<String>();
		Scanner dictionary = new Scanner(new File("dictionary.txt"));
		
		while (dictionary.hasNextLine()) {
			String word = dictionary.nextLine();	
			if (word.contains(middle) && (word.length() > 3)) {
				words.add(word);
			}
		}
		
		Iterator<String> iterator = words.iterator();
		for (String bad : badLetters) {
			while (iterator.hasNext()) {
				if (iterator.next().contains(bad)) {
					iterator.remove();
				}
			}
			iterator = words.iterator();
		}
		
		return words;
	}
	
}
