import java.io.*;
import java.util.*;
import java.math.*;
/**
 * Random guessing player.
 * This player is for task B.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class RandomGuessPlayer implements Player
{
	
	protected PlayerAttributes chosenPlayer=null;
	protected ArrayList<PlayerAttributes> possiblePlayers = new ArrayList<PlayerAttributes>();
	protected ArrayList<ArrayList<String>> attributes = new ArrayList<ArrayList<String>>();
	protected Set<AttributeValue> attributeValueSet = new LinkedHashSet<>();
	

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
		// Read the input file
		FileReader fr = new FileReader(gameFilename);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		String delimiters = " ";
		boolean playerDetails = false;
		
		// have an arraylist that we can temp store all the attributes at the top of the config list.
		ArrayList<String> tempAttribute=new ArrayList<>();

		// Keep reading the file unitl we read the end
		while((line=br.readLine()) != null){
			String[] token = line.split(delimiters);
			ArrayList<String> tempList = new ArrayList<String>();
			
			// Store possible attributes
			if(playerDetails==false){
				if(token.length==1){
					playerDetails=true;
					
				}
				for (int i=0; i<token.length;i++){
					tempList.add(token[i]);
				}
				attributes.add(tempList);

			}
			// Store player attributes
			else{
				// if we have a blank new line, store the temp attributes to the given player and clear the buffer
				if(line.equals("")){
					PlayerAttributes tempPlayerAttribute= new PlayerAttributes(tempAttribute.get(0),tempAttribute.get(1),
					tempAttribute.get(2),tempAttribute.get(3),tempAttribute.get(4),tempAttribute.get(5),
					tempAttribute.get(6),tempAttribute.get(7),tempAttribute.get(8),tempAttribute.get(9));
					
					
					possiblePlayers.add(tempPlayerAttribute);
					tempAttribute.clear();
					
					//System.out.println(token.length);
				}
				// else add the attributes to the tempAttribute list.
				else{
					if(token.length==1){
						tempAttribute.add(token[0]);
					}
					if(token.length==2){
						tempAttribute.add(token[1]);
						attributeValueSet.add(new AttributeValue(token[0],token[1]));
					}
				}
			}
		}
		System.out.println(attributeValueSet.size());
		// Determine the chosen player
		for(int i=0;i<possiblePlayers.size();i++){
			if(possiblePlayers.get(i).getName().equals(chosenName)){
				System.out.println(possiblePlayers.get(i).getName());
				chosenPlayer=possiblePlayers.get(i);
			}
		}

    } // end of RandomGuessPlayer()


    public Guess guess() {
		//if there is only one possible player left, guess that player
		if(possiblePlayers.size()==1){
			return new Guess(Guess.GuessType.Person, "",possiblePlayers.get(0).getName());
		}
		//otherwise guess from the remaining set
		else{
			int sizeSet=attributeValueSet.size();

		}

        // placeholder, replace
        return new Guess(Guess.GuessType.Person, "", "Placeholder");
    } // end of guess()


    public boolean answer(Guess currGuess) {

		
		//Guess Person
		if(currGuess.getType().equals(Guess.GuessType.Person)){
			if(currGuess.getValue().equals(chosenPlayer.getName())){
				return true;
			}
		}
		//Guess Attribute
		if(currGuess.getType().equals(Guess.GuessType.Attribute)){
			String tempAttribute=currGuess.getAttribute();
			
			//if(chosenPlayer.)
		}
        // placeholder, replace
        return false;
    } // end of answer()


	public boolean receiveAnswer(Guess currGuess, boolean answer) {

        // placeholder, replace
        return true;
    } // end of receiveAnswer()

	public int RandomNumberGenerator(int n){
	Random generator = new Random();
	
	return generator.nextInt(n)+1;
}
 // end of class RandomGuessPlayer
}


	