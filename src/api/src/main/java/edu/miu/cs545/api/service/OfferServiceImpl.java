package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Offer;
import edu.miu.cs545.api.entity.OfferState;
import edu.miu.cs545.api.entity.Property;
import edu.miu.cs545.api.repository.CustomerRepository;
import edu.miu.cs545.api.repository.OfferRepository;
import edu.miu.cs545.api.repository.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

//    @Override
//    public List<OfferDto> findAll() {
//        List<Offer> offers = offerRepository.findAll();
//        return offers.stream().map(offer -> modelMapper.map(offer, OfferDto.class)).collect(Collectors.toList());
//    }
//
    @Override
    public OfferDto findById(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Offer not found"));
        return modelMapper.map(offer, OfferDto.class);
    }
//
//    @Override
//    public boolean save(OfferDto offerDto) {
//        Offer offer = modelMapper.map(offerDto, Offer.class);
//        offerRepository.save(offer);
//        return true;
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        offerRepository.deleteById(id);
//        return true;
//    }
//
//    @Override
//    public List<OfferDto> findByCustomerId(Long customerId) {
//        List<Offer> offers = offerRepository.findByCustomerId(customerId);
//        return offers.stream().map(offer -> modelMapper.map(offer, OfferDto.class)).collect(Collectors.toList());
//    }

    @Override
    public List<OfferDto> checkOfferHistory(Long customerId) {
        // Retrieve customer entity from database
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        // Retrieve all properties owned by the customer
//        List<Property> properties = propertyRepository.findByOwner(customer);

        // Retrieve all offers made on those properties
        //List<Offer> offers = offerRepository.findByPropertyOwner(customer);
        List<Offer> offers = offerRepository.findAllByCustomerEquals(customer);

        List<OfferDto> offerDtos = new ArrayList<>();

          offers.stream().forEach( off-> {
              offerDtos.add( modelMapper.map(off, OfferDto.class));
              System.out.println("offer: " + off.getId());
          });

        return offerDtos;
    }

    @Override
    public boolean makeOffer(OfferDto offerDto) {
        // Retrieve property entity from database
        Property property = propertyRepository.findById(offerDto.getProperty().getId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));

        // Create a new offer entity
        Offer offer = new Offer();
        offer.setCustomer(customerRepository.findById(offerDto.getCustomer().getId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found")));
        offer.setProperty(property);
        offer.setOfferAmount(offerDto.getOfferAmount());
        offer.setStatus(OfferState.PENDING);

        // Save the offer entity to database
        offerRepository.save(offer);

        return true;
    }

    @Override
    public List<OfferDto> findCurrentOffersByCustomerId(Long customerId) {
        // Retrieve customer entity from database
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        // Retrieve all properties owned by the customer
        List<Property> properties = propertyRepository.findByOwner(customer);

        // Retrieve all offers made on those properties that are still pending
        List<Offer> offers = offerRepository.findByPropertyOwnerAndStatusIn(customer, Arrays.asList(OfferState.PENDING, OfferState.EVALUATING));

        // Convert offers to DTOs
        List<OfferDto> offerDtos = offers.stream()
                .map(offer -> modelMapper.map(offer, OfferDto.class))
                .collect(Collectors.toList());

        return offerDtos;
    }



//    @Override
//    public boolean update(OfferDto offerDto) {
//        Offer existingOffer = offerRepository.findById(offerDto.getId())
//                .orElseThrow(() -> new EntityNotFoundException("Offer not found"));
//
//        existingOffer.setDate(offerDto.getDate());
//        existingOffer.setTime(offerDto.getTime());
//        existingOffer.setOfferAmount(offerDto.getOfferAmount());
//        existingOffer.setStatus(offerDto.getStatus());
//
//        Property property = propertyRepository.findById(offerDto.getProperty().getId())
//                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
//        existingOffer.setProperty(property);
//
//        Customer customer = customerRepository.findById(offerDto.getCustomer().getId())
//                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
//        existingOffer.setCustomer(customer);
//
//        offerRepository.save(existingOffer);
//
//        return true;
//    }


}
