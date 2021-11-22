package dev.ricardo.PizzaYOLO.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PizzaAlreadyExistsAdvice {
	
	@ResponseBody
	@ExceptionHandler(PizzaAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.OK)
	String idNotNumberHandler(PizzaAlreadyExistsException e) {
		return e.getMessage();
	}

}
