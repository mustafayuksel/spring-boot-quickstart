package com.mustafayuksel.quickstart.springbootquickstart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class QuickStartExceptionHandler {
	private static Logger LOGGER = LogManager.getLogger(QuickStartExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleException(Exception ex) {
		LOGGER.error(ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}