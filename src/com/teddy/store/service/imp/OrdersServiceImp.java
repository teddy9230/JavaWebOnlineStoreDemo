package com.teddy.store.service.imp;

import com.teddy.store.dao.CommoditiesDao;
import com.teddy.store.dao.OrderDao;
import com.teddy.store.dao.OrderLineItemDao;
import com.teddy.store.dao.imp.CommoditiesDaoImpJdbc;
import com.teddy.store.dao.imp.OrderDaoImpJdbc;
import com.teddy.store.dao.imp.OrderLineItemDaoImpJdbc;
import com.teddy.store.domain.Commodities;
import com.teddy.store.domain.OrderLineItem;
import com.teddy.store.domain.Orders;
import com.teddy.store.service.OrdersService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrdersServiceImp implements OrdersService {

    CommoditiesDao commoditiesDao = new CommoditiesDaoImpJdbc();
    OrderDao orderDao = new OrderDaoImpJdbc();
    OrderLineItemDao orderLineItemDao =new OrderLineItemDaoImpJdbc();

    @Override
    public String submitOrders(List<Map<String, Object>> cart) {

        Orders orders = new Orders();
        Date date = new Date();
        String ordersid = String.valueOf(date.getTime() + String.valueOf((int)(Math.random() * 100)));

        orders.setId(ordersid);
        orders.setOrderDate(date);
        orders.setStatus(1);
        orders.setTotal(0.0f);

        orderDao.create(orders);

        double total = 0.0;

        for (Map item : cart){
            Long commoditiesid = (Long)item.get("commoditiesid");
            Integer quantity = (Integer)item.get("quantity");
            Commodities commodities = commoditiesDao.findByPk(commoditiesid);

            double subtotal = quantity * commodities.getPrice();
            total += subtotal;

            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setQuantity(quantity);
            lineItem.setCommodities(commodities);
            lineItem.setOrders(orders);
            lineItem.setSubTotal(subtotal);

            orderLineItemDao.create(lineItem);
        }

        orders.setTotal(total);

        orderDao.modify(orders);

        return ordersid;
    }
}
