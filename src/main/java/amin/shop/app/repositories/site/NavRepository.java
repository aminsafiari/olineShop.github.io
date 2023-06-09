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

    //for Auto save NUMBER itemOrder.
    //findTopByOrder = the final order, itemOrder.
    Nav findTopByOrderByItemOrderDesc();

    //the first have this itemOrder, give.
    Nav findTopByItemOrder(int itemOrder);


//-----------------------
//user pagination
//این خودش از CrudRepository , extend میکنه.
//    List<Nav> findAllByTitleContains(String title);//خبر
//-----------------------
//generic:Long -> type of id, class Nav.<<dataType use should nonPrimitive types>>
//CrudRepository-> use CRUD by jpa.
/*public interface NavRepository extends CrudRepository<Nav, Long> {

}*/
}
