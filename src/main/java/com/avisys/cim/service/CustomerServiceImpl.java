package com.avisys.cim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.custome_Exceptions.CustomerNotFoundException;
import com.avisys.cim.custome_Exceptions.MobileNumberAlreadyExistsException;
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

		if (firstName != null && lastName != null && mobileNumber != null) {

			// with JPQL
			// return customerRepo.getAllParameter(firstName, lastName, mobileNumber);

			// with derived query
			Customer customer = customerRepo
					.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndMobileNumber(firstName,
							lastName, mobileNumber);

			return List.of(customer);
		}
		// for fetching by mobileNumber
		else if (mobileNumber != null && firstName == null && lastName == null) {
			Customer customer = customerRepo.findByMobileNumber(mobileNumber);
			return List.of(customer);
		}
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
				throw new MobileNumberAlreadyExistsException(
						"Unable to create Customer. Mobile number already present.");
		}

		// adding customer to database
		return customerRepo.save(customer);

	}

	// delete customer by mobileNumber
	@Override
	public void delCustomerByMobNo(String mobNo) {
		Customer customerToDel = customerRepo.findByMobileNumber(mobNo);

		if (customerToDel != null) {
			customerRepo.delete(customerToDel);
		} else {
			throw new CustomerNotFoundException("Customer with mobile number " + mobNo + " not found.");
		}
	}

	// update mobile number
	@Override
	public Customer updateMobileNumber(String oldNo, String newNo) {
		Customer customerToUpdate = customerRepo.findByMobileNumber(oldNo);

		if (customerToUpdate == null)
			throw new CustomerNotFoundException("Customer with mobile number not found.");

		else if (customerRepo.existsCustomerByMobileNumber(newNo))
			throw new MobileNumberAlreadyExistsException("Mobile number " + newNo + " already present.");

		else
			customerToUpdate.getMobileNumber().remove(oldNo);
		customerToUpdate.getMobileNumber().add(newNo);

		return customerRepo.save(customerToUpdate);
	}

	// delete mobile number
	@Override
	public Customer delMobileNumber(String mobNo) {
		Customer customerToUpdate = customerRepo.findByMobileNumber(mobNo);

		if (customerToUpdate == null)
			throw new CustomerNotFoundException("Customer with mobile number not found.");

		else
			customerToUpdate.getMobileNumber().remove(mobNo);

		return customerRepo.save(customerToUpdate);
	}

}
