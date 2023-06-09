package amin.shop.app.helper.payment.zarinpal.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VerifyResponse {
    private int code;
    private int amount;
    private String order_id;
    private String card_holder;
    private String Shaparak_Ref_Id;
    private String customer_phone;
    private String custom;
    private String created_at;

    /*public static class CustomData implements Serializable {
        private String payerDesc;

        public String getPayerDesc() {
            return payerDesc;
        }

        public void setPayerDesc(String payerDesc) {
            this.payerDesc = payerDesc;
        }
    }*/

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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public void setCard_holder(String card_holder) {
        this.card_holder = card_holder;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getShaparak_Ref_Id() {
        return Shaparak_Ref_Id;
    }

    public void setShaparak_Ref_Id(String shaparak_Ref_Id) {
        Shaparak_Ref_Id = shaparak_Ref_Id;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /*public CustomData getCustomData() {
        Gson gson = new Gson();
        return gson.fromJson(custom, CustomData.class);
    }*/

    public Date getCreatedAt() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(String.valueOf(created_at));
    }



}
