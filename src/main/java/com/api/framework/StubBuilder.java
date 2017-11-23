package com.api.framework;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class StubBuilder {

	public static void createGETStub(String endpoint, String format, String body) {

		serverManager.startServer().stubFor(get(urlEqualTo(endpoint))
				.willReturn(aResponse().withHeader("Content-Type", format).withBody(body)));
	}
}
