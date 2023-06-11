package amin.shop.app.helper.payment.nextpay.models;

import java.io.Serializable;

public class PaymentResponse implements Serializable {
    private String trans_id;
    private int code;
    private int amount;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
