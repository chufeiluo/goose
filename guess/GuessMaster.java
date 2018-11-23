
//Assignment 2: GuessMaster 2.0
//Chu Fei Luo, 10187977


import java.util.Random;
import java.util.Scanner;

import java.util.ArrayList;

public class GuessMaster {
	private int numEntities;
	private ArrayList<Entity> entities; 
	
	public GuessMaster() {
		numEntities = 0;
		entities = new ArrayList<Entity>(); 
	}

	public void addEntity(Entity entity) {
		if(entity == null) 
			return;
		entities.add(entity);
		numEntities++;
	}
	
	public void playGame() {
		// initializing variables
		Random rng = new Random();
		Scanner player =  new Scanner(System.in);

		String input;
		Date inputDate;
		Entity toCheck;
		int index = rng.nextInt(numEntities);
		int round = 1;
		boolean correct = false;

		int score = 0;

		// uses a boolean array to keep track of which entities have already been given
		// to player
		boolean[] tested = new boolean[numEntities];
		for (int i = 0; i < numEntities; i++) {
			tested[i] = false;
		}

		// game lasts as many rounds as there are entities in the array, or until player
		// quits
		while (round <= numEntities) {
			// find a new index while checking that it hasn't been asked before
			while (tested[index] != false)
				index = rng.nextInt(numEntities);
			
			toCheck = entities.get(index);
			
			// ask player question
			System.out.println("Round " + (round) + "!");
			System.out.println(toCheck.entityType());
			System.out.println("What is the birthday of " + toCheck.getName() + "?");

			for (int i = 0; i < 5 && !correct; i++) { // player allowed 5 tries

				input = player.nextLine(); // get input, strip newlines
				input = input.replace("\n", "");

				if (input.equalsIgnoreCase("quit")) { // check for exit condition
					System.out.println("Quitting...");
					player.close();
					return;
				}

				if (input.contains("/") && !input.contains(" ")) // assuming mm/dd/yyyy format
					inputDate = new Date(input);
				else { // assuming Month Day, Year format otherwise
					input = input.replace(",", "");
					String[] in = input.split(" ");
					inputDate = new Date(in[0], Integer.parseInt(in[1]), Integer.parseInt(in[2]));
				}

				if (toCheck.getBorn().equals(inputDate)) { // correct!!
					correct = true;
					score += toCheck.getAwardedTicketNumber(); //add ticket number stored in entity to the total score
					System.out.println("BINGO!!!! Congratulations!");
					System.out.println("You earned " + toCheck.getAwardedTicketNumber() + " tickets!"); //print tickets earned
				}
				else if (toCheck.getBorn().precedes(inputDate)) // date entered is too early
					System.out.println("Incorrect! Please try an earlier date\n" + (4 - i) + " tries remaining");
				else // date entered is too late otherwise
					System.out.println("Incorrect! Please try a later date\n" + (4 - i) + " tries remaining");

				// if they ran out of tries, give correct answer
				if (i >= 4) {
					System.out.println("\nThe correct answer is " + entities.get(index).getBorn());
				}
			}
			// only exits loop when correct or ran out of tries
			// print closing message of entity and total score
			System.out.println(toCheck.closingMessage());
			System.out.println("\nTotal number of tickets: " + score + "\n");
			//reset correct flag, set flag corresponding to index number to true, loop back to next round
			correct = false;
			tested[index] = true;
			round++;
		}
		//when they finished all the rounds
		System.out.println("Final Score: " + score); 
		player.close();
		return;
	}

	public static void main(String[] args) {
		System.out.println("=========================\n");
		System.out.println("     GuessMaster 2.0 \n");
		System.out.println("=========================");
		
		Politician trudeau = new Politician("Justin Trudeau", new Date("December", 25,
				1971), "Male", "Liberal", 0.25);

		Singer dion = new Singer("Celine Dion", new Date("March", 30, 1961), "Female",
				"La voix du bon dieu", new Date("November", 6, 1981), 0.5);
		
		Person myCreator = new Person("my creator", new Date("December", 2, 1997), "Female",
				1);
		Country usa = new Country("The United States", new Date("July", 4, 1776), "Washington D.C.", 0.1);
		
		GuessMaster gm = new GuessMaster();
		
		gm.addEntity(trudeau);
		gm.addEntity(dion);
		gm.addEntity(myCreator);
		gm.addEntity(usa);
		
		gm.playGame();
	}
}
