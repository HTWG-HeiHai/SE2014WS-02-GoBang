package gbData;

public class gameField_test {
	public static void main(String[] args) {
		
		System.out.println("gameField wird getestet");
		
		gameToken tokenB = new tokenBlack();
		gameToken tokenW = new tokenWhite();
		gameField myField = new gameField();
		
		System.out.println(myField.getSize()); 		//19
		System.out.println(myField.putStone(15, 14, tokenB)); //e
		System.out.println(myField.putStone(15, 14, tokenB)); //b
		System.out.println(myField.putStone(15, 14, tokenW)); //b
		
		System.out.println(myField.putStone(-1, 1, tokenB));	//f
		System.out.println(myField.putStone(0, 0, tokenB));		//f
		System.out.println(myField.putStone(19,19, tokenW));	//e
		System.out.println(myField.putStone(20,15,tokenW)); 	//f
		System.out.println(myField.putStone(15,20, tokenW));	//f
		System.out.println(myField.putStone(20,20, tokenW));	//f
		
		
	}

}
