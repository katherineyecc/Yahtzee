package com.chuchuye.Yahtzee;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class StepDefReroll extends TestCase{
	
	Game game = new Game();
	
	@Given("I finish a round with no rerolls")
	public void i_finish_a_round_with_no_rerolls() {
		int[] dList = new int[] {1,2,1,2,1};
	    game.setDList(dList);
	}

	@When("I want to score a round")
	public void i_want_to_score_a_round() {
	    game.playerChoice(1);
	}

	@Then("I get my score")
	public void i_get_my_score() {
	    assertEquals(3, game.getScore(0));
	}

}
