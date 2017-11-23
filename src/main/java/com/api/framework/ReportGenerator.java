package com.api.framework;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

	public String fileType = "json";
	private List<String> jsonFiles = new ArrayList<>();

	public static void main(String args[]) {
		ReportGenerator();
	}

	public static void ReportGenerator() {
		String jsonPath = "build/test-results-files";
		String teamname = "Starks";
		ReportGenerator detailReport = new ReportGenerator();
		detailReport.generateReport(jsonPath, teamname);
	}
	private void searchDirectory(File directory) {

		if (directory.isDirectory()) {
			search(directory);
		} else {
		}
	}

	private void search(File file) {

		if (file.isDirectory()) {
			if (file.canRead()) {
				for (File temp : file.listFiles()) {
					if (temp.isDirectory()) {
						search(temp);
					} else {
						if (temp.getName().endsWith("." + fileType)) {
							jsonFiles.add(temp.getAbsoluteFile().toString());
						}
					}

				}
			} else {
			}

		}
	}

	public void generateReport(String jsonFilePath, String Project) {
		File reportOutputDirectory = new File("build/custom-reports");
		searchDirectory(new File(jsonFilePath));
		boolean skippedFails = true;
		boolean pendingFails = false;
		boolean undefinedFails = true;
		boolean runWithJenkins = false;
		boolean parallelTesting = false;
		boolean failsIfMissing = false;

		Configuration configuration = new Configuration(reportOutputDirectory, Project);
		configuration.setParallelTesting(parallelTesting);
		configuration.setRunWithJenkins(runWithJenkins);
		configuration.setBuildNumber("1");
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();

	}

}
