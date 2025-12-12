Feature: Verify author details from OpenLibrary API

  Scenario Outline: Validate author information using data table
    Given I hit the OpenLibrary author endpoint "<authorId>"
    Then the personal_name should be "<personalName>"
    And the response should contain alternate name "<alternateName>"

    Examples:
      | authorId | personalName     | alternateName                 |
      | OL1A     | Sachi Rautroy    | Yugashrashta Sachi Routray    |
