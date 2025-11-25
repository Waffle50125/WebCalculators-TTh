package edu.kirkwood.controller;

import edu.kirkwood.model.Fraction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static edu.kirkwood.shared.Helpers.isANumber;
import static edu.kirkwood.shared.Helpers.isAnInt;

@WebServlet(value="/fraction")
public class FractionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/fraction.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Step 1: Get raw data
        String numerator1 = req.getParameter("numerator1");
        String denominator1 = req.getParameter("denominator1");
        String operator = req.getParameter("operator");
        String numerator2 = req.getParameter("numerator2");
        String denominator2 = req.getParameter("denominator2");
        // Step 2: Set raw data as attributes
        req.setAttribute("numerator1",numerator1);
        req.setAttribute("denominator1",denominator1);
        req.setAttribute("operator",operator);
        req.setAttribute("numerator2",numerator2);
        req.setAttribute("denominator2",denominator2);
        // Step 4: Validate raw data
        boolean errorsFound = false;
        if(numerator1 == null || numerator1.isEmpty()){
            req.setAttribute("numerator1Error", "The first numerator is required");
            errorsFound = true;
        } else if(!isAnInt(numerator1)) {
            req.setAttribute("numerator1Error", "The first numerator must be an integer");
            errorsFound = true;
        }
        if(denominator1 == null || denominator1.isEmpty()){
            req.setAttribute("denominator1Error", "The first denominator is required");
            errorsFound = true;
        } else if(!isAnInt(denominator1)) {
            req.setAttribute("denominator1Error", "The first denominator must be an integer");
            errorsFound = true;
        }
        String[] validOperators = {"add", "subtract", "multiply", "divide"};
        List<String> validOperators2 = Arrays.asList(validOperators);
        if(!validOperators2.contains(operator)) {
            errorsFound = true;
            req.setAttribute("operatorError", "The operator must be one of the following: " + Arrays.toString(validOperators));
        }
        if(numerator2 == null || numerator2.isEmpty()){
            req.setAttribute("numerator2Error", "The second numerator is required");
            errorsFound = true;
        } else if(!isAnInt(numerator2)) {
            req.setAttribute("numerator2Error", "The second numerator must be an integer");
            errorsFound = true;
        }
        if(denominator2 == null || denominator2.isEmpty()){
            req.setAttribute("denominator2Error", "The second denominator is required");
            errorsFound = true;
        } else if(!isAnInt(denominator2)) {
            req.setAttribute("denominator2Error", "The second denominator must be an integer");
            errorsFound = true;
        }
        // Step 4b: Validate data by constructing an object
        Fraction fraction1 = null;
        Fraction fraction2 = null;
        if(!errorsFound) {
            try {
                fraction1 = new Fraction(Integer.valueOf(numerator1), Integer.valueOf(denominator1));
            } catch (ArithmeticException e) {
                req.setAttribute("denominator1Error", "Denominator 1 cannot be zero");
                errorsFound = true;
            }
            try {
                fraction2 = new Fraction(Integer.valueOf(numerator2), Integer.valueOf(denominator2));
            } catch (ArithmeticException e) {
                req.setAttribute("denominator2Error", "Denominator 2 cannot be zero");
                errorsFound = true;
            }
        }
        // Step 5: Produce the result
        if(!errorsFound) {
            Fraction fraction3 = null;
            String operatorSymbol = "";
            if(operator.equals("add")) {
                fraction3 = fraction1.add(fraction2);
                operatorSymbol = "+";
            } else if(operator.equals("subtract")) {
                fraction3 = fraction1.subtract(fraction2);
                operatorSymbol = "-";
            } else if(operator.equals("multiply")) {
                fraction3 = fraction1.multiply(fraction2);
                operatorSymbol = "×";
            } else if(operator.equals("divide")) {
                fraction3 = fraction1.divide(fraction2);
                operatorSymbol = "÷";
            }
            String result = String.format("%s %s %s = %s", fraction1.toMixedNumber(), operatorSymbol, fraction2.toMixedNumber(), fraction3.toMixedNumber());
            req.setAttribute("result", result);
        }
        // Step 3: Forward all attributes to the JSP
        req.getRequestDispatcher("WEB-INF/fraction.jsp").forward(req,resp);
    }
}
