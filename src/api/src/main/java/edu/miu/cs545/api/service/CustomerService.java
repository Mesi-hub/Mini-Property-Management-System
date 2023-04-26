package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.entity.Person;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> findAll();
    CustomerDto findById(long id);
    boolean deleteById(long id);
    boolean save(CustomerDto customer);
    List<OfferDto> findOffersByCustomerId(long customerId);
   boolean addCustomerToBlacklist( long customerId, long blackListedById);
    boolean addCustomerToWhitelist( long customerId, long whiteListedById);
}
