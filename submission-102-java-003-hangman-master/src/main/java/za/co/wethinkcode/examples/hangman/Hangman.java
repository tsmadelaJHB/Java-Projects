package za.co.wethinkcode.examples.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// tag::hangman-class[]
public class Hangman {
    private static String selectedWord;

    public static void main(String[] args) throws IOException {

        Random random = new Random();
        
        Player player = new Player();


        System.out.println("Words file? [leave empty to use short_words.txt]");
        String fileName = player.getWordsFile();                                                      // <2>


        List<String> words = Files.readAllLines(Path.of(fileName));                                     //<3>

        int randomIndex = random.nextInt(words.size());                                                 //<4>
        String randomWord = words.get(randomIndex).trim();                                                   //<5>
        Answer wordToGuess = new Answer(randomWord);

        randomIndex = random.nextInt(randomWord.length() - 1);                                        //<6>

        String resultWord = "_".repeat(randomWord.length());                                          //<7>

        Answer currentAnswer = wordToGuess.getHint(new Answer(resultWord),                                                   //<8>
                wordToGuess.toString().charAt(randomIndex));
        System.out.println("Guess the word: " + currentAnswer);

        while (!currentAnswer.toString().equals(wordToGuess.toString())) {                                         //<9>
            String guess = player.getWordsFile();                                                     //<10>
            if (player.wantsToQuit()) {                                                        //<1)
                System.out.println("Bye!");
                break;
            }
            char guessedLetter = guess.charAt(0);
            if (wordToGuess.hasLetter(guessedLetter)
                    && !currentAnswer.hasLetter(guessedLetter)) {                                    //<12>
                currentAnswer = wordToGuess.getHint(currentAnswer, guessedLetter);
                System.out.println(currentAnswer);
            } else {                                                                                    //<13>
                player.lostChance();
                System.out.println("Wrong! Number of guesses left: " + player.getChances());
                if (player.hasNoChances()) {
                    System.out.println("Sorry, you are out of guesses. The word was: " + wordToGuess.toString());
                    break;
                }
            }
        }

        if (currentAnswer.toString().equals(wordToGuess.toString())) {                                             //<14>
            System.out.println("That is correct! You escaped the noose .. this time.");
        }
    }
}
