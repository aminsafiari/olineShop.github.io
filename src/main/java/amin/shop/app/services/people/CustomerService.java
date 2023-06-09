package amin.shop.app.services.people;

import amin.shop.app.entities.people.Customer;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.repositories.people.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    //region CRUD -> Read.

    // Teacher Code.
    public Customer getById(long id) {
        Optional<Customer> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Customer getByUserId(long userId) {
        Customer data = repository.findByUserId(userId);
        return data;
    }

    //important code (pagination). for work with big Data.
    public List<Customer> getAll(Integer pageSize, Integer pageNumber) {
        //pagination.
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Customer> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        //pagination.
        return repository.count();
    }

    // My Code.
    /*public Customer getById(long id) {
        Optional<Customer> data = repository.findById(id);
        if (data.isEmpty()) return null;
        return data.get();
    }*/

    //endregion

    //CRUD -> Create.
    //For Insert Customer use This Code.
    public Customer add(Customer data) {
        //Todo: need check and tick(such as UserService -> add) and need any(invoice, orderItem...).
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update Customer use This Code.
    public Customer update(Customer data) throws DataNotFoundException {
        Customer oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setAddress(data.getAddress());
        oldData.setEmail(data.getEmail());
        oldData.setFirstName(data.getFirstName());
        oldData.setLastName(data.getLastName());
        oldData.setMobile(data.getMobile());
        oldData.setPostalCode(data.getPostalCode());
        oldData.setTel(data.getTel());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long id) throws DataNotFoundException {
        Customer oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        repository.deleteById(id);
        return true;
    }

//--------------------------------
    /*public List<Customer> findAllOrderByItemOrder() {
        return repository.findAll(Sort.by("itemOrder"));
    }*/

//-------------------------------------------------------------
    /*public List<Customer> findAllOrderByItemOrder() {
        List<Customer> list = new ArrayList<>();
        repository.findAll(Sort.by("itemOrder")).forEach(list::add);
        return list;
    }*/
}
