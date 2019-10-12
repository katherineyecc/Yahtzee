@tag
Feature: Score zero in Aces Feature
  I want to use this template for score0InAces file

  @tag1
  Scenario: Score zero in Aces
    Given I finish a round and have no one in all five dices
    
    When I want to score Aces
    
    Then I score zero points in Aces


