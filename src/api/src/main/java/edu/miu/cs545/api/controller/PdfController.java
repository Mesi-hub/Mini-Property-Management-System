package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.service.OfferService;
import edu.miu.cs545.api.service.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/pdfs")
public class PdfController {
    //TODO delete the file when integrated


    @Autowired
    PdfService pdfService;
    @Autowired
    OfferService offerService;
    @GetMapping("/offer/{id}")
    public ResponseEntity generatePdf(@PathVariable Long id) throws Exception {
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            pdfService.createOfferReceipt(offerService.findById(id), outputStream);
            final byte[] bytes = outputStream.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(bytes.length);
            return new ResponseEntity(bytes, headers, HttpStatus.OK);
        }
    }
}
