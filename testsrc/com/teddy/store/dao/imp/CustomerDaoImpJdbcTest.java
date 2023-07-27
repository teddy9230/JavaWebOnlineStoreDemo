package com.teddy.store.dao.imp;

import com.teddy.store.dao.CustomerDao;
import com.teddy.store.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerDaoImpJdbcTest {

    CustomerDao dao;

    @BeforeEach
    void setUp() {
        dao = new CustomerDaoImpJdbc();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {

        Customer customer = dao.findByPk("1");
        assertNotNull(customer);
        assertEquals("1", customer.getId());
        assertEquals("林于哲", customer.getName());
        assertEquals("password", customer.getPassword());
        assertEquals("台灣", customer.getAddress());
        assertEquals("0123456789", customer.getPhone());
        assertEquals("1998-09-23", customer.getBirthday());

    }

    @Test
    void findAll() {
        List<Customer> list = dao.findAll();
        assertEquals(list.size(), 1);
    }

    @Test
    void create() {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("林于哲");
        customer.setPassword("password");
        customer.setAddress("台灣");
        customer.setPhone("0123456789");
        customer.setBirthday("1998-09-23");


        dao.create(customer);
        // 按照主键查询
//        Customer customer1 =  dao.findByPk("1");
//        assertEquals("1", customer1.getId());
//        assertEquals("林于哲", customer1.getName());
//        assertEquals("password", customer1.getPassword());
//        assertEquals("台灣", customer1.getAddress());
//        assertEquals("0123456789", customer1.getPhone());
//        assertEquals("1998-09-23", customer1.getBirthday());

    }

    @Test
    void modify() {

        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("測試");
        customer.setPassword("password");
        customer.setAddress("台灣");
        customer.setPhone("0123456789");
        customer.setBirthday("2022-07-26");

        dao.modify(customer);
        // 按照主键查询
        Customer customer1 =  dao.findByPk("2");
        assertEquals("2", customer1.getId());
        assertEquals("測試", customer1.getName());
        assertEquals("password", customer1.getPassword());
        assertEquals("台灣", customer1.getAddress());
        assertEquals("0123456789", customer1.getPhone());
        assertEquals("2022-07-26", customer1.getBirthday());
    }

    @Test
    void remove() {

        dao.remove("2");
        // 按照主键查询
        Customer customer =  dao.findByPk("2");
        assertNull(customer);
    }
}