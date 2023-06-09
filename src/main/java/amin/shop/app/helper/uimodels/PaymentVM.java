package amin.shop.app.helper.uimodels;

import amin.shop.app.enums.PaymentType;

import java.util.List;

public class PaymentVM {
    private CustomerVM customer;
    private List<OrderItemVM> orderItemVMS;
    private PaymentType paymentType;
    private long customerId;

    public CustomerVM getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVM customer) {
        this.customer = customer;
    }

    public List<OrderItemVM> getOrderItemVMS() {
        return orderItemVMS;
    }

    public void setOrderItemVMS(List<OrderItemVM> orderItemVMS) {
        this.orderItemVMS = orderItemVMS;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
