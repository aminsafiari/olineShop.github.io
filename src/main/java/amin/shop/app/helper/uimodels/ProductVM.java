package amin.shop.app.helper.uimodels;

import amin.shop.app.entities.products.Color;
import amin.shop.app.entities.products.Feature;
import amin.shop.app.entities.products.Product;
import amin.shop.app.entities.products.Size;

import javax.persistence.Column;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * for insert products.
 */
public class ProductVM {
    private long id;
    private String title;
    @Column(length = 4000)
    private String description;
    private long price;
    private String image;
    //is this field enable or unable.
    private boolean enable;
    //when add any new field, better use this code to success error in database.
    @Column(nullable = true)
    private boolean exists;
    private long visitCount;
    private long categoryId;
    private Date addDate;
    //cann`t set ((colors, sizes, features)) in class Product then set where.
    //we uses from this with ID's.
    private List<Long> colors;
    private List<Long> sizes;
    private List<Long> features;
    //for storage full item Features.
    private List<Feature> featuresDataList;
    private List<Color> colorsList;
    private List<Size> sizesList;

    public ProductVM() {
    }

    //This is constructor for change or convert Product Object to ProductVM Objects.
    public ProductVM(Product product) {
        setId(product.getId());
        setTitle(product.getTitle());
        setDescription(product.getDescription());
        setPrice(product.getPrice());
        setImage(product.getImage());
        setEnable(product.isEnable());
        setExists(product.isExist());
        setVisitCount(product.getVisitCount());
        setCategoryId(product.getCategory().getId());
        setAddDate(product.getAddDate());
        product.getFeatures().stream().forEach(x -> getFeaturesDataList().add(x));
        product.getColors().stream().forEach(x -> getColorsList().add(x));
        product.getSizes().stream().forEach(x -> getSizesList().add(x));
        setColors(product.getColors().stream().map(x -> x.getId()).collect(Collectors.toList()));
        setSizes(product.getSizes().stream().map(x -> x.getId()).collect(Collectors.toList()));
        setFeatures(product.getFeatures().stream().map(x -> x.getId()).collect(Collectors.toList()));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(long visitCount) {
        this.visitCount = visitCount;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getAddDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(addDate);
    }

    public List<Long> getColors() {
        return colors;
    }

    public void setColors(List<Long> colors) {
        this.colors = colors;
    }

    public List<Long> getSizes() {
        return sizes;
    }

    public void setSizes(List<Long> sizes) {
        this.sizes = sizes;
    }

    public List<Long> getFeatures() {
        return features;
    }

    public void setFeatures(List<Long> features) {
        this.features = features;
    }

    public List<Feature> getFeaturesDataList() {
        if (featuresDataList == null)
            featuresDataList = new ArrayList<>();
        return featuresDataList;
    }

    public void setFeaturesDataList(List<Feature> featuresDataList) {
        this.featuresDataList = featuresDataList;
    }

    public List<Color> getColorsList() {
        if (colorsList == null) {
            colorsList = new ArrayList<>();
        }
        return colorsList;
    }

    public void setColorsList(List<Color> colorList) {
        this.colorsList = colorList;
    }

    public List<Size> getSizesList() {
        if (sizesList == null) {
            sizesList = new ArrayList<>();
        }
        return sizesList;
    }

    public void setSizesList(List<Size> sizeList) {
        this.sizesList = sizeList;
    }

    //this work change or convert ProductVM object to Product object.
    public Product convert() {
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setPrice(getPrice());
        product.setImage(getImage());
        product.setEnable(isEnable());
        product.setExist(isExists());
        product.setVisitCount(getVisitCount());
        return product;
    }

}
