package na417.Sup3.Q8;

public abstract class AnimalFactory {

	abstract Animal getAnimal();
	void move()
	{
		Animal a = getAnimal();
		a.move();
	}
}
