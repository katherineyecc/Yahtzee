package com.chuchuye.Yahtzee;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class StepDefScore0InAces extends TestCase {
	
	Game game = new Game();
	
	@Given("I finish a round and have no one in all five dices")
	public void i_finish_a_round_and_have_no_one_in_all_five_dices() {
		int[] dList = new int[] {2,3,4,5,6};
	    game.setDList(dList);
	}

	@When("I want to score Aces")
	public void i_want_to_score_Aces() {
	   game.playerChoice(0);
	}

	@Then("I score zero points in Aces")
	public void i_score_zero_points_in_Aces() {
	   assertEquals(0, game.getScore(0));
	}

}
