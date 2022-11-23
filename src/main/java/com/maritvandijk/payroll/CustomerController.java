package com.maritvandijk.payroll;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CustomerController {

    private final CustomerRepository repository;

    private final CustomerAssembler assembler;

    CustomerController(CustomerRepository repository, CustomerAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/customers")
    CollectionModel<EntityModel<Customer>> all() {

        List<EntityModel<Customer>> customers = repository.findAll()
                .stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(customers,
                linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }

    @PostMapping("/customers")
    ResponseEntity<?> newCustomer(@RequestBody Customer newCustomer) {
        EntityModel<Customer> entityModel = assembler.toModel(repository.save(newCustomer));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/customers/{customerId}")
    EntityModel<Customer> one(@PathVariable Long customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        return assembler.toModel(customer);
    }

    @PutMapping("/customers/{id}")
    ResponseEntity<?> replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        Customer updatedCustomer = repository.findById(id)
                .map(customer -> {
                    customer.setCustomerName(newCustomer.getCustomerName());
                    customer.setCompanyName(newCustomer.getCompanyName());
                    return repository.save(customer);
                }).orElseGet(() -> {
                    newCustomer.setCustomerId(id);
                    return repository.save(newCustomer);
                });

        EntityModel<Customer> entityModel = assembler.toModel(updatedCustomer);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/customers/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
