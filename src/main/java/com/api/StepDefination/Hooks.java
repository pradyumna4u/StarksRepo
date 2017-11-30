package com.api.StepDefination;

import java.io.IOException;

import com.api.framework.Log;
import com.api.framework.ReportGenerator;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by dtran on 7/26/16.
 */
public class Hooks {

	private static Scenario sem;

	@Before
	public void startUp1(Scenario scenario) throws IOException {
		sem = scenario;
		Log.startScenario(scenario.getName());
	}

	public static void ebedResult(String text) {
		sem.write(text);
	}

}
