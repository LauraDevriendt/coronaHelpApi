package com.learning.coronaHelpApi.services;

import com.learning.coronaHelpApi.Exceptions.HelperNotFoundException;
import com.learning.coronaHelpApi.models.HelpSeeker;
import com.learning.coronaHelpApi.models.Helper;
import com.learning.coronaHelpApi.repositories.HelpSeekerJpaRepository;
import com.learning.coronaHelpApi.repositories.HelperJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HelpSeekerService {

        private final HelpSeekerJpaRepository repository;

    public HelpSeekerService(HelpSeekerJpaRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<HelpSeeker> updateHelpSeeker(HelpSeeker helpSeeker) {
       HelpSeeker foundHelpSeeker =  repository.findById(helpSeeker.getId());
       if(foundHelpSeeker == null){
           throw new HelperNotFoundException("Can't update because helpSeeker isn't found in the database.", "Check if you filled in the right Id");
       } else {
          return new ResponseEntity<>( repository.save(helpSeeker), HttpStatus.OK);
       }
    }

    public HelpSeeker getHelpSeeker(Long id) {
        HelpSeeker helpSeeker = repository.findById(id);
        if(helpSeeker == null){
            throw new HelperNotFoundException("Can't find helpSeeker in database", "Check if you filled in the right Id");
        } else {
          return helpSeeker;
        }
    }

    // @todo snap berekening van calendar niet zo goed
    public List<HelpSeeker> findByAge(Integer age) {

        List<HelpSeeker> helpSeekers = repository.findAll();
        List<HelpSeeker> helpSeekersWithSameAge = new ArrayList<>();
        helpSeekers.forEach(helpSeeker -> {
           int helpSeekerAge = calculateAge(helpSeeker.getBirthDate());
            if(helpSeekerAge == age){
                helpSeekersWithSameAge.add(helpSeeker);
            }
        });
         return helpSeekersWithSameAge;
    }

    private int calculateAge(Date birthDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int date = calendar.get(Calendar.DATE);
        LocalDate birthDayLocal = LocalDate.of(year,month,date);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(birthDayLocal, now);

        return diff.getYears();
    }
}
