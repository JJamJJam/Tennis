package Tennis;

import java.util.Arrays;
import java.util.Random;

public class NoTieBreakGame extends Game {

	private static final long serialVersionUID = -8805344351322687088L;

	public NoTieBreakGame(int head, int setNumber, Player[] players) {
		super(head, setNumber, players);
	}
	public NoTieBreakGame(Game game) {
		super(game);
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
		if(isPointDuce() == false && (score[SCORE.POINT][0] >= 4 || score[SCORE.POINT][1] >= 4)) { // 
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
		//승리자 반환
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
			if(!getWinner()) //승리 조건이 만족하면 return true
			{
				continue;
			}
			else //승리자가 결정되었을 때
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
