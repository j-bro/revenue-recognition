package com.asdf.revenuerecognition.controllers;

import com.asdf.revenuerecognition.mappers.ContractMapper;
import com.asdf.revenuerecognition.beans.ContractBean;
import com.asdf.revenuerecognition.util.DateCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jeremybrown on 2016-11-18.
 */
public class ContractsController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String contractIdString = req.getParameter("contractid");
        ContractBean contractBean = null;
        if (contractIdString != null) {
            Long contractId = Long.valueOf(contractIdString);
            // Set contract bean
            contractBean = new ContractMapper().find(contractId);
            req.setAttribute("contractBean", contractBean);
        }
        else {
            req.setAttribute("invalidParameters", true);
            req.getRequestDispatcher("./contract.jsp").forward(req, res);
            return;
        }

        // Set date from request parameter
        String dateInput = req.getParameter("date-input");
        if (dateInput != null) {
            try {
                LocalDate d = LocalDate.parse(dateInput);
                GregorianCalendar dateBefore = GregorianCalendar.from(d.atStartOfDay(ZoneOffset.UTC));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dateString = d.format(formatter);
                req.setAttribute("dateString", dateString);
                req.setAttribute("recognizedRevenueBeforeDate", contractBean.getRecognizedRevenue(dateBefore));
            } catch (DateTimeException e) {
//                req.setAttribute("invalidParameters", true);
            }
        }

        req.getRequestDispatcher("./contract.jsp").forward(req, res);
    }
}
