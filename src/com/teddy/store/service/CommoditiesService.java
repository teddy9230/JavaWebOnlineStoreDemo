package com.teddy.store.service;

import com.teddy.store.domain.Commodities;
import java.util.List;

public interface CommoditiesService {

    List<Commodities> queryAll();

    List<Commodities> queryByStartEnd(int start, int end);

    Commodities queryDetail(long commoditiesid);

}
