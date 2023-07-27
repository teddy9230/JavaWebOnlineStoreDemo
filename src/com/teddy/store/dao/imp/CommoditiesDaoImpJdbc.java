package com.teddy.store.dao.imp;

import com.teddy.db.core.JdbcTemplate;
import com.teddy.store.dao.CommoditiesDao;
import com.teddy.store.domain.Commodities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommoditiesDaoImpJdbc implements CommoditiesDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    private void populate(List<Commodities> list, ResultSet rs) throws SQLException {
        Commodities commodities = new Commodities();
        commodities.setId(rs.getLong("id"));
        commodities.setName(rs.getString("name"));
        commodities.setPrice(rs.getDouble("price"));
        commodities.setDescription(rs.getString("description"));
        commodities.setBrand(rs.getString("brand"));
        commodities.setCpuBrand(rs.getString("cpu_brand"));
        commodities.setCpuType(rs.getString("cpu_type"));
        commodities.setMemoryCapacity(rs.getString("memory_capacity"));
        commodities.setHdCapacity(rs.getString("hd_capacity"));
        commodities.setCardModel(rs.getString("card_model"));
        commodities.setDisplaysize(rs.getString("displaysize"));
        commodities.setImage(rs.getString("image"));

        list.add(commodities);
    }

    @Override
    public Commodities findByPk(long pk) {

        List<Commodities> list = new ArrayList<Commodities>();

        String sql = "select id,name,price,description,brand,cpu_brand,cpu_type,memory_capacity,hd_capacity,card_model,displaysize,image from Commodities where id=?";
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
    public List<Commodities> findAll() {

        List<Commodities> list = new ArrayList<Commodities>();

        String sql = "select id,name,price,description,brand,cpu_brand,cpu_type,memory_capacity,hd_capacity,card_model,displaysize,image from Commodities";
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
        }, rs -> {
            populate(list, rs);
        });

        return list;
    }

    @Override
    public List<Commodities> findStartEnd(int start, int end) {

        List<Commodities> list = new ArrayList<Commodities>();

        StringBuffer sql = new StringBuffer("select id,name,price,description,brand,cpu_brand,cpu_type,memory_capacity,hd_capacity,card_model,displaysize,image from Commodities");
        sql.append(" LIMIT ").append(end - start);
        sql.append(" OFFSET ").append(start);

        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            return ps;
        }, rs -> {
            populate(list, rs);

        });

        return list;
    }

    @Override
    public void create(Commodities commodities) {

        String sql = "insert into Commodities (id,name,price,description,brand,cpu_brand,cpu_type,memory_capacity,hd_capacity,card_model,displaysize,image)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(conn -> {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, commodities.getId());
            ps.setString(2, commodities.getName());
            ps.setDouble(3, commodities.getPrice());
            ps.setString(4, commodities.getDescription());
            ps.setString(5, commodities.getBrand());
            ps.setString(6, commodities.getCpuBrand());
            ps.setString(7, commodities.getCpuType());
            ps.setString(8, commodities.getMemoryCapacity());
            ps.setString(9, commodities.getHdCapacity());
            ps.setString(10, commodities.getCardModel());
            ps.setString(11, commodities.getDisplaysize());
            ps.setString(12, commodities.getImage());

            return ps;
        });

    }

    @Override
    public void modify(Commodities commodities) {
        String sql = "update Commodities set name=?,price=?,description=?,brand=?,cpu_brand=?,cpu_type=?,memory_capacity=?,hd_capacity=?,card_model=?,displaysize=?,image=? where id=?";

        jdbcTemplate.update(conn -> {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, commodities.getName());
            ps.setDouble(2, commodities.getPrice());
            ps.setString(3, commodities.getDescription());
            ps.setString(4, commodities.getBrand());
            ps.setString(5, commodities.getCpuBrand());
            ps.setString(6, commodities.getCpuType());
            ps.setString(7, commodities.getMemoryCapacity());
            ps.setString(8, commodities.getHdCapacity());
            ps.setString(9, commodities.getCardModel());
            ps.setString(10, commodities.getDisplaysize());
            ps.setString(11, commodities.getImage());
            ps.setLong(12, commodities.getId());

            return ps;
        });
    }

    @Override
    public void remove(long pk) {
        String sql = "delete from Commodities where id=?";

        jdbcTemplate.update(conn -> {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, pk);

            return ps;
        });
    }
}
