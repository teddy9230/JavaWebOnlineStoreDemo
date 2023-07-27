package com.teddy.store.service.imp;

import com.teddy.store.domain.Commodities;
import com.teddy.store.service.CommoditiesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommoditiesServiceImpTest {

    CommoditiesService commoditiesService;

    @BeforeEach
    void setUp() {
        commoditiesService = new CommoditiesServiceImp();
    }

    @AfterEach
    void tearDown() {
        commoditiesService = null;
    }

    @Test
    void queryAll() {

        List<Commodities> list = commoditiesService.queryAll();
        assertEquals(34 , list.size());

        Commodities commodities = list.get(2);
        assertEquals(3L, commodities.getId());
        assertEquals("聯想天逸510S", commodities.getName());
        assertEquals(3099, commodities.getPrice());
        assertEquals("聯想（Lenovo）天逸510S商用台式辦公電腦整機（i3-7100 4G 1T 集顯 WiFi 藍牙 三年上門 win10）19.5英寸", commodities.getDescription());
        assertEquals("聯想（Lenovo）", commodities.getBrand());
        assertEquals("Intel ", commodities.getCpuBrand());
        assertEquals("Intel i3", commodities.getCpuType());
        assertEquals("4G", commodities.getMemoryCapacity());
        assertEquals("1T", commodities.getHdCapacity());
        assertEquals("集成顯卡", commodities.getCardModel());
        assertEquals("", commodities.getDisplaysize());
        assertEquals("5a6e946eNd622e938.jpg", commodities.getImage());

    }

    @Test
    void queryByStartEnd() {
        List<Commodities> list = commoditiesService.queryByStartEnd(0, 10);
        assertEquals(10 , list.size());

        Commodities commodities = list.get(2);
        assertEquals(3L, commodities.getId());
        assertEquals("聯想天逸510S", commodities.getName());
        assertEquals(3099, commodities.getPrice());
        assertEquals("聯想（Lenovo）天逸510S商用台式辦公電腦整機（i3-7100 4G 1T 集顯 WiFi 藍牙 三年上門 win10）19.5英寸", commodities.getDescription());
        assertEquals("聯想（Lenovo）", commodities.getBrand());
        assertEquals("Intel ", commodities.getCpuBrand());
        assertEquals("Intel i3", commodities.getCpuType());
        assertEquals("4G", commodities.getMemoryCapacity());
        assertEquals("1T", commodities.getHdCapacity());
        assertEquals("集成顯卡", commodities.getCardModel());
        assertEquals("", commodities.getDisplaysize());
        assertEquals("5a6e946eNd622e938.jpg", commodities.getImage());


    }

    @Test
    void queryDetail() {

        Commodities commodities = commoditiesService.queryDetail(3L);

        assertNotNull(commodities);

        assertEquals(3L, commodities.getId());
        assertEquals("聯想天逸510S", commodities.getName());
        assertEquals(3099, commodities.getPrice());
        assertEquals("聯想（Lenovo）天逸510S商用台式辦公電腦整機（i3-7100 4G 1T 集顯 WiFi 藍牙 三年上門 win10）19.5英寸", commodities.getDescription());
        assertEquals("聯想（Lenovo）", commodities.getBrand());
        assertEquals("Intel ", commodities.getCpuBrand());
        assertEquals("Intel i3", commodities.getCpuType());
        assertEquals("4G", commodities.getMemoryCapacity());
        assertEquals("1T", commodities.getHdCapacity());
        assertEquals("集成顯卡", commodities.getCardModel());
        assertEquals("", commodities.getDisplaysize());
        assertEquals("5a6e946eNd622e938.jpg", commodities.getImage());

    }
}