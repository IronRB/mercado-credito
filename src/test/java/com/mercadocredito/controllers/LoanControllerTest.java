package com.mercadocredito.controllers;

import com.mercadocredito.core.loan.domain.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

class LoanControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:" + 8080;
    }

    @Test
    void getLoans() {
       /** HttpEntity<String> entity = new HttpEntity<String>(null, null);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/loan-collection",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());*/
    }

    @Test
    void postLoan() {
    }
}