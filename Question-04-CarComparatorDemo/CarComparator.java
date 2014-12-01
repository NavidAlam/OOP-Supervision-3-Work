package na417.Sup3;
import java.util.*;


public class CarComparator implements Comparator<Car> {
	
	public int compare(Car car1, Car car2)
	{
		int result = 0;
		// compares the manufacturers by using the comparable interface implemented
		// by String objects
		int strComp = car1.getManufacturer().compareTo(car2.getManufacturer());
		
		// if the manufacturers are different we do not have to look further
		if(strComp != 0) {
			result = strComp;
		} else {
			if (car1.getAge() > car2.getAge()) {result = 1;}
			else if (car1.getAge() < car2.getAge()) {result = -1;} 		
			// nothing to do if the ages are equal as result is 0 by default
		}
		return result;
	}
}
