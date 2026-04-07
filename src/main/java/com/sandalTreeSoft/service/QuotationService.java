package com.sandalTreeSoft.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class QuotationService {

    @Autowired
    private DataSource dataSource;

    public byte[] generateQuotation(Long quotationId) throws Exception {

        InputStream inputStream = getClass()
                .getResourceAsStream("/reports/quotation.jrxml");

        JasperReport report = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();
        params.put("quotationId", quotationId);

        try (Connection connection = dataSource.getConnection()) {

            JasperPrint print = JasperFillManager.fillReport(
                    report,
                    params,
                    connection
            );

            return JasperExportManager.exportReportToPdf(print);
        }
    }
}