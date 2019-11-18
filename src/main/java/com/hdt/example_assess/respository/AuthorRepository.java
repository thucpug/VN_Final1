package com.hdt.example_assess.respository;

import com.hdt.example_assess.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findOneByAuthorName(String name);
}
