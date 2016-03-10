import java.util.Scanner;

public class Minesweeper {

	private static MineField field;
	private static Ranking rank;
	
	public static void main(String[] args) {
		rank=new Ranking();
		mainMessage();
		while(gameCountinue());
		System.out.println("\nThank you for playing :) Have a nice day!");
	}
	
	
	private static boolean gameCountinue() {
		field = new MineField();
		int result = 0;
		while (true){
			field.show();
			System.out.print("\nPlease enter your move(row col): ");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();
			
			if (input.equals("top")) {
				rank.show();
				continue;
			}
			if (input.equals("restart")) {
				rank.recordName(result);
				return true;
			}
			
			if (input.equals("exit")) {
				rank.recordName(result);
				return false;
			}
			
			if (field.legalMoveString(input)) {
				result++;
				if (result == 35) {
					System.out.println("Congratulations you WON the game!");
					rank.recordName(result);
					return true;	
				}
				continue;
			}
			
			if (field.getBoom()) {
				System.out.println("\nBooooooooooooooooooooooooooooom!You stepped on a mine!You survived " + result + " turns");
				rank.recordName(result);
				return true;
			}
		}
	}
	
	private static void mainMessage(){
		System.out.println("Welcome to Minesweeper!\n" + 
		"To play just input some coordinates and try not to step ont mine :)\n" +
		"Usefull commands:\n" +
		"restart- Starts a new game.\n" +
		"exit- Quits the game.\n" +
		"top- Reveals the top scoreboard.\n" +
		"Have Fun !\n");
	}
}
