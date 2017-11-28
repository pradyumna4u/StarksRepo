package com.api.StepDefination;

import java.util.List;

import javax.jms.Message;
import javax.jms.TextMessage;

import com.api.framework.Utility;
import com.api.servicewrapper.JMSServiceWrapper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QueueStepDefinition {

	String Inputmes;
	String InputQ;
	private List<TextMessage> Response;

	@Given("^User should Put Message \"([^\"]*)\" in \"([^\"]*)\"$")
	public void user_should_Put_Message_in(String arg1, String arg2) throws Throwable {
		Inputmes = Utility.readxml(arg1);
		InputQ = arg2;
		Hooks.ebedResult("######INPUT QUEUE :" + InputQ);
		Hooks.ebedResult("######INPUT MESSAGE :\n" + Inputmes);
		JMSServiceWrapper.sendMessage(Inputmes, InputQ);
	}

	@When("^User should get the Message in \"([^\"]*)\"$")
	public void user_should_get_the_Message_in(String arg1) throws Throwable {
		Response = JMSServiceWrapper.receiveAllFromQueue(arg1);
		Hooks.ebedResult("######OUTPUT QUEUE :\n" + arg1);
		for (TextMessage mes : Response) {
			Hooks.ebedResult("######OUTPUT MESSAGE :\n" + mes.toString());
			Hooks.ebedResult("######OUTPUT MESSAGE :\n" + mes.getText());
		}
	}

	@Then("^Validate the Queue Message is proper \"([^\"]*)\"$")
	public void validate_the_Queue_Message_is_proper(String arg1) throws Throwable {
		 //Utility.ValidateXml(Response, arg1);
	}

}
