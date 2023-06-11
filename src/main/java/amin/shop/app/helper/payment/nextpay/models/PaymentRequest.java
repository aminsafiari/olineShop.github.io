package amin.shop.app.helper.payment.nextpay.models;

import java.io.Serializable;

public class PaymentRequest implements Serializable {
    private String api_key;
    private Integer amount;
    //Return address from site(redirect this address).
    private String callback_uri;
    private String payer_desc;
    private String customer_phone;
    private String order_id;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCallback_uri() {
        return callback_uri;
    }

    public void setCallback_uri(String callback_uri) {
        this.callback_uri = callback_uri;
    }

    public String getPayer_desc() {
        return payer_desc;
    }

    public void setPayer_desc(String payer_desc) {
        this.payer_desc = payer_desc;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
