import java.util.Scanner;

public class Minesweeper {

	private static MineField field;
	private static Ranking rank;
	private static int result;

	public static void main(String[] args) {
		rank=new Ranking();
		mainMessage();
		init();
		while(gameCountinue());

	}

	/*This method checks both the input and the game state to end game or not*/
	private static boolean gameCountinue() {
		while (true){
			field.show();
			System.out.print("\nPlease enter your move(row col): ");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();

			commandHandle(input);
			gameHandle(input);
		}
	}

	private static void commandHandle(String input){
		if (input.equals("top")) {
			rank.show();
			gameCountinue();
		}

		if (input.equals("restart")) {
			rank.recordName(result);
			init();
			gameCountinue();
		}

		if (input.equals("exit")) {
			rank.recordName(result);
			System.out.println("\nThank you for playing :) Have a nice day!");
			System.exit(0);
		}
	}

	private static void gameHandle(String input){
		if (field.legalMoveString(input)) {
			result++;
			if (result == 35) {
				System.out.println("Congratulations you WON the game!");
				rank.recordName(result);
				init();
			}
			gameCountinue();
		} else if (field.getBoom()) {
			System.out.println("\nBooooooooooooooooooooooooooooom!You stepped on a mine!You survived " + result + " turns");
			rank.recordName(result);
			init();
		}
	}

	private static void init(){
		result = 0;
		field  = new MineField();
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
