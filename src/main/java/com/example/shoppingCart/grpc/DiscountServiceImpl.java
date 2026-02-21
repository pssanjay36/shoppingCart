package com.example.shoppingCart.grpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.shoppingCart.model.Order;
import com.example.shoppingCart.service.DiscountEngine;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@Component
@GrpcService
public class DiscountServiceImpl extends discount.DiscountServiceGrpc.DiscountServiceImplBase {

    @Autowired
    private DiscountEngine discountEngine;

    @Override
    public void applyDiscount(
            discount.Discount.OrderRequest request,
            StreamObserver<discount.Discount.OrderResponse> responseObserver) {

        // Step 1: Convert gRPC request to model object
        Order order = new Order();
        order.setOrderId(request.getOrderId());
        order.setCustomerType(request.getCustomerType());
        order.setOrderAmount(request.getOrderAmount());

        // Step 2: Call business layer
        order = discountEngine.applyDiscount(order);

        // Step 3: Build gRPC response
        discount.Discount.OrderResponse response =
                discount.Discount.OrderResponse.newBuilder()
                        .setOriginalAmount(order.getOrderAmount())
                        .setDiscountAmount(order.getDiscountAmount())
                        .setFinalAmount(order.getFinalAmount())
                        .setAppliedRule(
                                order.getAppliedRule() != null ? order.getAppliedRule() : "")
                        .build();

        // Step 4: Send response
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}