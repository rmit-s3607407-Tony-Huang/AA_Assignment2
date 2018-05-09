import java.io.*;

/**
 * Random guessing player.
 * This player is for task B.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class RandomGuessPlayer implements Player
{
	
	protected final PlayerAttributes choosenPlayer;
	protected final ArrayList<PlayerAttributes> possiblePlayers;
/* 	protected final ArrayList<String> hairLength;
	protected final ArrayList<String> glasses;
	protected final ArrayList<String> facialHair;
	protected final ArrayList<String> eyeColor;
	protected final ArrayList<String> pimples;
	protected final ArrayList<String> hat;
	protected final ArrayList<String> hairColor;
	protected final ArrayList<String> noseShape;
	protected final ArrayList<String> faceShape; */
	
    /**
     * Loads the game configuration from gameFilename, and also store the chosen
     * person.
     *
     * @param gameFilename Filename of game configuration.
     * @param chosenName Name of the chosen person for this player.
     * @throws IOException If there are IO issues with loading of gameFilename.
     *    Note you can handle IOException within the constructor and remove
     *    the "throws IOException" method specification, but make sure your
     *    implementation exits gracefully if an IOException is thrown.
     */
    public RandomGuessPlayer(String gameFilename, String chosenName)
        throws IOException
    {
		

    } // end of RandomGuessPlayer()


    public Guess guess() {

        // placeholder, replace
        return new Guess(Guess.GuessType.Person, "", "Placeholder");
    } // end of guess()


    public boolean answer(Guess currGuess) {

        // placeholder, replace
        return false;
    } // end of answer()


	public boolean receiveAnswer(Guess currGuess, boolean answer) {

        // placeholder, replace
        return true;
    } // end of receiveAnswer()

 // end of class RandomGuessPlayer

	class PlayerAttributes{
		
		protected String name;
		protected String hairLength;
		protected String glasses;
		protected String facialHair;
		protected String eyeColor;
		protected String pimples;
		protected String hat;
		protected String hairColor;
		protected String noseShape;
		protected String faceShape;
		
		PlayerAttributes(String name, String hairLength, String glasses, String facialHair, String eyeColor, String pimples,
		String hat, String hairColor, String noseShape, String faceShape){
			this.hairLength=hairLength;
			this.glasses=glasses;
			this.facialHair=facialHair;
			this.eyeColor=eyeColor;
			this.pimples=pimples;
			this.hat=hat;
			this.hairColor=hairColor;
			this.noseShape=noseShape;
			this.faceShape=faceShape;
		}
	}
	

}