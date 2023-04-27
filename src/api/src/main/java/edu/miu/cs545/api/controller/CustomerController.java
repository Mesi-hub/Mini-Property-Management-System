package edu.miu.cs545.api.controller;

import edu.miu.cs545.api.dto.CustomerDto;
import edu.miu.cs545.api.dto.OfferDto;
import edu.miu.cs545.api.entity.User;
import edu.miu.cs545.api.service.CustomerService;
import edu.miu.cs545.api.service.PdfService;
import edu.miu.cs545.api.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OfferService offerService;
    @Autowired
    ControllerSecurityUtil controllerSecurityUtil;
    @Autowired
    PdfService pdfService;
    @GetMapping
    ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteById(@PathVariable long id) {
        return ResponseEntity.ok(customerService.deleteById(id));
    }

    @PostMapping("/register")
    ResponseEntity<Boolean> registerCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.addNewCustomer(customerDto));
    }

    @PostMapping()
    ResponseEntity<Boolean> addCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.addNewCustomer(customerDto));
    }

    @PutMapping()
    ResponseEntity<Boolean> updateCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.updateCustomer(customerDto));
    }

    @GetMapping("/{id}/offers")
    public ResponseEntity<List<OfferDto>> checkOfferHistory(@PathVariable long id) {
        //Ignore ID and send only the allowed offers
        User user = controllerSecurityUtil.getLoggedinUser();
        List<OfferDto> offers = customerService.findOffersByCustomerId(user.getPerson().getId());
        System.out.println("contrller checkOfferHistory size: " + offers.size());
        return ResponseEntity.ok(offers);
    }

    @PostMapping("/{id}/offers")
    public ResponseEntity<Boolean> updateOfferStatus(@RequestBody OfferDto offerDto,
                                                     @PathVariable long id) {
        return ResponseEntity.ok(customerService.updateOfferStatus(offerDto, id));
    }


    @PostMapping("/black-list/{id}")
    ResponseEntity<Boolean> addCustomerToBlacklist(@PathVariable long id) {
        var user = controllerSecurityUtil.getLoggedinUser();
        var result = false;
        if (user != null) {
            result = customerService.addCustomerToBlacklist(id, user.getId());
        } else {
            result = customerService.addCustomerToBlacklist(id, 1);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/white-list/{id}")
    ResponseEntity<Boolean> addCustomerToWhiteList(@PathVariable long id) {
        var user = controllerSecurityUtil.getLoggedinUser();
        var result = false;
        if (user != null) {
            result = customerService.addCustomerToWhitelist(id, user.getId());
        } else {
            //TODO not necessary when auth is applied - just for dev
            result = customerService.addCustomerToWhitelist(id, 1);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{customerId}/offer")
    public ResponseEntity<Boolean> makeOffer(@PathVariable Long customerId, @RequestBody OfferDto offerDto) throws Exception {
        //Ignore customer Id
        User user = controllerSecurityUtil.getLoggedinUser();
        boolean success = offerService.makeOffer(user.getPerson().getId(), offerDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @GetMapping("/{customerId}/offer/{offerId}/receipt")
    public ResponseEntity receipt(@PathVariable Long customerId, @PathVariable Long offerId) throws Exception {
        //Ignore customer Id
        User user = controllerSecurityUtil.getLoggedinUser();
        OfferDto offerDto = offerService.findById(offerId);
        if(user.getPerson().getId() != offerDto.getCustomer().getId()){
            throw new Exception("Only the customer can request receipts.");
        }
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            pdfService.createOfferReceipt(offerService.findById(offerId), outputStream);
            final byte[] bytes = outputStream.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentLength(bytes.length);
            return new ResponseEntity(bytes, headers, HttpStatus.OK);
        }
    }

    @PostMapping("/{customerId}/offer/{offerId}/cancel")
    public ResponseEntity<Boolean> cancel(@PathVariable Long customerId, @PathVariable Long offerId) throws Exception {
        //Ignore customer Id
        User user = controllerSecurityUtil.getLoggedinUser();
        offerService.cancelByCustomer(user.getPerson().getId(), offerId);
        return ResponseEntity.ok().build();
    }
    

}
