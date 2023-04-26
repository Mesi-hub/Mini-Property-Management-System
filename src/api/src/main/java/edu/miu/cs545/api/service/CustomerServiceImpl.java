package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.AddressDto;
import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.dto.UserDto;
import edu.miu.cs545.api.entity.Address;
import edu.miu.cs545.api.entity.Administrator;
import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.repository.AddressRepository;
import edu.miu.cs545.api.repository.CustomerRepository;
import edu.miu.cs545.api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AddressRepository addressRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public List<CustomerDto> findAll() {
        var customersDtoList = new ArrayList<CustomerDto>();
        customerRepo.findAll().forEach(cust -> {
            customersDtoList.add(mapCustomerToDto(cust));
        });
        return customersDtoList;
    }

    @Override
    public CustomerDto findById(long id) {
        return mapCustomerToDto(customerRepo.findById(id).orElse(null));
    }

    @Override
    public boolean deleteById(long id) {
        customerRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean addNewCustomer(CustomerDto customerDto) {
        var user = new User();
        user.setName(customerDto.getFirstName());
        user.setEmail(customerDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(customerDto.getPassword()));
        userRepo.save(user);
        var customer = mapDtoToCustomer(customerDto);
        var address = addressRepo.save(customer.getAddress());
        customer.setAddress(address);
        customer.setUser(user);
        customerRepo.save(customer);
        return true;
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        Customer customerOld = customerRepo.findById(customerDto.getId()).orElseThrow();
        var user = customerOld.getUser();
        if(!customerDto.getPassword().isBlank()) {
            user.setPassword(bCryptPasswordEncoder.encode(customerDto.getPassword()));
            userRepo.save(user);
        }
        var customer = mapDtoToCustomer(customerDto);
        var address = addressRepo.save(customer.getAddress());
        customer.setAddress(address);
        customerRepo.save(customer);
        return true;
    }


    @Override
    public List<OfferDto> findOffersByCustomerId(long customerId) {
        return null;
    }

    @Override
    public boolean addCustomerToBlacklist(long customerId, long blackListedById) {
        return saveCustomerStatus(customerId, blackListedById, true);
    }

    @Override
    public boolean addCustomerToWhitelist(long customerId, long whiteListedById) {
        return saveCustomerStatus(customerId, whiteListedById, false);
    }

    private boolean saveCustomerStatus(long customerId, long blackListedById, boolean isBlackListed) {
        AtomicBoolean isSaved = new AtomicBoolean(false);
        customerRepo.findById(customerId)
                .ifPresent(customer -> {
                    customer.setBlackListed(isBlackListed);
                    var admin = new Administrator();
                    admin.setId(blackListedById);
                    customer.setBlackListedBy(admin);
                    customerRepo.save(customer);
                    isSaved.set(true);
                });
        return isSaved.get();
    }

    private CustomerDto mapCustomerToDto(Customer cust) {
        var customerDto = modelMapper.map(cust, CustomerDto.class);
        customerDto.setAddress(modelMapper.map(cust.getAddress(), AddressDto.class));
        //customerDto.setUser(modelMapper.map(cust.getUser(), UserDto.class));
        return customerDto;
    }

    private Customer mapDtoToCustomer(CustomerDto dto) {
        var customer = modelMapper.map(dto, Customer.class);
        customer.setAddress(modelMapper.map(dto.getAddress(), Address.class));

        /*if(dto.getUser() != null)
        userRepo.findById(dto.getUser().getId())
                .ifPresent(customer::setUser);*/

        return customer;
    }
}
