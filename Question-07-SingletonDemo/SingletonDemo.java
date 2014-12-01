public class Work {

	public static void main(String[] args) {
		
		GameGUI g = SingletonGUI.getInstance();
		
	}
}

class SingletonGUI
{
	private static GameGUI instanceOfGUI = null; 
	private SingletonGUI(){}
	
	public static GameGUI getInstance()
	{
		if(instanceOfGUI == null) {
			instanceOfGUI = new GameGUI();
		} 
		
		return instanceOfGUI;
	}
}