package com.sandalTreeSoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandalTreeSoft.service.QuotationService;

@RestController
@RequestMapping("/quotation")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> getQuotation(@PathVariable Long id) throws Exception {

        byte[] pdf = quotationService.generateQuotation(id);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=quotation.pdf")
                .body(pdf);
    }
}