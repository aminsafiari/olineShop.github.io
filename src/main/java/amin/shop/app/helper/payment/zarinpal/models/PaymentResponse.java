package amin.shop.app.helper.payment.zarinpal.models;

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

    //    private Data data;
//    private List<String> errors;
//
//    public Data getData() {
//        return data;
//    }
//
//    public void setData(Data data) {
//        this.data = data;
//    }
//
//    public List<String> getErrors() {
//        return errors;
//    }
//
//    public void setErrors(List<String> errors) {
//        this.errors = errors;
//    }
//
//    public static class Data {
//        private int code;
//        private String message;
//        private String authority;
//        private String fee_type;
//        private int fee;
//
//        public int getCode() {
//            return code;
//        }
//
//        public void setCode(int code) {
//            this.code = code;
//        }
//
//        public String getAuthority() {
//            return authority;
//        }
//
//        public void setAuthority(String authority) {
//            this.authority = authority;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//
//        public String getFee_type() {
//            return fee_type;
//        }
//
//        public void setFee_type(String fee_type) {
//            this.fee_type = fee_type;
//        }
//
//        public int getFee() {
//            return fee;
//        }
//
//        public void setFee(int fee) {
//            this.fee = fee;
//        }
//    }

}
