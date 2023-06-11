package amin.shop.app.services.site;

import amin.shop.app.entities.site.Nav;
import amin.shop.app.entities.site.Slider;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.repositories.site.SliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SliderService {

    @Autowired
    private SliderRepository repository;

    //region CRUD -> Read.
    public List<Slider> findAllOrderByItemOrder() {
        return repository.findAllByEnableIsTrue(Sort.by("itemOrder"));
    }

    public Slider getById(long id) {
        Optional<Slider> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public List<Slider> getAll(Integer pageSize, Integer pageNumber) {
        //pagination.
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("ItemOrder"));
        Page<Slider> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        //pagination.
        return repository.count();
    }

    //endregion

    //CRUD -> Create.
    //For Insert Slider use This Code.
    public Slider add(Slider data) throws Exception {
        if (data.getTitle() == null || data.getTitle().equals("")) {
            throw new Exception("Please enter title!");
        }
        if (data.getLink() == null || data.getLink().equals("")) {
            throw new Exception("Please enter title!");
        }
        //region for Auto save NUMBER itemOrder by Insert Nav data.
        Slider lastItem = repository.findTopByOrderByItemOrderDesc();
        if (lastItem != null && lastItem.getItemOrder() > 0) {
            data.setItemOrder(lastItem.getItemOrder() + 1);
        } else data.setItemOrder(1);
        //endregion
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update Slider use This Code.
    public Slider update(Slider data) throws DataNotFoundException {
        Slider oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setTitle(data.getTitle());
        oldData.setDescription(data.getDescription());
        oldData.setEnable(data.isEnable());
        oldData.setImage(data.getImage());
        oldData.setItemOrder(data.getItemOrder());
        oldData.setLink(data.getLink());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long id) throws DataNotFoundException {
        Slider oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        repository.deleteById(id);
        return true;
    }


    public Slider changeOrder(long id, int direction) throws Exception {
        Slider item = getById(id);
        if (item == null)
            throw new Exception("item not found!");
        switch (direction) {
            case 1 -> {
                //up
                if (item.getItemOrder() <= 1)
                    return item;
                Slider siblingItem = repository.findTopByItemOrder(item.getItemOrder() - 1);
                //if siblingItem is null, mean not exist then item set (item.getItemOrder() - 1).
                if (siblingItem == null) {
                    item.setItemOrder(item.getItemOrder() - 1);
                } else {
                    item.setItemOrder(siblingItem.getItemOrder());
                    siblingItem.setItemOrder(item.getItemOrder() + 1);
                    repository.save(siblingItem);
                }
            }
            case 0 -> {
                //down
                Slider siblingItem2 = repository.findTopByItemOrder(item.getItemOrder() + 1);
                //if siblingItem is null, mean not exist then item set (item.getItemOrder() - 1).
                if (siblingItem2 == null) {
                    //check last item ,if exist then change ,if not exist then don't change itemOrder for item.
                    Slider lastOrderItem = repository.findTopByOrderByItemOrderDesc();
                    if (item.getItemOrder() < lastOrderItem.getItemOrder())
                        item.setItemOrder(item.getItemOrder() + 1);
                } else {
                    item.setItemOrder(siblingItem2.getItemOrder());
                    siblingItem2.setItemOrder(item.getItemOrder() - 1);
                    repository.save(siblingItem2);
                }
            }
        }
        //item save, because at tow case 0&1, item should save.
        repository.save(item);
        return item;
    }

}
