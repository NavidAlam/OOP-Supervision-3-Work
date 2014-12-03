package na417.Sup3.Game.Core;
import java.io.*;
import java.util.*; 

public abstract class CyberPet {
	public enum Gender {Male, Female};
	public enum Species {Monkey, Octopus, Seagull};
	public enum Traits {Sleepy, Hungry, Happy, Active, Hardworking};
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
	
	private String name;
	private Gender gender;
	private Species species;
	private int age = 1;
	private float fullness = 1.0f;
	private float happiness = 1.0f;
	private float sleepiness = 1.0f;
	private float fitness = 0.5f;
	private ArrayList<Traits> traits;
	private int money = 100;
	private ArrayList<Items> items;
	private int gameTime = 0;
	public AgeBehaviours AgeBehaviour;
	
	public void feed() 
	{
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		int noOfFood = 0;
		int foodChoice;
		
		System.out.println("What do you want to feed your pet?");
		System.out.println("You have: ");
		
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
		
		if(noOfFood == 0) {
			System.out.println("Uh oh, you don't have any food," +
					" you can buy some from the store.");
			return;
		}
		
		foodChoice = getInt("food");
		
		switch(items.get(foodChoice))
		{
		case Cake:
			incrementFullness(0.6f);
			break;
		case Coke:
			incrementFullness(0.3f);
			break;
		}

		System.out.println(name + " is happy for eating " + items.get(foodChoice) +
				" they are: " + (100*fullness) + "% full");
		
		items.remove(foodChoice);
	}
	
	private void incrementFullness(float amount)
	{
		if(fullness != 1.0f)
		{
			fullness = fullness + amount > 1.0f ? 1.0f : fullness + amount;
		}
	}
	
	private void incrementHappiness(float amount)
	{
		if(happiness != 1.0f)
		{
			happiness = happiness + amount > 1.0f ? 1.0f : happiness + amount;
		}
	}
	
	private void incrementFitness(float amount)
	{
		if(fitness != 1.0f)
		{
			fitness = fitness + amount > 1.0f ? 1.0f : fitness + amount;
		}
	}
	
	
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
				System.out.println("That is not a valid " + context + " try again.");
			}
		} while (true);
	}
	
	private char getChar(ArrayList<Character> valid, String context)
	{
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		String choiceStr;
		char choiceChar;
		
		do{
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
	
	public void talk() {}
	
	public void sleep() {
		char choice;
		String context = "Are you sure you want to sleep? [Y|n]";
		ArrayList<Character> validChoices = new ArrayList<Character>();
		validChoices.add('Y');
		validChoices.add('n');
		choice = getChar(validChoices, context);
		if(choice == 'Y') {
			sleepiness = sleepiness + 0.5f > 1.0f ? 1.0f : sleepiness + 0.5f;
			System.out.println(name + " has slept and is feeling refreshed." + 
			" they are feeling " + 100*sleepiness + "% rested");
			updateGameState(ActionDone.Slept);
			return;
		}
		gameTime++;
	}
	
	public void buy() 
	{
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		int choice = -1;
		
		System.out.println("You have: £" + money);
		System.out.println("This store sells: ");
		
		for(Items i : Items.values())
		{
			System.out.print(i);
			System.out.print(" costing £" + i.getPrice());
			System.out.println(". Press " + i.ordinal() + " to buy");
		}
		
		choice = getInt("Item");
		
		if(Items.values()[choice].getPrice() > money)
		{
			System.out.println("You do not have enough money to buy this." +
					" You need: £" + (Items.values()[choice].getPrice() - money) + 
					" More. You can work for money.");
		} else {
			money -= Items.values()[choice].getPrice();
			items.add(Items.values()[choice]);
			System.out.println("You have brought a " + Items.values()[choice] + 
					". You now have: £" + money + " left.");
		}
	}
	
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
	
	public CyberPet(String name, Gender gender, Species species)
	{
		Random rnd = new Random();
		int randomTrait = rnd.nextInt(4);
		this.name = name;
		this.gender = gender;
		this.species = species;
		
		items = new ArrayList<Items>();
		traits = new ArrayList<Traits>();
		traits.add(Traits.values()[randomTrait]);
		
		AgeBehaviour = new Child();
	}
	
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
