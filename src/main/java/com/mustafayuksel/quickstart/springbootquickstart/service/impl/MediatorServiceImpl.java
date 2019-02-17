package com.mustafayuksel.quickstart.springbootquickstart.service.impl;

import com.mustafayuksel.quickstart.springbootquickstart.request.MediatorRequest;
import com.mustafayuksel.quickstart.springbootquickstart.response.MediatorResponse;
import com.mustafayuksel.quickstart.springbootquickstart.service.MediatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@Service
public class MediatorServiceImpl implements MediatorService {
	private static final String REQUEST_PROPERTY_KEY = "Accept-Language";
	private static final String REQUEST_PROPERTY_VALUE = "en-US,en;q=0.5";
	private static final String CHARSET_NAME = "UTF-8";
	private static final Logger LOGGER = LogManager.getLogger(MediatorServiceImpl.class);

	@Override
	@Cacheable(value = "doHttpCall", condition = "#mediatorRequest.getRequestMethod().name() == 'GET'", key = "#mediatorRequest.getCacheKey()", unless = "#result == null")
	public MediatorResponse doHttpCall(MediatorRequest mediatorRequest) throws IOException {
		HttpURLConnection httpURLConnection = openHttpConnection(mediatorRequest);
		if (!StringUtils.isEmpty(mediatorRequest.getRequest())) {
			DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
			wr.writeBytes(mediatorRequest.getRequest());
			wr.flush();
		}
		return prepareResponse(httpURLConnection);
	}

	private MediatorResponse prepareResponse(HttpURLConnection httpURLConnection) throws IOException {
		String responseMessage = new BufferedReader(
				new InputStreamReader(httpURLConnection.getInputStream(), CHARSET_NAME)).lines()
						.collect(Collectors.joining());
		return httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK
				? prepareSuccessfulResponse(responseMessage)
				: prepareUnsuccessfulResponse(responseMessage);
	}

	private MediatorResponse prepareUnsuccessfulResponse(String responseMessage) {
		LOGGER.info("There is a server error, Response " + "Message is: ", responseMessage);
		return new MediatorResponse(responseMessage, false);
	}

	private MediatorResponse prepareSuccessfulResponse(String responseMessage) {
		LOGGER.info("Message received " + "successfully, Response " + "Message is: ", responseMessage);
		return new MediatorResponse(responseMessage, true);
	}

	private HttpURLConnection openHttpConnection(MediatorRequest mediatorRequest) throws IOException {
		URL url = new URL(mediatorRequest.getEndpoint());
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setReadTimeout(mediatorRequest.getTimeOut());
		httpURLConnection.setConnectTimeout(mediatorRequest.getTimeOut());
		httpURLConnection.setRequestMethod(mediatorRequest.getRequestMethod().name());
		httpURLConnection.setRequestProperty(REQUEST_PROPERTY_KEY, REQUEST_PROPERTY_VALUE);
		httpURLConnection.setDoOutput(true);
		return httpURLConnection;
	}
}