@tag
Feature: Score Test feature
  I want to use this template for my Score file

  @tag1
  Scenario Outline: Score test with invalid numbers
    Given The Yahtzee Game for <player> is on
    
    When The player score <score>
    
    Then I get the return <result>

    Examples: 
      | player  | score | result  |
      | kat |    -1 | -1      |
      | kyrie |   476 | -1      |


  @tag2
  Scenario: Score test with valid number
  	Given The Yahtzee Game is on
  	
  	When The player score a valid number
  	
  	Then I get the returned score