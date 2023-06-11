package amin.shop.app.services.product;

import amin.shop.app.entities.products.Color;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.repositories.product.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {

    @Autowired
    private ColorRepository repository;

    //region CRUD -> Read.

    public Color getById(long id) {
        Optional<Color> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public List<Color> getAll() {
        return repository.findAll();
    }

    //important code (pagination). for work with big Data.
    public List<Color> getAll(Integer pageSize, Integer pageNumber) {
        //pagination.
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Color> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        //pagination.
        return repository.count();
    }

    //endregion

    //CRUD -> Create.
    //For Insert Color use This Code.
    public Color add(Color data) {
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update Color use This Code.
    public Color update(Color data) throws DataNotFoundException {
        Color oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setValue(data.getValue());
        oldData.setName(data.getName());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long id) throws DataNotFoundException {
        Color oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        repository.deleteById(id);
        return true;
    }
}
