package com.example.api.web.rest;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.node.ObjectNode;

import com.example.api.domain.Customer;
import com.example.api.domain.Address;
import com.example.api.service.CustomerService;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Void> addCustomer(@RequestBody Map<String, Object>map, UriComponentsBuilder builder) {
		ObjectMapper mapper = new ObjectMapper(); 
		Customer customer = mapper.convertValue(map.get("customer"), Customer.class);
		Map<String, Object> addresses = mapper.convertValue(map.get("addressList"), Map.class);
		List<Address> addressList = new ArrayList<Address>();
		for (Map.Entry<String, Object> entry : addresses.entrySet()) {
			Address address = mapper.convertValue(entry, Address.class);
			addressList.add(address);
		}
        boolean flag = service.addCustomer(customer, addressList);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		service.updateCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	@DeleteMapping
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
		service.deleteCustomer(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
//	@GetMapping
//	public List<Customer> findAll() {
//		return service.findAll();
//	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) 
	{
	List<Customer> list = service.getAllCustomers(pageNo, pageSize, sortBy);
	
	return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK); 
	}
	

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

}
