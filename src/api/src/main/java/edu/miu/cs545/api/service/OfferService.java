package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.OfferDto;

import java.util.List;

public interface OfferService {
    List<OfferDto> checkOfferHistory(Long customerId);
    boolean makeOffer(OfferDto offerDto);

    List<OfferDto> findCurrentOffersByCustomerId(Long customerId);

}
