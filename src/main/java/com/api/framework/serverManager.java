package com.api.framework;

import com.github.tomakehurst.wiremock.WireMockServer;

public class serverManager {
	static WireMockServer wireMockServer = new WireMockServer();

	public static WireMockServer startServer() {

		if (!wireMockServer.isRunning()) {
			wireMockServer.start();

		}
		return wireMockServer;
	}

	public static void stopServer() {
		if (wireMockServer.isRunning()) {
			wireMockServer.stop();
		}
	}
}
