package amin.shop.app.services.orders;

import amin.shop.app.entities.orders.Invoice;
import amin.shop.app.entities.orders.OrderItem;
import amin.shop.app.entities.orders.Transactions;
import amin.shop.app.entities.people.Customer;
import amin.shop.app.entities.people.User;
import amin.shop.app.entities.products.Product;
import amin.shop.app.enums.PaymentType;
import amin.shop.app.helper.payment.nextpay.controllers.NexPayService;
import amin.shop.app.helper.uimodels.PaymentVM;
import amin.shop.app.helper.uimodels.StartPaymentVM;
import amin.shop.app.services.people.CustomerService;
import amin.shop.app.services.people.UserService;
import amin.shop.app.services.product.ColorService;
import amin.shop.app.services.product.ProductService;
import amin.shop.app.services.product.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderItemService orderItemService;
    //For data be Validate, use this three Autowired.
    @Autowired
    private ColorService colorService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private NexPayService nexPayService;
    @Autowired
    private TransactionsService transactionsService;

    public StartPaymentVM addPayment(PaymentVM data) throws Exception {
        //TODO: task must to do.
        //we need do this 5 work until connect to Payment gateway.
        //1- insert user.
        //2- insert customer.
        //3- insert order item(Basket Information).
        //4- insert invoice.
        //5- (in controller),redirect to bank(Payment gateway).
        StartPaymentVM response = new StartPaymentVM();
        List<OrderItem> orderItemList = new ArrayList<>();
        Customer customerInfo = null;
        if (data.getCustomerId() == 0) {
            User userInfo = userService.add(data.getCustomer().getUserInfo());
            Customer customer = data.getCustomer().getCustomerInfo();
            customer.setUser(userInfo);
            customerInfo = customerService.add(customer);
        } else {
            customerInfo = customerService.getById(data.getCustomerId());
        }
        Customer finalCustomerInfo = customerInfo;
        data.getOrderItemVMS().forEach(x -> {
            OrderItem orderItem = new OrderItem();
            //for validate data.
            orderItem.setColor(colorService.getById(x.getColorId()));
            orderItem.setSize(sizeService.getById(x.getSizeId()));
            orderItem.setCount(x.getCount());
            orderItem.setCustomer(finalCustomerInfo);
            //This will make at least one less query to the database(such as part setPrice).
            Product product = productService.getById(x.getProductId());
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItemService.add(orderItem);
            orderItemList.add(orderItem);
            response.setAmount((int) (response.getAmount() + (orderItem.getPrice() * orderItem.getCount())));
        });
        Invoice invoice = new Invoice();
        invoice.setCustomer(customerInfo);
        invoice.setInvoiceDate(new Date());
        invoice.setOrderItem(orderItemList);
        invoice.setPayedDate(null);
        Invoice invoiceInfo = invoiceService.add(invoice);
        response.setDescription(data.getOrderItemVMS().size() + " products for " + data.getCustomer().getFullName());
        response.setMobile(customerInfo.getMobile());
        response.setCustomer(customerInfo);
        response.setInvoice(invoice);
        response.setPaymentType(data.getPaymentType());
        //OrderId is invoice_id.
        response.setOrderId(String.valueOf(invoiceInfo.getId()));
        return response;
    }

    public String goToPayment(StartPaymentVM startPaymentVM) throws Exception {
        String result = "#";
        if (startPaymentVM.getPaymentType() == PaymentType.NextPay) {
            result = nexPayService.goToPayment(startPaymentVM);
        }
        transactionsService.add(startPaymentVM);
        return result;
    }

    public Transactions doVerify(Transactions transactions) throws Exception {
        Transactions result = null;
        if (transactions.getPaymentType() == PaymentType.NextPay) {
            result = nexPayService.goToVerify(transactions);
            if (result.getTransactionVerifyCode() == 0) {
                Invoice invoice = invoiceService.getById(result.getInvoice().getId());
                invoice.setPayedDate(new Date());
                invoiceService.update(invoice);
            }
        }
        transactionsService.update(result);
        return result;
    }
}
