package na417.Sup3.Game.UI;
import java.io.*;
import java.util.*;

import na417.Sup3.Game.Core.CyberPet;
import na417.Sup3.Game.Core.Monkey;
import na417.Sup3.Game.Core.Octopus;
import na417.Sup3.Game.Core.Seagull;

public class GameUI {
	
	// contains all possible actions by the user in the main menu
	private enum UserActions {talk, play, sleep, feed, buy, work, exercise, viewStats, quit, speciesAction}
	
	// static as there can only be one player and pet per game
	static private String playerName;
	static private CyberPet Pet;
	
	
	public static void main(String[] args) {
		play();
	}
	
	private static int getInt(String context)
	{
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		String choiceStr;
		int choice;
		do{
			choiceStr = scanner.nextLine();
			try{
				choice = Integer.parseInt(choiceStr);
				return choice;
			} catch (NumberFormatException e)
			{
				System.out.println("That is not a valid " + context + " try again.");
			}
		} while (true);
	}
	
	private static void play()
	{
		// scanner to read text input
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		
		// Holds the information about the player's selected pet
		String petName = "";
		int petGender = 0;
		char petGenderChoice;
		int petSpecies = 0;
		String petSpeciesChoice = "";
		String petSpeciesName = "";
		
		
		// Holds user inputs in the main game
		UserActions userAction;
		
		// Gets information about the player's pet
		System.out.println("Welcome! What is your name? Type it in and press enter.");
		playerName = scanner.nextLine();
		
		System.out.println("Hi " + playerName + ". You can now choose your CyberPet");
		System.out.println("What is your Pet's name? Type it in and press enter.");
		petName = scanner.nextLine();
		System.out.println("OK, your pet will be called " + petName);
		
		
		do{
			System.out.println("Now is " + petName + " a boy[b] or a girl[g]?");
			petGenderChoice = scanner.nextLine().charAt(0);
			
			if(petGenderChoice == 'b' | petGenderChoice == 'g') {
				break;
			} else {
				System.out.println("That is not a valid choice, try again.");
			}
		} while (true);
		
		petGender = petGenderChoice == 'b' ? 0 : 1;
				
		if(petGender == 0) {
			System.out.println("OK, " + petName + " is a boy.");
		} else {
			System.out.println("OK, " + petName + " is a girl.");
		}
		
		do{
			System.out.println("Now, what species is " + petName + "? A Monkey[0], an Octopus[1] or a Seagull[2]?");
			petSpeciesChoice = scanner.nextLine();
			try{
				petSpecies = Integer.parseInt(petSpeciesChoice);
				break;
			} catch (NumberFormatException e) 
			{
				System.out.println("That is not a valid choice of species, try again.");
			}
		} while (true);
		
		// instantiate the pet
		switch(petSpecies)
		{
		case 0:
			Pet = new Monkey(petName, CyberPet.Gender.values()[petGender], CyberPet.Species.values()[petSpecies]);
			petSpeciesName = "Monkey";
			break;
		case 1:
			Pet = new Octopus(petName, CyberPet.Gender.values()[petGender], CyberPet.Species.values()[petSpecies]);
			petSpeciesName = "Octopus";
			break;
		case 2:
			Pet = new Seagull(petName, CyberPet.Gender.values()[petGender], CyberPet.Species.values()[petSpecies]);
			petSpeciesName = "Seagull";
			break;
		}
		
		System.out.println("Good job, here is a description of your new pet: ");
		
		// the boolean argument is false to indicate we do not want a verbose output
		Pet.displayStats(false);
		
		// this is the main menu
		while (true)
		{
			System.out.println("What do you want to do now?");
			// possible actions 
			System.out.println("talk[0], play[1], sleep[2], feed[3], buy[4], work[5], exercise[6]," +
					" viewStats[7], quit[8], " + petSpeciesName + " stuff[9]");
			
			userAction = UserActions.values()[scanner.nextInt()];
			switch(userAction){
			case talk:
				Pet.talk();
				break;
			case play:
				Pet.AgeBehaviour.play();
				break;
			case sleep:
				Pet.sleep();
				break;
			case feed:
				Pet.feed();
				break;
			case buy:
				Pet.buy();
				break;
			case work:
				Pet.AgeBehaviour.work();
				break;
			case exercise:
				Pet.AgeBehaviour.exercise();
				break;
			case viewStats:
				Pet.displayStats(true);
				break;
			case quit:
				// verifies if player wants to quit
				System.out.println("Are you sure? [y|n]");
				char response = scanner.nextLine().charAt(0); 
				if(response == 'y') { 
					System.out.println("Bye!"); 
					return; 
				} 
				break;
			case speciesAction:
				int choice;
				switch(petSpeciesName)
				{
				case "Monkey":
					System.out.println("Do you want to climb[1] or groom[2] or " +
							"return to the main menu[3]");
					choice = getInt(petSpeciesName + " action");
					switch(choice)
					{
					case 1:
						
						break;
					case 2:
						break;
					}
					break;
				case "Octopus":
					System.out.println("Do you want to swim[1] or hunt[2]" +
							"return to the main menu[3]");
					choice = getInt(petSpeciesName + " action");
					switch(choice)
					{
					case 1:
						
						break;
					case 2:
						break;
					}
					break;
				case "Seagull":
					System.out.println("Do you want to fly[1] or scavenge[2] +" +
							"return to the main menu[3]");
					choice = getInt(petSpeciesName + " action");
					switch(choice)
					{
					case 1:
						break;
					case 2:
						break;
					}
					break;
				}
				break;
			}
		}
	}
}
