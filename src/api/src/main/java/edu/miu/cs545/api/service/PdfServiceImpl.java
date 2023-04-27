package edu.miu.cs545.api.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.entity.Offer;
import edu.miu.cs545.api.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.stream.Stream;

@Service
public class PdfServiceImpl implements PdfService {
    @Autowired
    OfferRepository offerRepository;
    public void createOfferReceipt(OfferDto offerDto, OutputStream outputStream) throws Exception{
        Offer offer = offerRepository.findById(offerDto.getId()).orElseThrow();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        PdfPTable table = new PdfPTable(4);
        addTableHeader(table);
        addRows(table, offer);
        addCustomRows(table);

        document.add(table);
        document.close();
    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("Property", "Customer", "Owner", "Price")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
    private void addRows(PdfPTable table, Offer offer) {
        table.addCell(offer.getProperty().getTitle() + " (" + offer.getProperty().getId()+ ")");
        table.addCell(offer.getCustomer().getLastName()+ "," + offer.getCustomer().getFirstName() + " (" + offer.getCustomer().getId()+ ")");
        table.addCell(offer.getProperty().getOwner().getLastName()+ "," + offer.getProperty().getOwner().getFirstName() + " (" + offer.getProperty().getOwner().getId()+ ")");
        table.addCell("$ " + offer.getOfferAmount());

    }
    private void addCustomRows(PdfPTable table){

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("Receipt"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);
    }
}
