package com.api.StepDefination;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.api.framework.ReportGenerator;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/Features/test.Feature", plugin = { "pretty",
		"json:build/test-results-files/cucumber.json" })
public class RunCukesTest {
	@AfterClass
    public static void teardown() {
        ReportGenerator.ReportGenerator();
    }
}