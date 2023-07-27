package com.teddy.store.dao.imp;

import com.teddy.db.core.JdbcTemplate;
import com.teddy.store.dao.OrderLineItemDao;
import com.teddy.store.domain.Commodities;
import com.teddy.store.domain.OrderLineItem;
import com.teddy.store.domain.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineItemDaoImpJdbc implements OrderLineItemDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    private void populate(List<OrderLineItem> list, ResultSet rs) throws SQLException {
        OrderLineItem lineItem = new OrderLineItem();

        lineItem.setId(rs.getLong("id"));
        lineItem.setQuantity(rs.getInt("quantity"));
        lineItem.setSubTotal(rs.getFloat("sub_total"));

        Orders orders = new Orders();
        orders.setId(rs.getString("orderid"));
        lineItem.setOrders(orders);

        Commodities commodities = new Commodities();
        commodities.setId(rs.getLong("commoditiesid"));
        lineItem.setCommodities(commodities);

        list.add(lineItem);
    }

    @Override
    public OrderLineItem findByPk(long pk) {

        List<OrderLineItem> list = new ArrayList<OrderLineItem>();

        String sql = "select id,commoditiesid,orderid,quantity,sub_total from OrderLineItems where id=?";

        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, pk);
            return ps;
        }, rs -> {
            populate(list, rs);
        });

        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<OrderLineItem> findAll() {
        List<OrderLineItem> list = new ArrayList<OrderLineItem>();

        String sql = "select id,commoditiesid,orderid,quantity,sub_total from OrderLineItems";

        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
        }, rs -> {
            populate(list, rs);
        });

        return list;
    }

    @Override
    public void create(OrderLineItem lineItem) {

        String sql = "insert into OrderLineItems (id,commoditiesid,orderid,quantity,sub_total) values (?,?,?,?,?)";

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, lineItem.getId());
            ps.setLong(2, lineItem.getCommodities().getId());
            ps.setString(3, lineItem.getOrders().getId());
            ps.setInt(4, lineItem.getQuantity());
            ps.setDouble(5, lineItem.getSubTotal());

            return ps;
        });
    }

    @Override
    public void modify(OrderLineItem lineItem) {

        String sql = "update OrderLineItems set commoditiesid=?,orderid=?,quantity=?,sub_total=? where id=?";

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, lineItem.getCommodities().getId());
            ps.setString(2, lineItem.getOrders().getId());
            ps.setInt(3, lineItem.getQuantity());
            ps.setDouble(4, lineItem.getSubTotal());
            ps.setLong(5, lineItem.getId());

            return ps;
        });
    }

    @Override
    public void remove(int pk) {
        String sql = "delete  from OrderLineItems where id=?";

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pk);

            return ps;
        });
    }
}
