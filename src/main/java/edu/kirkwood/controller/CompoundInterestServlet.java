package edu.kirkwood.controller;


import edu.kirkwood.model.CompoundInterest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static edu.kirkwood.shared.Helpers.*;

@WebServlet(value = "/compoundInterest")
public class CompoundInterestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/compoundInterest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get raw data
        String principal = req.getParameter("principal");
        String interestRate = req.getParameter("interestRate");
        String periodsPerYear = req.getParameter("periodsPerYear");
        String time = req.getParameter("time");
        // Set raw data as attributes
        req.setAttribute("principal", principal);
        req.setAttribute("interestRate", interestRate);
        req.setAttribute("periodsPerYear", periodsPerYear);
        req.setAttribute("time", time);
        // Validate raw data
        boolean errorsFound = false;
        if (principal == null || principal.isEmpty()) {
            req.setAttribute("principalError", "Principal is required");
            errorsFound = true;
        } else if (!isANumber(principal)) {
            req.setAttribute("principalError", "Principal must be a number");
            errorsFound = true;
        } else if (Double.parseDouble(principal) < 0) {
            req.setAttribute("principalError", "Principal must be greater than 0");
            errorsFound = true;
        }

        if (interestRate == null || interestRate.isEmpty()) {
            req.setAttribute("interestRateError", "Interest rate is required");
            errorsFound = true;
        } else if (!isANumber(interestRate)) {
            req.setAttribute("interestRateError", "Interest rate must be a number");
            errorsFound = true;
        } else if (Double.parseDouble(interestRate) < 0 || Double.parseDouble(interestRate) > 1) {
            req.setAttribute("interestRateError", "Interest rate must be between 0 and 1, ex: .07 for 7%");
            errorsFound = true;
        }

        if (periodsPerYear == null || periodsPerYear.isEmpty()) {
            req.setAttribute("periodsPerYearError", "Periods per year is required");
            errorsFound = true;
        } else if (!periodsPerYear.equalsIgnoreCase("e")) { // If the periodsPerYear is E then it is exponential and skips over number validation
            if (!isANumber(periodsPerYear)) {
                req.setAttribute("periodsPerYearError", "Periods per year must be a number or E for exponential growth");
                errorsFound = true;
            } else if (Double.parseDouble(periodsPerYear) < 0) {
                req.setAttribute("periodsPerYearError", "Periods per year must be greater than 0");
                errorsFound = true;
            }
        }

        if (time == null || time.isEmpty()) {
            req.setAttribute("timeError", "Time is required");
            errorsFound = true;
        } else if (!isAnInt(time)) {
            req.setAttribute("timeError", "Time must be a whole number");
            errorsFound = true;
        } else if (Integer.parseInt(time) < 0) {
            req.setAttribute("timeError", "Time must be greater than 0");
            errorsFound = true;
        }
        // Validate data by constructing a CompoundInterest object
        CompoundInterest equation = null;
        if (!errorsFound) {
            try {
                equation = new CompoundInterest();
                equation.setPrincipal(Double.parseDouble(principal));
            } catch (IllegalArgumentException ex) {
                req.setAttribute("principalError", ex.getMessage());
            }
            try {
                equation.setInterestRate(Double.parseDouble(interestRate));
            } catch (IllegalArgumentException ex) {
                req.setAttribute("interestRateError", ex.getMessage());
            }
            try {
                equation.setPeriodsPerYear(periodsPerYear);
            } catch (IllegalArgumentException ex) {
                req.setAttribute("periodsPerYearError", ex.getMessage());
            }
            try {
                equation.setTime(Integer.parseInt(time));
            } catch (IllegalArgumentException ex) {
                req.setAttribute("timeError", ex.getMessage());
            }
        }
        // Produce the result
        if (!errorsFound) {
            String futureValue = toCurrency(equation.calculateFutureValue());
            String result = "";
            if(equation.getPeriodsPerYear() == Math.E) {
                result = String.format("%.2f(E)^(%.2f * %s) = %s"
                        , equation.getPrincipal()
                        , equation.getInterestRate()
                        , equation.getTime()
                        , futureValue);
            } else {
                result = String.format("%.2f(1 + (%.2f / %.2f))^(%.2f * %s) = %s"
                        , equation.getPrincipal()
                        , equation.getInterestRate()
                        , equation.getPeriodsPerYear()
                        , equation.getPeriodsPerYear()
                        , equation.getTime()
                        , futureValue);
            }
            req.setAttribute("result", result);
        }
        req.getRequestDispatcher("WEB-INF/compoundInterest.jsp").forward(req, resp);
    }
}
