package dev.ricardo.PizzaYOLO.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import dev.ricardo.PizzaYOLO.entity.Pizza;
import dev.ricardo.PizzaYOLO.exceptions.PizzaAlreadyExistsException;
import dev.ricardo.PizzaYOLO.exceptions.PizzaIncompleteBodyException;
import dev.ricardo.PizzaYOLO.exceptions.PizzaNotFoundException;
import dev.ricardo.PizzaYOLO.repository.PizzaRepository;

class PizzaServiceTest {
	
	PizzaRepository repository = mock(PizzaRepository.class);
	PizzaService service = new PizzaService(repository);
	
	Pizza pizzaA = new Pizza(1L, "Toscana", "Calabresa e mussarela", 28.0);
	Pizza pizzaB = new Pizza(2L, "Escarola", "Escarola refogada com alho frito e mussarela", 25.0);
	Pizza pizzaC = new Pizza(3L, "Atum", "Atum com vinagrete, cebola", 23.0);
	List<Pizza> pizzaList = Arrays.asList(pizzaA, pizzaB, pizzaC);
	Page<Pizza> pizzaPage = new PageImpl<>(pizzaList);
	Pageable pageable = PageRequest.of(0, 3);

	@Test
	void testFindAll_shouldReturnPageOfPizzas() {
		when(repository.findAll(pageable)).thenReturn(pizzaPage);
		
		Page<Pizza> result = service.findAll(pageable);
		
		verify(repository).findAll(pageable);
		assertEquals(3, result.getContent().size());
		assertEquals(PageImpl.class, result.getClass());
		
	}

	@Test
	void testFindById_shouldReturnCorrectPizzaWhenExists() {
		when(repository.findById(pizzaA.getId())).thenReturn(Optional.of(pizzaA));
		
		Pizza foundPizza = service.findById(pizzaA.getId());
		
		verify(repository).findById(pizzaA.getId());
		assertEquals(pizzaA, foundPizza);
	}
	
	@Test
	void testFindById_shouldReturnExceptionWhenIdNotExists() {
		assertThrows(
				PizzaNotFoundException.class,
				() -> service.findById(14L)
		);
	}

	@Test
	void testSave_shouldThrowExceptionWhenIncompleteBody() {
		Pizza pizza_semNome = new Pizza(4L, null, "Mussarela, tomate e manjericao", 25.0);
		Pizza pizza_semDescricao = new Pizza(4L, "Margerita", null, 25.0);
		Pizza pizza_semPreco = new Pizza(4L, "Margerita", "Mussarela, tomate e manjericao", null);
		
		assertThrows(
				PizzaIncompleteBodyException.class,
				() -> service.save(pizza_semNome) 
		);
		assertThrows(
				PizzaIncompleteBodyException.class,
				() -> service.save(pizza_semDescricao) 
		);
		assertThrows(
				PizzaIncompleteBodyException.class,
				() -> service.save(pizza_semPreco) 
		);
	}
	
	@Test
	void testSave_shouldThrowExceptionIfPizzaAlreadyExists() {
		when(repository.findByNome(pizzaA.getNome())).thenReturn(pizzaA);
		
		assertThrows(
				PizzaAlreadyExistsException.class,
				() -> service.save(pizzaA) 
		);
	}
	
	@Test
	void testSave_shouldCallRepositoryWhenCompleteBody() {
		when(repository.save(pizzaA)).thenReturn(pizzaA);
		
		service.save(pizzaA);
		
		verify(repository).save(pizzaA);
	}
	
	@Test
	void testUpdate_shouldThrowExceptionIfIncompleteBodyOrNoId() {
		Pizza pizza_semNome = new Pizza(4L, null, "Mussarela, tomate e manjericao", 25.0);
		Pizza pizza_semDescricao = new Pizza(4L, "Margerita", null, 25.0);
		Pizza pizza_semPreco = new Pizza(4L, "Margerita", "Mussarela, tomate e manjericao", null);
		
		assertThrows(
				PizzaIncompleteBodyException.class,
				() -> service.update(1L, pizza_semNome) 
		);
		assertThrows(
				PizzaIncompleteBodyException.class,
				() -> service.update(1L, pizza_semDescricao) 
		);
		assertThrows(
				PizzaIncompleteBodyException.class,
				() -> service.update(1L, pizza_semPreco) 
		);
		
		// sem Id
		assertThrows(
				PizzaIncompleteBodyException.class,
				() -> service.update(null, pizzaA) 
		);
	}

	@Test
	void testUpdate_shouldFindExistingPizzaById() {
		Pizza updatePizza =
				new Pizza(null, "Update nome", "update descricao", 90.0);
		
		when(repository.findById(1L)).thenReturn(Optional.of(pizzaA));
		
		Pizza foundPizza = service.update(1L, updatePizza);
		
		assertEquals(1L, foundPizza.getId());
	}
	
	@Test
	void testUpdate_shouldThrowExceptionIfIdNotFound() {
		Pizza updatePizza =
				new Pizza(null, "Update nome", "update descricao", 90.0);
		
		assertThrows(
				PizzaNotFoundException.class,
				() -> service.update(3L, updatePizza)
		);
	}
	
	@Test
	void testUpdate_shouldReturnUpdatePizzaWhenFoundAndComplete() {
		Pizza updatePizza =
				new Pizza(1L, "Update nome", "update descricao", 90.0);
		
		when(repository.findById(pizzaA.getId())).thenReturn(Optional.of(pizzaA));
		when(repository.save(updatePizza)).thenReturn(updatePizza);
		
		Pizza updated = service.update(pizzaA.getId(), updatePizza);
		
		assertEquals(updatePizza, updated);
	}
	
	@Test
	void testDelete_shouldThrowExceptionIfIdNotExists() {
		assertThrows(
				PizzaNotFoundException.class,
				() -> service.delete(13L)
		);
	}

	@Test
	void testDelete_shouldCallDeleteMethodIfIdExists() {
		when(repository.findById(1L)).thenReturn(Optional.of(pizzaA));
		
		service.delete(1L);
		
		verify(repository).delete(pizzaA);
	}

}
