package com.avisys.cim.service;

import java.util.List;

import com.avisys.cim.pojos.Customer;

public interface ICustomerService {
	List<Customer> fetchAll();

	public List<Customer> fetchByParameters(String firstName, String lastName, String mobileNumber);

	public Customer createCustomer(Customer customer);

	public void delCustomerByMobNo(String mobNo);

}
