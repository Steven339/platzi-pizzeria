package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import com.platzi.pizza.web.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        log.info("Fetching all pizzas");
        List<PizzaEntity> pizzas = pizzaService.getAll();
        log.debug("Found {} pizzas", pizzas.size());
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable Long id) {
        log.info("Fetching pizza with id: {}", id);
        if(!pizzaService.exists(id)) {
            log.error("Pizza not found with id: {}", id);
            throw new ResourceNotFoundException("Pizza not found with id: " + id);
        }
        log.debug("Found pizza with id: {}", id);
        return ResponseEntity.ok(pizzaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza) {
        log.info("Creating new pizza: {}", pizza);
        if (pizza.getId() != null && pizzaService.exists(pizza.getId())) {
            log.warn("Attempted to create a pizza with existing ID: {}", pizza.getId());
            throw new IllegalArgumentException("A new pizza cannot already have an ID");
        }
        PizzaEntity savedPizza = pizzaService.save(pizza);
        log.info("Created pizza with id: {}", savedPizza.getId());
        return new ResponseEntity<>(savedPizza, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        log.info("Updating pizza with id: {}", pizza.getId());
        if (pizza.getId() == null) {
            log.error("Attempted to update a pizza without ID");
            throw new IllegalArgumentException("Pizza ID cannot be null for update");
        }
        if (!pizzaService.exists(pizza.getId())) {
            log.error("Pizza not found for update with id: {}", pizza.getId());
            throw new ResourceNotFoundException("Pizza not found with id: " + pizza.getId());
        }
        PizzaEntity updatedPizza = pizzaService.save(pizza);
        log.info("Updated pizza with id: {}", updatedPizza.getId());
        return ResponseEntity.ok(updatedPizza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting pizza with id: {}", id);
        if (!pizzaService.exists(id)) {
            log.error("Pizza not found for deletion with id: {}", id);
            throw new ResourceNotFoundException("Pizza not found with id: " + id);
        }
        pizzaService.delete(id);
        log.info("Deleted pizza with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}