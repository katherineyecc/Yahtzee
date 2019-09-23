package com.chuchuye.Yahtzee;

public class Game {//相当于一局游戏
	
	private int[] scoreBoard = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	private int[] dList = new int[5];
	
	private int rowCount = 0;
	
	Dice dice = new Dice();
	
	public int getRowCount() {
		return rowCount;
	}
	
	public int[] getDice() {
		return dList;
	}
	
	public void rowTheDice() {//投掷骰子（5个全投）
		if(rowCount<=3) {
			dice.genDice();
			dList = dice.getDice();
			rowCount++;
		}
	}
	
	public void rowPartDice(String s) {//投部分的骰子，s就代表要投骰子的序号
		if(rowCount<=3) {
			dice.rowPartDice(s);
			dList = dice.getDice();
			rowCount++;
		}
	}
	
	public void playerChoice(int number) {//接收玩家的选择，根据选择把该局的骰子归到某一类别中记分
		switch(number) {
			case 1:
				for(int index=0; index<5; index++) {
					if(dList[index]==1) {
						scoreBoard[0] += dList[index];
					}
				}
				break;
			case 2:
				for(int index=0; index<5; index++) {
					if(dList[index]==2) {
						scoreBoard[1] += dList[index];
					}
				}
				break;
			case 3:
				for(int index=0; index<5; index++) {
					if(dList[index]==3) {
						scoreBoard[2] += dList[index];
					}
				}
				break;
			case 4:
				for(int index=0; index<5; index++) {
					if(dList[index]==4) {
						scoreBoard[3] += dList[index];
					}
					
				}
				break;
			case 5:
				for(int index=0; index<5; index++) {
					if(dList[index]==5) {
						scoreBoard[4] += dList[index];
					}
				}
				break;
			case 6:
				for(int index=0; index<5; index++) {
					if(dList[index]==6) {
						scoreBoard[5] += dList[index];
					}
				}
				break;
			case 7:
				//验证是否满足LargeStraight的要求，不满足即为0
				//LargeStraight要么是1，2，3，4，5
				//要么是2，3，4，5，6
				//只有这两种情况
				Verify verify1 = new Verify(dList);
				boolean flag1 = verify1.verifyLargeStraight();
				if(flag1==true) {
					scoreBoard[6] = 40;
				}
				break;
			case 8:
				//验证是否满足SmallStraight
				//SmallStraight要么是1，2，3，4，x
				//要么是2，3，4，5，x
				//要么是3，4，5，6，x
				Verify verify2 = new Verify(dList);
				boolean flag2 = verify2.verifySmallStraight();
				if(flag2==true) {
					scoreBoard[7] = 30;
				}
				break;
			case 9:
				//验证是否满足FullHouse
				Verify verify3 = new Verify(dList);
				boolean flag3 = verify3.verifyFullHouse();
				if(flag3==true) {
					scoreBoard[8] = 25;
				}
				break;
			case 10:
				//验证是否是3 of a kind
				Verify verify4 = new Verify(dList);
				boolean flag4 = verify4.verifyThreeOfAKind();
				if(flag4==true) {
					for(int index=0; index<5; index++) {
						scoreBoard[9] += dList[index];
					}
				}
				break;
			case 11:
				//验证是否是4 of a kind
				Verify verify5 = new Verify(dList);
				boolean flag5 = verify5.verifyFourOfAKind();
				if(flag5==true) {
					for(int index=0; index<5; index++) {
						scoreBoard[10] += dList[index];
					}
				}
				break;
			case 12:
				//Chances
				for(int index=0; index<5; index++) {
					scoreBoard[11] += dList[index];
				}
				break;
			case 13:
				//Yahtzee
				Verify verify6 = new Verify(dList);
				boolean flag6 = verify6.verifyYahtzee();
				if(flag6==true) {
					scoreBoard[12] = dList[0]*5;
				}
				break;
		}
	}
	
	public int getScore(int index) {
		return scoreBoard[index];
	}
	
	public void setDList(int[] dlist) {
		for(int index=0; index<5; index++) {
			dList[index] = dlist[index];
		}
	}

}
