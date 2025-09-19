package presentation;

public class Orange extends Fruit {
	public Orange () {
		System.out.println("this is orange");
	}
	
	public static void main(String[] args) {
		Orange o = new Orange();
		o.show();
		Fruit a = (Fruit) o ;
		
		a.show();
		((Orange)a).showPoid();
	}
	
	void show() {
		System.out.println("hhhh");
	}
	void showPoid() {
		System.out.println("poid ");
	}
}
