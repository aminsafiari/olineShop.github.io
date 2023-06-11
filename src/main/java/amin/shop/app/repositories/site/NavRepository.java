package amin.shop.app.repositories.site;

import amin.shop.app.entities.site.Nav;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavRepository
        extends PagingAndSortingRepository<Nav, Long>, CrudRepository<Nav, Long> {
    List<Nav> findAllByEnableIsTrue(Sort sort);

    //findTopByOrder = the final order, itemOrder.
    Nav findTopByOrderByItemOrderDesc();

    Nav findTopByItemOrder(int itemOrder);

}
