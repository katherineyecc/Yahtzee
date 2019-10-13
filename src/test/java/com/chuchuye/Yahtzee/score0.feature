@tag
Feature: Score zero Feature
  I want to use this template for score0 file

  @tag1
  Scenario Outline: Score zero in category
    Given I finish a round and have no <category> in all five dices
    
    When I want to score <categoryNumber>
    
    Then I score zero point in <categoryNumber>
    
    Examples: 
    | category | categoryNumber |
    |   ones   |      1         |
    |   Twos   |      2         |
    |   Threes |      3         |
    |   Fours  |      4         |
    |   Fivess |      5         |
    |   Sixes  |      6         |
		|  LargeS  |      7         |
		|  SmallS  |      8         |
		| FullHouse|      9         |
		|ThreeOfAKind|    10        |
		|FourOfAKind|     11        |
		|  Yahtzee |      13        |
		
	@tag2
	Scenario: Score zero in upper section bonus
		Given I finished upper section and upper section's total score is under sixty-three
		
		When I want to check upper section bonus
		
		Then I score zero point in upper section bonus

