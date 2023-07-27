package com.teddy.store.service.imp;

import com.teddy.store.dao.CustomerDao;
import com.teddy.store.dao.imp.CustomerDaoImpJdbc;
import com.teddy.store.domain.Customer;
import com.teddy.store.service.CustomerService;
import com.teddy.store.service.ServiceException;

public class CustomerServiceImp implements CustomerService {

    CustomerDao customerDao = new CustomerDaoImpJdbc();

    @Override
    public boolean login(Customer customer) {

        Customer dbCustomer = customerDao.findByPk(customer.getId());

        if (dbCustomer != null && dbCustomer.getPassword().equals(customer.getPassword())) {
            customer.setPhone(dbCustomer.getPhone());
            customer.setAddress(dbCustomer.getAddress());
            customer.setName(dbCustomer.getName());
            customer.setBirthday(dbCustomer.getBirthday());

            return true;
        }

        return false;
    }

    @Override
    public void register(Customer customer) throws ServiceException {

        Customer dbCustomer = customerDao.findByPk(customer.getId());

        if (dbCustomer != null){
            throw new ServiceException("ID: " + customer.getId() + "已存在!!");
        }

        customerDao.create(customer);
    }
}
