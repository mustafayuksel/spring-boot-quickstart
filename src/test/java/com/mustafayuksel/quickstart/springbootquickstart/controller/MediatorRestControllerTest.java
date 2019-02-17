package com.mustafayuksel.quickstart.springbootquickstart.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mustafayuksel.quickstart.springbootquickstart.request.MediatorRequest;
import com.mustafayuksel.quickstart.springbootquickstart.response.MediatorResponse;
import com.mustafayuksel.quickstart.springbootquickstart.service.MediatorService;

public class MediatorRestControllerTest {

	private MediatorRestController mediatorRestController;
	private MediatorService mediatorService;

	@Before
	public void setup() {
		mediatorService = Mockito.mock(MediatorService.class);
		mediatorRestController = new MediatorRestController(mediatorService);
	}

	@Test
	public void callWithXMLTest() throws IOException {
		// given
		String request = "<?xml version=\"1.0\"?>\n" + "\n" + "<soap:Envelope\n"
				+ "xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope/\"\n"
				+ "soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" + "\n" + "<soap:Body>\n"
				+ "  <m:GetPrice xmlns:m=\"https://www.w3schools.com/prices\">\n" + "    <m:Item>Apples</m:Item>\n"
				+ "  </m:GetPrice>\n" + "</soap:Body>\n" + "\n" + "</soap:Envelope>";
		String endpoint = "http://www.w3.org/2003/05/soap-envelope/";
		MediatorRequest mediatorRequest = new MediatorRequest(request, 10, endpoint, RequestMethod.POST);
		MediatorResponse mediatorResponse = new MediatorResponse("OK", true);
		Mockito.when(mediatorService.doHttpCall(mediatorRequest)).thenReturn(mediatorResponse);

		// when
		MediatorResponse response = mediatorRestController.callWithXML(mediatorRequest);

		// then
		assertEquals(response.getSuccess(), true);
		assertEquals(response.getResponse(), mediatorResponse.getResponse());
		Mockito.verify(mediatorService, Mockito.only()).doHttpCall(mediatorRequest);
	}

	@Test
	public void callWithJsonTest() throws IOException {
		// given
		String request = "{ name: \"John\", age: 31, city: \"New York\" }";
		String endpoint = "http://www.w3.org/2003/05/soap-envelope/";
		MediatorRequest mediatorRequest = new MediatorRequest(request, 10, endpoint, RequestMethod.POST);
		MediatorResponse mediatorResponse = new MediatorResponse("OK", true);
		Mockito.when(mediatorService.doHttpCall(mediatorRequest)).thenReturn(mediatorResponse);

		// when
		MediatorResponse response = mediatorRestController.callWithJSON(mediatorRequest);

		// then
		assertEquals(response.getSuccess(), true);
		assertEquals(response.getResponse(), mediatorResponse.getResponse());
		Mockito.verify(mediatorService, Mockito.only()).doHttpCall(mediatorRequest);
	}
}