package com.learning.coronaHelpApi.controllers;

import com.learning.coronaHelpApi.models.Helper;
import com.learning.coronaHelpApi.repositories.HelperJpaRepository;
import com.learning.coronaHelpApi.services.HelperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/coronaHelpApi/v1/helpers")
public class HelperController {

    private final HelperJpaRepository repository;
    private final HelperService service;

    public HelperController(HelperJpaRepository repository, HelperService service) {
        this.repository = repository;
        this.service =service;
    }

    @GetMapping
    public ResponseEntity<List<Helper>> getHelpers() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Helper> getHelper(@PathVariable Long id) {
        return  new ResponseEntity<>(service.getHelper(id), HttpStatus.OK);
    }

    // @todo error handling met fielderrors werkt niet... en de rest ook niet echt
    @PostMapping
    public ResponseEntity<Helper>  createHelper(@Valid @RequestBody final Helper helper){
        return new ResponseEntity<>(repository.saveAndFlush(helper), HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Helper> updateHelper(@RequestBody Helper helper) {
        return service.updateHelper(helper);
    }



    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<String> deleteHelper(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Helper with id" + id + " is deleted", HttpStatus.OK);
    }



}
