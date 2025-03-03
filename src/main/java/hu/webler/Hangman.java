package hu.webler;

import java.util.Random;
import java.util.Scanner;

public class Hangman {

    private static int  wrongTry = 15;

    public static   void main (String[] args) {
        Scanner scanner=new Scanner (System.in);
        boolean playAgain ;
        do {
            wrongTry = 2;
            playAgain = playGame (scanner);
        } while (playAgain);

    }


    private static boolean playGame(Scanner scanner ){
        String word =generateRandomWord(getWords());
        String res = hidedWord (word);
        System.out.println(res);
        StringBuilder updatedWord = new StringBuilder(res);

        while(wrongTry > 0) {
            String userGuess = getUserInput(scanner, "Kérek egy betűt:");
            String whatThe = changeAGuess(word, updatedWord, userGuess);
            System.out.println(whatThe);
            if (!updatedWord.toString().contains("_")) {
                System.out.println("Nyertél!");
                break;
            }
        }
            if (wrongTry == 0) {
                System.out.println("Vesztettél!");

        }
        return askPlayAgain(scanner);
    }



    private static boolean askPlayAgain(Scanner scanner) {
        System.out.println("Szeretnél még játszani? (i - igen, minden más nem");
        String response = scanner.next().toLowerCase();
        return response.equals("i");
    }

    private static String[] getWords() {
        return new String[] {"Budapest", "Amszterdam", "Berlin"};
    }

    private static String  generateRandomWord(String[] words) {
        Random random = new Random();
        int index = random.nextInt(words.length);
        return words[index];
        //return words [new Random().nextInt(words.length)];
    }

    public static String[] hidedWord(String word, int length) {
        int lenght = word.length();
        char[] charsOfWord = new char[length];
        for (int i = 0; i < length; i++) {
            char letter = '_';
            charsOfWord[i] = letter;
        }

        StringBuilder hidedWord = new StringBuilder();
        for (char c : charsOfWord) {
            hidedWord.append(c).append(" ");
        }
        return new String[]{hidedWord.toString ()};
    }

    private static String getUserInput( Scanner scanner, String text ) {
        System.out.println(text);
        return scanner.next();
    }

    private static boolean checkIfValidGuess( String word, String letter) {
        boolean isValidGuess=word.toLowerCase ().contains (letter.toLowerCase ());
        if (!isValidGuess) {
            wrongTry++;
            System.out.println("Probálkozások száma: " + wrongTry);
        }
        return isValidGuess;
    }
    private static String changeAGuess(String word, StringBuilder updatedWord,  String userGuess) {
        if (checkIfValidGuess (word, userGuess)) {
            for (int i=0; i < word.length (); i++) {
                if (word.toLowerCase ().charAt (i) == userGuess.toLowerCase ().charAt (0)) {
                        int index = i * 2;
                        updatedWord.setCharAt (i, word.charAt (i));
                }
            }
        }
        return updatedWord.toString ();
    }
}