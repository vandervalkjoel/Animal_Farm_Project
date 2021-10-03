package P4;

@SuppressWarnings("serial")
public class Chicken extends Animal{
	private static int id;
	public Chicken() {
		setName("Chicken" + ++id);
		setMealAmount(5);
	}
	public void sound(){
		if(isAlive()) System.out.println("Cluck!");
	}
	public void swim(){
		if(isAlive()) System.out.println("Chicken can swim as following:...!");
	}
}

