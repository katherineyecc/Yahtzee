package com.chuchuye.Yahtzee;

public class Score {
	
	private int currentScore;
	
	public int getScore() {
		if((currentScore<0) || (currentScore>475)) {
			return -1;
		}
		return currentScore;
	}
	
	public void setScore(int score) {
		currentScore = score;
	}

}
