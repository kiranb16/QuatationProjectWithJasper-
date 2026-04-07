package com.sandalTreeSoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandalTreeSoft.entity.Quotation;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {
}