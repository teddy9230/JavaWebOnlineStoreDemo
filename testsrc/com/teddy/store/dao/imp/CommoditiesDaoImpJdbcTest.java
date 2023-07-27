package com.teddy.store.dao.imp;

import com.teddy.store.dao.CommoditiesDao;
import com.teddy.store.domain.Commodities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommoditiesDaoImpJdbcTest {

    CommoditiesDao dao;

    @BeforeEach
    void setUp() {
        dao = new CommoditiesDaoImpJdbc();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }

    @Test
    void findByPk() {

        Commodities commodities = dao.findByPk(1L);
        assertNotNull(commodities);
        assertEquals(1L, commodities.getId());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机", commodities.getName());
        assertEquals(3399, commodities.getPrice());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机(八代i3-8100 8G 1T 四年上门 有线键鼠 FHD宽屏)21.5英寸 ", commodities.getDescription());
        assertEquals("5ae00211N25afad2c.jpg", commodities.getImage());
        assertEquals("", commodities.getBrand());
        assertEquals("", commodities.getCpuBrand());
        assertEquals("", commodities.getCardModel());
        assertEquals("", commodities.getMemoryCapacity());
        assertEquals("", commodities.getCpuType());
        assertEquals("", commodities.getHdCapacity());
        assertEquals("", commodities.getDisplaysize());

    }

    @Test
    void findAll() {

        List<Commodities> list = dao.findAll();
        assertEquals(list.size(), 34);

        Commodities commodities = list.get(0);
        assertNotNull(commodities);
        assertEquals(1L, commodities.getId());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机", commodities.getName());
        assertEquals(3399, commodities.getPrice());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机(八代i3-8100 8G 1T 四年上门 有线键鼠 FHD宽屏)21.5英寸 ", commodities.getDescription());
        assertEquals("5ae00211N25afad2c.jpg", commodities.getImage());
        assertEquals("", commodities.getBrand());
        assertEquals("", commodities.getCpuBrand());
        assertEquals("", commodities.getCardModel());
        assertEquals("", commodities.getCpuType());
        assertEquals("", commodities.getMemoryCapacity());
        assertEquals("", commodities.getHdCapacity());
        assertEquals("", commodities.getDisplaysize());

    }

    @Test
    void findStartEnd() {

        List<Commodities> list = dao.findStartEnd(0, 10);
        assertEquals(10, list.size());

        Commodities commodities = list.get(0);
        assertNotNull(commodities);
        assertEquals(1L, commodities.getId());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机", commodities.getName());
        assertEquals(3399, commodities.getPrice());
        assertEquals("戴尔(DELL)成就3470高性能商用办公台式电脑整机(八代i3-8100 8G 1T 四年上门 有线键鼠 FHD宽屏)21.5英寸 ", commodities.getDescription());
        assertEquals("5ae00211N25afad2c.jpg", commodities.getImage());
        assertEquals("", commodities.getBrand());
        assertEquals("", commodities.getCpuBrand());
        assertEquals("", commodities.getCardModel());
        assertEquals("", commodities.getCpuType());
        assertEquals("", commodities.getMemoryCapacity());
        assertEquals("", commodities.getHdCapacity());
        assertEquals("", commodities.getDisplaysize());
    }

    @Test
    void create() {

        Commodities commodities = new Commodities();
        commodities.setId(9999);
        commodities.setName("蘋果Mac Mini");
        commodities.setPrice(5000);
        commodities.setDescription("蘋果Mac Mini 2018年初");
        commodities.setBrand("蘋果");
        commodities.setCpuBrand("Intel");
        commodities.setCpuType("i5");
        commodities.setMemoryCapacity("8G");
        commodities.setHdCapacity("500G");
        commodities.setCardModel("GTX 9系/7系");
        commodities.setDisplaysize("無");
        commodities.setImage("aaa.jpg");

        dao.create(commodities);
        Commodities commodities1 = dao.findByPk(9999);
        assertNotNull(commodities1);
        assertEquals(9999, commodities1.getId());
        assertEquals("蘋果Mac Mini", commodities1.getName());
        assertEquals(5000, commodities.getPrice());
        assertEquals("蘋果Mac Mini 2018年初", commodities.getDescription());
        assertEquals("aaa.jpg", commodities1.getImage());
        assertEquals("蘋果", commodities1.getBrand());
        assertEquals("Intel", commodities1.getCpuBrand());
        assertEquals("i5", commodities1.getCpuType());
        assertEquals("GTX 9系/7系", commodities1.getCardModel());
        assertEquals("8G", commodities1.getMemoryCapacity());
        assertEquals("500G", commodities1.getHdCapacity());
        assertEquals("無", commodities1.getDisplaysize());

    }

    @Test
    void modify() {

        Commodities commodities = new Commodities();
        commodities.setId(9999);
        commodities.setName("蘋果Mac Pro");
        commodities.setPrice(120000);
        commodities.setDescription("蘋果Mac Pro笔记本 2018年初");
        commodities.setBrand("蘋果1");
        commodities.setCpuBrand("Intel A");
        commodities.setCpuType("Intel i7");
        commodities.setMemoryCapacity("16G");
        commodities.setHdCapacity("500G固态硬盘");
        commodities.setCardModel("GTX");
        commodities.setDisplaysize("15寸");
        commodities.setImage("ab.jpg");

        dao.modify(commodities);

        Commodities commodities1 = dao.findByPk(9999);
        assertNotNull(commodities1);
        assertEquals(9999, commodities1.getId());
        assertEquals("蘋果Mac Pro", commodities1.getName());
        assertEquals(120000, commodities.getPrice());
        assertEquals("蘋果Mac Pro笔记本 2018年初", commodities.getDescription());
        assertEquals("ab.jpg", commodities1.getImage());
        assertEquals("蘋果1", commodities1.getBrand());
        assertEquals("Intel A", commodities1.getCpuBrand());
        assertEquals("Intel i7", commodities1.getCpuType());
        assertEquals("GTX", commodities1.getCardModel());
        assertEquals("16G", commodities1.getMemoryCapacity());
        assertEquals("500G固态硬盘", commodities1.getHdCapacity());
        assertEquals("15寸", commodities1.getDisplaysize());

    }

    @Test
    void remove() {

        dao.remove(9999);

        Commodities commodities = dao.findByPk(9999);
        assertNull(commodities);
    }
}