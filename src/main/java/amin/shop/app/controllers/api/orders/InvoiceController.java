package amin.shop.app.controllers.api.orders;

import amin.shop.app.config.JwtTokenUtil;
import amin.shop.app.entities.orders.Invoice;
import amin.shop.app.entities.people.Customer;
import amin.shop.app.enums.ResponseStatus;
import amin.shop.app.enums.UserRole;
import amin.shop.app.helper.exceptions.JwtTokenException;
import amin.shop.app.helper.ui.ServiceResponse;
import amin.shop.app.helper.uimodels.UserVM;
import amin.shop.app.services.orders.InvoiceService;
import amin.shop.app.services.people.CustomerService;
import amin.shop.app.services.people.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService service;
    //security work.
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;


    @GetMapping("/find")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    //cid -> customer_id
    public ServiceResponse<Invoice> find(@RequestParam long cid,
                                         @RequestParam Integer pageSize,
                                         @RequestParam Integer pageNumber,
                                         HttpServletRequest request) {
        try {

            UserVM userVM = getUserVMFromToken(request);
            if (userVM.getRole() != UserRole.ADMIN) {
                Customer customer = customerService.getByUserId(userVM.getId());
                if (customer.getId() != cid)
                    throw new Exception("You can see only your invoices!");
            }
            List<Invoice> result = service.findByCustomer(cid, pageSize, pageNumber);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Invoice>(e);
        }
    }

    @GetMapping("/getInfo/{id}")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Invoice> search(@PathVariable long id, HttpServletRequest request) {
        try {
            Invoice result = service.getById(id);
            UserVM userVM = getUserVMFromToken(request);
            if (userVM.getRole() != UserRole.ADMIN) {
                Customer customer = customerService.getByUserId(userVM.getId());
                if (customer.getId() != result.getCustomer().getId())
                    throw new Exception("You can see only your invoices!");
            }
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Invoice>(e);
        }
    }

    @PostMapping("/")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Invoice> add(@RequestBody Invoice data) {
        try {
            Invoice result = service.add(data);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Invoice>(e);
        }
    }

    //you can update handle with @PutMapping is better and true, but for this simple client environment work use this annotation.
    @PutMapping("/")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Invoice> update(@RequestBody Invoice data) {
        try {
            Invoice result = service.update(data);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Invoice>(e);
        }
    }

    @DeleteMapping("/{id}")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = service.deleteById(id);
            return new ServiceResponse<Boolean>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Boolean>(e);
        }
    }

    private UserVM getUserVMFromToken(HttpServletRequest request) throws JwtTokenException {
        String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
            throw new JwtTokenException("Request token header does not set!");

        String token = requestTokenHeader.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);

        if (username == null)
            throw new JwtTokenException("username can not result!");

        UserVM userVM = new UserVM(userService.getByUsername(username));
        return userVM;
    }

}
