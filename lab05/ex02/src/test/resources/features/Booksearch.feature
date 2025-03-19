Feature: Book search
  To help customers find their favorite books quickly, the library offers search options.

  Background:
    Given the following books exist:
      | title                | author          | published  |
      | One good book        | Anonymous       | 2013-03-14 |
      | Some other book      | Tim Tomson      | 2014-08-23 |
      | How to cook a dino   | Fred Flintstone | 2012-01-01 |
      | The Science of Code  | Jane Doe        | 2015-06-07 |

  Scenario: Search books by publication year
    When the customer searches for books published between 2013-01-01 and 2014-12-31
    Then 2 books should have been found
    And Book 1 should have the title 'Some other book'
    And Book 2 should have the title 'One good book'

  Scenario: Search books in a year with no results
    When the customer searches for books published between 2010-01-01 and 2010-12-31
    Then 0 books should have been found
