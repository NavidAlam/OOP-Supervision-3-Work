package na417.Sup3.Game.Core;
import java.io.*;
import java.util.*; 

public abstract class CyberPet {
	// various enums used to identify properties the pet may have
	// TODO caps all enum vals 
	public enum Gender {Male, Female};
	public enum Species {Monkey, Octopus, Seagull};
	public enum Traits {Sleepy, Hungry, Happy, Active, Hardworking};
	// each Item enum has an associated price which is used in the buy method
	public enum Items {
		Weights(20), Treadmill(50), Cake(10), Coke(2), ActionFigures(15), 
		BouncyCastle(50), Wii(100), PS4(300), PC(500), Quadrotor(1000);
		private final int price;
		Items(int price)
		{
			this.price = price;
		}
		
		int getPrice() {return price;}
	};
	public enum ActionDone {Ate, Slept, Worked, Exercised, Played, Brought};
	
	// holds basic details about the pet
	private String name;
	private Gender gender;
	private Species species;
	private int age = 1;
	
	//holds current state of the pet's wellbeing
	private float fullness = 1.0f;
	private float happiness = 1.0f;
	private float sleepiness = 1.0f;
	private float fitness = 0.5f;
	//holds misc states 
	private ArrayList<Traits> traits;
	private int money = 100;
	private ArrayList<Items> items;
	private int gameTime = 0;
	public AgeBehaviours AgeBehaviour;
	
	// lets the player feed the pet any food they have in items 
	public void feed() 
	{
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		int noOfFood = 0;
		int foodChoice;

		System.out.println("What do you want to feed your pet?");
		System.out.println("You have: ");
		
		// lists items the user can feed their pet
		for(int i = 0; i < items.size(); i++)
		{
			Items item = items.get(i);
			if((item == Items.Cake) || (item == Items.Coke))
			{
				System.out.print(item);
				System.out.println(" press " + i + " to eat");
				noOfFood++;
			}
		}
		
		// if the user does not have any food
		if(noOfFood == 0) {
			System.out.println("Uh oh, you don't have any food," +
					" you can buy some from the store.");
			return;
		}
		
		// gets user's choice of food
		foodChoice = getInt("food");
		
		// reduces hunger / increments fullness based on food
		switch(items.get(foodChoice))
		{
		case Cake:
			incrementFullness(0.6f);
			break;
		case Coke:
			incrementFullness(0.3f);
			break;
		}

		// lets the user know the outcome
		System.out.println(name + " is happy for eating " + items.get(foodChoice) +
				", they are: " + (100*fullness) + "% full");
		
		items.remove(foodChoice);
	}
	
	// fullness cannot exceed 1.0, this method ensures that
	private void incrementFullness(float amount)
	{
		if(fullness != 1.0f)
		{
			fullness = fullness + amount > 1.0f ? 1.0f : fullness + amount;
		}
	}
	
	// similarly with happiness
	private void incrementHappiness(float amount)
	{
		if(happiness != 1.0f)
		{
			happiness = happiness + amount > 1.0f ? 1.0f : happiness + amount;
		}
	}
	
	// and with fitness
	private void incrementFitness(float amount)
	{
		if(fitness != 1.0f)
		{
			fitness = fitness + amount > 1.0f ? 1.0f : fitness + amount;
		}
	}
	
