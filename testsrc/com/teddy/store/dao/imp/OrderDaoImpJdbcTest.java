package com.teddy.store.dao.imp;

import com.teddy.store.dao.OrderDao;
import com.teddy.store.domain.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImpJdbcTest {

    OrderDao dao;


    @BeforeEach
    void setUp() {
        dao = new OrderDaoImpJdbc();
    }

    @AfterEach
    void tearDown() {
        dao = null;

    }

    @Test
    void findByPk() {
        Orders orders = dao.findByPk("1");
        assertNotNull(orders);
        assertEquals("1", orders.getId());
        assertEquals(20220723L, orders.getOrderDate().getTime());
        assertEquals(1, orders.getStatus());
        assertEquals(5000, orders.getTotal());
    }

    @Test
    void findAll() {

        List<Orders> list = dao.findAll();
        assertEquals(1, list.size());

        Orders orders = list.get(0);
        assertNotNull(orders);
        assertEquals("1", orders.getId());
        assertEquals(20220723L, orders.getOrderDate().getTime());
        assertEquals(1, orders.getStatus());
        assertEquals(5000.0, orders.getTotal());

    }

    @Test
    void create() {
        Orders orders = new Orders();
        orders.setId("1");
        orders.setStatus(0);
        orders.setOrderDate(new Date(20220723L));
        orders.setTotal(5000.0);

        dao.create(orders);
        Orders orders1 = dao.findByPk("1");
        assertNotNull(orders1);
        assertEquals("1", orders.getId());
        assertEquals(20220723L, orders.getOrderDate().getTime());
        assertEquals(0, orders.getStatus());
        assertEquals(5000.0, orders.getTotal());
    }

    @Test
    void modify() {
        Orders orders = new Orders();
        orders.setId("1");
        orders.setStatus(1);
        orders.setOrderDate(new Date(000000000L));
        orders.setTotal(1300.0);

        dao.modify(orders);

        Orders orders1 = dao.findByPk("1");
        assertNotNull(orders1);
        assertEquals("1", orders.getId());
        assertEquals(000000000L, orders.getOrderDate().getTime());
        assertEquals(1, orders.getStatus());
        assertEquals(1300.0, orders.getTotal());
    }

    @Test
    void remove() {
        dao.remove("1");
        Orders orders = dao.findByPk("1");
        assertNull(orders);
    }
}