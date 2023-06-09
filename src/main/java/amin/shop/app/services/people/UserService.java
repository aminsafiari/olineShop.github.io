package amin.shop.app.services.people;

import amin.shop.app.entities.people.User;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.helper.utils.SecurityUtils;
import amin.shop.app.repositories.people.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public User auth(String username, String password) {
        try {
            password = securityUtils.encryptSHA1(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return repository.findFirstByUsernameAndPassword(username, password);
    }

    public User getByUsername(String username) {
        return repository.findFirstByUsername(username);
    }

    //important code (pagination). for work with big Data.
    public List<User> getAll(Integer pageSize, Integer pageNumber) {
        //pagination.
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<User> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        //pagination.
        return repository.count();
    }

    public long getEnableCount() {
        //pagination.
        return repository.countByEnableIsTrue();
    }
    //region CRUD -> Read.

    // Teacher Code.
    public User getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    // My Code.
    /*public User getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isEmpty()) return null;
        return data.get();
    }*/

    //endregion

    //CRUD -> Create.
    //For Insert User use This Code.
    public User add(User data) throws Exception {
        if (data.getUsername() == null || data.getUsername().equals("")) {
            throw new Exception("Please enter username!");
        }
        User oldUser = getByUsername(data.getUsername());
        if (oldUser != null) {
            throw new Exception("Duplicated username, please change your username!");
        }
        if (data.getPassword() == null || data.getPassword().equals("")) {
            throw new Exception("Please enter password!");
        }
        if (data.getEmail() == null || data.getEmail().equals("")) {
            throw new Exception("Please enter email!");
        }
        //TODO: ex: check password strength(send this part code from portal java fro teacher).
        data.setPassword(securityUtils.encryptSHA1(data.getPassword()));
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update User use This Code.
    public User update(User data) throws DataNotFoundException, NoSuchAlgorithmException {
        User oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setEmail(data.getEmail());
        oldData.setEnable(data.isEnable());
        oldData.setFirstName(data.getFirstName());
        oldData.setLastName(data.getLastName());
        if (data.getPassword() != null && !data.getPassword().equals("")) {
            oldData.setPassword(securityUtils.encryptSHA1(data.getPassword()));
        }
        //better for change password have other method.
        //        oldData.setPassword(data.getPassword());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long id) throws DataNotFoundException {
        User oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        repository.deleteById(id);
        return true;
    }

    public User changePassword(long id, String oldPassword, String newPassword)
            throws Exception {
        //hash
        try {
            oldPassword = securityUtils.encryptSHA1(oldPassword);
            newPassword = securityUtils.encryptSHA1(newPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = getById(id);
        if (user == null) {
            throw new DataNotFoundException("User not found!");
        }
        if (!user.getPassword().equals(oldPassword)) {
            throw new Exception("invalid old password!");
        }
        user.setPassword(newPassword);
        return repository.save(user);
    }
}
