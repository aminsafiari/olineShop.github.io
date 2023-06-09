package amin.shop.app.repositories.site;

import amin.shop.app.entities.site.Content;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//user pagination
//این خودش از CrudRepository , extend میکنه.
public interface ContentRepository
        extends PagingAndSortingRepository<Content, Long>, CrudRepository<Content, Long> {
    //return find first stuff by key.
    Content findFirstByKey(String key);

    @Override
    List<Content> findAll();

//-----------------------
    //    List<Content> findAllByTitleContains(String title);//خبر
}

//-----------------------
//generic:Long -> type of id, class Nav.<<dataType use should nonPrimitive types>>
//CrudRepository-> use CRUD by jpa.
/*public interface ContentRepository extends CrudRepository<Content, Long> {

}*/
