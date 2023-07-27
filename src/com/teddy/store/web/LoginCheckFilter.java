package com.teddy.store.web;

import com.teddy.store.domain.Customer;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter(filterName = "LoginCheckFilter", urlPatterns = {"/*", "/controller"})
public class LoginCheckFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Customer customer =  (Customer) req.getSession().getAttribute("customer");

        String action = req.getParameter("action");

        if (customer == null && !"login".equals(action) && !"reg_init".equals(action)){
               req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

        chain.doFilter(req, resp);

    }
}
