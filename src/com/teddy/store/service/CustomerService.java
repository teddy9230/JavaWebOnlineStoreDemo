package com.teddy.store.service;

import com.teddy.store.domain.Customer;

public interface CustomerService {

    boolean login(Customer customer);

    void register(Customer customer) throws ServiceException;
}
