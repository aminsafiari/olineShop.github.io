package amin.shop.app.repositories.product;

import amin.shop.app.entities.products.Color;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends CrudRepository<Color, Long>, PagingAndSortingRepository<Color, Long> {
    @Override
    List<Color> findAll();
}
