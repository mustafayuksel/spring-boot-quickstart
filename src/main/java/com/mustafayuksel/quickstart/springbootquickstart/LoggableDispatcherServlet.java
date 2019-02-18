package com.mustafayuksel.quickstart.springbootquickstart;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggableDispatcherServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LogManager.getLogger("LoggableDispatcherServlet");

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!(request instanceof ContentCachingRequestWrapper)) {
			request = new ContentCachingRequestWrapper(request);
		}
		if (!(response instanceof ContentCachingResponseWrapper)) {
			response = new ContentCachingResponseWrapper(response);
		}
		HandlerExecutionChain handler = getHandler(request);

		try {
			super.doDispatch(request, response);
		} finally {
			log(request, response, handler);
			updateResponse(response);
		}
	}

	private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache,
			HandlerExecutionChain handler) throws IOException {
		logger.info("Response Status: " + String.valueOf(responseToCache.getStatus()));
		logger.info("Request Method: " + requestToCache.getMethod());
		logger.info("Request URI: " + requestToCache.getRequestURI());
		logger.info("Request Remote Adr: " + requestToCache.getRemoteAddr());
		logger.info("Handler: " + handler.toString());
		logger.info("Request Body: "
				+ requestToCache.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		logger.info("Response Body: " + getResponsePayload(responseToCache));
	}

	private String getResponsePayload(HttpServletResponse response) {
		ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response,
				ContentCachingResponseWrapper.class);
		if (wrapper != null) {
			byte[] responseArray = wrapper.getContentAsByteArray();
			if (responseArray.length > 0) {
				return new String(responseArray);
			}
		}
		return "";
	}

	private void updateResponse(HttpServletResponse response) throws IOException {
		ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response,
				ContentCachingResponseWrapper.class);
		responseWrapper.copyBodyToResponse();
	}
}