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
							
		String line=null;
		String delimiters = " ";
		boolean playerDetails = false;
		
		// have an arraylist that we can temp store all the attributes at the top of the config list.
		ArrayList<String> tempAttribute=new ArrayList<>();

		// Keep reading the file unitl we read the end
		while((line=br.readLine()) != null){

			String[] token = line.split(delimiters);
			ArrayList<String> tempList = new ArrayList<String>();
			
			
/* 			// Store possible attributes
			if(playerDetails==false){
				if(token.length==1){
					playerDetails=true;
					
				}
				for (int i=0; i<token.length;i++){
					tempList.add(token[i]);
				}
				attributes.add(tempList);

			}
			// Store player attributes */
			if(token.length==1&&playerDetails==false){
					playerDetails=true;
			}
			else if(token.length!=1&&playerDetails==false){
				for (int i=0; i<token.length;i++){
					tempList.add(token[i]);
				}
				attributes.add(tempList);
			}
			else{
				
				if(!line.equals("")){
					if(token.length==1){
						tempAttribute.add(token[0]);
					}
					if(token.length==2){
						tempAttribute.add(token[1]);
						//attributeValueSet.add(new AttributeValue(token[0],token[1]));
					}
					
				}
				
				if(line.equals("")){
					PlayerAttributes tempPlayerAttribute= new PlayerAttributes(tempAttribute.get(0),tempAttribute.get(1),
					tempAttribute.get(2),tempAttribute.get(3),tempAttribute.get(4),tempAttribute.get(5),
					tempAttribute.get(6),tempAttribute.get(7),tempAttribute.get(8),tempAttribute.get(9));
					
					
					possiblePlayers.add(tempPlayerAttribute);
					tempAttribute.clear();
					
				}
			}
		}
		PlayerAttributes tempPlayerAttribute= new PlayerAttributes(tempAttribute.get(0),tempAttribute.get(1),
		tempAttribute.get(2),tempAttribute.get(3),tempAttribute.get(4),tempAttribute.get(5),
		tempAttribute.get(6),tempAttribute.get(7),tempAttribute.get(8),tempAttribute.get(9));
					
		possiblePlayers.add(tempPlayerAttribute);
		
/* 		System.out.println("Possible players" + possiblePlayers.size());
		System.out.println("Attribute size" + attributes.size()); */
		
		// Determine the chosen player
		int index=0;
		for(int i=0;i<possiblePlayers.size();i++){
			if(possiblePlayers.get(i).getName().equals(chosenName)){
				chosenPlayer=possiblePlayers.get(i);
				index=i;
			}
		}
		possiblePlayers.remove(index);
