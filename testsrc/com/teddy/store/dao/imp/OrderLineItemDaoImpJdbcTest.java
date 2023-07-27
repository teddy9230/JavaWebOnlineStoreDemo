package com.teddy.store.dao.imp;

import com.teddy.store.dao.OrderLineItemDao;
import com.teddy.store.domain.Commodities;
import com.teddy.store.domain.OrderLineItem;
import com.teddy.store.domain.Orders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineItemDaoImpJdbcTest {

    OrderLineItemDao dao;

    @BeforeEach
    void setUp() {
        dao = new OrderLineItemDaoImpJdbc();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {
        OrderLineItem lineItem = dao.findByPk(2);
        assertNotNull(lineItem);
        assertEquals(2, lineItem.getId());
        assertEquals(50, lineItem.getQuantity());
        assertEquals(8787, lineItem.getSubTotal());
        assertEquals(3, lineItem.getCommodities().getId());
        assertEquals("1", lineItem.getOrders().getId());
    }

    @Test
    void findAll() {

        List<OrderLineItem> list = dao.findAll();
        assertEquals(2, list.size());

        OrderLineItem lineItem = list.get(1);
        assertNotNull(lineItem);
        assertEquals(3, lineItem.getId());
        assertEquals(100, lineItem.getQuantity());
        assertEquals(1000, lineItem.getSubTotal());
        assertEquals(4, lineItem.getCommodities().getId());
        assertEquals("2", lineItem.getOrders().getId());

    }

    @Test
    void create() {

        OrderLineItem lineItem = new OrderLineItem();
        lineItem.setQuantity(999);
        lineItem.setSubTotal(99999);

        Commodities commodities = new Commodities();
        commodities.setId(34);
        lineItem.setCommodities(commodities);

        Orders orders = new Orders();
        orders.setId("1");
        lineItem.setOrders(orders);

        dao.create(lineItem);


        OrderLineItem lineItem1 = dao.findByPk(4);
        assertNotNull(lineItem1);
        assertEquals(4, lineItem1.getId());
        assertEquals(999, lineItem1.getQuantity());
        assertEquals(99999, lineItem1.getSubTotal());
        assertEquals(34, lineItem1.getCommodities().getId());
        assertEquals("1", lineItem1.getOrders().getId());

    }

    @Test
    void modify() {

        OrderLineItem lineItem = new OrderLineItem();
        lineItem.setId(4);
        lineItem.setQuantity(0);
        lineItem.setSubTotal(0);

        Commodities commodities = new Commodities();
        commodities.setId(1);
        lineItem.setCommodities(commodities);

        Orders orders = new Orders();
        orders.setId("1");
        lineItem.setOrders(orders);

        dao.modify(lineItem);

        OrderLineItem lineItem1 = dao.findByPk(4);
        assertNotNull(lineItem1);
        assertEquals(4, lineItem1.getId());
        assertEquals(0, lineItem1.getQuantity());
        assertEquals(0, lineItem1.getSubTotal());
        assertEquals(1, lineItem1.getCommodities().getId());
        assertEquals("1", lineItem1.getOrders().getId());

    }

    @Test
    void remove() {

        dao.remove(4);
        OrderLineItem lineItem = dao.findByPk(4);
        assertNull(lineItem);

    }
}