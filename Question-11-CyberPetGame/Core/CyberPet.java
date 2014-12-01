package na417.Sup3.Game.Core;
import java.io.*;
import java.util.Scanner; 

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
	
	private String name;
	private Gender gender;
	private Species species;
	private int age = 1;
	private float fullness = 1.0f;
	private float happiness = 1.0f;
	private float sleepiness = 1.0f;
	private float fitness = 0.5f;
	private Traits[] traits;
	private int money = 100;
	private Items[] items;
	private int gameTime = 0;
	public AgeBehaviours AgeBehaviour;
	
	public void feed() 
	{
		
	}
	
	public void talk() {}
	
	public void sleep() {}
	
	public void buy() 
	{
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		int choice = -1;
		int price = 0;
		
		System.out.println("You have: £" + money);
		System.out.println("This store sells: ");
		
		
		for(Items i : Items.values())
		{
			System.out.print(i);
			System.out.print(" costing £" + i.getPrice());
			System.out.println(". Press " + i.ordinal() + " to buy");
		}
		
		choice = scanner.nextInt();
	}
	
	public CyberPet(String name, Gender gender, Species species)
	{
		this.name = name;
		this.gender = gender;
		this.species = species;
		traits = new Traits[0];
		items = new Items[0];
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
			for(int i = 0; i < traits.length; i++)
			{
				traitsOutput += traits[i];
			}
			System.out.println(traitsOutput);
			
			String itemsOutput = "Items: ";
			for(int i = 0; i < items.length; i++)
			{
				itemsOutput += items[i];
			}
			System.out.println(itemsOutput);
		}
	}
	
	
}
