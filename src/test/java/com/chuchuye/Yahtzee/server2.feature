@tag
Feature: Title of your feature
  I want to use this template for my feature file
  
  @tag1
		Scenario: tackle a three-player game from beginning to end
			Given The game is on
			
			When Three players finish the game according to the input table
			
			Then The server check the outcome
		
	@tag2
		Scenario: switch from one player to the correct next one
			Given The game is turned on
			
			When Three players finish all thirteen rounds
			
			Then The play sequence should be the same as planned