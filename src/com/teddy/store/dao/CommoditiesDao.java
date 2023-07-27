package com.teddy.store.dao;

import com.teddy.store.domain.Commodities;

import java.util.List;

public interface CommoditiesDao {

    Commodities findByPk(long pk);

    List<Commodities> findAll();

    /**
     * 提供分页查询
     * @param start 开始索引 索引从0开始
     * @param end 结束索引  索引从0开始
     * @return 商品列表
     */
    List<Commodities> findStartEnd(int start, int end);

    void create(Commodities commodities);

    void modify(Commodities commodities);

    void remove(long pk);
}
