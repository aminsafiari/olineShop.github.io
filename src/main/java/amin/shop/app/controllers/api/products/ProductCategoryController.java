package amin.shop.app.controllers.api.products;

import amin.shop.app.entities.products.ProductCategory;
import amin.shop.app.enums.ResponseStatus;
import amin.shop.app.helper.ui.ServiceResponse;
import amin.shop.app.services.product.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService service;

    @GetMapping("")
    public ServiceResponse<ProductCategory> get() {
        try {
            List<ProductCategory> result = service.findAllOrderByItemOrder();
            return new ServiceResponse<ProductCategory>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductCategory>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceResponse<ProductCategory> getAll(@RequestParam Integer pageSize,
                                                   @RequestParam Integer pageNumber) {
        try {
            List<ProductCategory> result = service.getAll(pageSize, pageNumber);
            long totalCount = service.getAllCount();
            return new ServiceResponse<ProductCategory>(ResponseStatus.SUCCESS, result, totalCount);
        } catch (Exception e) {
            return new ServiceResponse<ProductCategory>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<ProductCategory> search(@PathVariable long id) {
        try {
            ProductCategory result = service.getById(id);
            return new ServiceResponse<ProductCategory>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductCategory>(e);
        }
    }

    @PostMapping("/")
    public ServiceResponse<ProductCategory> add(@RequestBody ProductCategory data) {
        try {
            ProductCategory result = service.add(data);
            return new ServiceResponse<ProductCategory>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductCategory>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<ProductCategory> update(@RequestBody ProductCategory data) {
        try {
            ProductCategory result = service.update(data);
            return new ServiceResponse<ProductCategory>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductCategory>(e);
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
