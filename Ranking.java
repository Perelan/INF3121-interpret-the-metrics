import java.util.Scanner;

public class Ranking{

	private final int MAX_PEOPLE_LIMIT=5;
	private String[] name;
	private int[] record;
	private int last;

	Ranking(){
		name=new String[MAX_PEOPLE_LIMIT];
		record=new int[MAX_PEOPLE_LIMIT];
		last=0;
	}

	/**
	 *This method checks if a player is entitled to enter the scoreboard
	 * @param result The result for a player
     */
	public void recordName(int result) {
		System.out.print("\n Please enter your name -");
		Scanner in=new Scanner(System.in);
		String newName=in.nextLine();

		if((last==MAX_PEOPLE_LIMIT)&&record[MAX_PEOPLE_LIMIT-1]>result){
			System.out.println("\nSorry you cannot enter top "+(MAX_PEOPLE_LIMIT)+" players");
			return;
		}
		else if(last==MAX_PEOPLE_LIMIT){
			name[MAX_PEOPLE_LIMIT-1]=newName;
			record[MAX_PEOPLE_LIMIT-1]=result;
		}
		else{
			name[last]=newName;
			record[last]=result;
			last++;
		}
		sort();
		show();
	}

	public void show() {
		if(last==0){
			System.out.println("Still no results");
			return;
		}
		String s = "N Name\t\tresult\n";
		for(int i=0;i<last;i++){
			s += (i+1)+" "+name[i]+"\t"+record[i]+'\n';
		}
		System.out.println(s);
	}

	private void sort(){
		while(handleSort());
	}

	/**
	 * This method sorts the scoreboard based on best scores
	 * @return boolean, true if the sorting was completed
     */
	private boolean handleSort(){
		for(int i=0;i<(last-1);i++){
			if(record[i+1]>record[i]){
				int swapR=record[i];
				record[i]=record[i+1];
				record[i+1]=swapR;

				String swapN=name[i];
				name[i]=name[i+1];
				name[i+1]=swapN;
				return true;
			}
		}
		return false;
	}
}