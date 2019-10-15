package com.chuchuye.Yahtzee;

import com.chuchuye.Client.ClientMock;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class StepDefServer extends TestCase {
	
	ServerMock sm = new ServerMock();
	
	@Given("The server is turned on")
	public void the_server_is_turned_on() {
	    sm.initServer();
	}

	@When("Three players connect to the server")
	public void three_players_connect_to_the_server() {
	    // We create Clients
	}

	@Then("The game start")
	public void the_game_start() {
	    assertEquals(true, sm.playerReady);
	}
	


}
