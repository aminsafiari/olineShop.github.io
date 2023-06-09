package amin.shop.app.controllers.api.people;

import amin.shop.app.config.JwtTokenUtil;
import amin.shop.app.entities.people.Customer;
import amin.shop.app.entities.people.User;
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
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<UserVM> login(@RequestBody User user) {
        User userData = service.auth(user.getUsername(), user.getPassword());
        if (userData == null)
            return new ServiceResponse<UserVM>
                    (ResponseStatus.FAILED, "Incorrect username or password");
        UserVM userVM = new UserVM(userData);
        //we set token in header and send any Request.
        String token = jwtTokenUtil.generateToken(userVM);
        userVM.setToken(token);
        return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, userVM);
    }

    @GetMapping("/getAll")
    public ServiceResponse<UserVM> getAll(@RequestParam Integer pageSize,
                                          @RequestParam Integer pageNumber) {
        try {
            List<User> result = service.getAll(pageSize, pageNumber);
            List<UserVM> resultVM = new ArrayList<>();
            result.stream().forEach(x -> resultVM.add(new UserVM(x)));
            long totalCount = service.getAllCount();
            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, resultVM, totalCount);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<UserVM>(e);
        }
    }

    @GetMapping("/{id}")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<UserVM> search(@PathVariable long id) {
        try {
            User result = service.getById(id);
            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<UserVM>(e);
        }
    }

    @GetMapping("/getUserInfo")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<UserVM> getUserInfo(HttpServletRequest request) {
        try {
            //region copy this code from <<JwtRequestFilter>>.
            String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
                throw new JwtTokenException("Request token header does not set!");

            String token = requestTokenHeader.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);

            if (username == null)
                throw new Exception("username can not resolve!");
            //endregion

            UserVM user = new UserVM(service.getByUsername(username));

            if (user.getRole() != UserRole.ADMIN) {
                Customer customer = customerService.getByUserId(user.getId());
                user.setCustomerId(customer.getId());
                user.setCustomer(new CustomerVM(customer));
            }

            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, user);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<UserVM>(e);
        }
    }

    @PostMapping("/")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<UserVM> add(@RequestBody User data) {
        try {
            User result = service.add(data);
            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<UserVM>(e);
        }
    }

    //you can update handle with @PutMapping is better and true, but for this simple client environment work use this annotation.
    @PutMapping("/")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<UserVM> update(@RequestBody User data) {
        try {
            User result = service.update(data);
            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<UserVM>(e);
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

    @PutMapping("/changePass")
    public ServiceResponse<UserVM> changePassword(@RequestBody UserVM data) {
        try {
            User result = service.changePassword(data.getId(), data.getPassword(), data.getNewPassword());
            return new ServiceResponse<UserVM>(ResponseStatus.SUCCESS, new UserVM(result));
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
        }
    }
}
