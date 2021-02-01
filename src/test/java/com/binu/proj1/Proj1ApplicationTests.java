package com.binu.proj1;


import com.binu.proj1.entity.Person;
import com.binu.proj1.repository.AddressRepository;
import com.binu.proj1.repository.PersonRepository;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.PersistenceContext;

import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Proj1ApplicationTests {

	private static final String API_ROOT = "http://localhost:8080";

	@Autowired
	PersonRepository personRepository;

	@Autowired
	AddressRepository addressRepository;

	@Test
	public void checkMainPage() {
		final Response response = RestAssured.get(API_ROOT+"/index");
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void addPerson() {
		final Person person = createRandomPerson();

		final Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("first_name", person.getFirst_name())
				.param("last_name", person.getLast_name())
				.post(API_ROOT+"/addperson");

		//assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals("302", ""+response.getStatusCode());

		// Person personFound = personRepository.findByName(person.getFirst_name());
		//Person personFound4 = personRepository.findByName2(person.getFirst_name());

	}


	private Person createRandomPerson() {
		final Person person = new Person();
		Random rand = new Random();
		int randomNum = rand.nextInt((100 - 10) + 1) + 10;
		person.setPerson_id(randomNum);
		person.setFirst_name("first"+randomNum);
		person.setLast_name("second"+randomNum);
		return person;
	}

}