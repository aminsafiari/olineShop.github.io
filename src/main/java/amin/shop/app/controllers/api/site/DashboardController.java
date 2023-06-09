package amin.shop.app.controllers.api.site;

import amin.shop.app.enums.ResponseStatus;
import amin.shop.app.helper.ui.ServiceResponse;
import amin.shop.app.helper.uimodels.DashboardVM;
import amin.shop.app.services.orders.InvoiceService;
import amin.shop.app.services.people.CustomerService;
import amin.shop.app.services.people.UserService;
import amin.shop.app.services.product.ProductCategoryService;
import amin.shop.app.services.product.ProductService;
import amin.shop.app.services.site.BlogService;
import amin.shop.app.services.site.NavService;
import amin.shop.app.services.site.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private NavService navService;
    @Autowired
    private SliderService sliderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("")
    public ServiceResponse<DashboardVM> get() {
        try {
            DashboardVM result = new DashboardVM();
            result.setNavigations(navService.getAllCount());
            result.setSliders(sliderService.getAllCount());
            result.setUsers(userService.getAllCount());
            result.setCustomers(customerService.getAllCount());
            result.setActiveBlog(blogService.getAllCountData());
            result.setAllBlog(blogService.getAllCount());
            result.setCategories(productCategoryService.getAllCount());
            result.setProducts(productService.getAllCount());
            result.setExistsProducts(productService.getExistsCount());
            result.setEnableProducts(productService.getEnableCount());
            result.setUsers(userService.getAllCount());
            result.setActiveUsers(userService.getEnableCount());
            result.setCustomers(customerService.getAllCount());
            result.setInvoices(invoiceService.getAllCount());
            result.setPayedInvoices(invoiceService.getPayedCount());

            return new ServiceResponse<DashboardVM>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            //not god this exception handle. you need to search how exception handling with annotation and save to database(create new entities ExceptionLogs) or write filter,
            //and return << ServiceResponse<...>(e);
            return new ServiceResponse<DashboardVM>(e);
        }
    }
}
