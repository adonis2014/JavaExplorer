package name.chenyuelin.webapp.controller;

import name.chenyuelin.custom.RequestArgumentNotValidException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class AbstractController {
	@ExceptionHandler(RequestArgumentNotValidException.class)
	public ResponseEntity<?> handleRequestArgumentNotValidException(RequestArgumentNotValidException requestArgumentNotValidException) {
		//TODO
		return null;
	}
}
