package com.chuchuye.Yahtzee;

import junit.framework.TestCase;

public class GameTest extends TestCase {
	
	Game game = new Game();
	
	public void testGetRowCount() {
		assertEquals(0, game.getRowCount());
	}
	
	public void testGetDice() {
		assertNotNull(game.getDice());
	}
	
	public void testRowTheDice() {
		game.rowTheDice();
		assertNotNull(game.getDice());
	}
	
	public void testRowPartDice() {
		String s = "1 3 4";
		int[] originalDiceList = new int[] {1,1,1,1,1};
		int row = game.getRowCount();
		game.rowPartDice(s);
		int[] rerollDiceList = game.getDice();
		assertFalse((originalDiceList[0]==rerollDiceList[0])&&
				(originalDiceList[2]==rerollDiceList[2])&&
				(originalDiceList[3]==rerollDiceList[3]));
		assertEquals(1, game.getRowCount());
	}
	
	public void testPlayerChoice() {
		int number = 1;
		int[] dlist = new int[] {1,1,1,2,2};
		game.setDList(dlist);
		game.playerChoice(number);
		assertEquals(3, game.getScore(0));
		
		number = 4;
		int[] dlist2 = new int[] {1,2,3,5,6};
		game.setDList(dlist2);
		game.playerChoice(number);
		assertEquals(0, game.getScore(3));
		
		number = 12;
		int[] dlist3 = new int[] {3,3,3,4,4};
		game.setDList(dlist3);
		game.playerChoice(number);
		assertEquals(17, game.getScore(11));
	}
}
