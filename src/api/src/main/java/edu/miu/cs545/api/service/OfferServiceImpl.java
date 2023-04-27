package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.MessageDto;
import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.entity.*;
import edu.miu.cs545.api.repository.CustomerRepository;
import edu.miu.cs545.api.service.MessageService;
import edu.miu.cs545.api.repository.OfferRepository;
import edu.miu.cs545.api.repository.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    MessageService messageService;


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
    public boolean makeOffer(Long customerId, OfferDto offerDto) throws Exception {
        // Retrieve property entity from database using the provided id
        Property property = propertyRepository.findById(offerDto.getProperty().getId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        if(!(property.getStatus() == PropertyState.PENDING ||
           property.getStatus() == PropertyState.AVAILABLE )){
            throw new Exception("Offers are not accepted.");
        }
        property.setStatus(PropertyState.PENDING);
        propertyRepository.save(property);
        // Create a new offer entity
        Offer offer = new Offer();
        offer.setCustomer(customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found")));
        offer.setProperty(property);
        offer.setOfferAmount(offerDto.getOfferAmount());
        offer.setStatus(OfferState.PENDING);
        offer.setDate(LocalDate.now());
        offer.setTime(LocalTime.now());

        // Save the offer entity to database
        offerRepository.save(offer);

        return true;
    }

    @Override
    public boolean startEvaluating(Long ownerId, Long offerId) throws Exception {
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        if(offer.getProperty().getOwner().getId() != ownerId){
            throw new Exception("You can only evaluate your offers.");
        }
        if(offer.getStatus() != OfferState.PENDING){
            throw new Exception("Offer is not in state Pending");
        }
        Property property = propertyRepository.findById(offer.getProperty().getId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        if(!(property.getStatus() == PropertyState.PENDING ||
            property.getStatus() == PropertyState.AVAILABLE )){
                throw new Exception("Offers are not accepted.");
        }
        property.setStatus(PropertyState.PENDING);
        propertyRepository.save(property);
             
        offer.setStatus(OfferState.EVALUATING);
        offerRepository.save(offer);
        return true;
    }

    @Override
    public boolean accept(Long ownerId, Long offerId) throws Exception{
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        if(offer.getProperty().getOwner().getId() != ownerId){
            throw new Exception("You can only accept your offers.");
        }
        if(offer.getStatus() != OfferState.EVALUATING){
            throw new Exception("Offer is not in state Evaluating");
        }
        Property property = propertyRepository.findById(offer.getProperty().getId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        if(!(property.getStatus() == PropertyState.PENDING ||
            property.getStatus() == PropertyState.AVAILABLE )){
                throw new Exception("Offers are not accepted.");
        }
        offer.setStatus(OfferState.ACCEPTED);
        offerRepository.save(offer);

        property.setStatus(PropertyState.CONTINGENT);
        propertyRepository.save(property);
        return true;
    }

    @Override
    public boolean cancelByCustomer(Long customerId, Long offerId) throws Exception{
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        if(offer.getCustomer().getId() != customerId){
            throw new Exception("You can only cancel own offers.");
        }
        if(!(offer.getStatus() == OfferState.EVALUATING ||
           offer.getStatus() == OfferState.PENDING)){
            throw new Exception("Offer is not in state evaluating or pending. It is " + offer.getStatus());
        }
        Property property = propertyRepository.findById(offer.getProperty().getId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        if(!(property.getStatus() == PropertyState.PENDING ||
            property.getStatus() == PropertyState.AVAILABLE )){
                throw new Exception("Offers are not accepted.");
        }
        offer.setStatus(OfferState.CANCELLED);
        offerRepository.save(offer);

        List<OfferState> states = new ArrayList<>();
        states.add(OfferState.EVALUATING);
        states.add(OfferState.PENDING);
        List<Offer> existing = offerRepository.findByPropertyIdAndStatusIn(property.getId(), states); 
        if( existing.size()== 0){
            property.setStatus(PropertyState.AVAILABLE);
            propertyRepository.save(property);        
        }
        return true;
    }

    @Override
    public boolean cancelByOwner(Long ownerId, Long offerId) throws Exception{
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        if(offer.getProperty().getOwner().getId() != ownerId){
            throw new Exception("You can only cancel own offers.");
        }
        if(!(offer.getStatus() == OfferState.EVALUATING ||
           offer.getStatus() == OfferState.PENDING ||
           offer.getStatus() == OfferState.ACCEPTED)){
            throw new Exception("Offer is not in state Evaluating, Pending or Accepted");
        }
        Property property = propertyRepository.findById(offer.getProperty().getId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        if(!(property.getStatus() == PropertyState.PENDING ||
            property.getStatus() == PropertyState.AVAILABLE ||
            property.getStatus() == PropertyState.CONTINGENT)){
                throw new Exception("Offers are not accepted.");
        }
             
        offer.setStatus(OfferState.CANCELLED);
        offerRepository.save(offer);

        List<OfferState> states = new ArrayList<>();
        states.add(OfferState.EVALUATING);
        states.add(OfferState.PENDING);
        List<Offer> existing = offerRepository.findByPropertyIdAndStatusIn(property.getId(), states); 
        if( existing.size()== 0){
            property.setStatus(PropertyState.AVAILABLE);
            propertyRepository.save(property);        
        }
        else {
            property.setStatus(PropertyState.PENDING);
            propertyRepository.save(property);
        }
        return true;
    }

    @Override
    public boolean closeByOwner(Long ownerId, Long offerId) throws Exception{
        Offer offer = offerRepository.findById(offerId).orElseThrow();
        if(offer.getProperty().getOwner().getId() != ownerId){
            throw new Exception("You can only cancel own offers.");
        }
        if(offer.getStatus() != OfferState.ACCEPTED){
            throw new Exception("Offer is not in state Accepted");
        }
        Property property = propertyRepository.findById(offer.getProperty().getId())
                .orElseThrow(() -> new EntityNotFoundException("Property not found"));
        if(property.getStatus() != PropertyState.CONTINGENT){
                throw new Exception("Propery is not contingent");
        }
             
        offer.setStatus(OfferState.CLOSED);
        offerRepository.save(offer);

        property.setStatus(PropertyState.SOLD);
        propertyRepository.save(property); 

        List<OfferState> states = new ArrayList<>();
        states.add(OfferState.EVALUATING);
        states.add(OfferState.PENDING);
        states.add(OfferState.ACCEPTED);
        offerRepository.updateOffers(property.getId(), OfferState.CANCELLED, states);
        
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Congratulations!!! Transaction Successful. - Offer:" + offer.getId() + " - House: " + offer.getProperty().getTitle());
        messageDto.setRecipient(offer.getCustomer());        
        messageService.postMessage(messageDto, offer.getCustomer().getUser());
        
        return true;
    }

    @Override
    public List<OfferDto> findCurrentOffersByCustomerId(Long customerId) {
        // Retrieve customer entity from database
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        
        // Retrieve all offers made on those properties that are still pending
        List<Offer> offers = offerRepository.findByPropertyOwnerAndStatusIn(customer, Arrays.asList(OfferState.PENDING, OfferState.EVALUATING));

        // Convert offers to DTOs
        List<OfferDto> offerDtos = offers.stream()
                .map(offer -> modelMapper.map(offer, OfferDto.class))
                .collect(Collectors.toList());

        return offerDtos;
    }

}
