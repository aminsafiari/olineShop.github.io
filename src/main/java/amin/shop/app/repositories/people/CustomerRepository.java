package amin.shop.app.repositories.people;

import amin.shop.app.entities.people.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository
        extends CrudRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {
    @Query("from Customer where user.id = :userId")
    Customer findByUserId(long userId);
}
