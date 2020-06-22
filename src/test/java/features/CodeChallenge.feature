Feature: GitHub API Testing

  Scenario: Creating a Repository
    Given User "PiotrSzyszko" logs in
    When User creates repository named "firstRepo"
    Then Repository is created


  Scenario: Push Commit
    Given User "PiotrSzyszko" logs in
    When User pushes commit with message "my commit message" into "firstRepo" repository
    Then Commit is pushed to repository


  Scenario: Creating Pull Request
    Given User "PiotrSzyszko" logs in
    When User creates Pull Request on "firstRepo" repository from "master" branch to "newBranch"
    Then Pull Request is created


  Scenario: Accepting Pull Request
    Given User "PiotrSzyszko" logs in
    When User accepts Pull Request number "1" on "firstRepo" repository
    Then Pull Request is accepted


  Scenario: Deleting a repository
    Given User "PiotrSzyszko" logs in
    When User deletes "firstRepo" repository
    Then Repository is deleted