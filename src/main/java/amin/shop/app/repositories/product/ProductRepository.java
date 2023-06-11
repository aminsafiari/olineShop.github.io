package amin.shop.app.repositories.product;

import amin.shop.app.entities.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.naming.directory.SearchResult;
import java.util.List;

@Repository
public interface ProductRepository
        extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    //at class Product I have (private ProductCategory category;).
    //this query is JPA-&-Hibernate.
    @Query("from Product where category.id = :categoryId")
    List<Product> findAllByCategory(long categoryId);

    /**
     * find all product that exist in this once category(categoryId).
     *
     * @param categoryId
     * @return long count
     */
    @Query("select count(id) from Product where category.id = :categoryId")
    long countByCategoryId(long categoryId);

    /**
     * find by categoryId, Along with pagination.
     *
     * @param categoryId
     * @param pageable
     * @return page<Product>
     */
    @Query(value = "from Product where category.id = :categoryId",
            countQuery = "select count(id) from Product where category.id = :categoryId")
    Page<Product> findAllByCategory(long categoryId, Pageable pageable);

    @Query("from Product where enable=true " +
                    "and (title like concat('%', :search,'%') " +
            "or description like concat('%', :search, '%'))")
    List<Product> findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(String search);

    /**
     * Find 6 products that were added by new date.
     *
     * @return Product List
     */
    List<Product> findTop6ByOrderByAddDateDesc();

    /**
     * Find 6 popular products.
     *
     * @return Product List
     */
    List<Product> findTop6ByOrderByVisitCountDesc();

    /**
     * Find 6 cheapest products.
     *
     * @return Product List
     */
    List<Product> findTop6ByOrderByPriceAsc();

    /**
     * Find 6 expensive products.
     *
     * @return Product List
     */
    List<Product> findTop6ByOrderByPriceDesc();

    long countByExistsIsTrue();

    long countByEnableIsTrue();
}