package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Customer;
import edu.miu.cs545.api.entity.Offer;
import edu.miu.cs545.api.entity.OfferState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByPropertyOwner(Customer customer);

    List<Offer> findAllByCustomerEquals(Customer cust);
    List<Offer> findByCustomerId(Long CustomerId);
    List<Offer> findByPropertyOwnerAndStatusIn(Customer customer, List<OfferState> offerStates);

}

