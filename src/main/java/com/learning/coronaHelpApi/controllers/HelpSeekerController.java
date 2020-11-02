package com.learning.coronaHelpApi.controllers;

import com.learning.coronaHelpApi.models.HelpSeeker;
import com.learning.coronaHelpApi.repositories.HelpSeekerJpaRepository;
import com.learning.coronaHelpApi.services.HelpSeekerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import javax.validation.Valid;
;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/coronaHelpApi/v1/helpSeekers")
public class HelpSeekerController {

    private final HelpSeekerJpaRepository repository;
    private final HelpSeekerService service;

    public HelpSeekerController(HelpSeekerJpaRepository repository, HelpSeekerService service) {
        this.repository = repository;
        this.service =service;
    }

    @GetMapping
    public ResponseEntity<List<HelpSeeker>> getHelpSeekers() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(params = "city", value = "")
    public ResponseEntity<List<HelpSeeker>> getHelpSeekersByCity(@RequestParam String city) {
        return new ResponseEntity<>(repository.findByAddressCity(city), HttpStatus.OK);
    }

    // @todo werkt niet ...
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @RequestMapping(params = "birthDate", value = "")
    public ResponseEntity<List<HelpSeeker>> getHelpSeekersByBirthDate(@RequestParam   Date birthDate) {
        return new ResponseEntity<>(repository.findByBirthDate(birthDate), HttpStatus.OK);
    }


    @RequestMapping(params = "age", value = "")
    public ResponseEntity<List<HelpSeeker>> getHelpSeekersByAge(@RequestParam Integer age) {
        return new ResponseEntity<>(service.findByAge(age), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<HelpSeeker> getHelpSeeker(@PathVariable Long id) {
        return  new ResponseEntity<>(service.getHelpSeeker(id), HttpStatus.OK);
    }

    // @todo error handling met fielderrors werkt niet...
    //@todo krijg many to many niet opgeslagen in database
    @PostMapping
    public ResponseEntity<HelpSeeker>  createHelpSeeker(@Valid @RequestBody final HelpSeeker helpSeeker){
        return new ResponseEntity<>(repository.saveAndFlush(helpSeeker), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<HelpSeeker> updateHelpSeeker(@RequestBody HelpSeeker helpSeeker) {
        return service.updateHelpSeeker(helpSeeker);
    }


    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<String> deleteHelpSeeker(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("HelpSeeker with id " + id + " is deleted", HttpStatus.OK);
    }



}
