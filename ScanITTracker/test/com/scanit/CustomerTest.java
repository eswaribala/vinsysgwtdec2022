package com.scanit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.scanit.server.models.Customer;
import com.scanit.server.models.Gender;

class CustomerTest {
	
	private static Customer customer;
	
	@BeforeAll
	public static void createCustomer() {
		customer=new Customer();
		customer.setDob(new Date(70,11,2));	
		customer.setGender(Gender.FEMALE);
	}
	

	@Test
	void testGetDob() {
	   assertTrue(customer.getDob().getYear()<new Date().getYear());
	}

	@Test
	void testCustomerGender() {
		//fail("Not yet implemented");
		assertEquals(Gender.MALE,customer.getGender());
	}

}
