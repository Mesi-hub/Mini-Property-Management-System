package edu.miu.cs545.api.service;

import edu.miu.cs545.api.dto.OfferDto;

import java.io.OutputStream;

public interface PdfService {
    void createOfferReceipt(OfferDto offer, OutputStream outputStream) throws Exception;
}
