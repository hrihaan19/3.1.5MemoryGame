/**
 * Project 3.1.5 Memory Game
 * * Logic: Displays a randomized sequence of strings. The player must 
 * recreate the sequence from memory.
 */
public class MemoryGame
{
  public static void main(String[] args) {

    // TO DO: Create the "memory strings" - an array of 5 single character strings
    String[] memoryStrings = {"A", "B", "C", "D", "E"};

    // Create the game and gameboard. Configure with 3 buttons.
    MemoryGameGUI game = new MemoryGameGUI();
    game.createBoard(3, true);

    // Initialize scoring and loop variables
    int score = 0;
    int rounds = 0;
    boolean keepPlaying = true;


    // role switch
    // Hrihaan: Driver (person writing the code)
    // Avi: Navigator (suggesting edits and guiding)

    // main game loop runs until the player decides to stop
    while (keepPlaying) {
        rounds++;
        
        // copy the memory strings into a new array that will be shuffled
        String[] randomSeq = new String[memoryStrings.length];
        System.arraycopy(memoryStrings, 0, randomSeq, 0, memoryStrings.length);
        
        // shuffle the sequence using a random swapping algorithm
        for (int i = 0; i < randomSeq.length; i++) {
            int randIndex = (int)(Math.random() * randomSeq.length);
            String temp = randomSeq[i];
            randomSeq[i] = randomSeq[randIndex];
            randomSeq[randIndex] = temp;
        }

        // play the sequence on screen with a 0.5 second delay
        // the player's input guess is captured 
        String guess = game.playSequence(randomSeq, .5);


        // role switch #2
        // Avi: Driver 
        // Hrihaan: Navigator

        // check that the guess is not null (handles the Cancel button case)
        if (guess != null) {

            // clean the user's input by removing spaces and commas
            String cleanGuess = guess.replace(" ", "").replace(",", "");
            
            // convert the correct sequence array into a string for comparison
            String actualSeq = "";
            for (String s : randomSeq) {
                actualSeq += s;
            }

            // compare the user's guess to the actual sequence
            if (cleanGuess.equalsIgnoreCase(actualSeq)) {
                game.matched();
                score++;
            } else {
                game.tryAgain();
            }
        }


        // role switch #3
        // Hrihaan: Driver
        // Avi: Navigator

        // ask the player if they want to keep playing
        keepPlaying = game.playAgain();
    }
   
    // print the final score and number of rounds played
    game.showScore(score, rounds);
  }
}
