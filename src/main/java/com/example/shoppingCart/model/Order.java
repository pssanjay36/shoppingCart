package com.example.shoppingCart.model;

public class Order {
    private String orderId;
    private String customerType;
    private double orderAmount;
    private double discountAmount;
    private String appliedRule;

    
    public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getCustomerType() {
		return customerType;
	}


	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}


	public double getOrderAmount() {
		return orderAmount;
	}


	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}


	public double getDiscountAmount() {
		return discountAmount;
	}


	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}


	public String getAppliedRule() {
		return appliedRule;
	}


	public void setAppliedRule(String appliedRule) {
		this.appliedRule = appliedRule;
	}


	// getters and setters
    public double getFinalAmount() {
        return orderAmount - discountAmount;
    }
}
