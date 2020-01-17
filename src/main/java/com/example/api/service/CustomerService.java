package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public synchronized boolean addCustomer(Customer customer){
	        List<Customer> list = repository.findByEmail(customer.getEmail()); 	
                if (list.size() > 0) {
    	           return false;
                } else {
    	        repository.save(customer);
    	        return true;
       }
	}
	
	public void updateCustomer(Customer customer) {
		repository.save(customer);
	}
	
	public void deleteCustomer(Long customerId) {
		repository.delete(getCustomerById(customerId));
	}
	
	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	public Customer getCustomerById(long customerId) {
		Customer obj = repository.findById(customerId).get();
		return obj;
	}
}
