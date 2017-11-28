package com.api.StepDefination;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import com.api.framework.ReportGenerator;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/Features/test.Feature", plugin = { "pretty",
		"json:build/test-results-files/cucumber.json" })
public class RunCukesTest {
	public static void main(String args[]) {
		ReportGenerator.ReportGenerator();
	}
}