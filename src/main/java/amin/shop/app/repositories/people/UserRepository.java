package amin.shop.app.repositories.people;

import amin.shop.app.entities.people.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    //for login.
    User findFirstByUsernameAndPassword(String username, String password);

    User findFirstByUsername(String username);

    long countByEnableIsTrue();
}
