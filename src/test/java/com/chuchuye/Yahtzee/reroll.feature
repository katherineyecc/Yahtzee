@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario: Score with no rerolls
    Given I finish a round with no rerolls
    
    When I want to score a round
    
    Then I get my score

	@tag2
	Scenario: Score with one reroll of less than five dices
		Given I roll the dice once
		
		When I want to reroll less than five of them
		
		Then I get my reroll-once score