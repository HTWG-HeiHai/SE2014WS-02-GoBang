package gbData;

public class gameToken_test {
	
	public static void main(String[] args) {
		
		gameToken myToken = new tokenBlack();
		System.out.println("Name: " + myToken.getName());
		System.out.println("Color: " + myToken.getColor());
		
		myToken = new tokenX();
		System.out.println("Name: " + myToken.getName());
		System.out.println("Color: " + myToken.getColor());
		
		myToken = new tokenO();
		System.out.println("Name: " + myToken.getName());
		System.out.println("Color: " + myToken.getColor());
		
		myToken = new tokenWhite();
		System.out.println("Name: " + myToken.getName());
		System.out.println("Color: " + myToken.getColor());
		
	}

}
