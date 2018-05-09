import java.io.*;
import java.util.*;
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
/* 	
 	protected final ArrayList<String> hairLength = new ArrayList<String>();
	protected final ArrayList<String> glasses = new ArrayList<String>();
	protected final ArrayList<String> facialHair = new ArrayList<String>();
	protected final ArrayList<String> eyeColor = new ArrayList<String>();
	protected final ArrayList<String> pimples = new ArrayList<String>();
	protected final ArrayList<String> hat = new ArrayList<String>();
	protected final ArrayList<String> hairColor = new ArrayList<String>();
	protected final ArrayList<String> noseShape = new ArrayList<String>();
	protected final ArrayList<String> faceShape = new ArrayList<String>();  */
	

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
		
		FileReader fr = new FileReader(gameFilename);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		String delimiters = " ";
		boolean playerDetails = false;
		
		ArrayList<String> tempAttribute=new ArrayList<>();

		

		
		
		while((line=br.readLine()) != null){
			String[] token = line.split(delimiters);
			ArrayList<String> tempList = new ArrayList<String>();
			
			if(playerDetails==false){
				if(token.length==1){
					playerDetails=true;
					
				}
				for (int i=0; i<token.length;i++){
					tempList.add(token[i]);
				}
				attributes.add(tempList);

			}
			else{
				if(line.equals("")){
					PlayerAttributes tempPlayerAttribute= new PlayerAttributes(tempAttribute.get(0),tempAttribute.get(1),
					tempAttribute.get(2),tempAttribute.get(3),tempAttribute.get(4),tempAttribute.get(5),
					tempAttribute.get(6),tempAttribute.get(7),tempAttribute.get(8),tempAttribute.get(9));
					
					
					possiblePlayers.add(tempPlayerAttribute);
					tempAttribute.clear();
					
					//System.out.println(token.length);
				}
				else{
					if(token.length==1){
						tempAttribute.add(token[0]);
					}
					if(token.length==2){
						tempAttribute.add(token[1]);
					}
				}
			}
		}
		System.out.println("size1" +attributes.size());
		System.out.println("size2" +possiblePlayers.size());
		
				
		
		
/* 		chosenPlayer= new PlayerAttributes("P1","blue","green","white","brown","brown","blue","white","yellow","white");
		System.out.println(chosenPlayer.getName());  */

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
}
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
			this.name=name;
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
		
		public String getName(){
			
			return name;
		}
	}
	

