package com.api.framework;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.custommonkey.xmlunit.XMLUnit;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

@Component
public final class XMLUtil {

	public Document loadXMLfromString(String outputMessage) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(outputMessage));
		return builder.parse(is);
	}

	public String getStringFromDocument(Document doc) {
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void xmlValidator(String Actual,String ExpectedFilePath) throws ParserConfigurationException, SAXException, IOException {
		XMLUnit.setIgnoreWhitespace(true); // ignore whitespace differences
		Diff myDiff = DiffBuilder.compare(Actual).withTest(Utility.readtxt("/"+ExpectedFilePath)).build();
	     
	    Iterator<Difference> iter = myDiff.getDifferences().iterator();
	    int size = 0;
	    while (iter.hasNext()) {
	        iter.next().toString();
	        size++;
	    }
	    assertTrue(size>0);
	}
	
}