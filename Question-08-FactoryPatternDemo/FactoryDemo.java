package na417.Sup3.Q8;
import java.util.*;

public class FactoryDemo {

	
	public static void main(String[] args) {
		// demo of the factory pattern in use
		ArrayList<Animal> myAnimals = new ArrayList<Animal>();
		
		// create concrete factories
		CowFactory cowF = new CowFactory();
		MonkeyFactory monkeyF = new MonkeyFactory();
			
		// contrived examples showing the generic operation in AnimalFactory
		cowF.move();
		monkeyF.move();
		
		// using the factories to make a list of animals
		myAnimals.add(cowF.getAnimal());
		myAnimals.add(monkeyF.getAnimal());
	}

}
