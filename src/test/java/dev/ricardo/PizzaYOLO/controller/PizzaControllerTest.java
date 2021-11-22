package dev.ricardo.PizzaYOLO.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.ricardo.PizzaYOLO.entity.Pizza;
import dev.ricardo.PizzaYOLO.service.PizzaService;

class PizzaControllerTest {
	
	PizzaService service = mock(PizzaService.class);
	PizzaController controller = new PizzaController(service);
	
	Pizza pizzaA = new Pizza(1L, "Toscana", "Calabresa e mussarela", 28.0);
	Pizza pizzaB = new Pizza(2L, "Escarola", "Escarola refogada com alho frito e mussarela", 25.0);
	Pizza pizzaC = new Pizza(3L, "Atum", "Atum com vinagrete, cebola", 23.0);
	List<Pizza> pizzaList = Arrays.asList(pizzaA, pizzaB, pizzaC);
	Page<Pizza> pizzaPage = new PageImpl<>(pizzaList);
	Pageable pageable = PageRequest.of(0, 3);

	@Test
	void testFindAll_shouldCallPizzaService() {
		when(service.findAll(pageable)).thenReturn(pizzaPage);
		
		controller.findAll(pageable);
		
		verify(service).findAll(pageable);
	}

	@Test
	void testFindAll_shouldReturnListOfPizzaAndStatusOK() {
		ResponseEntity<List<Pizza>> expected = 
				new ResponseEntity<>(pizzaList, HttpStatus.OK);
		
		when(service.findAll(pageable)).thenReturn(pizzaPage);
		
		ResponseEntity<List<Pizza>> response = controller.findAll(pageable);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testFindById_shouldCallPizzaService() {
		when(service.findById(1L)).thenReturn(pizzaA);
		
		controller.findById(1L);
		
		verify(service).findById(1L);
	}

	@Test
	void testFindById_ShouldReturnResponseEntityOKWhenFound() {
		when(service.findById(1L)).thenReturn(pizzaA);
		
		ResponseEntity<Pizza> expected = new ResponseEntity<>(pizzaA, HttpStatus.OK);
		ResponseEntity<Pizza> response = controller.findById(1L);
		
		assertEquals(expected, response);
	}

	@Test
	void testSave_shouldCallPizzaServiceSaveMethod() {
		when(service.save(pizzaA)).thenReturn(pizzaA);
		
		controller.save(pizzaA);
		
		verify(service).save(pizzaA);
	}
	
	@Test
	void testSave_shouldReturnResponseEntityCREATED() {
		when(service.save(pizzaA)).thenReturn(pizzaA);
		
		ResponseEntity<Pizza> expected = 
				new ResponseEntity<Pizza>(pizzaA, HttpStatus.CREATED);
		ResponseEntity<Pizza> response = controller.save(pizzaA);
		
		assertEquals(expected, response);
	}
	
	@Test
	void testUpdate_shouldCallPizzaServiceUpdateMethod() {
		when(service.update(1L, pizzaA)).thenReturn(pizzaA);
		
		controller.update(1L, pizzaA);
		
		verify(service).update(1L, pizzaA);
	}

	@Test
	void testUpdate_shouldReturnResponseEntityACCEPTED() {
		when(service.update(1L, pizzaA)).thenReturn(pizzaA);
		
		ResponseEntity<Pizza> expected = 
				new ResponseEntity<Pizza>(pizzaA, HttpStatus.ACCEPTED);
		ResponseEntity<Pizza> response = controller.update(1L, pizzaA);
		
		assertEquals(expected, response);
	}
	
	@Test	
	void testDelete_shouldCallPizzaServiceDeleteMethod() {
		controller.delete(1L);
		
		verify(service).delete(1L);
	}

	@Test
	void testDelete_shouldReturnResponseEntityACCEPTED() {
		Long pizzaId = 1L;
		ResponseEntity<String> expected = new ResponseEntity<String>(
				"Pizza com id " + pizzaId + " deletada com sucesso.", 
        		HttpStatus.ACCEPTED);
		
		ResponseEntity<String> response = controller.delete(pizzaId);
		
		assertEquals(expected, response);
	}

}
