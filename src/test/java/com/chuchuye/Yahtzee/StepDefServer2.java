package com.chuchuye.Yahtzee;

import com.chuchuye.Client.ClientMock;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class StepDefServer2 extends TestCase {
	
	ServerMock sm = new ServerMock();
	ServerMock sm2 = new ServerMock();
	String[] inputTable1 = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13"};
	String[] inputTable2 = new String[] {"13","12","11","10","9","8","7","6","5","4","3","2","1"};
	String[] inputTable3 = new String[] {"1","3","5","7","9","11","13","2","4","6","8","10","12"};
	
	
	@Given("The game is turned on")
	public void the_game_is_turned_on() {
	   sm2.initServer2();
	}

	@When("Three players finish all thirteen rounds")
	public void three_players_finish_all_thirteen_rounds() {
	    //We create 3 Clients
	}

	@Then("The play sequence should be the same as planned")
	public void the_play_sequence_should_be_the_same_as_planned() {
		int[] sequence = new int[] {1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3};
		for(int index=0; index<39; index++) {
			assertEquals(sequence[index], sm2.ongoingPlayer[index]);
		}
	}
	/*
	
	@Given("The game is on")
	public void the_game_is_on() {
	    sm.initServer2();
	}

	@When("Three players finish the game according to the input table")
	public void three_players_finish_the_game_according_to_the_input_table() {
	    //We turn on 3 Clients
	}

	@Then("The server check the outcome")
	public void the_server_check_the_outcome() {
	    for(int index=0; index<13; index++) {
	    	assertEquals(inputTable1[index], sm.player1Game[index]);
	    	assertEquals(inputTable2[index], sm.player2Game[index]);
	    	assertEquals(inputTable3[index], sm.player3Game[index]);
	    }
	}
*/
	
}
