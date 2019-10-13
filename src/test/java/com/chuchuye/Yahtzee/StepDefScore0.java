package com.chuchuye.Yahtzee;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class StepDefScore0 extends TestCase {
	
	Game game = new Game();
	
	@Given("I finish a round and have no ones in all five dices")
	public void i_finish_a_round_and_have_no_ones_in_all_five_dices() {
		int[] dList = new int[] {2,3,4,5,6};
	    game.setDList(dList);
	}

	@When("I want to score {int}")
	public void i_want_to_score(Integer int1) {
	    game.playerChoice(int1);
	}

	@Then("I score zero points in {int}")
	public void i_score_zero_points_in(Integer int1) {
	    int score = game.getScore(int1-1);
	    assertEquals(0, score);
	}

	@Given("I finish a round and have no Twos in all five dices")
	public void i_finish_a_round_and_have_no_Twos_in_all_five_dices() {
		int[] dList = new int[] {1,3,4,5,6};
	    game.setDList(dList);
	}

	@Given("I finish a round and have no Threes in all five dices")
	public void i_finish_a_round_and_have_no_Threes_in_all_five_dices() {
		int[] dList = new int[] {2,1,4,5,6};
	    game.setDList(dList);
	}

	@Given("I finish a round and have no Fours in all five dices")
	public void i_finish_a_round_and_have_no_Fours_in_all_five_dices() {
		int[] dList = new int[] {1,3,2,5,6};
	    game.setDList(dList);
	}

	@Given("I finish a round and have no Fivess in all five dices")
	public void i_finish_a_round_and_have_no_Fivess_in_all_five_dices() {
		int[] dList = new int[] {2,3,4,1,6};
	    game.setDList(dList);
	}

	@Given("I finish a round and have no Sixes in all five dices")
	public void i_finish_a_round_and_have_no_Sixes_in_all_five_dices() {
		int[] dList = new int[] {1,3,4,5,2};
	    game.setDList(dList);
	}

	@Given("I finish a round and have no LargeS in all five dices")
	public void i_finish_a_round_and_have_no_LargeS_in_all_five_dices() {
		int[] dList = new int[] {2,5,4,5,6};
	    game.setDList(dList);
	}

	@Given("I finish a round and have no SmallS in all five dices")
	public void i_finish_a_round_and_have_no_SmallS_in_all_five_dices() {
		int[] dList = new int[] {1,2,4,5,6};
	    game.setDList(dList);
	}

	@Given("I finish a round and have no FullHouse in all five dices")
	public void i_finish_a_round_and_have_no_FullHouse_in_all_five_dices() {
		int[] dList = new int[] {2,3,4,5,6};
	    game.setDList(dList);
	}

	@Given("I finish a round and have no ThreeOfAKind in all five dices")
	public void i_finish_a_round_and_have_no_ThreeOfAKind_in_all_five_dices() {
		int[] dList = new int[] {1,3,4,5,6};
	    game.setDList(dList);
	}

	@Given("I finish a round and have no FourOfAKind in all five dices")
	public void i_finish_a_round_and_have_no_FourOfAKind_in_all_five_dices() {
		int[] dList = new int[] {2,3,4,5,6};
	    game.setDList(dList);
	}

	@Given("I finish a round and have no Yahtzee in all five dices")
	public void i_finish_a_round_and_have_no_Yahtzee_in_all_five_dices() {
		int[] dList = new int[] {1,3,4,5,6};
	    game.setDList(dList);
	}


}
