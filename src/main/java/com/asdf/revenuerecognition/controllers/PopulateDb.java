package com.asdf.revenuerecognition.controllers;

import com.asdf.revenuerecognition.beans.ContractBean;
import com.asdf.revenuerecognition.beans.ProductBean;
import com.asdf.revenuerecognition.beans.ProductBeanFactory;
import com.asdf.revenuerecognition.mappers.ContractMapper;
import com.asdf.revenuerecognition.mappers.ProductMapper;
import com.asdf.revenuerecognition.util.Money;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

/**
 * Created by jeremybrown on 2016-11-18.
 */
public class PopulateDb extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        ProductMapper productMapper = new ProductMapper();
        ProductBean dbProduct = ProductBeanFactory.newDatabase("DB PRODUCT");
        ProductBean wordProduct = ProductBeanFactory.newWordProcessor("WORD PRODUCT");
        ProductBean spreadsheetProduct = ProductBeanFactory.newSpreadSheet("SPREADSHEET PRODUCT");
        productMapper.insert(dbProduct);
        productMapper.insert(wordProduct);
        productMapper.insert(spreadsheetProduct);
        out.write("Created DB, WORD, SPREADSHEET PRODUCTS<br/>");

        ContractMapper contractMapper = new ContractMapper();
        ContractBean contract1 = new ContractBean(dbProduct, Money.dollars(1000), GregorianCalendar.from(ZonedDateTime.now()));
        ContractBean contract2 = new ContractBean(wordProduct, Money.dollars(10), GregorianCalendar.from(ZonedDateTime.now()));
        ContractBean contract3 = new ContractBean(spreadsheetProduct, Money.dollars(100), GregorianCalendar.from(ZonedDateTime.now()));
        contractMapper.insert(contract1);
        contractMapper.insert(contract2);
        contractMapper.insert(contract3);
        out.write("Created 3 CONTRACTS<br/>");
    }

}
