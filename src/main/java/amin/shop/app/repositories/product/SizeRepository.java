package amin.shop.app.repositories.product;

import amin.shop.app.entities.products.Size;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository
        extends CrudRepository<Size, Long>, PagingAndSortingRepository<Size, Long> {
    //Override for return List type.
    @Override
    List<Size> findAll();
}
