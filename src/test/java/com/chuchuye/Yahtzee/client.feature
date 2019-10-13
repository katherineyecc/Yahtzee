@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario: A player join the game
    Given The server is on
    
    When The Client connect to the server

    Then The Client receives the welcome message

	@tag2
	Scenario: A player end the game
		Given The server is on
		
		When The Client connect to the server and finish all rounds
		
		Then The Client receives ending message