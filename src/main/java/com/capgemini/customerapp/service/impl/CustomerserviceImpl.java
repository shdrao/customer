package com.capgemini.customerapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exception.AuthenticationFailedException;
import com.capgemini.customerapp.exception.CustomerExistsException;
import com.capgemini.customerapp.exception.CustomerNotFoundException;
import com.capgemini.customerapp.repository.CustomerRepository;
import com.capgemini.customerapp.service.CustomerService;

@Service
public class CustomerserviceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer authentication(Customer customer) throws CustomerNotFoundException, AuthenticationFailedException {
		Optional<Customer> customer1 = customerRepository.findById((int) customer.getCustomerId());
		if (!customer1.isPresent())
			throw new CustomerNotFoundException("Customer Not found");
		if (customer1.isPresent()) {
			if (customer1.get().getCustomerPassword().equals(customer.getCustomerPassword()))
				return customer1.get();
		}
		throw new AuthenticationFailedException("Login Failed Try again");
	}

	@Override
	public Customer getCustomerById(int customerId) throws CustomerNotFoundException {
		Optional<Customer> customer1 = customerRepository.findById(customerId);
		if (!customer1.isPresent())
			throw new CustomerNotFoundException("Customer Not found");
		return customer1.get();
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void deleteCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> customer1 = customerRepository.findById((int) customer.getCustomerId());
		if (!customer1.isPresent())
			throw new CustomerNotFoundException("Customer Not found");
		customerRepository.delete(customer);
	}

	@Override
	public Customer editCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> customer1 = customerRepository.findById((int) customer.getCustomerId());
		if (!customer1.isPresent())
			throw new CustomerNotFoundException("Customer Not found");
		return customerRepository.save(customer);
	}

	@Override
	public Customer addCustomer(Customer customer) throws CustomerExistsException {
		Optional<Customer> customer1 = customerRepository.findById((int) customer.getCustomerId());
		if (customer1.isPresent())
			throw new CustomerExistsException("Customer Already Exists");
		return customerRepository.save(customer);

	}

}
