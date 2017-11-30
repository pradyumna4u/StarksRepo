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
		JMSServiceWrapper.sendMessage(Inputmes, "", InputQ);
	}

	@When("^User should get the Message in \"([^\"]*)\"$")
	public void user_should_get_the_Message_in(String arg1) throws Throwable {
		Response = JMSServiceWrapper.receiveAllFromQueue("", arg1);
		Hooks.ebedResult("######OUTPUT QUEUE :\n" + arg1);
		for (TextMessage mes : Response) {
			Hooks.ebedResult("######OUTPUT MESSAGE :\n" + mes.toString());
			Hooks.ebedResult("######OUTPUT MESSAGE :\n" + mes.getText());
		}
	}

	@Then("^Validate the Queue Message is proper \"([^\"]*)\"$")
	public void validate_the_Queue_Message_is_proper(String arg1) throws Throwable {
		// Utility.ValidateXml(Response, arg1);
	}

	@Given("^User should Put Message QueueManaer \"([^\"]*)\" and \"([^\"]*)\" in \"([^\"]*)\"$")
	public void user_should_Put_Message_QueueManaer_and_in(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Inputmes = Utility.readxml(arg2);
		InputQ = arg3;
		Hooks.ebedResult("######INPUT QUEUE :" + InputQ);
		Hooks.ebedResult("######INPUT MESSAGE :\n" + Inputmes);
		JMSServiceWrapper.sendMessage(Inputmes, arg1, InputQ);
	}

	@When("^User should get the Message in QueueManaer \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_should_get_the_Message_in_QueueManaer_and(String arg1, String arg2) throws Throwable {
		Response = JMSServiceWrapper.receiveAllFromQueue(arg1, arg2);
		Thread.sleep(5000);
		Hooks.ebedResult("######OUTPUT QUEUE :\n" + arg1);
		for (TextMessage mes : Response) {
			Hooks.ebedResult("######OUTPUT MESSAGE :\n" + mes.toString());
			Hooks.ebedResult("######OUTPUT MESSAGE :\n" + mes.getText());
		}
	}
	
	@Given("^User should Put txtMessage QueueManaer \"([^\"]*)\" and \"([^\"]*)\" in \"([^\"]*)\"$")
	public void user_should_Put_txtMessage_QueueManaer_and_in(String arg1, String arg2, String arg3) throws Throwable {
		Inputmes = Utility.readtxt(arg2);
		InputQ = arg3;
		Hooks.ebedResult("######INPUT QUEUE :" + InputQ);
		Hooks.ebedResult("######INPUT MESSAGE :\n" + Inputmes);
		JMSServiceWrapper.sendMessage(Inputmes, arg1, InputQ);
	}

}
