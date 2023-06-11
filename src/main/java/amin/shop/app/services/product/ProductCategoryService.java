package amin.shop.app.services.product;

import amin.shop.app.entities.products.Product;
import amin.shop.app.entities.products.ProductCategory;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.repositories.product.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private ProductService productService;


    //region CRUD -> Read.
    public List<ProductCategory> findAllOrderByItemOrder() {
        return repository.findAllByEnableIsTrue(Sort.by("id"));
    }

    public ProductCategory getById(long id) {
        Optional<ProductCategory> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    //important code (pagination). for work with big Data.
    public List<ProductCategory> getAll(Integer pageSize, Integer pageNumber) {
        //pagination.
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<ProductCategory> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        //pagination.
        return repository.count();
    }

    //endregion

    //CRUD -> Create.
    //For Insert ProductCategory use This Code.
    public ProductCategory add(ProductCategory data) {
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update ProductCategory use This Code.
    public ProductCategory update(ProductCategory data) throws DataNotFoundException {
        ProductCategory oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setTitle(data.getTitle());
        oldData.setDescription(data.getDescription());
        oldData.setEnable(data.isEnable());
        oldData.setImage(data.getImage());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long id) throws Exception {
        ProductCategory oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        List<Product> productList = productService.findAllByCategory(id);
        if (productList.size() > 0) throw new Exception("Please delete products in this category at first!");
        repository.deleteById(id);
        return true;
    }
}
