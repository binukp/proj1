package com.binu.proj1.repository;

import com.binu.proj1.entity.Address;

import com.binu.proj1.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "SELECT u FROM Address u WHERE u.person = :person_id")
    List<Address> findAAllAddressByPersonID(@Param("person_id") Person person_id);


}


