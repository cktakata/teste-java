package com.example.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;
import com.example.api.repository.AddressRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

@Service
public class CustomerService {

	private CustomerRepository repository;
	private AddressRepository addressRepository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public synchronized boolean addCustomer(Customer customer, List<Address> addressList){
	        List<Customer> list = repository.findByEmail(customer.getEmail()); 	
                if (list.size() > 0) {
    	           return false;
                } else {
    	        repository.save(customer);
    	        for (Address address: addressList) {
    	        	address.setCustomer(customer);
    	        	addressRepository.save(address);
    	        }
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
	
	public List<Customer> getAllCustomers(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<Customer> pagedResult = repository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Customer>();
        }
    }
	
}
