package amin.shop.app.services.site;

import amin.shop.app.entities.site.Blog;
import amin.shop.app.enums.BlogStatus;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.repositories.site.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository repository;

    //region CRUD -> Read.
    public List<Blog> search(String keyword) {
        return repository.findAllByTitleContainsOrDescriptionContains(keyword);
    }

    public Blog getById(long id) {
        Optional<Blog> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public List<Blog> getAll(Integer pageSize, Integer pageNumber) {
        //pagination.
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("publishDate"));
        Page<Blog> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        //pagination.
        return repository.count();
    }

    //To display the blog page on the home page.
    public List<Blog> getAllData(Integer pageSize, Integer pageNumber) {
        //pagination.
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("publishDate").descending());
        Page<Blog> all = repository.findAllByStatusAndPublishDateLessThanEqual(BlogStatus.PUBLISHED, new Date(), page);
        return all.toList();
    }

    public long getAllCountData() {
        //pagination.
        return repository.countAllByStatusAndPublishDateLessThanEqual(BlogStatus.PUBLISHED, new Date());
    }

    //endregion

    //CRUD -> Create.
    //For Insert Blog use This Code.
    public Blog add(Blog data) throws Exception {
        if (data.getTitle() == null || data.getTitle().equals(""))
            throw new Exception("Please fill title field!");
        data.setVisitCount(0);
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update Blog use This Code.
    public Blog update(Blog data) throws DataNotFoundException {
        Blog oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setTitle(data.getTitle());
        oldData.setDescription(data.getDescription());
        oldData.setImage(data.getImage());
        oldData.setDescription(data.getDescription());
        oldData.setSubtitle(data.getSubtitle());
        oldData.setStatus(data.getStatus());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long id) throws DataNotFoundException {
        Blog oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        repository.deleteById(id);
        return true;
    }

    public Blog increaseVisitCount(long id) throws DataNotFoundException {
        Blog oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        oldData.setVisitCount(oldData.getVisitCount() + 1);
        return repository.save(oldData);
    }

}
