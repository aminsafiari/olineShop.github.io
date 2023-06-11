package amin.shop.app.services.product;

import amin.shop.app.entities.products.Feature;
import amin.shop.app.entities.products.Product;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.repositories.product.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository repository;
    @Autowired
    private ProductService productService;

    //region CRUD -> Read.

    public Feature getById(long id) {
        Optional<Feature> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    //endregion

    //CRUD -> Create.
    //For Insert Feature use This Code.
    public Feature add(Feature data) {
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update Feature use This Code.
    public Feature update(Feature data) throws DataNotFoundException {
        Feature oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setValue(data.getValue());
        oldData.setKey(data.getKey());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long featureId, long productId) throws DataNotFoundException {
        Feature oldFeatures = getById(featureId);
        if (oldFeatures == null) {
            throw new DataNotFoundException("data with id " + featureId + " not found!");
        }
        if (productId != -1) {
            Product oldProduct = productService.getById(productId);
            if (oldProduct != null && oldProduct.getFeatures() != null) {
                if (oldProduct.getFeatures().stream().map(x -> x.getId()).anyMatch(z -> z == featureId)) {
                    oldProduct.removeFeature(featureId);
                }
            }
        }
        repository.deleteById(featureId);
        return true;
    }
}
