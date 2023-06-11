package amin.shop.app.repositories.site;

import amin.shop.app.entities.site.Content;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository
        extends PagingAndSortingRepository<Content, Long>, CrudRepository<Content, Long> {
    //return: find first stuff by key.
    Content findFirstByKey(String key);

    @Override
    List<Content> findAll();

}