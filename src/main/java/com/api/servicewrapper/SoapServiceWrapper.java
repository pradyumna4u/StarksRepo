package com.api.servicewrapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.soap.*;

import org.apache.commons.lang3.StringUtils;

import com.api.framework.Utility;

public class SoapServiceWrapper {

	// SAAJ - SOAP Client Testing
	public static String callSoapService(String SoapEndpoint, String SoapAction) {
		return callSoapWebService(SoapEndpoint, SoapAction);
	}

	private static String callSoapWebService(String soapEndpointUrl, String soapAction) {
		String Response = StringUtils.EMPTY;
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);
			ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
			soapResponse.writeTo(byteOutStream);
			Response = new String(byteOutStream.toByteArray());
			soapConnection.close();
		} catch (Exception e) {
			System.err.println(
					"\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
			e.printStackTrace();
		}
		return Response;
	}

	private static SOAPMessage createSOAPRequest(String Message) throws Exception {
		InputStream is = new ByteArrayInputStream(Utility.readtxt("/SoapRequest").getBytes());
		SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
		return request;
	}

}