package dev.ricardo.PizzaYOLO.exceptions;

public class PizzaIncompleteBodyException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PizzaIncompleteBodyException() {
		super("Informe todos os dados: a pizza deve ter 'nome', 'descrição' e 'preço'.");
	}
}
