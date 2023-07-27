//package com.teddy.store.service.imp;
//
//import com.teddy.store.domain.Customer;
//import com.teddy.store.service.CustomerService;
//import com.teddy.store.service.ServiceException;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CustomerServiceImpTest {
//
//    CustomerService customerSerivce;
//
//    @BeforeEach
//    void setUp() {
//        customerSerivce = new CustomerServiceImp();
//    }
//
//    @AfterEach
//    void tearDown() {
//        customerSerivce = null;
//    }
//
//    @Test
//    @DisplayName("登入成功")
//    void login() {
//
//        Customer customer = new Customer();
//        customer.setId("1");
//        customer.setPassword("password");
//
//        assertTrue(customerSerivce.login(customer));
//        assertNotNull(customer);
//        assertEquals("1",customer.getId());
//        assertEquals("林于哲",customer.getName());
//        assertEquals("password",customer.getPassword());
//        assertEquals("台灣",customer.getAddress());
//        assertEquals("0123456789",customer.getPhone());
//        assertEquals(20220101L,customer.getBirthday().getTime());
//
//    }
//
//    @Test
//    @DisplayName("登入失敗")
//    void loginError() {
//
//        Customer customer = new Customer();
//        customer.setId("1");
//        customer.setPassword("password1");
//
//        assertFalse(customerSerivce.login(customer));
//    }
//
//    @Test
//    @DisplayName("註冊成功")
//    void register() throws ServiceException {
//        Customer customer = new Customer();
//        customer.setId("2");
//        customer.setName("測試");
//        customer.setPassword("test");
//        customer.setAddress("台灣");
//        customer.setPhone("123123123");
//        customer.setBirthday(new Date(123123L));
//
//        customerSerivce.register(customer);
//
//        Customer customer1 = new Customer();
//        customer1.setId("2");
//        customer1.setPassword("test");
//
//        customerSerivce.login(customer1);
//
//        assertTrue(customerSerivce.login(customer1));
//        assertNotNull(customer1);
//        assertEquals("2",customer1.getId());
//        assertEquals("測試",customer1.getName());
//        assertEquals("test",customer1.getPassword());
//        assertEquals("台灣",customer1.getAddress());
//        assertEquals("123123123",customer1.getPhone());
//        assertEquals(123123L,customer1.getBirthday().getTime());
//
//    }
//
//    @Test
//    @DisplayName("註冊失敗 ID重複")
//    void registerError() {
//        Customer customer = new Customer();
//        customer.setId("2");
//        customer.setPassword("test");
//
//        assertThrows(ServiceException.class, ()->{
//            customerSerivce.register(customer);
//        });
//
//    }
//}