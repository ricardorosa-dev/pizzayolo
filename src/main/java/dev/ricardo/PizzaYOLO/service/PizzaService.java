package dev.ricardo.PizzaYOLO.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.ricardo.PizzaYOLO.entity.Pizza;
import dev.ricardo.PizzaYOLO.exceptions.PizzaAlreadyExistsException;
import dev.ricardo.PizzaYOLO.exceptions.PizzaIncompleteBodyException;
import dev.ricardo.PizzaYOLO.exceptions.PizzaNotFoundException;
import dev.ricardo.PizzaYOLO.repository.PizzaRepository;

@Service
public class PizzaService {
	
	private PizzaRepository repository;
	
	@Autowired
	public PizzaService(PizzaRepository repository) {
		this.repository = repository;
	}
	
	public Page<Pizza> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Pizza findById(Long id) {
			return repository.findById(id)
					.orElseThrow(() -> new PizzaNotFoundException(id));
	}
	
	public Pizza save(Pizza newPizza) {
		Pizza exists = repository.findByNome(newPizza.getNome());
		if (exists != null) {
			throw new PizzaAlreadyExistsException();
		}
		
		if (newPizza.getNome() == null
			|| newPizza.getDescricao() == null
			|| newPizza.getPreco() == null) {
			throw new PizzaIncompleteBodyException(); 
		}
		return repository.save(newPizza);
	}
	
	public Pizza update(Long id, Pizza updatePizza) {
		if (id == null
				|| updatePizza.getNome() == null
				|| updatePizza.getDescricao() == null
				|| updatePizza.getPreco() == null) {
				throw new PizzaIncompleteBodyException(); 
			}
		
		Optional<Pizza> pizza = repository.findById(id);
		
		if (pizza.isPresent()) {
			pizza.get().setNome(updatePizza.getNome());
			pizza.get().setDescricao(updatePizza.getDescricao());
			pizza.get().setPreco(updatePizza.getPreco());
			
			repository.save(pizza.get());
			
			return pizza.get();
		}
		throw new PizzaNotFoundException(id);
	}
	
	public void delete(Long id) {
		Pizza pizza = repository.findById(id)
				.orElseThrow(() -> new PizzaNotFoundException(id));
		
		repository.delete(pizza);
	}

}
