package com.teddy.store.service.imp;

import com.teddy.store.dao.CommoditiesDao;
import com.teddy.store.dao.imp.CommoditiesDaoImpJdbc;
import com.teddy.store.domain.Commodities;
import com.teddy.store.service.CommoditiesService;

import java.util.List;

public class CommoditiesServiceImp implements CommoditiesService {

    CommoditiesDao commoditiesDao = new CommoditiesDaoImpJdbc();

    @Override
    public List<Commodities> queryAll() {
        return commoditiesDao.findAll();
    }

    @Override
    public List<Commodities> queryByStartEnd(int start, int end) {
        return commoditiesDao.findStartEnd(start, end);
    }

    @Override
    public Commodities queryDetail(long commoditiesid) {
        return commoditiesDao.findByPk(commoditiesid);
    }
}
