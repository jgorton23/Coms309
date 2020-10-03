package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.*;


@Repository
public interface MyDatabase extends JpaRepository<Person, Integer> {
	@Query(value = "select p from person where p.id = ?1", nativeQuery = true)
	Person findById(long id);
}