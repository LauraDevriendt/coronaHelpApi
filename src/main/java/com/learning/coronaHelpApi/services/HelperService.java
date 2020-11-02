package com.learning.coronaHelpApi.services;

import com.learning.coronaHelpApi.Exceptions.HelperNotFoundException;
import com.learning.coronaHelpApi.models.Helper;
import com.learning.coronaHelpApi.repositories.HelperJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HelperService {

    private final HelperJpaRepository repository;

    public HelperService(HelperJpaRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Helper> updateHelper(Helper helper) {
       Helper foundHelper =  repository.findById(helper.getId());
       if(foundHelper == null){
           throw new HelperNotFoundException("Can't update because helper isn't found in the database.", "Check if you filled in the right Id");
       } else {
          return new ResponseEntity<>( repository.save(helper), HttpStatus.OK);
       }
    }

    public Helper getHelper(Long id) {
        Helper helper = repository.findById(id);
        if(helper == null){
            throw new HelperNotFoundException("Can't find helper in database", "Check if you filled in the right Id");
        } else {
          return helper;
        }
    }
}
