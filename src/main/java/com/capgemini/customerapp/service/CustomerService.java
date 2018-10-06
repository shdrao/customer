package com.capgemini.customerapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exception.AuthenticationFailedException;
import com.capgemini.customerapp.exception.CustomerExistsException;
import com.capgemini.customerapp.exception.CustomerNotFoundException;

@Service
public interface CustomerService {

	public Customer authentication(Customer customer) throws CustomerNotFoundException, AuthenticationFailedException;//

	public Customer addCustomer(Customer customer) throws CustomerExistsException;//

	public Customer getCustomerById(int customerId) throws CustomerNotFoundException;//

	public List<Customer> getAllCustomers();//

	public void deleteCustomer(Customer customer) throws CustomerNotFoundException;//

	public Customer editCustomer(Customer customer) throws CustomerNotFoundException;//

}
