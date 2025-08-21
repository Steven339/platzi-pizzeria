package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.pizzaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza) {
        if(pizza.getId() == null || !this.pizzaService.exists(pizza.getId())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if(pizza.getId() != null && this.pizzaService.exists(pizza.getId())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(this.pizzaService.exists(id)) {
            this.pizzaService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
