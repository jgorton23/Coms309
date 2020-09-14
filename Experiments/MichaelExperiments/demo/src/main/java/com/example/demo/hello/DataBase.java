package com.example.demo.hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBase extends JpaRepository<LocalDB, Integer>{

	LocalDB findOne(Integer id);

}
