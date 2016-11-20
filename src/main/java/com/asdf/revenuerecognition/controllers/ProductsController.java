package com.asdf.revenuerecognition.controllers;

import com.asdf.revenuerecognition.beans.ProductBean;
import com.asdf.revenuerecognition.mappers.ProductMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jeremybrown on 2016-11-18.
 */
public class ProductsController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        Long productId = Long.valueOf(req.getParameter("productid"));

        ProductBean productBean = new ProductMapper().find(productId);
        req.setAttribute("productBean", productBean);

        req.getRequestDispatcher("./product.jsp").forward(req, res);
    }
}
