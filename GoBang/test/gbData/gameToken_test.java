package gbData;

public class gameToken_test {
	
	public static void main(String[] args) {
		
		gameToken myToken = new tokenBlack();
		String test = myToken.getName();
		System.out.println(test);
		
		myToken = new tokenX();
		test = myToken.getName();
		System.out.println(test);
		
		myToken = new tokenO();
		test = myToken.getName();
		System.out.println(test);
		
		myToken = new tokenWhite();
		test = myToken.getName();
		System.out.println(test);
		
	}

}
