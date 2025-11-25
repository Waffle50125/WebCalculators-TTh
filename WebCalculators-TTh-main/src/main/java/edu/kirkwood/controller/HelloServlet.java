package edu.kirkwood.controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static edu.kirkwood.shared.Helpers.*;

@WebServlet(value = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/hello.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get raw data from the form
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        // Set raw data as a request attribute
        req.setAttribute("num1",num1);
        req.setAttribute("num2",num2);
        // Validate the form data
        boolean errorsFound = false;
        if(num1 == null || num1.isEmpty()){
            req.setAttribute("num1Error", "The first number is required");
            errorsFound = true;
        } else if(!isANumber(num1)) {
            req.setAttribute("num1Error", "The first number is not a number");
            errorsFound = true;
        }
        if(num2 == null || num2.isEmpty()){
            req.setAttribute("num2Error", "The second number is required");
            errorsFound = true;
        } else if(!isANumber(num2)) {
            req.setAttribute("num2Error", "The second number is not a number");
            errorsFound = true;
        }
        // Generate a response
        if(!errorsFound){
            double sum = getSum.apply(num1,num2);
            String result = String.format("%s + %s = %s", num1,num2,round(sum,10));
            req.setAttribute("result",result);
        }
        // forward the response to the JSP
        req.getRequestDispatcher("WEB-INF/hello.jsp").forward(req,resp);
    }


}