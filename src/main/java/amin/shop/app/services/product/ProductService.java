package amin.shop.app.services.product;

import amin.shop.app.entities.products.Color;
import amin.shop.app.entities.products.Feature;
import amin.shop.app.entities.products.Product;
import amin.shop.app.entities.products.Size;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.helper.uimodels.ProductVM;
import amin.shop.app.repositories.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private FeatureService featureService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private SizeService sizeService;

    @Autowired
    private ProductCategoryService productCategoryService;

    //region CRUD -> Read.
    public List<Product> findAllByCategory(long categoryId) {
        return repository.findAllByCategory(categoryId);
    }

    public Product getById(long id) {
        Optional<Product> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public List<ProductVM> search(String keyword) {
        List<Product> all = repository.findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(keyword);
        List<ProductVM> vmList = new ArrayList<>();
        all.forEach(x -> vmList.add(new ProductVM(x)));
        return vmList;
    }

    /**
     * @return page<prodcut> to List type.
     */
    public List<Product> getAll(Integer pageSize, Integer pageNumber) {
        //pagination.
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Product> all = repository.findAll(page);
        return all.toList();
    }

    /**
     * find by categoryId.
     *
     * @return page<prodcutVM> to List type.
     */
    public List<ProductVM> getAllByCategoryId(long categoryId, Integer pageSize, Integer pageNumber) {
        //pagination.
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Product> all = repository.findAllByCategory(categoryId, page);
        List<ProductVM> vmList = new ArrayList<>();
        all.toList().forEach(x -> vmList.add(new ProductVM(x)));
        return vmList;
    }

    public long getAllCount() {
        //pagination.
        return repository.count();
    }

    public long getExistsCount() {
        //pagination.
        return repository.countByExistsIsTrue();
    }

    public long getEnableCount() {
        //pagination.
        return repository.countByEnableIsTrue();
    }

    public long getAllCountByCategoryId(long categoryId) {
        //pagination.
        return repository.countByCategoryId(categoryId);
    }

    //endregion

    //CRUD -> Create.

    /**
     * Find 6 products that were added by new date.
     *
     * @return ProductVM List
     */
    public List<ProductVM> findTop6ByOrderByAddDateDesc() {
        List<ProductVM> vmList = new ArrayList<>();
        List<Product> top6 = repository.findTop6ByOrderByAddDateDesc();
        top6.forEach(x -> vmList.add(new ProductVM(x)));

        return vmList;
    }

    /**
     * Find 6 popular products.
     *
     * @return ProductVM List
     */
    public List<ProductVM> findTop6ByOrderByVisitCountDesc() {
        List<ProductVM> vmList = new ArrayList<>();
        List<Product> top6 = repository.findTop6ByOrderByVisitCountDesc();
        top6.forEach(x -> vmList.add(new ProductVM(x)));

        return vmList;
    }

    /**
     * Find 6 cheapest products.
     *
     * @return ProductVM List
     */
    public List<ProductVM> findTop6ByOrderByPriceAsc() {
        List<ProductVM> vmList = new ArrayList<>();
        List<Product> top6 = repository.findTop6ByOrderByPriceAsc();
        top6.forEach(x -> vmList.add(new ProductVM(x)));

        return vmList;
    }

    /**
     * Find 6 expensive products.
     *
     * @return ProductVM List
     */
    public List<ProductVM> findTop6ByOrderByPriceDesc() {
        List<ProductVM> vmList = new ArrayList<>();
        List<Product> top6 = repository.findTop6ByOrderByPriceDesc();
        top6.forEach(x -> vmList.add(new ProductVM(x)));

        return vmList;
    }

    //For Insert Product use This Code.
    public Product add(ProductVM vm) {
        Product data = vm.convert();
        //because vm.getFeature or (...) is id long type, we need to get feature or (...) object and use from class FeatureService or (...).
        if (vm.getFeatures() != null)
            vm.getFeatures().forEach(x -> data.addFeature(featureService.getById(x)));
        if (vm.getColors() != null)
            vm.getColors().forEach(x -> data.addColor(colorService.getById(x)));
        if (vm.getSizes() != null)
            vm.getSizes().forEach(x -> data.addSize(sizeService.getById(x)));

        data.setCategory(productCategoryService.getById(vm.getCategoryId()));
        data.setAddDate(new Date());
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update Product use This Code.
    public Product update(ProductVM data) throws DataNotFoundException {
        Product oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setTitle(data.getTitle());
        oldData.setDescription(data.getDescription());
        oldData.setEnable(data.isEnable());
        oldData.setImage(data.getImage());
        oldData.setExist(data.isExists());
        oldData.setPrice(data.getPrice());

        //To avoid repetition in saving item
        if (data.getColors() != null) {
            for (long colorId : data.getColors()) {
                if (oldData.getColors().stream().map(x -> x.getId()).noneMatch(z -> z == colorId)) {
                    oldData.addColor(colorService.getById(colorId));
                }
            }
            //This code removes the colors in the new list from the list by using the removeIf method on the list of colors.
            //In addition, using removeIf directly reduces the probability of <<exception : ConcurrentModificationException>>, because this method performs the atomic removal operation for all elements in which the predicate condition is false.
            oldData.getColors().removeIf(color -> data.getColors().stream().noneMatch(x -> x == color.getId()));
        }
        //To avoid repetition in saving item
        if (data.getSizes() != null) {
            synchronized (oldData.getSizes()) {
                for (long sizeId : data.getSizes()) {
                    if (oldData.getSizes().stream().map(x -> x.getId()).noneMatch(z -> z == sizeId)) {
                        oldData.addSize(sizeService.getById(sizeId));
                    }
                }
                //This code removes the sizes in the new list from the list by using the removeIf method on the list of sizes.
                //In addition, using removeIf directly reduces the probability of <<exception : ConcurrentModificationException>>, because this method performs the atomic removal operation for all elements in which the predicate condition is false.
                oldData.getSizes().removeIf(size -> data.getSizes().stream().noneMatch(x -> x == size.getId()));
            }
        }
        //To avoid repetition in saving item
        if (data.getFeatures() != null) {
            synchronized (oldData.getFeatures()) {
                for (long featureId : data.getFeatures()) {
                    if (oldData.getFeatures().stream().map(x -> x.getId()).noneMatch(z -> z == featureId)) {
                        oldData.addFeature(featureService.getById(featureId));
                    }
                    //This code removes the features in the new list from the list by using the removeIf method on the list of features.
                    //In addition, using removeIf directly reduces the probability of <<exception : ConcurrentModificationException>>, because this method performs the atomic removal operation for all elements in which the predicate condition is false.
                    oldData.getFeatures().removeIf(feature -> data.getFeatures().stream().noneMatch(x -> x == feature.getId()));
                }
            }
        }
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    public boolean deleteById(long id) throws Exception {
        Product oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        //region before deleting Feature, This must be done.
        //Otherwise, because oldData is empty, we create an exception.
        //But the command <repository.deleteById(id);> must be executed earlier than <featureService.deleteById(x);>.
        List<Long> deletingFeatures = new ArrayList<>();
        oldData.getFeatures().forEach(x -> deletingFeatures.add(x.getId()));
        //endregion
        repository.deleteById(id);
        //When deleting the product, (ORM) deletes ((product-features,product-colors,product-sizes)) but not ((Feature <<objects>>)) so we have to delete it manually by this command
        //Removing the <<objects>> attribute must be removed after removing the product, otherwise you will throw an exception.
        deletingFeatures.forEach(x -> {
            try {
                featureService.deleteById(x, id);
            } catch (DataNotFoundException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    public Product increaseVisitCount(long id) throws DataNotFoundException {
        Product oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        oldData.setVisitCount(oldData.getVisitCount() + 1);
        return repository.save(oldData);
    }
}
