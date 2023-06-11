package amin.shop.app.services.site;

import amin.shop.app.entities.site.Content;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.repositories.site.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository repository;

    //region CRUD -> Read.
    public Content findByKey(String key) {
        return repository.findFirstByKey(key);
    }

    public List<Content> getAllDate() {
        return repository.findAll();
    }

    public List<Content> getAll(Integer pageSize, Integer pageNumber) {
        //pagination.
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Content> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        //pagination.
        return repository.count();
    }

    public Content getById(long id) {
        Optional<Content> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    //endregion

    //CRUD -> Create.
    //For Insert Content use This Code.
    public Content add(Content data) {
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update Content use This Code.
    public Content update(Content data) throws DataNotFoundException {
        Content oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setValue(data.getValue());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long id) throws DataNotFoundException {
        Content oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        repository.deleteById(id);
        return true;
    }

}
