package com.binu.proj1.repository;

import com.binu.proj1.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT u FROM Person u WHERE u.first_name = ?1 ")
    Person findByName(String name);

    @Query(value = "SELECT * FROM Person u WHERE u.first_name = :first_name",nativeQuery = true)
    Person findByName2(@Param("first_name") String first_name);

}


