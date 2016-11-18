package com.asdf.revenuerecognition.controllers;

import com.asdf.revenuerecognition.mappers.ContractMapper;
import com.asdf.revenuerecognition.beans.ContractBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jeremybrown on 2016-11-18.
 */
public class ContractController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        Long contractId = Long.valueOf(req.getParameter("contractid"));

        ContractBean contractBean = new ContractMapper().find(contractId);
        req.setAttribute("contractBean", contractBean);

        req.getRequestDispatcher("./contract.jsp").forward(req, res);
    }
}
