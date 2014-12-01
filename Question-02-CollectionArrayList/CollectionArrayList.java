
import java.util.*;

public class CollectionArrayList<T> extends AbstractList<T>{

	private ArrayList<T> internalArrayList = new ArrayList<T>();
	
	// final as it cannot be reassigned at compile time
	private final int DEFAULT_SIZE = 10;
	
	// stores current length of data, not all values in data are actually used 
	// so there will often be lots of nulls in the array
	private int size = DEFAULT_SIZE;
	
	// stores the index of the last actual element in data
	// there is no last element, -1 is a default value
	private int lastIndex = -1;
	
	// holds the elements
	// of type object as arrays cannot have a parameterised type
	private Object[] data;
	
	// allocates memory when constructed
	CollectionArrayList()
	{
		data =  new Object[size];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// testing my implementation of ArrayList
		CollectionArrayList<Integer> testAL = new CollectionArrayList<Integer>();
		testAL.add(new Integer(5));
		testAL.add(new Integer(7));
		testAL.add(new Integer(-2));
		
		// prints 3
		System.out.println(testAL.size());
		
		// prints 5, 7, -2
		testAL.print();
		
		testAL.add(1, 4);
		// prints 5, 4, 7, -2,
		testAL.print();
		
		testAL.add(2,5);
		// print 5, 4, 5, 7, -2,
		testAL.print();
		
		testAL.remove(2);
		// prints 5, 4, 7, -2,
		testAL.print();
		
		testAL.set(3, 11);
		// prints 5, 4, 7, 11,
		testAL.print();
	}
	
	// returns the element at the specified index
	@Override
	public T get(int index) {
		// range check
		assert((index >= 0) && (index <= lastIndex));
		return (T)data[index];
	}
	
	// add element at the index specified
	@Override 
	public void add(int index, T element)
	{			
		// range check, note we can add element onto the end of the array
		// so we have have index <= lastIndex+1 rather than index <= lastIndex
		assert((index >= 0) && (index <= (lastIndex+1)));
		
		// if we want to add at the end
		if(index == (lastIndex+1)){
					add(element);
		} else {
		// make space for this object
		shiftObjectsRight(index);
		
		// one more object in the array
		lastIndex++;
		
		data[index] = element;
		}
	}
	
	// used for debugging
	public void print()
	{
		for(Object o : data)
		{
			if(o == null) {continue;}
			System.out.print(o + ", ");
		}
		
		System.out.println("");
	}
	
	// adds at the end of the array
	public boolean add(T element)
	{	
		// if the array is full resize it
		if(lastIndex == (size-1)) {
			lengthenArray();
		}
		
		// one more object in array
		lastIndex++;
		data[lastIndex] = element;
		
		return true;
	}
	
	// doubles length of the data array and copies old items over
	private void lengthenArray()
	{
		size *= 2;
		Object[] longerArray = new Object[size];
		int index = 0;
		// copies (references to) the non null elements over from the old array to the new one  
		while(data[index] != null)
		{
			longerArray[index] = data[index];
			index++;
		}
		// assigns new array to old array
		data = longerArray;
	}
	
	// replaces the element at the specified index, with the given element
	// also returns the previous element from that index
	@Override
	public T set(int index, T element)
	{
		// range check
		assert((index >= 0) && (index <= lastIndex));
		
		T prevElement = (T)data[index];
		data[index] = element;
		
		return prevElement; 
	}
	
	// removes the element at the specified index and returns it
	@Override 
	public T remove(int index)
	{
		// range check
		assert((index >= 0) && (index <= lastIndex));
		
		T remElement = (T)data[index];
		
		// shifts objects to avoid gaps
		shiftObjectsLeft(index); 

		// one less object in array
		lastIndex--;
		
		return remElement;
	}
	
	// shifts all objects from the index specified left by 1
	private void shiftObjectsLeft(int shiftFrom)
	{
		for(int i = shiftFrom; i <= lastIndex; i++)
		{
			data[i] = data[i+1];
		}
	}
	
	// shifts all objects from the index specified right by one
	// note we are not range checking, as that is done in the methods 
	// it is called from
	private void shiftObjectsRight(int shiftFrom)
	{
		for(int i = lastIndex; i >= shiftFrom; i--)
		{
			data[i+1] = data[i];
		}
	}
	
	// returns number of actual (not null) objects in the array
	@Override
	public int size() {
		return (lastIndex+1);
	}
}
