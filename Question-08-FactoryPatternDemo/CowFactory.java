package na417.Sup3.Q8;

public class CowFactory extends AnimalFactory{

	@Override
	Animal getAnimal() {
		return new Cow();
	}

}
