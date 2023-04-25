package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.entity.Person;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> findAll();
    CustomerDto findById(long id);
    boolean deleteById(long id);
    boolean save(CustomerDto customer);
    List<OfferDto> findOffersByCustomerId(long customerId);
}
