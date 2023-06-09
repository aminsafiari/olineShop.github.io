package amin.shop.app.controllers.api.site;

import amin.shop.app.entities.site.Slider;
import amin.shop.app.enums.ResponseStatus;
import amin.shop.app.helper.ui.ServiceResponse;
import amin.shop.app.services.site.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slider")
public class SliderController {
    @Autowired
    private SliderService service;

    @GetMapping("")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Slider> get() {
        try {
            List<Slider> result = service.findAllOrderByItemOrder();
            return new ServiceResponse<Slider>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Slider>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceResponse<Slider> getAll(@RequestParam Integer pageSize,
                                          @RequestParam Integer pageNumber) {
        try {
            List<Slider> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceResponse<Slider>(ResponseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Slider>(e);
        }
    }

    @GetMapping("/{id}")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Slider> getById(@PathVariable long id) {
        try {
            Slider result = service.getById(id);
            return new ServiceResponse<Slider>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Slider>(e);
        }
    }

    @PostMapping("/")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Slider> add(@RequestBody Slider data) {
        try {
            Slider result = service.add(data);
            return new ServiceResponse<Slider>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Slider>(e);
        }
    }

    @PostMapping("/changeOrder/{id}/{direction}")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Slider> changeOrder(@PathVariable long id, @PathVariable int direction) {
        try {
            Slider result = service.changeOrder(id, direction);
            return new ServiceResponse<Slider>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Slider>(e);
        }
    }

    //you can update handle with @PutMapping is better and true, but for this simple client environment work use this annotation.
    @PutMapping("/")
    //if @PostMapping use @RequestBody (Read : Query String -> Body)
    //if @GetMapping use @RequestPram (Read : Query String -> Header)
    public ServiceResponse<Slider> update(@RequestBody Slider data) {
        try {
            Slider result = service.update(data);
            return new ServiceResponse<Slider>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<Slider>(e);
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
