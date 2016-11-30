package com.asdf.revenuerecognition.controllers;

import com.asdf.revenuerecognition.beans.ProductBean;
import com.asdf.revenuerecognition.mappers.AbstractMapper;
import com.asdf.revenuerecognition.mappers.ContractMapper;
import com.asdf.revenuerecognition.beans.ContractBean;
import com.asdf.revenuerecognition.mappers.ProductMapper;
import com.asdf.revenuerecognition.util.DateCalculator;
import com.asdf.revenuerecognition.util.Money;

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
                // Invalid/no date specified
            }
        }

        req.getRequestDispatcher("./contract.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Determine request type
        boolean deletePost = req.getParameter("actiontype").equals("delete");

        ContractMapper mapper = new ContractMapper();

        if (deletePost) {
            // Delete a contract
            String contractIdString = req.getParameter("contractid");
            if (contractIdString != null) {
                Long contractId = Long.valueOf(contractIdString);
                mapper.delete(contractId);
                res.sendRedirect("contractList.jsp");
            } else {
                writeError(res, "Invalid contract ID");
            }
        } else {
            // Create a contract

            // Get request parameters
            String contractRevenueString = req.getParameter("contract-revenue");
            String dateInputString = req.getParameter("date-input");
            String productIdString = req.getParameter("productid");

            // Parse product ID
            Long productId = null;
            try {
                productId = Long.parseLong(productIdString);
            } catch (NumberFormatException e) {
                writeError(res, "Invalid product ID");
                return;
            }

            Double contractRevenue = null;
            try {
                contractRevenue = Double.parseDouble(contractRevenueString);
            } catch (NumberFormatException e) {
                writeError(res, "Invalid revenue value");
                return;
            }

            LocalDate d = null;
            try {
                d = LocalDate.parse(dateInputString);
            } catch (NumberFormatException | DateTimeParseException e) {
                writeError(res, "Invalid date");
                return;
            }
            if (d == null) {
                writeError(res, "Invalid product ID");
                return;
            }
            GregorianCalendar whenSigned = GregorianCalendar.from(d.atStartOfDay(ZoneOffset.UTC));

            // Get product object
            ProductBean prod = new ProductMapper().find(productId);
            if (prod == null) {
                writeError(res, "Invalid product ID");
                return;
            }
            ContractBean newContract = new ContractBean(prod, Money.dollars(contractRevenue), whenSigned);
            mapper.insert(newContract);

            // Redirect to new contract
            res.sendRedirect(String.format("contract?contractid=%s", newContract.getId()));
        }
    }

    /**
     * Write the specified string to the output of the specified response.
     * @param response the response
     * @param errorMsg the error message
     * @throws IOException if unable to get the response's writer object
     */
    private void writeError(HttpServletResponse response, String errorMsg) throws IOException {
        response.getWriter().write(errorMsg);
    }

}
