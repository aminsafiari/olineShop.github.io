package amin.shop.app.services.orders;

import amin.shop.app.entities.orders.OrderItem;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.repositories.orders.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    //region CRUD -> Read.

    // Teacher Code.
    public OrderItem getById(long id) {
        Optional<OrderItem> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    // My Code.
    /*public OrderItem getById(long id) {
        Optional<OrderItem> data = repository.findById(id);
        if (data.isEmpty()) return null;
        return data.get();
    }*/

    //endregion

    //CRUD -> Create.
    //For Insert OrderItem use This Code.
    public OrderItem add(OrderItem data) {
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update OrderItem use This Code.
    public OrderItem update(OrderItem data) throws DataNotFoundException {
        OrderItem oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setCount(data.getCount());
        oldData.setPrice(data.getPrice());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long id) throws DataNotFoundException {
        OrderItem oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        repository.deleteById(id);
        return true;
    }

//--------------------------------
    /*public List<OrderItem> findAllOrderByItemOrder() {
        return repository.findAll(Sort.by("itemOrder"));
    }*/

//-------------------------------------------------------------
    /*public List<OrderItem> findAllOrderByItemOrder() {
        List<OrderItem> list = new ArrayList<>();
        repository.findAll(Sort.by("itemOrder")).forEach(list::add);
        return list;
    }*/
}
