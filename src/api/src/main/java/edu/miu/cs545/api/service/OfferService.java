package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.entity.Offer;

import java.util.List;

public interface OfferService {
    List<OfferDto> checkOfferHistory(Long customerId);
    //boolean makeOffer(OfferDto offerDto);
    boolean makeOffer(Long customerId, OfferDto offerDto) throws Exception;

    OfferDto findById(Long id);
    List<OfferDto> findCurrentOffersByCustomerId(Long customerId);
    boolean startEvaluating(Long ownerId, Long offerId) throws Exception;
    boolean accept(Long ownerId, Long offerId) throws Exception;
    boolean cancelByCustomer(Long customerId, Long offerId) throws Exception;
    boolean cancelByOwner(Long ownerId, Long offerId) throws Exception;
    boolean closeByOwner(Long ownerId, Long offerId) throws Exception;
}
