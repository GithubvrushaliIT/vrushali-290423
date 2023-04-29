package com.avisys.cim.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avisys.cim.pojos.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// JPQL for find customers by firstname,lastname,mobilenumber

//	@Query("select c from Customer c where mobileNumber=:mob and upper(c.firstName) like upper(concat('%', :firstName,'%')) and upper(c.lastName) like upper(concat('%', :lastName,'%'))")
//	public List<Customer> getAllParameter(@Param(value = "firstName") String firstName,
//			@Param(value = "lastName") String lastName, @Param(value = "mob") String mob);

	// Data JPA query for find customers by firstname,lastname,mobilenumber

	public List<Customer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndMobileNumber(
			String firstName, String lastName, String mobileNumber);

	public List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

	public List<Customer> findByLastNameContainingIgnoreCase(String lastName);

	public List<Customer> findByMobileNumber(String mobileNumber);

	// check if a mobileNumber is exist or not
	boolean existsCustomerByMobileNumber(String mobileNumber);
}
