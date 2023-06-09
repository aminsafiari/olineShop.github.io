package amin.shop.app.controllers.api.orders;

import amin.shop.app.enums.ResponseStatus;
import amin.shop.app.helper.ui.ServiceResponse;
import amin.shop.app.helper.uimodels.PaymentVM;
import amin.shop.app.helper.uimodels.StartPaymentVM;
import amin.shop.app.services.orders.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<StartPaymentVM> addPayment(@RequestBody PaymentVM data) {
        try {
            StartPaymentVM startPaymentVM = paymentService.addPayment(data);
            String location = paymentService.goToPayment(startPaymentVM);
            startPaymentVM.setLocation(location);
            return new ServiceResponse<StartPaymentVM>(ResponseStatus.SUCCESS, startPaymentVM);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<StartPaymentVM>(e);
        }
    }


}
