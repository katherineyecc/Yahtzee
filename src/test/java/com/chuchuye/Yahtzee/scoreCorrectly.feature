@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario Outline: Title of your scenario outline
    Given I finish a round and have dice list <dice>
    
    When I want to score <category> correctly
    
    Then I score <points> points in <category>

    Examples: 
      | dice | category | points |
      | 1 2 3 4 5   | 1 | 1  |
      | 1 2 2 3 4   | 2 | 4  |
      | 3 1 3 1 1   | 3 | 6  |
      | 1 4 4 4 5   | 4 | 12 |
      | 1 5 1 1 1   | 5 | 5  |
      | 1 6 3 6 5   | 6 | 12 |
      | 5 1 2 3 4   | 7 | 40 |
      | 3 1 3 4 2   | 8 | 30 |
      | 1 1 5 1 5   | 9 | 25 |
      | 1 5 1 1 1   | 10| 9  |
      | 3 1 1 1 1   | 11| 7  | 
      | 1 1 1 1 6   | 12| 10 |
      | 1 1 1 1 1   | 13| 50 |
 