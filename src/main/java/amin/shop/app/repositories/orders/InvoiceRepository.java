package amin.shop.app.repositories.orders;

import amin.shop.app.entities.orders.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    @Query("from Invoice where customer.id = :customerId")
    List<Invoice> findAllByCustomer(long customerId);

    @Query(value = "from Invoice where customer.id = :customerId",
            countQuery = "select count(id) from Invoice where customer.id = : customerId")
    Page<Invoice> findAllByCustomer(long customerId, Pageable pageable);

    long countByPayedDateIsNotNull();
}
