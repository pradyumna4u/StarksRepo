Feature: API TESTING

  Scenario: Call the rest api and validate the response
    Given User should call api "DemoApi"
    When User Should hit the endpoint "state/get/IND/JK"
    And User will get the reponse
    Then Validate the response is proper

  Scenario: Call the Soap and validate the response
    Given User should call Soap Endpoint "DemoSoapEndpoint"
    When User Should hit the Action "DemoSoapAction" with message "SoapRequest"
    And User will get the Soap reponse
    Then Validate the Soap response is proper "TestExpectedxml"

  Scenario: Call the Queue Service and validate the response
    Given User should Put Message "/testBOLDctoDC" in "QA.21.WMM.MOSAIC.BOL"
    When User should get the Message in "QL.04.TEST"
    Then Validate the Queue Message is proper "TestExpectedxml"
