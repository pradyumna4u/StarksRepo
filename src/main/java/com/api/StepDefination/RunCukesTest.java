package com.api.StepDefination;

import org.junit.runner.RunWith;

import com.api.framework.ReportGenerator;
import com.api.tag.AfterSuite;

import cucumber.api.CucumberOptions;

@RunWith(ExtendedCucumberRunner.class)
@CucumberOptions(features = "src/main/resources/Features/test.Feature", plugin = { "pretty",
		"json:build/test-results-files/cucumber.json" })
public class RunCukesTest {
	@AfterSuite
	public static void teardown() {
		ReportGenerator.ReportGenerator();
	}
}