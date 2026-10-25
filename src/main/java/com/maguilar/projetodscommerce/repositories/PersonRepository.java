package com.maguilar.projetodscommerce.repositories;

import com.maguilar.projetodscommerce.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
