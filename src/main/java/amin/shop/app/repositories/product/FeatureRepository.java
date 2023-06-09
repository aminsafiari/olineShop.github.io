package amin.shop.app.repositories.product;

import amin.shop.app.entities.products.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Long> {

}
