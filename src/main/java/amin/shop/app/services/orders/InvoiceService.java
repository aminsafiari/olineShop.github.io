package amin.shop.app.services.orders;

import amin.shop.app.entities.orders.Invoice;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.repositories.orders.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    public List<Invoice> findByCustomer(long customerId) {
        return repository.findAllByCustomer(customerId);
    }

    public List<Invoice> findByCustomer(long customerId, Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Invoice> all = repository.findAllByCustomer(customerId, page);
        return all.toList();
    }

    //region CRUD -> Read.

    public Invoice getById(long id) {
        Optional<Invoice> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public long getAllCount() {
        //pagination.
        return repository.count();
    }

    public long getPayedCount() {
        return repository.countByPayedDateIsNotNull();
    }

    //endregion

    //CRUD -> Create.
    //For Insert Invoice use This Code.
    public Invoice add(Invoice data) {
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update Invoice use This Code.
    public Invoice update(Invoice data) throws DataNotFoundException {
        Invoice oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setPayedDate(data.getPayedDate());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long id) throws DataNotFoundException {
        Invoice oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        repository.deleteById(id);
        return true;
    }

}