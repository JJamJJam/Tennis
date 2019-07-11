package Tennis;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Figlet.FigletFont;

public class DisplayGame {
	static File fontFile  = new File(".\\standard.flf");
	public static void printString(String str)
	{
		try {
			String asciiArt1 = FigletFont.convertOneLine(fontFile, str);
			System.out.println(asciiArt1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printStart()
	{
		cls();
		printString("P L A Y");
		printString("TENNIS");

		
		 System.out.format("+------------+%n");
		System.out.println("| 1. 플레이  |");
		 System.out.format("+------------+%n");
		System.out.println("| 2. 기록재생|");
		 System.out.format("+------------+%n");
		System.out.println("| 3. 종료    |");
		 System.out.format("+------------+%n"); 
		System.out.print("입력(1, 2, 3): ");
	}
	public static void printMenu() {

		DisplayGame.printString(" 1. SIGLE");
		DisplayGame.printString("2.DOUBLE");
		System.out.print("입력(1, 2): ");
		PlayTennisMain.head = PlayTennisMain.sc.nextInt()*2;
		PlayTennisMain.ps = new Player[PlayTennisMain.head];
		cls();

		DisplayGame.printString("Input Name");
		for (int i = 0; i < PlayTennisMain.ps.length; i++) {
			System.out.print("Player"+i+"입력: ");
			String name = PlayTennisMain.sc.next();
			PlayTennisMain.ps[i] = new Player(name);
		}
		cls();
		
		
		printString("Number of SET");
		 System.out.format("+----------------+%n");
		System.out.println("| 1. SINGLE GAME |");
		 System.out.format("+----------------+%n");
		System.out.println("| 2. BEST OF 3   |");
		 System.out.format("+----------------+%n");
		System.out.println("| 3. BEST OF 5   |");
		 System.out.format("+----------------+%n"); 
		System.out.print("입력(1, 2, 3): ");
		PlayTennisMain.setNumber = PlayTennisMain.sc.nextInt()*2-1;
		System.out.println();
		cls();

		DisplayGame.printString("TIEBREAK ( Y / N )");
		System.out.println("입력(Y/N): ");
		PlayTennisMain.tiebreak = PlayTennisMain.sc.next().toUpperCase().equals("Y")?true:false;
		cls();
	}
	public static void printCount(Game game)
	{
		cls();
		int head = game.getHead();
		int [][] scores = game.getScore();
		Player[] players = game.getPlayers();
		
		char[][] fontDisp = getFontDisp("t");
		char [][] setDisplay = new char [24][80];
		String pname1 = "";
		String pname2 = "";
		boolean gameType = false; //추가
		
		if (head==2) {
			pname1 = players[0].getName().substring(0, Math.min(3, players[0].getName().length()));
			pname2 = players[1].getName().substring(0, Math.min(3, players[0].getName().length()));
		} else if (head==4) {
			pname1 = players[0].getName().charAt(0)+"&"+players[1].getName().charAt(0);
			pname2 = players[2].getName().charAt(0)+"&"+players[3].getName().charAt(0);
		}
		
		if (game instanceof TieBreakGame) gameType = true;
		
		
		for (int i = 0; i < setDisplay.length; i++) {   //테이블 선 그리기
			for (int j = 0; j < setDisplay[0].length; j++) {
				if (i==0 || j==0&&i<23 || j==13&&i<23 || j==45&&i<23 || j==60&&i<23 || j==79&&i<23 || i==7 || i==15 || i==23) setDisplay[i][j]='*';
			}
		}
		fontDisp = getFontDisp("S");
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+1][j2+1]= fontDisp[j][j2];
			}	
		}
		fontDisp = getFontDisp("Nam");
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+1][j2+17]= fontDisp[j][j2];
			}		
		}
		fontDisp = getFontDisp("G");
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+1][j2+49]= fontDisp[j][j2];
			}		
		}
		fontDisp = getFontDisp("P");
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+1][j2+65]= fontDisp[j][j2];
			}		
		}
		
		fontDisp = getFontDisp(Integer.toString(scores[2][0]));     //입력시작
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+8][j2+1]= fontDisp[j][j2];
			}	
		}
		fontDisp = getFontDisp(pname1);     
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+8][j2+17]= fontDisp[j][j2];
			}	
		}
		fontDisp = getFontDisp(Integer.toString(scores[1][0]));     
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+8][j2+49]= fontDisp[j][j2];
			}	
		}
		fontDisp = getFontDisp(getScaledscore(0, scores, gameType));     
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+8][j2+62]= fontDisp[j][j2];
			}	
		}
		fontDisp = getFontDisp(Integer.toString(scores[2][1]));     //두번째줄 입력
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+16][j2+1]= fontDisp[j][j2];
			}	
		}
		fontDisp = getFontDisp(pname2);     
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+16][j2+17]= fontDisp[j][j2];
			}	
		}
		fontDisp = getFontDisp(Integer.toString(scores[1][1]));     
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+16][j2+49]= fontDisp[j][j2];
			}	
		}
		fontDisp = getFontDisp(getScaledscore(1, scores,gameType));     
		for (int j = 0; j < fontDisp.length; j++) {
			for (int j2 = 0; j2 < fontDisp[j].length; j2++) {
				setDisplay[j+16][j2+62]= fontDisp[j][j2];
			}	
		}
		String displayStr = "";
		for (int i = 0; i < setDisplay.length; i++) {  //출력파트
			displayStr += String.valueOf(setDisplay[i])+"\n";
		}
		System.out.println(displayStr);
		try 
		{			
			Thread.sleep(100);		
		} 
		catch (InterruptedException e)
		{ 		
			e.printStackTrace();
		}
	}

	private static String getScaledscore(int player, int [][]scores, boolean gameType) {
		
		String result = "e";
		int opponent = 1-player;
		int scorep = scores[SCORE.POINT][player];
		int scoreo = scores[SCORE.POINT][opponent];
		int gamep = scores[SCORE.GAME][player];
		int gameo = scores[SCORE.GAME][opponent];
		
		if (gameType && gamep==6 && gameo==6) result = Integer.toString(scores[SCORE.POINT][player]); //타이브레이커일때 게임 스코어카운트
		else if (scorep==0) result = "0";
		else if (scorep ==1) result = "15";
		else if (scorep ==2) result = "30";
		else if (scorep ==3) {
			if (scoreo<=3 ) result = "40";
			else if (scoreo>3) result = " ";
		}
		else if (scorep>=4 && scorep-scoreo==1) result = "Ad";
		else if (scorep>=4 && scorep-scoreo==-1) result = " ";		
		else if (scorep>=4 && scorep-scoreo==0) result = " ";		
		return result;
	}


	
	public static void printEnd(Player[] ps)
	{
		cls();
		String name = "";
		if(ps.length ==2)
		{
			name=ps[0].getName();
			name+="   ,   "+ps[1].getName();
		}
		else
			name = ps[0].getName();
		printString("WINNER");
		printString(name);
		System.out.println("\t\t 계속 하려면 엔터...");
		try {
			System.in.read();
			System.in.skip(System.in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printRecord()
	{
		//.dat파일을 읽어와서 출력....
		File curDir = new File(".\\");
		String[] fileList = curDir.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".dat");
			}
		});
		for (int i = 0; i < fileList.length; i++) {
			System.out.println((i+1)+": " + fileList[i]);
		}
		if(fileList.length == 0)
		{
			System.out.println("출력할 경기가 없습니다.");
			return;
		}
		System.out.print("출력할 경기 입력: ");
		
		int num = PlayTennisMain.sc.nextInt();
		Record rec = Record.Read(fileList[num-1]);
		ArrayList<Game> list = rec.getList();
		for (int i = 0; i < list.size(); i++) {
			printCount(list.get(i));
		}
		System.out.println("계속 하려면 enter....");
		try {
			System.in.read();
			System.in.skip(System.in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static char[][] getFontDisp(String string) {

		File font = new File(".\\standard.flf");
		String asciiArt = null;
		
		try {
			asciiArt = FigletFont.convertOneLine(font, string);
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringTokenizer st = new StringTokenizer(asciiArt, "\n");
		char [][] convertMessage = new char[st.countTokens()][];
		int index=0;
		while (st.hasMoreTokens()) {
			convertMessage[index++] = st.nextToken().toCharArray();
		}
		return convertMessage;
	}
	public static void cls() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	       
	}

}
