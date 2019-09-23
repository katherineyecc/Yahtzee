package com.chuchuye.Yahtzee;

public class Player {
	
	private String Name;
	private int currentRound = 1;
	private int score = 0;
	
	private int bonus = 0;//0||35
	private int YahtzeeBonus = 0;//0||100
	
	private int[] scoreBoard = new int[13];//存储了当前玩家的所有分数
	
	private boolean YahtzeeFlag = false;
	
	public Player(){
		
	}
	
	Player(String name){
		Name = name;
	}
	
	public int[] getScoreBoard() {
		return scoreBoard;//将玩家记分板上的分数返回
	}
	
	public void setScoreBoard(int index, int sc) {
		scoreBoard[index] = sc;
	}
	
	public void addRound() {
		currentRound++;
	}
	
	
	public int getCurrentRound() {
		return currentRound;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int number) {
		score = number;
	}
	
	public void countScore() {//计算记分板上的总分
		int tempScore = 0;
		int index = 0;
		for(; index<13; index++) {
			tempScore += scoreBoard[index];
		}
		tempScore += bonus + YahtzeeBonus;
		score = tempScore;
	}
	
	public void countBonus() {
		int index = 0;
		int tempScore = 0;
		for(; index<6; index++) {
			tempScore += scoreBoard[index];
		}
		if(tempScore<63) {
			bonus = 0;
		} else {
			bonus = 35;
		}
	}
	
	public int getBonus() {
		return bonus;
	}
	
	public void setBonus(int number) {
		bonus = number;
	}
	
	public void countYahtzeeBonus() {
		if(YahtzeeFlag == true) { //如果flag为true，代表这是第二次获得Yahtzee，判断逻辑放在游戏过程中进行
			YahtzeeBonus = 100;
		} else {
			YahtzeeBonus = 0;
		}
	}
	
	public int getYahtzeeBonus() {
		return YahtzeeBonus;
	}
	
	public void setYahtzeeBonus(int number) {
		YahtzeeBonus = number;
	}
	
	public void setYahtzeeFlag() {
		YahtzeeFlag = true;
	}
	
	public boolean getYahtzeeFlag() {
		return YahtzeeFlag;
	}
	
	public int getSpecificScore(int index) {
		return scoreBoard[index];
	}
	
}
