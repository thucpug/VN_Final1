package com.hdt.example_assess.respository;

import com.hdt.example_assess.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguagesRepository extends JpaRepository<Languages,Integer> {
}
