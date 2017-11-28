package com.api.StepDefination;

import org.apache.commons.lang3.StringUtils;

import com.api.framework.Prop;
import com.api.framework.Utility;
import com.api.servicewrapper.SoapServiceWrapper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SoapStepDefinination {
	String SoapEndpoint = StringUtils.EMPTY;
	String Action = StringUtils.EMPTY;
	String Req = StringUtils.EMPTY;
	String Response = StringUtils.EMPTY;

	@Given("^User should call Soap Endpoint \"([^\"]*)\"$")
	public void user_should_call_Soap_Endpoint(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		SoapEndpoint = Prop.getProp(arg1);
		Hooks.ebedResult("######SOAP ENDPOINT" + SoapEndpoint);

	}

	@When("^User Should hit the Action \"([^\"]*)\" with message \"([^\"]*)\"$")
	public void user_Should_hit_the_Action_with_message(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Action = Prop.getProp(arg1);
		Hooks.ebedResult("######SOAP ACTION" + Action);
		Req = Utility.readtxt("/" + arg2);
		Hooks.ebedResult("######SOAP REQUEST" + Req);

	}

	@When("^User will get the Soap reponse$")
	public void user_will_get_the_Soap_reponse() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Response = SoapServiceWrapper.callSoapService(SoapEndpoint, Action);
	}

	@Then("^Validate the Soap response is proper \"([^\"]*)\"$")
	public void validate_the_Soap_response_is_proper(String arg1) throws Throwable {
		Hooks.ebedResult("######SOAP RESPONSE \n" + Response);
		//Utility.ValidateXml(Response, arg1);
	}

}
