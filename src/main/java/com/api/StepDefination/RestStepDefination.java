package com.api.StepDefination;

import org.apache.commons.lang3.StringUtils;

import com.api.framework.JsonUtil;
import com.api.framework.Prop;
import com.api.framework.RestServiceWrapper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RestStepDefination {

	String Apipath = StringUtils.EMPTY;
	String Endpoint = StringUtils.EMPTY;
	String Response = StringUtils.EMPTY;

	@Given("^User should call api \"([^\"]*)\"$")
	public void user_should_call_api(String arg1) throws Throwable {
		Apipath = Prop.getProp(arg1);
		Hooks.ebedResult("######REST Apipath :" + Apipath);
	}

	@When("^User Should hit the endpoint \"([^\"]*)\"$")
	public void user_Should_hit_the_endpoint(String arg1) throws Throwable {
		Endpoint = arg1;
		Hooks.ebedResult("######REST ACTION :" + arg1);
	}

	@When("^User will get the reponse$")
	public void user_will_get_the_reponse() throws Throwable {
		Response = RestServiceWrapper.restGetCall(Apipath, Endpoint, 200);
	}

	@Then("^Validate the response is proper$")
	public void validate_the_response_is_proper() throws Throwable {
		System.out.println(Response);
		Hooks.ebedResult("######REST Response: " + Response);
		JsonUtil.validateJson(Response, "/TestEpexted");
	}

}
