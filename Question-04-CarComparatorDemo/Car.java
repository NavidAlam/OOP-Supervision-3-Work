package na417.Sup3;
import java.util.*;

public class Car {
	private String manufacturer;
	private int age;
	
	Car(String manufacturer, int age)
	{
		this.manufacturer = manufacturer;
		this.age = age;	
	}
	
	// getters
	public String getManufacturer() {return manufacturer;}

	public int getAge() {return age;}
}
