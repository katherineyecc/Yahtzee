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
		
	@tag3
	Scenario: Score with one reroll of five dices
		Given I roll the dice once
		
		When I want to reroll all five dices
		
		Then I get my reroll-once score
		
	@tag4
	Scenario: Score after two rerolls
		Given I roll the dice once
		
		When I want to reroll the dice
		
		And I want to reroll the dice again
		
		Then I get my reroll score
		
	@tag5
	Scenario Outline: Combination of two rerolls
		Given I roll the dice once
		
		When I want to reroll the dice of position <firstPosition>
		
		And I want to reroll the dice of position <secondPosition>
		
		Then I get my reroll score
		
		Examples:
		
		| firstPosition | secondPosition |
		| "1"           | "1 2 3 4 5"    |
		| "1 2 3 4 5"   | "3"            |
		| "2"           | "4"            |
		| "1 2 3 4 5"   | "1 2 3 4 5"    |
		| "1 3 5"       | "2 4"          |