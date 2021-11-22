package dev.ricardo.PizzaYOLO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ricardo.PizzaYOLO.entity.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long>{
	
	Pizza findByNome(String nome);

}
