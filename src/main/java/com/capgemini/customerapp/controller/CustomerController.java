package com.capgemini.customerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/cust")//
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer> responseEntity;
		responseEntity = new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping("/edit")//
	public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer) {

		Customer customer1 = customerService.getCustomerById(customer.getCustomerId());
		if (customer1 != null)
			return new ResponseEntity<Customer>(customerService.editCustomer(customer), HttpStatus.OK);

		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/cust/{customerId}")//
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
		Customer customer1 = customerService.getCustomerById(customerId);
		if (customer1 != null)
			return new ResponseEntity<Customer>(customerService.getCustomerById(customerId), HttpStatus.OK);
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/{customerId}")//
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) {
		Customer customer1 = customerService.getCustomerById(customerId);
		if (customer1 != null) {
			customerService.deleteCustomer(customer1);
			return new ResponseEntity<Customer>(HttpStatus.OK);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/login")//
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody Customer customer) {
		Customer customer1 = customerService.authentication(customer);
		if (customer1 != null)
			return new ResponseEntity<Customer>(customerService.authentication(customer), HttpStatus.OK);
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/all")//
	public ResponseEntity<List> listAllCustomers() {
		List<Customer> list = customerService.getAllCustomers();
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}

}
