package amin.shop.app.controllers.api.site;

import amin.shop.app.entities.site.Nav;
import amin.shop.app.enums.ResponseStatus;
import amin.shop.app.helper.ui.ServiceResponse;
import amin.shop.app.services.site.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nav")
public class NavController {
    @Autowired
    private NavService service;

    @GetMapping("")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Nav> get() {
        try {
            List<Nav> result = service.findAllOrderByItemOrder();
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Nav>(e);
        }
    }

    // pagination.
    @GetMapping("/getAll")
    public ServiceResponse<Nav> getAll(@RequestParam Integer pageSize,
                                       @RequestParam Integer pageNumber) {
        try {
            List<Nav> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Nav>(e);
        }
    }

    @GetMapping("/{id}")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Nav> getById(@PathVariable long id) {
        try {
            Nav result = service.getById(id);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Nav>(e);
        }
    }

    @PostMapping("/changeOrder/{id}/{direction}")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Nav> changeOrder(@PathVariable long id, @PathVariable int direction) {
        try {
            Nav result = service.changeOrder(id, direction);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Nav>(e);
        }
    }

    @PostMapping("/")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Nav> add(@RequestBody Nav data) {
        try {
            Nav result = service.add(data);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Nav>(e);
        }
    }

    //you can update handle with @PutMapping is better and true, but for this simple client environment work use this annotation.
    @PutMapping("/")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Nav> update(@RequestBody Nav data) {
        try {
            Nav result = service.update(data);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Nav>(e);
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
}
