package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Offer;
import edu.miu.cs545.api.entity.OfferState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByPropertyOwner(Customer customer);
    List<Offer> findAllByCustomerEquals(Customer cust);
    List<Offer> findByCustomerId(Long CustomerId);
    @Query("SELECT offer FROM Offer offer INNER JOIN offer.property property INNER JOIN property.owner owner WHERE owner.id = :ownerId")
    List<Offer> findByOwnerId(Long ownerId);
    List<Offer> findByPropertyOwnerAndStatusIn(Customer customer, List<OfferState> offerStates);    
    List<Offer> findByPropertyIdAndStatusIn(Long propertyId, List<OfferState> offerStates);
    @Transactional
    @Modifying
    @Query("UPDATE Offer o SET o.status = :newState WHERE o.id IN (SELECT o1.id FROM Offer o1 INNER JOIN o1.property p WHERE p.id = :propertyId AND p.status IN :inStates)")
    void updateOffers(Long propertyId, OfferState newState, List<OfferState> inStates);
}

