package com.sandalTreeSoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandalTreeSoft.entity.Quotation;
import com.sandalTreeSoft.entity.QuotationItem;
import com.sandalTreeSoft.entity.QuotationRequest;
import com.sandalTreeSoft.repo.QuotationItemRepository;
import com.sandalTreeSoft.repo.QuotationRepository;
import com.sandalTreeSoft.service.QuotationService;

@RestController
@RequestMapping("/quotation")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;
    
    @Autowired
    QuotationItemRepository quotationitemRepository;
    
    @Autowired
    QuotationRepository quotationRepository;
    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> getQuotation(@PathVariable Long id) throws Exception {

        byte[] pdf = quotationService.generateQuotation(id);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=quotation.pdf")
                .body(pdf);
    }
    
    @PostMapping("/save")
    public Quotation saveQuotation(@RequestBody QuotationRequest request) {

        Quotation quotation = new Quotation();
        quotation.setCustomerName(request.getCustomerName());

        quotation = quotationRepository.save(quotation);

        double total = 0;

        for (QuotationItem item : request.getItems()) {
            item.setQuotationId(quotation.getId());
            total += item.getPrice() * item.getQuantity();
            quotationitemRepository.save(item);
        }

        quotation.setTotalAmount(total);
        return quotationRepository.save(quotation);
    }
}