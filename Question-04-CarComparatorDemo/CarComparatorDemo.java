package na417.Sup3;
import java.util.*;


public class CarComparatorDemo {
	public static void main(String[] args)
	{
		// test of the comparator
		ArrayList<Car> cars = new ArrayList<Car>();
		
		cars.add(new Car("Volvo", 5));
		cars.add(new Car("BMW", 4));
		cars.add(new Car("Merc", 4));
		cars.add(new Car("Volvo",3));
		cars.add(new Car("BMW", 2));
		
		// using our comparator
		Collections.sort(cars, new CarComparator());
		
		for(Car c : cars)
			{System.out.print(c.getManufacturer() + ", ");
			 System.out.print(c.getAge());
			 System.out.println("");}

		/* prints 
		BMW, 2
		BMW, 4
		Merc, 4
		Volvo, 3
		Volvo, 5
		As expected
		*/
	}
}
