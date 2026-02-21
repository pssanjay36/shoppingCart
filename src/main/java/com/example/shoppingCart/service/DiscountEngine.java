package com.example.shoppingCart.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppingCart.model.Order;

@Service
public class DiscountEngine {

    @Autowired
    private KieContainer kieContainer;

    public Order applyDiscount(Order order) {

        KieSession kieSession = null;

        try {
            kieSession = kieContainer.newKieSession();
            kieSession.insert(order);
            kieSession.fireAllRules();
        } finally {
            if (kieSession != null) {
                kieSession.dispose();
            }
        }

        return order;
    }
}