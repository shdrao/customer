package com.capgemini.customerapp.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exception.CustomerNotFoundException;
import com.capgemini.customerapp.repository.CustomerRepository;
import com.capgemini.customerapp.service.impl.CustomerserviceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ServiceTest {
	
	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerserviceImpl customerService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAuthenticateWhichreturnscustomer() {
		Customer customer= new Customer(1, null, "12", null, null);
		Customer customer1= new Customer(1, "A", "12", "XYZ", "a@a");
		Optional<Customer> optionalProduct = Optional.of(customer1);
		when(customerRepository.findById(customer.getCustomerId())).thenReturn(optionalProduct);
		assertEquals(customer1, customerService.authentication(customer));
	}
	
//	@Test
//	public void testFindCustomerByIdWhichReturnsCustomer() throws CustomerNotFoundException {
//		Customer customer1= new Customer(1, "A", "12", "XYZ", "a@a");
//		Optional<Customer> optionalProduct = Optional.of(customer1);
//		when(customerRepository.findById(1)).thenReturn(optionalProduct);
//		assertEquals(customer1, customerService.getCustomerById(1));
//	}
	
//	@Test
//	public void testAddCustomerWhichReturnsCustomer() {
//		Customer customer1= new Customer(1, "A", "12", "XYZ", "a@a");
//		when(customerRepository.save(customer1)).thenReturn(customer1);
//		assertEquals(customer1, customerService.addCustomer(customer1));
//	}
	
//	@Test
//	public void testDeleteCustomer() throws CustomerNotFoundException {
//		Customer customer1= new Customer(1, "A", "12", "XYZ", "a@a");
//		Optional<Customer> op = Optional.of(customer1);
//		when(customerRepository.findById(customer1.getCustomerId())).thenReturn(op);
//		customerService.deleteCustomer(customer1);		
//		verify(customerRepository, times(1)).delete(customer1);
//	}
	
	@Test
	public void testUpdateCustomerWhichReturnsProduct() {
		Customer customer1= new Customer(1, "A", "12", "XYZ", "a@a");
		Optional<Customer> op = Optional.of(customer1);
		when(customerRepository.findById(customer1.getCustomerId())).thenReturn(op);
		when(customerRepository.save(customer1)).thenReturn(customer1);
		assertEquals(customer1, customerService.editCustomer(customer1));
	}

	
	

}
