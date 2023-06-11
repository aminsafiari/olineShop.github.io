package amin.shop.app.controllers.api.people;

import amin.shop.app.config.JwtTokenUtil;
import amin.shop.app.entities.people.Customer;
import amin.shop.app.enums.ResponseStatus;
import amin.shop.app.enums.UserRole;
import amin.shop.app.helper.exceptions.JwtTokenException;
import amin.shop.app.helper.ui.ServiceResponse;
import amin.shop.app.helper.uimodels.CustomerVM;
import amin.shop.app.helper.uimodels.UserVM;
import amin.shop.app.services.people.CustomerService;
import amin.shop.app.services.people.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ServiceResponse<Customer> getAll(@RequestParam Integer pageSize,
                                            @RequestParam Integer pageNumber) {
        try {
            List<Customer> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
        }

    }

    @GetMapping("/{id}")
    public ServiceResponse<Customer> getById(@PathVariable long id) {
        try {
            Customer result = service.getById(id);
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
        }
    }

    @PostMapping("/")
    public ServiceResponse<Customer> add(@RequestBody Customer data) {
        try {
            Customer result = service.add(data);
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<Customer> update(@RequestBody Customer data) {
        try {
            Customer result = service.update(data);
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
        }
    }

    //For edit customer information in customer dashboard.
    @PutMapping("/updateInfo")
    public ServiceResponse<Customer> updateInfo(@RequestBody CustomerVM data, HttpServletRequest request) {
        try {
            UserVM userVM = getUserVMFromToken(request);
            if (userVM.getRole() != UserRole.ADMIN) {
                //service -> customerService.
                Customer customer = service.getByUserId(userVM.getId());
                if (customer.getId() != data.getId())
                    throw new Exception("You can update only your information!");
            }
            Customer result = service.update(data.getCustomerInfo());
            userService.update(data.getUserInfo());
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
        }
    }

    @DeleteMapping("/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = service.deleteById(id);
            return new ServiceResponse<Boolean>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
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
