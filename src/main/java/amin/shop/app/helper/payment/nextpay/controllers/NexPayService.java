package amin.shop.app.helper.payment.nextpay.controllers;

import amin.shop.app.entities.orders.Transactions;
import amin.shop.app.helper.payment.nextpay.models.PaymentRequest;
import amin.shop.app.helper.payment.nextpay.models.PaymentResponse;
import amin.shop.app.helper.payment.nextpay.models.VerifyRequest;
import amin.shop.app.helper.payment.nextpay.models.VerifyResponse;
import amin.shop.app.helper.uimodels.StartPaymentVM;
import amin.shop.app.helper.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NexPayService {

    @Value("${nextpay.api_key}")
    private String api_key;
    @Value("${nextpay.callBackUrl}")
    private String callBackUrl;
    @Value("${nextpay.paymentAddress}")
    private String paymentAddress;
    @Value("${nextpay.startPayAddress}")
    private String startPayAddress;
    @Value("${nextpay.verificationAddress}")
    private String verificationAddress;

    public String goToPayment(StartPaymentVM data) throws Exception {
        PaymentRequest request = new PaymentRequest();
//        request.setAmount((int) data.getAmount());
        request.setAmount(1000);
        request.setPayer_desc(data.getDescription());
        request.setCallback_uri(callBackUrl);
        request.setApi_key(api_key);
        request.setCustomer_phone(data.getMobile());
        request.setOrder_id(data.getOrderId());

        HttpUtils<PaymentResponse> httpUtils = new HttpUtils<>(PaymentResponse.class);
        PaymentResponse response = httpUtils.callPost(paymentAddress, request);
        data.setCode(response.getCode());
        data.setTrans_id(response.getTrans_id());
        data.setAmount(response.getAmount());
        if (response.getCode() != -1) {
            throw new Exception("Error on payment");
        }
        return startPayAddress + response.getTrans_id();
    }

    public Transactions goToVerify(Transactions transactions) throws Exception {
//        request.setAmount((int) data.getAmount());
        VerifyRequest request = new VerifyRequest();
        request.setApi_key(api_key);
        request.setTrans_id(transactions.getTrans_id());
        request.setAmount(transactions.getAmount());

        HttpUtils<VerifyResponse> httpUtils = new HttpUtils<>(VerifyResponse.class);
        VerifyResponse response = httpUtils.callPost(verificationAddress, request);
        transactions.setTransactionVerifyCode(response.getCode());
        transactions.setAmount(response.getAmount());
        transactions.setCard_holder(response.getCard_holder());
        transactions.setCustomer_phone(response.getCustomer_phone());
        transactions.setShaparak_Ref_Id(response.getShaparak_Ref_Id());
        transactions.setCreated_at(response.getCreated_at());
        if (response.getCode() != 0) {
            throw new Exception("Error on payment");
        }
        return transactions;
    }

}
