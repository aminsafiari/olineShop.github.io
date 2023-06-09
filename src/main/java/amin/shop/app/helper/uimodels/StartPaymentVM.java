package amin.shop.app.helper.uimodels;

import amin.shop.app.entities.orders.Invoice;
import amin.shop.app.entities.people.Customer;
import amin.shop.app.enums.PaymentType;

public class StartPaymentVM {
    private int amount;
    private String description;
    private String location;
    private String orderId;
    private String mobile;
    private Customer customer;
    private Invoice invoice;
    private String trans_id;
    //code -> before request.
    private int code;
    //name payment getaway company
    private PaymentType paymentType;

    public StartPaymentVM() {
        amount = 0;
        description = "";
        location = "#";
        mobile = "";
        orderId = "";
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
