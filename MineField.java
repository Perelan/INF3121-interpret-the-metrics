import java.util.Random;

class MineField{
	private boolean[][] mines,visible;
	private boolean boom;
	private final int rowMax = 5;
	private final int colMax = 10;
	private int count;

	MineField(){
		mines=new boolean[rowMax][colMax];
		visible=new boolean[rowMax][colMax];
		boom=false;

		initMap();

		int counter2=15;
		int randomRow,randomCol;
		Random RGenerator=new Random();

		while(counter2>0){
			randomRow=Math.abs(RGenerator.nextInt()%rowMax);
			randomCol=Math.abs(RGenerator.nextInt()%colMax);

			if(trymove(randomRow,randomCol)){
				counter2--;
			}
		}
	}

	private void initMap(){
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				mines[row][col]=false;
				visible[row][col]=false;
			}
		}
	}

	private boolean trymove(int randomRow, int randomCol) {
		if(mines[randomRow][randomCol]){
			return false;
		}
		mines[randomRow][randomCol]=true;
		return true;
	}

	private void boom() {
		for(int row=0;row<rowMax;row++){
			for(int col=0;col<colMax;col++){
				if(mines[row][col]){
					visible[row][col]=true;
				}
			}
		}
		boom=true;
		show();
	}

	/**
	 * This method will draw the map in terms of the state of each cell
	 * @param row input from user
	 * @param col input from user
     * @return char specific to each cell according to the state
     */
	private char drawChar(int row, int col) {
	    count=0;
		if(visible[row][col]){
			if(mines[row][col]) return '*';

			handleThis(row, col);

			return printMap(count);
		}
		else if(boom){
			return '-';
		}
		else{
			return '?';
		}

	}

	/**
	 * This method handles the count of mines around each selected cell
	 * @param row selected row from user
	 * @param col selected col from user
     */
	public void handleThis(int row, int col){
		for(int irow=row-1;irow<=row+1;irow++){
			for(int icol=col-1;icol<=col+1;icol++){
				if(icol>=0&&icol<colMax&&irow>=0&&irow<rowMax){
					if(mines[irow][icol]) count++;
				}
			}
		}
	}

	/**
	 * This method prints out the number of mines around the selected cell
	 * @param count decides which number to return
	 * @return char value representing the number of mines around the cell
     */
	public char printMap(int count){
		switch(count){
			case 0:return '0';
			case 1:return '1';
			case 2:return '2';
			case 3:return '3';
			case 4:return '4';
			case 5:return '5';
			case 6:return '6';
			case 7:return '7';
			case 8:return '8';
			default:return 'X';
		}
	}

	public boolean getBoom(){
		return boom;
	}

	/*This method checks if the requested move is legal*/

	/**
	 * This method checks if the requesteed move is legal
	 * @param input input from user
	 * @return boolean true if valid
     */
	public boolean legalMoveString(String input) {
		String[] separated=input.split("\\s+");
		int row, col;

		try{
			row=Integer.parseInt(separated[0]);
			col=Integer.parseInt(separated[1]);
			if(row<0||col<0||row>=rowMax||col>=colMax){
				throw new java.io.IOException();
			}
		}
		catch(Exception e){
			System.out.println("\nInvalid Input!");
			return false;
		}

		if(legalMoveValue(row,col)){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * This method is initiated from legalMoveString to validate/execute the input coordinates
	 * @param row input from user
	 * @param col input from user
     * @return true if valid
     */
	private boolean legalMoveValue(int row, int col) {
		if(visible[row][col]){
			System.out.println("You stepped in already revealed area!");
			return false;
		}
		else{
				visible[row][col]=true;
		}
		if(mines[row][col]){
			boom();
			return false;
		}
		return true;
	}

	/**
	 * This method prints out the grid with the program's state
	 */
	public void show() {
		System.out.println("\n    0 1 2 3 4 5 6 7 8 9 \n   ---------------------");

		for(int row=0;row<rowMax;row++){
			System.out.print(row+" |");
			for(int col=0;col<colMax;col++){
				System.out.print(" "+drawChar(row,col));
			}
			System.out.println(" |");
		}
		System.out.println("   ---------------------");
	}
}