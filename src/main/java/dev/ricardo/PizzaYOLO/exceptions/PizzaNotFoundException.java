package dev.ricardo.PizzaYOLO.exceptions;

public class PizzaNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PizzaNotFoundException(Long id) {
		super("Não existe pizza associada ao id " + id + ".");
	}
}
