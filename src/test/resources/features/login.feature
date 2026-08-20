@login
Feature: Login functionality on SauceDemo

  As a registered user
  I want to log into the application
  So that I can access the products page

  Background:
    Given the user is on the login page

  @smoke
  Scenario: Successful login with a standard user
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the user should be redirected to the products page

  Scenario Outline: Successful login with users that have known UI quirks
    When the user logs in with username "<username>" and password "secret_sauce"
    Then the user should be redirected to the products page

    Examples:
      | username                |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |

  @regression
  Scenario Outline: Failed login with invalid credentials or a locked account
    When the user logs in with username "<username>" and password "<password>"
    Then the user should see the error message "<errorMessage>"

    Examples:
      | username        | password        | errorMessage                                                              |
      | locked_out_user | secret_sauce    | Epic sadface: Sorry, this user has been locked out.                       |
      | standard_user   | wrong_password  | Epic sadface: Username and password do not match any user in this service |
      | unknown_user    | secret_sauce    | Epic sadface: Username and password do not match any user in this service |

  @regression
  Scenario: Login fails when username is missing
    When the user logs in with username "" and password "secret_sauce"
    Then the user should see the error message "Epic sadface: Username is required"

  @regression
  Scenario: Login fails when password is missing
    When the user logs in with username "standard_user" and password ""
    Then the user should see the error message "Epic sadface: Password is required"

  @regression
  Scenario: Login fails when both username and password are missing
    When the user logs in with username "" and password ""
    Then the user should see the error message "Epic sadface: Username is required"
