package com.hdt.example_assess.respository;

import com.hdt.example_assess.entity.Kind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindRepository extends JpaRepository<Kind,Integer> {
}
