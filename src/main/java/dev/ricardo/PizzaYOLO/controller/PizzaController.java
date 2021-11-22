package dev.ricardo.PizzaYOLO.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ricardo.PizzaYOLO.entity.Pizza;
import dev.ricardo.PizzaYOLO.service.PizzaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/pizza")
@CrossOrigin
@Api(value = "Pizzaria PizzaYOLO")
public class PizzaController {
	
	private PizzaService service;
	
	@Autowired
	public PizzaController(PizzaService service) {
		this.service = service;
	}
	
	@ApiOperation(value = "Retorna uma lista paginada de Pizzas")
	@GetMapping
	public ResponseEntity<List<Pizza>> findAll(Pageable pageable) {
		Page<Pizza> page = service.findAll(pageable);
		
		List<Pizza> pizzaList = page.getContent();
		
		return new ResponseEntity<>(pizzaList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna uma pizza com o id enviado")
	@GetMapping("/{id}")
	public ResponseEntity<Pizza> findById(@PathVariable("id") Long id) {
			Pizza pizza = service.findById(id);
			return new ResponseEntity<>(pizza, HttpStatus.OK);
			
	}
	
	@ApiOperation(value = "Cria uma nova pizza com os dados enviados no corpo da requisição")
	@PostMapping
	public ResponseEntity<Pizza> save(@RequestBody Pizza newPizza) {
		Pizza pizza = service.save(newPizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Edita os dados da pizza com id igual ao passado na url")
	@PutMapping("/{id}")
	public ResponseEntity<Pizza> update(
			@PathVariable("id") Long id, 
			@RequestBody Pizza upudatePizza) {
		Pizza pizza = service.update(id, upudatePizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(value = "Deleta a pizza com o id passado na url")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		service.delete(id);
		
        return new ResponseEntity<String>(
        		"Pizza com id " + id + " deletada com sucesso.", 
        		HttpStatus.ACCEPTED);
	}

}
