package amin.shop.app.repositories.site;

import amin.shop.app.entities.site.Nav;
import amin.shop.app.entities.site.Slider;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//++JpaRepository have CrudRepository and PagingAndSortingRepository.++
public interface SliderRepository
        extends CrudRepository<Slider, Long>, PagingAndSortingRepository<Slider, Long> {
    List<Slider> findAllByEnableIsTrue(Sort sort);

    //for Auto save NUMBER itemOrder.
    //findTopByOrder = the final order, itemOrder.
    Slider findTopByOrderByItemOrderDesc();

    //the first have this itemOrder, give.
    Slider findTopByItemOrder(int itemOrder);


//    List<Slider> findAll();
}
