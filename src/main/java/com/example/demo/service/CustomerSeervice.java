package com.example.demo.service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.model.Customers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerSeervice {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<CustomerDTO> getAllCustomers(){
        List<Customers> customers= customerRepository.findAll();
        List<CustomerDTO> customerDTOS=customers.stream().map(c-> modelMapper.map(c,CustomerDTO.class)).collect(Collectors.toList());
       return customerDTOS;
    }

}
