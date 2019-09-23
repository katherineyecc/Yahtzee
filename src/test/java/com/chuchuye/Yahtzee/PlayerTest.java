package com.chuchuye.Yahtzee;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {
	
	Player player;
	
	public void testInit() {
		String name = "Kat";
		player = new Player(name);
		assertEquals(name, player.getName());
	}
	
	public void testGetCurrentRound() {
		player = new Player();
		assertEquals(1, player.getCurrentRound());
	}
	
	public void testGetAndSetScoreBoard() {
		player = new Player();
		player.setScoreBoard(0, 5);
		assertEquals(5, player.getScoreBoard()[0]);
	}
	
	public void testAddRound() {
		player = new Player();
		player.addRound();
		assertEquals(2, player.getCurrentRound());
	}
	
	public void testGetScore() {
		player = new Player();
		int score = player.getScore();
		assertEquals(0, score);
	}
	
	public void testSetScore() {
		player = new Player();
		player.setScore(10);
		assertEquals(10, player.getScore());
	}
	
	public void testCountScore() {
		player = new Player();
		for(int index=0; index<13; index++) {
			player.setScoreBoard(index, 1);
		}
		player.countScore();
		int score = player.getScore();
		assertEquals(13, score);
	}
	
	public void testGetBonus() {
		player = new Player();
		assertEquals(0, player.getBonus());
	}
	
	public void testSetBonus() {
		player = new Player();
		player.setBonus(10);
		assertEquals(10, player.getBonus());
	}
	
	public void testCountBonus() {
		player = new Player();
		for(int index=0; index<6; index++) {
			player.setScoreBoard(index, index*6);
		}
		player.countBonus();
		assertEquals(35, player.getBonus());
	}
	
	public void testGetYahtzeeBonus() {
		player = new Player();
		assertEquals(0, player.getYahtzeeBonus());
	}
	
	public void testSetYahtzeeBonus() {
		player = new Player();
		player.setYahtzeeBonus(100);
		assertEquals(100, player.getYahtzeeBonus());
	}
	
	public void testGetYahtzeeFlag() {
		player = new Player();
		assertFalse(player.getYahtzeeFlag());
	}
	
	public void testSetYahtzeeFlag() {
		player = new Player();
		player.setYahtzeeFlag();
		assertEquals(player.getYahtzeeFlag());
	}
	
	public void testGetSpecificScore() {
		player = new Player();
		player.setScoreBoard(12, 100);
		assertEquals(100, player.getSpecificScore(12));
	}
	
	public void testCountYahtzeeBonus() {
		player = new Player();
		player.setYahtzeeFlag();
		assertEquals(100, player.getSpecificScore(12));
	}

}
