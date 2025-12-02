package edu.kirkwood.controller;

import edu.kirkwood.model.Temperature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static edu.kirkwood.shared.Helpers.isANumber;

@WebServlet(value = "/temperature")
public class TemperatureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/temperature.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Step 1: Get raw data from the form
        String degree1 = req.getParameter("degree1");
        String scale1 = req.getParameter("scale1");
        String operation = req.getParameter("operation");
        String degree2 = req.getParameter("degree2");
        String scale2 = req.getParameter("scale2");
        String targetScale = req.getParameter("targetScale");

        // Step 2: Set raw data as request attributes (to preserve form values)
        req.setAttribute("degree1", degree1);
        req.setAttribute("scale1", scale1);
        req.setAttribute("operation", operation);
        req.setAttribute("degree2", degree2);
        req.setAttribute("scale2", scale2);
        req.setAttribute("targetScale", targetScale);

        // Step 3: Validate the form data
        boolean errorsFound = false;

        // Validate first temperature (always required)
        if (degree1 == null || degree1.isEmpty()) {
            req.setAttribute("degree1Error", "Temperature 1 is required");
            errorsFound = true;
        } else if (!isANumber(degree1)) {
            req.setAttribute("degree1Error", "Temperature 1 must be a valid number");
            errorsFound = true;
        }

        if (scale1 == null || scale1.isEmpty()) {
            req.setAttribute("scale1Error", "Scale 1 is required");
            errorsFound = true;
        }

        // Validate operation (always required)
        if (operation == null || operation.isEmpty()) {
            req.setAttribute("operationError", "Please select an operation");
            errorsFound = true;
        }

        // Validate based on operation type
        if (operation != null && operation.equals("convert")) {
            // For conversion: validate target scale
            if (targetScale == null || targetScale.isEmpty()) {
                req.setAttribute("targetScaleError", "Target scale is required for conversion");
                errorsFound = true;
            }
        } else if (operation != null && (operation.equals("add") || operation.equals("subtract"))) {
            // For add/subtract: validate second temperature
            if (degree2 == null || degree2.isEmpty()) {
                req.setAttribute("degree2Error", "Temperature 2 is required for addition/subtraction");
                errorsFound = true;
            } else if (!isANumber(degree2)) {
                req.setAttribute("degree2Error", "Temperature 2 must be a valid number");
                errorsFound = true;
            }

            if (scale2 == null || scale2.isEmpty()) {
                req.setAttribute("scale2Error", "Scale 2 is required for addition/subtraction");
                errorsFound = true;
            }
        }

        // Step 4: Perform calculation if no errors
        if (!errorsFound) {
            try {
                Temperature temp1 = new Temperature(Double.parseDouble(degree1), scale1);
                String result = "";

                switch (operation) {
                    case "convert":
                        String converted = temp1.convertTo(targetScale);
                        result = String.format("%s = %s", temp1.toString(), converted);
                        break;
                    case "add":
                        Temperature temp2Add = new Temperature(Double.parseDouble(degree2), scale2);
                        Temperature sumResult = temp1.add(temp2Add);
                        result = String.format("%s + %s = %s", temp1.toString(), temp2Add.toString(), sumResult.toString());
                        break;
                    case "subtract":
                        Temperature temp2Sub = new Temperature(Double.parseDouble(degree2), scale2);
                        Temperature subResult = temp1.subtract(temp2Sub);
                        result = String.format("%s - %s = %s", temp1.toString(), temp2Sub.toString(), subResult.toString());
                        break;
                    default:
                        result = "Invalid operation selected";
                }

                req.setAttribute("result", result);

            } catch (IllegalArgumentException e) {
                req.setAttribute("calculationError", e.getMessage());
            }
        }

        // Step 5: Forward to JSP
        req.getRequestDispatcher("WEB-INF/temperature.jsp").forward(req, resp);
    }
}