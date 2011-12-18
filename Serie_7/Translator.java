/* Programmierung 1 HS 2011 Aufgabe 7-1 */

import java.util.*;

public class Translator{

	public static void main(String[] args){

		Dictionary dict = new Dictionary();

		// add some words with translations
		dict.addTranslations("Himmel", new String[]{"sky", "heaven (rel.)"});
		dict.addTranslations("Bank", new String[]{"bank", "bench"});
		dict.addTranslations("Absatz", new String[]{"heel", "paragraph"});
		dict.addTranslations("Kohle", new String[]{"coal", "bucks"});
		dict.addTranslations("teuer", new String[]{"expensive", "costly", "beloved"});
		dict.addTranslations("Schwindel", new String[]{ "dizziness"});
		dict.addTranslations("Schwindel", new String[]{"con"});

		// translate every word entered by the user 
		Scanner scn = new Scanner(System.in);
		String word;
		while (true){
			System.out.print("Enter a word to translate: (ctrl+c to abort): ");
			word = scn.nextLine();
			try{
				List<String> translations = dict.translate(word);
				for(String t :  translations) System.out.println(t);
				System.out.print("\n");
			}catch(WordNotFoundException e){
				System.out.println("Word not found.\n");
			}
		}
	}
}
