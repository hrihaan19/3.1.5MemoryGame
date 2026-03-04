import java.util.ArrayList;
import java.util.Collections;

/**
 * Project 3.1.5
 *
 * The Memory Game shows a random sequence of "memory strings" in a variety of buttons.
 */
public class MemoryGame
{
  public static void main(String[] args) {

    // Create the "memory strings" - an array of 4 single character strings
    String[] memoryStrings = {"A", "B", "C", "D"};

    // Create the game and gameboard
    MemoryGameGUI game = new MemoryGameGUI();
    game.createBoard(3, true); 

    int score = 0;
    int rounds = 0;
    double delay = 0.5; // Initial delay in seconds
    boolean playAgain = true;

    // Play the game until user wants to quit
    while (playAgain) 
    {
        rounds++;
        
        // Manual shuffle logic to avoid needing RandomPermutation class
        ArrayList<String> shuffleList = new ArrayList<String>();
        for (String s : memoryStrings) {
            shuffleList.add(s);
        }
        Collections.shuffle(shuffleList);
        String[] randomSeq = shuffleList.toArray(new String[0]);
  
        // Play one sequence with current delay
        String guess = game.playSequence(randomSeq, delay);

        if (guess != null) 
        {
            // Cleanup: remove commas and spaces
            guess = guess.replace(" ", "").replace(",", "");
            
            String actualSeq = "";
            for (String s : randomSeq) {
                actualSeq += s;
            }

            // Check for a match
            if (guess.equalsIgnoreCase(actualSeq)) 
            {
                game.matched();
                score++;
                // Increase speed: Decrease delay (ensure it doesn't go below 0.1)
                delay = Math.max(0.1, delay - 0.1); 
            }
            else
            {
                game.tryAgain();
                // Decrease speed: Increase delay by .5
                delay += 0.5;
            }
        }
            
        // Ask to play another round
        playAgain = game.playAgain(); 
    }
   
    // End game and show final score
    game.showScore(score, rounds); 
    game.quit(); 
  }
}