/* 		for(int i=0;i<attributes.size();i++){
			for(int j=0;j<attributes.get(i).size();j++){
				System.out.print(attributes.get(i).get(j));
			}
			System.out.println();
			
		} */
    } // end of RandomGuessPlayer()


    public Guess guess() {
		
		//if there is only one possible player left, guess that player
		if(possiblePlayers.size()==1){
			return new Guess(Guess.GuessType.Person, "",possiblePlayers.get(0).getName());
		}
		else{
			//create the set
			ArrayList<AttributeValue> attributeValueSet = new ArrayList<AttributeValue>();
			
			for(int i=0;i<possiblePlayers.size();i++){
				attributeValueSet.add(new AttributeValue("hairlength", possiblePlayers.get(i).getHairLength()));
				attributeValueSet.add(new AttributeValue("glasses", possiblePlayers.get(i).getGlasses()));
				attributeValueSet.add(new AttributeValue("facialHair", possiblePlayers.get(i).getFacialHair()));
				attributeValueSet.add(new AttributeValue("eyeColor", possiblePlayers.get(i).getEyeColor()));
				attributeValueSet.add(new AttributeValue("pimples", possiblePlayers.get(i).getPimples()));
				attributeValueSet.add(new AttributeValue("hat", possiblePlayers.get(i).getHat()));
				attributeValueSet.add(new AttributeValue("hairColor", possiblePlayers.get(i).getHairColor()));
				attributeValueSet.add(new AttributeValue("noseShape", possiblePlayers.get(i).getNoseShape()));
				attributeValueSet.add(new AttributeValue("faceShape", possiblePlayers.get(i).getFaceShape()));				
			}
/* 			
			for(int j=0;j<attributeValueSet.size();j++){
				System.out.println((j+1) + " " +attributeValueSet.get(j).getAttribute() + " " + attributeValueSet.get(j).getValue());
				if((j+1)%9==0){
					System.out.println();
				}
			} */
			int randInt=randomNumberGenerator(attributeValueSet.size());
			return new Guess(Guess.GuessType.Attribute,attributeValueSet.get(randInt).getAttribute(),attributeValueSet.get(randInt).getValue());
		}
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
			String tempValue=currGuess.getValue();
			
			int index=indexFinder(tempAttribute);
			switch(index){
				case 0:
					if(chosenPlayer.getHairLength().equals(tempValue)){
						//System.out.println("Chosen : " +chosenPlayer.getHairLength());
						return true;
					}
					break;
				case 1:
					if(chosenPlayer.getGlasses().equals(tempValue)){
						//System.out.println("Chosen : " +chosenPlayer.getGlasses());
						return true;
					}
					break;
				case 2:
					if(chosenPlayer.getFacialHair().equals(tempValue)){
						//System.out.println("Chosen : " +chosenPlayer.getFacialHair());
						return true;
					}
					break;
				case 3:
					if(chosenPlayer.getEyeColor().equals(tempValue)){
						//System.out.println("Chosen : " +chosenPlayer.getEyeColor());
						return true;
					}
					break;
				case 4:
					if(chosenPlayer.getPimples().equals(tempValue)){
						//System.out.println("Chosen : " +chosenPlayer.getPimples());
						return true;
					}
					break;
				case 5:
					if(chosenPlayer.getHat().equals(tempValue)){
						//System.out.println("Chosen : " +chosenPlayer.getHat());
						return true;
					}
					break;
				case 6:
					if(chosenPlayer.getHairColor().equals(tempValue)){
						//System.out.println("Chosen : " +chosenPlayer.getHairColor());
						return true;
					}
					break;
				case 7:
					if(chosenPlayer.getNoseShape().equals(tempValue)){
						//System.out.println("Chosen : " +chosenPlayer.getNoseShape());
						return true;
					}
					break;
				case 8:
					if(chosenPlayer.getFaceShape().equals(tempValue)){
						//System.out.println("Chosen : " +chosenPlayer.getFaceShape());
						return true;
					}
					break;
				default:
					break;
				
			}
		}
		return false;
    } // end of answer()


	public boolean receiveAnswer(Guess currGuess, boolean answer) {
		
		if(currGuess.getType().equals(Guess.GuessType.Person)){
			if(answer){
				 
				return true;
			}
		}
		else{
			ArrayList<PlayerAttributes> tempPossiblePlayers = new ArrayList<PlayerAttributes>();
			String tempAttribute=currGuess.getAttribute();
			String tempValue=currGuess.getValue();
			
			int index=indexFinder(tempAttribute);
			switch(index){
				case 0:
					if(answer){
						for(int i=0;i<possiblePlayers.size();i++){
							if(possiblePlayers.get(i).getHairLength().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					else{
						for(int i=0;i<possiblePlayers.size();i++){
							if(!possiblePlayers.get(i).getHairLength().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					possiblePlayers=tempPossiblePlayers;					
					break;
					
				case 1:
					if(answer){
						for(int i=0;i<possiblePlayers.size();i++){
							if(possiblePlayers.get(i).getGlasses().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					else{
						for(int i=0;i<possiblePlayers.size();i++){
							if(!possiblePlayers.get(i).getGlasses().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					possiblePlayers=tempPossiblePlayers;
					break;
					
				case 2:
					if(answer){
						for(int i=0;i<possiblePlayers.size();i++){
							if(possiblePlayers.get(i).getFacialHair().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					else{
						for(int i=0;i<possiblePlayers.size();i++){
							if(!possiblePlayers.get(i).getFacialHair().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					possiblePlayers=tempPossiblePlayers;
					break;
				case 3:
					if(answer){
						for(int i=0;i<possiblePlayers.size();i++){
							if(possiblePlayers.get(i).getEyeColor().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					else{
						for(int i=0;i<possiblePlayers.size();i++){
							if(!possiblePlayers.get(i).getEyeColor().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					possiblePlayers=tempPossiblePlayers;
					break;
				case 4:
					if(answer){
						for(int i=0;i<possiblePlayers.size();i++){
							if(possiblePlayers.get(i).getPimples().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					else{
						for(int i=0;i<possiblePlayers.size();i++){
							if(!possiblePlayers.get(i).getPimples().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					possiblePlayers=tempPossiblePlayers;
					break;
				case 5:
					if(answer){
						for(int i=0;i<possiblePlayers.size();i++){
							if(possiblePlayers.get(i).getHat().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					else{
						for(int i=0;i<possiblePlayers.size();i++){
							if(!possiblePlayers.get(i).getHat().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					possiblePlayers=tempPossiblePlayers;
					break;
				case 6:
					if(answer){
						for(int i=0;i<possiblePlayers.size();i++){
							if(possiblePlayers.get(i).getHairColor().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					else{
						for(int i=0;i<possiblePlayers.size();i++){
							if(!possiblePlayers.get(i).getHairColor().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					possiblePlayers=tempPossiblePlayers;
					break;
				case 7:
					if(answer){
						for(int i=0;i<possiblePlayers.size();i++){
							if(possiblePlayers.get(i).getNoseShape().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					else{
						for(int i=0;i<possiblePlayers.size();i++){
							if(!possiblePlayers.get(i).getNoseShape().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					possiblePlayers=tempPossiblePlayers;
					break;
				case 8:
					if(answer){
						for(int i=0;i<possiblePlayers.size();i++){
							if(possiblePlayers.get(i).getFaceShape().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					else{
						for(int i=0;i<possiblePlayers.size();i++){
							if(!possiblePlayers.get(i).getFaceShape().equals(tempValue)){
								tempPossiblePlayers.add(possiblePlayers.get(i));
							}
						}
					}
					possiblePlayers=tempPossiblePlayers;
					break;
				default:
					break;
			}
			
		}
		System.out.print("Random:");
		for(int i=0;i<possiblePlayers.size();i++){
			System.out.print(" " + possiblePlayers.get(i).getName());
		}
		System.out.println();		System.out.println();
        return false;
    } // end of receiveAnswer()
	
	public int randomNumberGenerator(int n){
		Random generator = new Random();
		
		return generator.nextInt(n);
	}

	public int indexFinder(String tempAttribute){
		
		for(int index=0;index<attributes.size();index++){
			if(tempAttribute.equals(attributes.get(index).get(0))){
				return index;
			}
		}
		return -1;
	}
 // end of class RandomGuessPlayer
}


	