	// gets an integer from the user
	// the context string allows for a contextualised error message if the user
	// does not input an integer
	private int getInt(String context)
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
				// uses context
				System.out.println("That is not a valid " + context + " try again.");
			}
		} while (true);
	}
	
	// gets a char from the user with the condition that it must be a member of
	// the set of valid chars (the ArrayList valid).
	// Also for a string (context) to be displayed beforehand 
	private char getChar(ArrayList<Character> valid, String context)
	{
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		String choiceStr;
		char choiceChar;
		
		do{
			// uses context
			System.out.println(context);
			choiceStr = scanner.nextLine();
			choiceChar = choiceStr.charAt(0);
			
			if(valid.contains(choiceChar)){
				return choiceChar;
			} else {
				System.out.println("That is not a valid choice, try again.");
			}
		} while(true);
	}
	
	// TODO implement this
	public void talk() {}
	
	// lets the user tell their pet to sleep
	public void sleep() {
		char choice;
		// initialises variables used in the getChar method
		String context = "Are you sure you want to sleep? [Y|n]";
		ArrayList<Character> validChoices = new ArrayList<Character>();
		validChoices.add('Y');
		validChoices.add('n');
		// uses getChar to get user's choice
		choice = getChar(validChoices, context);
		// if we want to sleep
		if(choice == 'Y') {
			// updates sleepiness
			sleepiness = sleepiness + 0.5f > 1.0f ? 1.0f : sleepiness + 0.5f;
			
			// makes the user aware of the action performed
			System.out.println(name + " has slept and is feeling refreshed." + 
			" they are feeling " + 100*sleepiness + "% rested");
			updateGameState(ActionDone.Slept);
			return;
		}
		// if they do not want to sleep, just increment the game time 
		gameTime++;
	}
	
	// allows the user to spend their money to buy items from a shop
	public void buy() 
	{
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		
		// holds user's choice
		int choice;
		
		// makes the user aware of how much they can spend
		System.out.println("You have: £" + money);
		System.out.println("This store sells: ");
		
		// lists all the items, their price and how to buy them
		for(Items i : Items.values())
		{
			System.out.print(i);
			System.out.print(" costing £" + i.getPrice());
			System.out.println(". Press " + i.ordinal() + " to buy");
		}
		
		// gets the user's choice of what to buy
		choice = getInt("Item");
		
		// if the user's choice is too expensive
		if(Items.values()[choice].getPrice() > money)
		{
			// do not buy anything, just make the user aware they need more money
			System.out.println("You do not have enough money to buy this." +
					" You need: £" + (Items.values()[choice].getPrice() - money) + 
					" More. You can work for money.");
		} else {
			// update the money
			money -= Items.values()[choice].getPrice();
			// add it to the pet's list of items
			items.add(Items.values()[choice]);
			// tell the user what happened
			System.out.println("You have brought a " + Items.values()[choice] + 
					". You now have: £" + money + " left.");
		}
	}
	
	// will be called after each action to update the state of the pet
	// it will take into account the traits, age and items of the pet
	// TODO implement this
	private void updateGameState(ActionDone action)
	{
		//{Ate, Slept, Worked, Exercised, Played, Brought};
		switch(action)
		{
		case Ate:
			gameTime++;
			break;
		case Slept:
			gameTime+=3;
			break;
		case Worked:
			gameTime+=3;
			break;
		case Exercised:
			gameTime+=2;
			break;
		case Played:
			gameTime+=2;
			break;
		case Brought:
			gameTime++;
			break;
		}
		
		// evolve pet
		if (gameTime > 50){
			
		}
		
	}
	
	// constructor
	public CyberPet(String name, Gender gender, Species species)
	{
		// initialises the members of CyberPet
		// from arguments
		this.name = name;
		this.gender = gender;
		this.species = species;
		
		// initialises traits and adds a random trait
		Random rnd = new Random();
		int randomTrait = rnd.nextInt(4);
		traits = new ArrayList<Traits>();
		traits.add(Traits.values()[randomTrait]);
		
		// initialises items
		items = new ArrayList<Items>();
		
		// initialises age behaviours as a child, as the pet starts as a child
		AgeBehaviour = new Child();
	}
	
	// displays all the information about the pet
	// if the verbose flag is enabled, more information is displayed
	public void displayStats(boolean verbose)
	{
		System.out.println("Name: " + name);
		System.out.println("Gender: " + gender);
		System.out.println("Species: " + species);
		System.out.println("Age: " + age);
		
		if(verbose) {
			System.out.println("Fullness: " + fullness);
			System.out.println("Happiness: " + happiness);
			System.out.println("Sleepiness: " + sleepiness);
			System.out.println("Fitness: " + fitness);
			
			String traitsOutput = "Traits: ";
			for(Traits t: traits)
			{
				traitsOutput += t;
			}
			System.out.println(traitsOutput);
			
			String itemsOutput = "Items: ";
			for(Items i : items)
			{
				itemsOutput += i;
			}
			System.out.println(itemsOutput);
		}
	}
}
