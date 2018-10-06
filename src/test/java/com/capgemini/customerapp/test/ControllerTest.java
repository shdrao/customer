package com.capgemini.customerapp.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.customerapp.controller.CustomerController;
import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.service.impl.CustomerserviceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ControllerTest {

	@Mock
	private CustomerserviceImpl customerService;

	@InjectMocks
	private CustomerController customerController;

	MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddCustomerWhichReturnsCustomer() throws Exception {
		Customer customer = new Customer(123, "ABC", "12", "XYZ", "a@a");
		when(customerService.addCustomer(Mockito.isA(Customer.class))).thenReturn(customer);
		mockMvc.perform(post("/cust").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\r\n" + 
				"        \"customerId\": 123,\r\n" + 
				"        \"customerName\": \"ABC\",\r\n" + 
				"        \"customerPassword\": \"12\",\r\n" + 
				"        \"customerAddress\": \"XYZ\",\r\n" + 
				"        \"customerEmail\": \"a@a\"\r\n" + 
				"    }")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId").value("123")).andDo(print());
	}

	@Test
	public void testEditCustomerWhichreturnsCustomer() throws Exception {
		Customer customer = new Customer(123, "ABC", "12", "XYZ", "a@a");
		when(customerService.getCustomerById(Mockito.isA(Integer.class))).thenReturn(customer);
		when(customerService.editCustomer(Mockito.isA(Customer.class))).thenReturn(customer);
		mockMvc.perform(post("/edit").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\r\n" + 
				"        \"customerId\": 1,\r\n" + 
				"        \"customerName\": \"ABC\",\r\n" + 
				"        \"customerPassword\": \"12\",\r\n" + 
				"        \"customerAddress\": \"X\",\r\n" + 
				"        \"customerEmail\": \"m@m\"\r\n" + 
				"    }").accept(MediaType.APPLICATION_JSON_UTF8))
		.andDo(print())
		.andExpect(status().isOk()).andExpect(jsonPath("$.customerName").value("ABC")).andDo(print());
		}

	@Test
	public void testGetCustomerByIdWhichReturnsCustomer() throws Exception {
		Customer customer = new Customer(123, "ABC", "12", "XYZ", "m@m");
		when(customerService.getCustomerById(Mockito.isA(Integer.class))).thenReturn(customer);
		mockMvc.perform(
				get("/cust/1").contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.customerEmail").value("m@m"));
	}

	@Test
	public void testDeleteWhichReturnsCustomer() {
		Customer customer = new Customer(123, "ABC", "12", "XYZ", "m@m");
		customerService.deleteCustomer(customer);
		verify(customerService, times(1)).deleteCustomer(customer);
	}

	@Test
	public void testAuthenticateWhichreturnsCustomer() throws Exception {
		Customer customer = new Customer(123, null, "12", null, null);
		Customer customer1 = new Customer(123, "ABC", "12", "XYZ", "m@m");
		when(customerService.authentication(Mockito.isA(Customer.class))).thenReturn(customer1);
		mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\r\n" + "    \"customerId\": 1,\r\n" + "    \"customerPassword\": \"12\"\r\n" + "}")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerEmail").value("m@m"));
	}
	
//	@Test
//	public void testListAllCustomersWhichReturnsList() {
//		mockMvc.perform(get("/all").accept(MediaType.APPLICATION_PROBLEM_JSON_UTF8))
//		.andExpect(jsonPath("$".).andDo(print());
//	}
	

}
