package com.api.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class StubBuilder {

	public static void createGETStub(String endpoint, String format, String body) {

		serverManager.startServer().stubFor(get(urlEqualTo(endpoint))
				.willReturn(aResponse().withHeader("Content-Type", format).withBody(body)));
	}
	
	public static void postGETStub(String endpoint, String format, String body) {

		verify(postRequestedFor(urlEqualTo(endpoint))
		        .withHeader("Content-Type", equalTo(format)));
	}
	
	
}
