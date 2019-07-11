package Tennis;
import java.util.Scanner;




public class PlayTennisMain {

	
	static int head;
	static int mode;
	static int setNumber;
	static boolean tiebreak = true;
	static Player[] ps;
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			printStart();
			if(mode == 2)// 기록 재생
				playRecord();

			else if (mode == 1)
			{
				DisplayGame.printMenu();
				playTennis();
				printEnd();
			}
			else if (mode ==3) System.exit(-1);
		}
	}
	private static void playRecord() {

		DisplayGame.printString("P L A Y");
		DisplayGame.printString("RECORD");
		DisplayGame.printRecord();
	}

	private static void playTennis() {
		Game game;
		if(tiebreak == false)
			game = new NoTieBreakGame(head, setNumber, ps);
		
		else
			game = new TieBreakGame(head, setNumber, ps);
		ps = game.play();
	}

	private static void printEnd() {
		DisplayGame.printEnd(ps);
	}

	

	private static void printStart() {
		DisplayGame.cls();
		DisplayGame.printStart();
		mode = sc.nextInt();
		DisplayGame.cls();
		return;
	}
}