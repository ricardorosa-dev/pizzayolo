package dev.ricardo.PizzaYOLO.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PizzaNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(PizzaNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String pizzaNotFoundHandler(PizzaNotFoundException e) {
		return e.getMessage();
	}

}
