package amin.shop.app.repositories.orders;

import amin.shop.app.entities.orders.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository
        extends CrudRepository<Transactions, Long>, PagingAndSortingRepository<Transactions, Long> {
    @Query("from Transactions where trans_id = :transId")
    Transactions findByTrans(String transId);
}
