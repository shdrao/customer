package com.capgemini.customerapp.client;

import org.springframework.web.client.RestTemplate;

import com.capgemini.customerapp.entity.Customer;

public class Client {
	public static final RestTemplate REST_TEMPLET = new RestTemplate();
	public static final String baseUrl = "http://localhost:8080/";

	public static void main(String[] args) {
		String url = baseUrl + "cust";
		Customer customer1 = new Customer(1, "A", "12", "XYZ", "a@a");
		//customer1 = addCustomer(url, customer1);
		System.out.println(customer1);
		url = baseUrl + "delete/1";
		deleteCustomer(url);
	}

	private static void deleteCustomer(String url) {
		// TODO Auto-generated method stub
		REST_TEMPLET.delete(url);
	}

	private static Customer addCustomer(String url, Customer customer) {
		// TODO Auto-generated method stub
		return REST_TEMPLET.postForObject(url, customer, Customer.class);
	}

}
