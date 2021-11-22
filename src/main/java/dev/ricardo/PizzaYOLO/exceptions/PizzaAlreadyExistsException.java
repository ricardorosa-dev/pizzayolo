package dev.ricardo.PizzaYOLO.exceptions;

public class PizzaAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PizzaAlreadyExistsException() {
		super("Já existe uma pizza com esse nome!");
	}

}
