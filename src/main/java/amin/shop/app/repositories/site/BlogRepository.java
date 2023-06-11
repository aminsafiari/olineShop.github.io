package amin.shop.app.repositories.site;

import amin.shop.app.entities.site.Blog;
import amin.shop.app.enums.BlogStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
//user pagination
public interface BlogRepository
        extends CrudRepository<Blog, Long>, PagingAndSortingRepository<Blog, Long> {

    @Query("from Blog where " +
            "title like concat('%',:search,'%') " +
            "or description like concat('%', :search, '%') ")
    List<Blog> findAllByTitleContainsOrDescriptionContains(String search);

    //for show in blog page in home page.
    Page<Blog> findAllByStatusAndPublishDateLessThanEqual(BlogStatus status, Date publishDate, Pageable pageable);

    //for show in blog page in home page.
    Long countAllByStatusAndPublishDateLessThanEqual(BlogStatus status, Date publishDate);

}
