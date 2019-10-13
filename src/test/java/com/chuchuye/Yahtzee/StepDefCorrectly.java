package com.chuchuye.Yahtzee;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class StepDefCorrectly extends TestCase {
	
	Game game = new Game();
	Player player = new Player();
	
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
	
	@Given("I finish upper section and upper section's total score is over or equal to sixty-three points")
	public void i_finish_upper_section_and_upper_section_s_total_score_is_over_or_equal_to_sixty_three_points() {
		for(int index=0; index<6; index++) {
	    	player.setScoreBoard(index, (index+1)*5);
	    }
	}

	@When("I want to score the upper section bonus")
	public void i_want_to_score_the_upper_section_bonus() {
		player.countBonus();
	}

	@Then("I score thirty-five points in upper section bonus")
	public void i_score_thirty_five_points_in_upper_section_bonus() {
		int bonus = player.getBonus();
	    assertEquals(35, bonus);
	}

}
