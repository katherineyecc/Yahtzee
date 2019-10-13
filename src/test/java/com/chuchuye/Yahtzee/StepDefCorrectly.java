package com.chuchuye.Yahtzee;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class StepDefCorrectly extends TestCase {
	
	Game game = new Game();
	
	@Given("I finish a round and have dice list {int} {int} {int} {int} {int}")
	public void i_finish_a_round_and_have_dice_list(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5) {
		int[] dList = new int[] {int1,int2,int3,int4,int5};
	    game.setDList(dList);
	}

	@When("I want to score {int} correctly")
	public void i_want_to_score_correctly(Integer int1) {
	    game.playerChoice(int1);
	}
	
	@Then("I score {int} points in {int}")
	public void i_score_points_in(Integer int1, Integer int2) {
	    assertEquals((int) int1, game.getScore(int2-1));
	}

}
