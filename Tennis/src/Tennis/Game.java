package Tennis;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

class SCORE{
	final static int POINT = 0; 
	final static int GAME = 1; 
	final static int SET = 2;
	final static int WIN = 3;
}
abstract public class Game implements Serializable{
	
	private static final long serialVersionUID = 4990056118434112091L;
	
	private Record rec; 
	private int score [][];
	private int head = 0;
	private int setNumber = 0;
	Player [] players;
	
	abstract void scoreCount();
	
	abstract boolean isPointDuce();
	
	abstract boolean isGameDuce();
	
	abstract boolean getGame();
	
	abstract boolean getSet();
	
	abstract boolean getWinner();

	abstract Player[] play();
	
	public Game(int head, int setNumber, Player[] players) {
		this.head = head;
		this.setNumber = setNumber;
		this.players = players;
		this.score = new int[4][2];
		String pattern = "yyyyMMdd_HHmmss";
		LocalDateTime now = LocalDateTime.now();
		this.rec = new Record(now.format(DateTimeFormatter.ofPattern(pattern)));
		
	}

	

	public Record getRec() {
		return rec;
	}

	public void setRec(Record rec) {
		this.rec = rec;
	}

	public Game(Game game) {
		this.score = new int[4][2];
		for (int i = 0; i < game.score.length; i++) {
			this.score[i] = Arrays.copyOf(game.score[i], game.score[i].length);
		}
		this.head = game.head;
		this.setNumber = game.setNumber;
		this.players = new Player[this.head];
		for (int i = 0; i < players.length; i++) {
			this.players[i] = game.players[i];
		}
	}

	public int[][] getScore() {
		return score;
	}

	public void setScore(int[][] score) {
		this.score = score;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getSetNumber() {
		return setNumber;
	}

	public void setSetNumber(int setNumber) {
		this.setNumber = setNumber;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	
}


