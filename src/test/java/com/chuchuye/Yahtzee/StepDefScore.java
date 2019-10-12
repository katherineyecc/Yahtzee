package com.chuchuye.Yahtzee;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class StepDefScore extends TestCase {
	
	Score score;
	
	@Given("The Yahtzee Game for kat is on")
	public void the_Yahtzee_Game_for_kat_is_on() {
	    //Player kat has entered the game
	}

	@When("The player score {int}")
	public void the_player_score(Integer int1) {
	    score = new Score();
	    score.setScore(int1);
	}

	@Then("I get the return {int}")
	public void i_get_the_return(Integer int1) {
	    assertEquals(-1, score.getScore());
	}

	@Given("The Yahtzee Game for kyrie is on")
	public void the_Yahtzee_Game_for_kyrie_is_on() {
	   //Player kyrie has entered the game
	}

	@Given("The Yahtzee Game is on")
	public void the_Yahtzee_Game_is_on() {
	    //Game is on
	}

	@When("The player score a valid number")
	public void the_player_score_a_valid_number() {
		score = new Score();
	    score.setScore(135);
	}

	@Then("I get the returned score")
	public void i_get_the_returned_score() {
	   assertEquals(135, score.getScore());
	}


}
