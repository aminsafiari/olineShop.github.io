package amin.shop.app.repositories.product;

import amin.shop.app.entities.products.ProductCategory;
import amin.shop.app.entities.site.Slider;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductCategoryRepository
        extends CrudRepository<ProductCategory, Long>, PagingAndSortingRepository<ProductCategory, Long> {
    List<ProductCategory> findAllByEnableIsTrue(Sort sort);

}
