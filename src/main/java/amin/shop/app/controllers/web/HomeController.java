package amin.shop.app.controllers.web;

import amin.shop.app.entities.orders.Transactions;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.services.orders.PaymentService;
import amin.shop.app.services.orders.TransactionsService;
import amin.shop.app.services.product.ProductService;
import amin.shop.app.services.site.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("")
    public String index() {
        return "home";
    }

    @GetMapping("products")
    public String products() {
        return "products";
    }

    @GetMapping("blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    //For show single page blogs.
    @GetMapping("blog/{id}")
    public String blogInfo(@PathVariable Long id, Model model) {
        model.addAttribute("dataId", id);
        try {
            blogService.increaseVisitCount(id);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return "blogInfo";
    }

    //For show collection page products.
    @GetMapping("products/{id}")
    public String productCat(@PathVariable Long id, Model model) {
        model.addAttribute("dataId", id);
        return "productsCategory";
    }

    //For show single page products.
    @GetMapping("product/{id}")
    public String product(@PathVariable Long id, Model model) {
        model.addAttribute("dataId", id);
        try {
            productService.increaseVisitCount(id);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return "product";
    }

    @GetMapping("basket")
    public String basket() {
        return "basket";
    }

    @GetMapping("payment")
    public String payment() {
        return "payment";
    }

    @GetMapping("/verify")
    public String verify(@RequestParam String trans_id,
                         @RequestParam String order_id,
                         @RequestParam Integer amount,
                         @RequestParam String np_status,
                         Model model) {
        try {
            Transactions transactions = transactionsService.getByTransId(trans_id);
            if (transactions == null) {
                throw new Exception("Data not found!");
            }
            transactions.setOrder_id(order_id);
            transactions.setAmount(amount);
            transactions.setVerifyStatus(np_status);
            if ((transactions.getShaparak_Ref_Id() == null
                    || transactions.getShaparak_Ref_Id().equals(""))
                    && np_status.toLowerCase().equals("OK".toLowerCase())) {

                Transactions result = paymentService.doVerify(transactions);
                model.addAttribute("transaction", result);
            } else {
                model.addAttribute("transaction", transactions);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //if have different error, show error.
            if (e != null && e.getMessage() != null)
                model.addAttribute("Message", e.getMessage());
        }
        return "verify";
    }
}
