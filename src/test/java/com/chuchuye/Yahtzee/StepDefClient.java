package com.chuchuye.Yahtzee;

import com.chuchuye.Client.ClientMock;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class StepDefClient extends TestCase {
	
	private ClientMock cli;
	
	@Given("The server is on")
	public void the_server_is_on() {
	    // We checked the server is on
		// We turn on the server
	}

	@When("The Client connect to the server")
	public void the_Client_connect_to_the_server() {
	    cli = new ClientMock();
	    cli.initClient();
	}

	@Then("The Client receives the welcome message")
	public void the_Client_receives_the_welcome_message() {
	    String msg = "Welcome to the Game!";
	    assertEquals(msg, cli.returnWelcomeMsg());
	}

}
