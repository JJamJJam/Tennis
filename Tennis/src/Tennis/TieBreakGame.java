package Tennis;

import java.util.Arrays;
import java.util.Random;


public class TieBreakGame extends Game {


	private static final long serialVersionUID = -3471003745280952012L;

	public TieBreakGame(int head, int setNumber, Player[] players) {
		super(head, setNumber, players);
	}
	public TieBreakGame(Game game) {
		super(game);
	}

	boolean isTie() {
		int num =6;
		int [][] score = getScore();

		if(score[SCORE.GAME][0] == num && score[SCORE.GAME][1] == num ) {
			return true;
		}
		return false;
	}

	@Override
	boolean isPointDuce() {
		int score[][] = getScore();
		int num = 3;
		if(score[SCORE.POINT][0] >= num && score[SCORE.POINT][1] >= num 
				&& Math.abs(score[SCORE.POINT][0] - score[SCORE.POINT][1]) < 2) {
			return true;
		}
		return false;
	}

	boolean isGameDuce() {
		int score[][] = getScore();
		int num = 5;
		if(score[SCORE.GAME][0] >= num && score[SCORE.GAME][1] >= num 
				&& Math.abs(score[SCORE.GAME][0] - score[SCORE.GAME][1]) < 2) {
			return true;
		}
		return false;
	}


	@Override
	boolean getGame() {
		int score[][] = getScore();

		if (isTie()) {
			if((score[SCORE.POINT][0] >= 7 && score[SCORE.POINT][0]-score[SCORE.POINT][1]>=2 )) 
				{score[SCORE.GAME][0]++;}
			else if ((score[SCORE.POINT][1] >= 7 &&score[SCORE.POINT][1]-score[SCORE.POINT][0]>=2 )) 
				{score[SCORE.GAME][1]++;}
		}

		if(!isTie() && isPointDuce() == false && (score[SCORE.POINT][0] >= 4 || score[SCORE.POINT][1] >= 4)) { // 
			if (score[SCORE.POINT][0] > score[SCORE.POINT][1]) {
				score[SCORE.GAME][0]++;
			}

			else 
				score[SCORE.GAME][1]++;

			score[SCORE.POINT][0]=0;
			score[SCORE.POINT][1]=0;
			return true;
		} 
		return false;
		//end if
		//듀스인 상황
		//점수가 둘다 3 이상이고, 1점 더먹은애가 어드벤테이지, 동점이면 다시 듀스, 2점차면 게임 끝
	}

	@Override
	boolean getSet() {
		int score[][] = getScore();
		if(isGameDuce() == false && (score[SCORE.GAME][0] >= 6 || score[SCORE.GAME][1] >= 6)) {
			if (score[SCORE.GAME][0] > score[SCORE.GAME][1]) {
				score[SCORE.SET][0]++;
			}else score[SCORE.SET][1]++;

			score[SCORE.GAME][0]=0;
			score[SCORE.GAME][1]=0;
			return true;
		}return false;
	}

	@Override
	boolean getWinner() {
		int setNumber = getSetNumber();
		int score[][] = getScore();
		if(score[SCORE.SET][0] == setNumber/2+1) 
		{
			score[SCORE.WIN][0] = 1;
			return true; 
		}
		else if(score[SCORE.SET][1] == setNumber/2+1) 
		{
			score[SCORE.WIN][1] = 1;
			return true;
		}
		return false;
	}

	@Override
	void scoreCount() {
		int score[][] = getScore();
		Random rnd  =  new Random();
		int scorer = rnd.nextInt(2);
		if (scorer == 0) {
			score[SCORE.POINT][0]++;

		} else {
			score[SCORE.POINT][1]++;	
		}
	}

	@Override
	Player[] play() {
		// TODO Auto-generated method stub
		int score[][] = getScore();
		int head = getHead();
		Record rec = getRec();

		while (true) {
			DisplayGame.printCount(this);
			rec.recordGame(this);

			scoreCount();
			if(!getGame()) //true 듀스도 아니고 게임이 끝났을때
			{
				continue;
			}
			if(!getSet()) //true 게임듀스도 아니고 끝났을때
			{
				continue;
			}
			if(!getWinner())
			{
				continue;
			}
			else
			{
				DisplayGame.printCount(this); //마지막 스코어 출력
				rec.recordGame(this);
				rec.Write(); //경기 내용 파일에 저장
				if(score[SCORE.WIN][0] == 1)
					return Arrays.copyOfRange(getPlayers(), 0, head/2);

				else
					return Arrays.copyOfRange(getPlayers(), head/2, head);
			}
		} //end while
	} //end play()
}





