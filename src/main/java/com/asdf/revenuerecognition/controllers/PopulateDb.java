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
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Make products
        ProductMapper productMapper = new ProductMapper();
        ProductBean dbProduct = ProductBeanFactory.newDatabase("DB PRODUCT");
        productMapper.insert(dbProduct);
        out.write("Inserted DB PRODUCT<br/>");
        ProductBean wordProduct = ProductBeanFactory.newWordProcessor("WORD PRODUCT");
        productMapper.insert(wordProduct);
        out.write("Inserted WORD PRODUCT<br/>");
        ProductBean spreadsheetProduct = ProductBeanFactory.newSpreadSheet("SPREADSHEET PRODUCT");
        productMapper.insert(spreadsheetProduct);
        out.write("Inserted SPREADSHEET PRODUCT<br/>");

        // Make contracts & revenue recognitions
        ContractMapper contractMapper = new ContractMapper();
        out.write("Creating contract 1<br/>");
        ContractBean contract1 = new ContractBean(dbProduct, Money.dollars(1000), GregorianCalendar.from(ZonedDateTime.now()));
        contractMapper.insert(contract1);
        out.write("Inserted contract 1<br/>");

        out.write("Creating contract 2<br/>");
        ContractBean contract2 = new ContractBean(wordProduct, Money.dollars(10), GregorianCalendar.from(ZonedDateTime.now()));
        contractMapper.insert(contract2);
        out.write("Inserted contract 2<br/>");

        out.write("Creating contract 3<br/>");
        ContractBean contract3 = new ContractBean(spreadsheetProduct, Money.dollars(100), GregorianCalendar.from(ZonedDateTime.now()));
        contractMapper.insert(contract3);
        out.write("Inserted contract 3<br/>");

        out.write("Created 3 CONTRACTS & THEIR REVENUE RECOGNITIONS<br/>");
    }

}
