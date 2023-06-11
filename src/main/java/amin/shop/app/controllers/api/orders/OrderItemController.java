package amin.shop.app.controllers.api.orders;

import amin.shop.app.entities.orders.OrderItem;
import amin.shop.app.enums.ResponseStatus;
import amin.shop.app.helper.ui.ServiceResponse;
import amin.shop.app.services.orders.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService service;

    @GetMapping("/{id}")
    public ServiceResponse<OrderItem> search(@PathVariable long id) {
        try {
            OrderItem result = service.getById(id);
            return new ServiceResponse<OrderItem>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<OrderItem>(e);
        }
    }

    @PostMapping("/")
    public ServiceResponse<OrderItem> add(@RequestBody OrderItem data) {
        try {
            OrderItem result = service.add(data);
            return new ServiceResponse<OrderItem>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<OrderItem>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<OrderItem> update(@RequestBody OrderItem data) {
        try {
            OrderItem result = service.update(data);
            return new ServiceResponse<OrderItem>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<OrderItem>(e);
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

}
