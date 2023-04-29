package com.avisys.cim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.custome_Exceptions.MobileNumberAlreadyExitException;
import com.avisys.cim.dao.CustomerRepository;
import com.avisys.cim.pojos.Customer;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerRepository customerRepo;

	// for getting all the customers
	@Override
	public List<Customer> fetchAll() {
		return customerRepo.findAll();
	}

	// get the customer by parameters

	public List<Customer> fetchByParameters(String firstName, String lastName, String mobileNumber) {

		// for fetching by all parameters

		if (firstName != null && lastName != null && mobileNumber != null)

			// with JPQL
			// return customerRepo.getAllParameter(firstName, lastName, mobileNumber);

			// with derived query
			return customerRepo.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndMobileNumber(
					firstName, lastName, mobileNumber);

		// for fetching by mobileNumber
		else if (mobileNumber != null && firstName == null && lastName == null)
			return customerRepo.findByMobileNumber(mobileNumber);

		// for fetching by firstName
		else if (firstName != null && mobileNumber == null && lastName == null)
			return customerRepo.findByFirstNameContainingIgnoreCase(firstName);

		// for fetching by lastName
		else if (lastName != null && mobileNumber == null && firstName == null)
			return customerRepo.findByLastNameContainingIgnoreCase(lastName);
		return null;

	}

	// create customer if mobile number is not present
	public Customer createCustomer(Customer customer) {

		// checking if any of the mobileNumber already exists
		for (String mobNo : customer.getMobileNumber()) {
			if (customerRepo.existsCustomerByMobileNumber(mobNo))
				// exception thrown
				throw new MobileNumberAlreadyExitException("Unable to create Customer. Mobile number already present.");
		}

		// adding customer to database
		return customerRepo.save(customer);

	}

}
