package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Offer;
import edu.miu.cs545.api.entity.OfferState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByPropertyOwner(Customer customer);
    List<Offer> findAllByCustomerEquals(Customer cust);
    List<Offer> findByCustomerId(Long CustomerId);
    @Query("SELECT offer FROM Offer offer INNER JOIN offer.property property INNER JOIN property.owner owner WHERE owner.id = :ownerId")
    List<Offer> findByOwnerId(Long ownerId);
    List<Offer> findByPropertyOwnerAndStatusIn(Customer customer, List<OfferState> offerStates);

}

