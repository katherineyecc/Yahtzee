package com.chuchuye.Yahtzee;

import junit.framework.TestCase;

public class ScoreTest extends TestCase {
	public void testScore() {
		Score score = new Score();
		score.setScore(5);
		int currentScore = score.getScore();
		assertEquals(5, currentScore);
	}

}
