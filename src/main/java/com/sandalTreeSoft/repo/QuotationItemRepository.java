package com.sandalTreeSoft.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandalTreeSoft.entity.QuotationItem;

public interface QuotationItemRepository extends JpaRepository<QuotationItem, Long> {
    List<QuotationItem> findByQuotationId(Long quotationId);
}