package amin.shop.app.services.orders;

import amin.shop.app.entities.orders.Transactions;
import amin.shop.app.helper.exceptions.DataNotFoundException;
import amin.shop.app.helper.uimodels.StartPaymentVM;
import amin.shop.app.repositories.orders.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository repository;

    //region CRUD -> Read.

    // Teacher Code.
    public Transactions getByTransId(String trans_id) {
        Transactions data = repository.findByTrans(trans_id);
        return data;
    }

    public Transactions getById(long id) {
        Optional<Transactions> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    // My Code.
    /*public Transactions getById(long id) {
        Optional<Transactions> data = repository.findById(id);
        if (data.isEmpty()) return null;
        return data.get();
    }*/

    //endregion

    //CRUD -> Create.
    //For Insert Transactions use This Code.
    public Transactions add(Transactions data) {
        return repository.save(data);
    }

    public Transactions add(StartPaymentVM startPaymentVM) {
        Transactions data = new Transactions();
        data.setShaparak_Ref_Id("");
        data.setAddDate(new Date());
        data.setAmount(startPaymentVM.getAmount());
        data.setTrans_id(startPaymentVM.getTrans_id());
        data.setCustomer(startPaymentVM.getCustomer());
        data.setPayer_desc(startPaymentVM.getDescription());
        data.setInvoice(startPaymentVM.getInvoice());
        data.setCustomer_phone(data.getCustomer_phone());
        data.setCode(startPaymentVM.getCode());
        data.setPaymentType(startPaymentVM.getPaymentType());
        return repository.save(data);
    }

    //CRUD -> Update.
    //For Update Transactions use This Code.
    public Transactions update(Transactions data) throws DataNotFoundException {
        Transactions oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found!");
        }
        oldData.setVerifyStatus(data.getVerifyStatus());
        oldData.setTransactionVerifyCode(data.getTransactionVerifyCode());
        oldData.setAmount(data.getAmount());
        oldData.setCard_holder(data.getCard_holder());
        oldData.setCreated_at(data.getCreated_at());
        oldData.setShaparak_Ref_Id(data.getShaparak_Ref_Id());
        return repository.save(oldData);
    }

    //CRUD -> Delete.
    //-->Never need delete.
    /*public boolean deleteById(long id) throws DataNotFoundException {
        Transactions oldData = getById(id);
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + id + " not found!");
        }
        repository.deleteById(id);
        return true;
    }*/

}