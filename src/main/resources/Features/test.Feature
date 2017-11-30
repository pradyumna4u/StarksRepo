Feature: API TESTING

 # Scenario: Call the rest api and validate the response
  #  Given User should call api "DemoApi"
   # When User Should hit the endpoint "state/get/IND/JK"
   # And User will get the reponse
    #Then Validate the response is proper

  #Scenario: Call the Soap and validate the response
   # Given User should call Soap Endpoint "DemoSoapEndpoint"
   # When User Should hit the Action "DemoSoapAction" with message "SoapRequest"
   # And User will get the Soap reponse
   # Then Validate the Soap response is proper "TestExpectedxml"

  #Scenario: Call the Queue Service and validate the response
   # Given User should Put Message QueueManaer "" and "/testBOLDctoDC" in "QA.21.WMM.MOSAIC.BOL"
   # When User should get the Message in "QL.04.TEST"
   # Then Validate the Queue Message is proper "TestExpectedxml"

  #QA.04.WMM.MOSAIC.ITEM_EXPORT
  #Scenario: Call the Item Export and validate the response
   # Given User should Put Message QueueManaer "" and "/itemexport" in "QA.04.WMM.MOSAIC.ITEM_EXPORT"
   # When User should get the Message in "QL.04.TEST"
   # Then Validate the Queue Message is proper "TestExpectedxml"

  #Scenario: Call the Item Export and validate the response
   # Given User should Put Message QueueManaer "lime" and "/Cubiscan" in "QL.91.WMS.LIME.PROD_DIM_DL"
   # When User should get the Message in QueueManaer "mosaic" and "QL.04.TEST"

  #Then Validate the Queue Message is proper "TestExpectedxml"
  Scenario: Call the Item Export and validate the response
    Given User should Put txtMessage QueueManaer "lime" and "/e2eUPCRecleam" in "QL.91.PROMPT.LIME.ITEM.DATA"
    Given User should Put txtMessage QueueManaer "lime" and "/POS_PFI_EDI_832_Header" in "QL.91.PROMPT.LIME.ITEM.UOW"
    When User should get the Message in QueueManaer "mosaic" and "QL.04.TEST"
