package com.mustafayuksel.quickstart.springbootquickstart.service.impl;

import com.mustafayuksel.quickstart.springbootquickstart.request.MediatorRequest;
import com.mustafayuksel.quickstart.springbootquickstart.response.MediatorResponse;
import com.mustafayuksel.quickstart.springbootquickstart.service.MediatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

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
    private static final String HTTP_METHOD_NAME = "POST";
    private static final String CHARSET_NAME = "UTF-8";
    private static final Logger LOGGER = LogManager
            .getLogger(MediatorServiceImpl.class);

    @Override
    public MediatorResponse doHttpCall(MediatorRequest mediatorRequest) {
        try {
            HttpURLConnection httpURLConnection =
                    openHttpConnection(mediatorRequest.getTimeOut(),
                            mediatorRequest.getEndpoint());
            try (DataOutputStream wr = new
                    DataOutputStream((httpURLConnection
                    .getOutputStream()))) {
                wr.writeBytes(mediatorRequest.getRequest());
                wr.flush();
            } catch (IOException e) {
                return handleExceptionResponse(e);
            }
            return prepareResponse(httpURLConnection);
        } catch (IOException e) {
            return handleExceptionResponse(e);
        }
    }

    private MediatorResponse prepareResponse(HttpURLConnection httpURLConnection) throws IOException {
        String responseMessage = new BufferedReader(new InputStreamReader(
                httpURLConnection.getInputStream(), CHARSET_NAME)).lines()
                .collect(Collectors.joining());
        return httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK ?
                prepareSuccessfulResponse(responseMessage) : prepareUnsuccessfulResponse(responseMessage);
    }

    private MediatorResponse prepareUnsuccessfulResponse(String responseMessage) {
        LOGGER.info("There is a server error, Response "
                + "Message is: ", responseMessage);
        return new MediatorResponse(responseMessage, false);
    }

    private MediatorResponse prepareSuccessfulResponse(String responseMessage) {
        LOGGER.info("Message received " + "successfully, Response " +
                "Message is: ", responseMessage);
        return new MediatorResponse(responseMessage, true);
    }

    private HttpURLConnection openHttpConnection(int timeOut, String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection httpURLConnection =
                (HttpURLConnection) url.openConnection();
        httpURLConnection.setReadTimeout(timeOut);
        httpURLConnection.setRequestMethod(HTTP_METHOD_NAME);
        httpURLConnection.setRequestProperty
                (REQUEST_PROPERTY_KEY, REQUEST_PROPERTY_VALUE);
        httpURLConnection.setDoOutput(true);
        return httpURLConnection;
    }

    private MediatorResponse handleExceptionResponse
            (IOException e) {
        LOGGER.error(e.getMessage());
        return new MediatorResponse(e.getMessage(), false);
    }
}