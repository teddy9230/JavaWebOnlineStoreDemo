package com.teddy.store.web;


import com.teddy.store.domain.Commodities;
import com.teddy.store.domain.Customer;
import com.teddy.store.service.CommoditiesService;
import com.teddy.store.service.CustomerService;
import com.teddy.store.service.OrdersService;
import com.teddy.store.service.ServiceException;
import com.teddy.store.service.imp.CommoditiesServiceImp;
import com.teddy.store.service.imp.CustomerServiceImp;
import com.teddy.store.service.imp.OrdersServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//@WebServlet(name = "Controller", urlPatterns = {"/controller"} )
public class Controller extends HttpServlet {

//    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private CustomerService customerService = new CustomerServiceImp();
    private CommoditiesService commoditiesService = new CommoditiesServiceImp();
    private OrdersService ordersService = new OrdersServiceImp();

    private int totalPageNumber = 0;
    private int pageSize = 10;
    private int currentPage = 1;

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        pageSize = Integer.valueOf(config.getInitParameter("pageSize"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("reg".equals(action)) {
//            會員註冊
            String userid = request.getParameter("userid");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String birthday = request.getParameter("birthday");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");

//            Server驗證
            List<String> errors = new ArrayList<>();

            if (userid == null || userid.equals("")){
                errors.add("會員帳號不能為空!");
            }

            if (name == null || name.equals("")){
                errors.add("會員姓名不能為空!");
            }

            if (password == null || password2 == null || !password.equals(password2)){
                errors.add("密碼輸入不一致!");
            }


            String pattern = "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$";

            if(birthday == null || !Pattern.matches(pattern, birthday)){
                errors.add("出生日期格式有誤!");
            }

            if(errors.size() > 0) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
            }else{
                Customer customer = new Customer();
                customer.setId(userid);
                customer.setName(name);
                customer.setPassword(password);
                customer.setAddress(address);
                customer.setPhone(phone);
                customer.setBirthday(birthday);

//                try{
//                    Date d = dateFormat.parse(birthday);
//                    customer.setBirthday(d);
//                }catch(ParseException ex){
//                    ex.printStackTrace();
//                }

//                會員註冊
                try {
                    customerService.register(customer);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } catch (ServiceException ex) {
//                    會員 已註冊
                    errors.add("會員ID已經註冊!");
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
                }

            }
        } else if ("login".equals(action)){
            String userid = request.getParameter("userid");
            String password = request.getParameter("password");

            Customer customer = new Customer();
            customer.setId(userid);
            customer.setPassword(password);


            if (customerService.login(customer)) {
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);
                request.getRequestDispatcher("main.jsp").forward(request, response);

            } else {
                List<String> errors = new ArrayList<>();
                errors.add("輸入帳號或密碼有錯誤!");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else if ("list".equals(action)){
            List<Commodities> commoditiesList = commoditiesService.queryAll();

            if ( commoditiesList.size() % pageSize == 0 ) {
                totalPageNumber = commoditiesList.size() / pageSize;
            } else {
                totalPageNumber = commoditiesList.size() / pageSize + 1;
            }

            request.setAttribute("totalPageNumber", totalPageNumber);
            request.setAttribute("currentPage", currentPage);

            int start = (currentPage - 1) * pageSize;
            int end = currentPage * pageSize;

            if ( currentPage == totalPageNumber) {
                end = commoditiesList.size();
            }

            request.setAttribute("commoditiesList", commoditiesList.subList(start, end));
            request.getRequestDispatcher("commodities_list.jsp").forward(request, response);

        } else if ("page".equals(action)){
            List<Commodities> commoditiesList = commoditiesService.queryAll();
            request.setAttribute("commoditiesList", commoditiesList);
            request.getRequestDispatcher("commodities_list.jsp").forward(request, response);
        } else if ("paging".equals(action)){
            String page = request.getParameter("page");

            if (page.equals("prev")){
                currentPage--;
                if ( currentPage < 1 ){
                    currentPage = 1;
                }
            } else if (page.equals("next")){
                currentPage++;
                if ( currentPage > totalPageNumber ){
                    currentPage = totalPageNumber;
                }
            } else {
                currentPage = Integer.valueOf(page);
            }

            int start = (currentPage - 1) * pageSize;
            int end = currentPage * pageSize;

            List<Commodities> commoditiesList = commoditiesService.queryByStartEnd(start, end);

            request.setAttribute("totalPageNumber", totalPageNumber);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("commoditiesList", commoditiesList);
            request.getRequestDispatcher("commodities_list.jsp").forward(request, response);
        } else if ("detail".equals(action)) {
            String commoditiesid = request.getParameter("id");
            Commodities commodities = commoditiesService.queryDetail(Long.valueOf(commoditiesid));

            request.setAttribute("commodities", commodities);
            request.getRequestDispatcher("commodities_detail.jsp").forward(request, response);
        } else if ("add".equals(action)) {
            Long commoditiesid = Long.valueOf(request.getParameter("commoditiesid"));
            String commoditiesname = request.getParameter("name");
            Float price = new Float(request.getParameter("price"));

            List<Map<String, Object>> cart =  (List<Map<String, Object>>) request.getSession().getAttribute("cart");

            if (cart == null){
                cart = new ArrayList<Map<String, Object>>();
                request.getSession().setAttribute("cart", cart);
            }

            int flag = 0;
            for (Map<String, Object> item : cart){
                Long commoditiesid2 = (Long) item.get("commoditiesid");

                if (commoditiesid.equals(commoditiesid2)) {
                    Integer quantity = (Integer) item.get("quantity");
                    quantity++;
                    item.put("quantity", quantity);

                    flag++;
                }

            }

            if (flag == 0) {
                Map<String, Object> item = new HashMap<>();
                item.put("commoditiesid", commoditiesid);
                item.put("commoditiesname", commoditiesname);
                item.put("quantity", 1);
                item.put("price", price);

                cart.add(item);
            }


            System.out.println(cart);

            String pagename = request.getParameter("pagename");

            if ( pagename.equals("list")){
                int start = (currentPage - 1) * pageSize;
                int end = currentPage * pageSize;

                List<Commodities> commoditiesList = commoditiesService.queryByStartEnd(start, end);

                request.setAttribute("totalPageNumber", totalPageNumber);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("commoditiesList", commoditiesList);

                request.getRequestDispatcher("commodities_list.jsp").forward(request, response);

            } else if (pagename.equals("detail")) {

                Commodities commodities = commoditiesService.queryDetail(Long.valueOf(commoditiesid));
                request.setAttribute("commodities", commodities);
                request.getRequestDispatcher("commodities_detail.jsp").forward(request, response);
            }

        } else if ("cart".equals(action)) {
                List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

                double total = 0.0;

                if ( cart != null ){
                    for ( Map<String, Object> item : cart) {
                        Integer quantity = (Integer) item.get("quantity");
                        Float price = (Float) item.get("price");
                        double subtotal = price * quantity;

                        total += subtotal;
                    }
                }

                request.setAttribute("total", total);
                request.getRequestDispatcher("cart.jsp").forward(request, response);

        }  else if ("sub_ord".equals(action)){
            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
            for (Map<String, Object> item : cart) {
                Long commoditiesid = (Long) item.get("commoditiesid");
                String strquantity = request.getParameter("quantity_" + commoditiesid);
                int quantity = 0;
                try {
                    quantity = Integer.valueOf(strquantity);
                } catch (Exception e) {
                }

                item.put("quantity", quantity);
            }

            String ordersid = ordersService.submitOrders(cart);
            request.setAttribute("ordersid",ordersid);
            request.getRequestDispatcher("order_success.jsp").forward(request, response);
            request.getSession().removeAttribute("cart");
        } else if ("main".equals(action)){
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } else if ("logout".equals(action)){
            request.getSession().removeAttribute("cart");
            request.getSession().removeAttribute("customer");

            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if ("reg_init".equals(action)){

            request.getRequestDispatcher("customer_reg.jsp").forward(request, response);

        }

    }
}
