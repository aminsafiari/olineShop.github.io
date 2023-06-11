package amin.shop.app.controllers.api.products;

import amin.shop.app.entities.products.Feature;
import amin.shop.app.enums.ResponseStatus;
import amin.shop.app.helper.ui.ServiceResponse;
import amin.shop.app.services.product.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feature")
public class FeatureController {
    @Autowired
    private FeatureService service;

    @GetMapping("/{id}")
    public ServiceResponse<Feature> search(@PathVariable long id) {
        try {
            Feature result = service.getById(id);
            return new ServiceResponse<Feature>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Feature>(e);
        }
    }

    @PostMapping("/")
    public ServiceResponse<Feature> add(@RequestBody Feature data) {
        try {
            Feature result = service.add(data);
            return new ServiceResponse<Feature>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Feature>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<Feature> update(@RequestBody Feature data) {
        try {
            Feature result = service.update(data);
            return new ServiceResponse<Feature>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Feature>(e);
        }
    }

    @DeleteMapping("/{featureId}")
    public ServiceResponse<Boolean> delete(@PathVariable long featureId, @RequestParam long productId) {
        try {
            boolean result = service.deleteById(featureId, productId);
            return new ServiceResponse<Boolean>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Boolean>(e);
        }
    }

}
