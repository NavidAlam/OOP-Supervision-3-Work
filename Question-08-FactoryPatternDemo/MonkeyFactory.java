package na417.Sup3.Q8;

public class MonkeyFactory extends AnimalFactory {

	@Override
	Animal getAnimal() {
		return new Monkey();
	}
	
}
