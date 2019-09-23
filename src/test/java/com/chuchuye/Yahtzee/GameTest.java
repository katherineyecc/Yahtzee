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
	}
}
