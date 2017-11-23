package com.api.StepDefination;


/**
 * Created by Pradyumna Kumar Singh on 7/26/16.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/Features/test.Feature", plugin = { "pretty",
		"json:build/test-results-files/cucumber.json"})
public class RunCukesTest {
	public static void main(String args[]) {
	}
}