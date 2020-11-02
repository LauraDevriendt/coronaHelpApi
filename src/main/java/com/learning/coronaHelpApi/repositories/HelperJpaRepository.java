package com.learning.coronaHelpApi.repositories;

import com.learning.coronaHelpApi.models.Helper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelperJpaRepository  extends JpaRepository<Helper,Integer> {
Helper findById(Long id);
void deleteById(Long id);
}
