package com.chuchuye.Yahtzee;

import junit.framework.TestCase;

public class DiceTest extends TestCase {
	
	Dice dice = new Dice();
	int[] diceList = new int[] {1,2,3,4,5};
	String s = "1 3 4";
		
	public void testSetAndGetDice() {
		dice.setDice(diceList);
		for(int index=0; index<5; index++) {
			assertEquals(diceList[index], dice.getDice()[index]);
		}
	}

	public void testGenDice() {
		dice.genDice();
		assertNotNull(dice.getDice());
	}
	

	public void testRowPartDice() {
		int[] originalDiceList = new int[] {1,1,1,1,1};
		dice.setDice(originalDiceList);
		dice.rowPartDice(s);
		int[] rerollDiceList = dice.getDice();
		assertFalse((originalDiceList[0]==rerollDiceList[0])&&
				(originalDiceList[2]==rerollDiceList[2])&&
				(originalDiceList[3]==rerollDiceList[3]));
	}
	
	public void testTotalScore() {
		dice.setDice(diceList);
		assertEquals(15, dice.totalScore());
	}

}
