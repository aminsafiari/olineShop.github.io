package amin.shop.app.entities.orders;

import amin.shop.app.entities.people.Customer;
import amin.shop.app.enums.PaymentType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transactions {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    //payment description.
    private String payer_desc;
    //date -> Payment Getaway.
    private String created_at;
    private int amount;
    private String order_id;

    private String trans_id;
    private String card_holder;
    private String Shaparak_Ref_Id;
    private String customer_phone;
    //code -> request
    private int code;
    private String verifyStatus;
    private int transactionVerifyCode;
    //self add date before customer payed.
    private Date addDate;
    private PaymentType paymentType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPayer_desc() {
        return payer_desc;
    }

    public void setPayer_desc(String payer_desc) {
        this.payer_desc = payer_desc;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public void setCard_holder(String card_holder) {
        this.card_holder = card_holder;
    }

    public String getShaparak_Ref_Id() {
        return Shaparak_Ref_Id;
    }

    public void setShaparak_Ref_Id(String shaparak_Ref_Id) {
        Shaparak_Ref_Id = shaparak_Ref_Id;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public int getTransactionVerifyCode() {
        return transactionVerifyCode;
    }

    public void setTransactionVerifyCode(int transactionVerifyCode) {
        this.transactionVerifyCode = transactionVerifyCode;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
