package com.avisys.cim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.cim.service.ICustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	public CustomerController() {
		System.out.println("in ctor of : " + getClass().getName());
	}

	@Autowired
	private ICustomerService customerService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllCustomers() {

		return new ResponseEntity<>(customerService.fetchAll(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getByParameter(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) String mobileNumber) {
		return new ResponseEntity<>(customerService.fetchByParameters(firstName, lastName, mobileNumber),
				HttpStatus.OK);
	}
	/*
	 * @PostMapping("/add") public ResponseEntity<?> addCustomer(@RequestBody
	 * Customer customer) {
	 * 
	 * try { return new ResponseEntity<>(customerService.createCustomer(customer),
	 * HttpStatus.CREATED); } catch (MobileNumberAlreadyExitException e) { return
	 * new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */
}
