package com.learning.coronaHelpApi.repositories;

import com.learning.coronaHelpApi.models.HelpSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HelpSeekerJpaRepository extends JpaRepository<HelpSeeker, Integer> {
    List<HelpSeeker> findByAddressCity(String city);
    List<HelpSeeker> findByBirthDate(Date birthdate);
    HelpSeeker findById(Long id);
    void deleteById(Long id);
}

