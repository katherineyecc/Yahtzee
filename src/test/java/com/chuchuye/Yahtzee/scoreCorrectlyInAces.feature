@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario Outline: Title of your scenario outline
    Given I finish a round and have dice list <dice>
    
    When I want to score Aces correctly
    
    Then I score <points> points in Aces

    Examples: 
      | dice | points |
      | 1 2 3 4 5     | 1      |
      | 1 1 2 3 4     | 2      |
      | 3 1 3 1 1     | 3      |
      | 1 1 1 1 5     | 4      |
      | 1 1 1 1 1     | 5      |
