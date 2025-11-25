package edu.kirkwood.controller;

import edu.kirkwood.model.CelesteMetricMeasurement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static edu.kirkwood.shared.Helpers.isANumber;

@WebServlet(value = "/metricconversion")
public class MetricConversionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/metricconversion.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number =  req.getParameter("number");
        String inputUnit = req.getParameter("inputUnit");
        String targetUnit = req.getParameter("targetUnit");
        String errorMessage = "";
        String result = "";

        req.setAttribute("number",number);
        req.setAttribute("inputUnit",inputUnit);
        req.setAttribute("targetUnit",targetUnit);

        boolean valid = true;

        if(number == null || number.equals("")) {
            valid = false;
            errorMessage += "Number is required\n";
        } else if (!isANumber(number)) {
            valid = false;
            errorMessage += "Please enter a number\n";
        }
        if (!(inputUnit.equalsIgnoreCase("mm") || inputUnit.equalsIgnoreCase("cm")|| inputUnit.equalsIgnoreCase("m") || inputUnit.equalsIgnoreCase("km"))){
            valid = false;
            errorMessage += "Please select a valid unit to input\n";
        }
        if (!(targetUnit.equalsIgnoreCase("mm") || targetUnit.equalsIgnoreCase("cm")|| targetUnit.equalsIgnoreCase("m") || targetUnit.equalsIgnoreCase("km"))){
            valid = false;
            errorMessage += "Please select a valid unit to convert to\n";
        }
        req.setAttribute("errorMessage",errorMessage);

        CelesteMetricMeasurement inputMeasurement = new CelesteMetricMeasurement();
        CelesteMetricMeasurement outputMeasurement = new CelesteMetricMeasurement();
        if (valid) {
            inputMeasurement.setUnit(inputUnit);
            inputMeasurement.setValue(Double.parseDouble(number));
            if (targetUnit.equalsIgnoreCase(("mm"))){
                outputMeasurement = inputMeasurement.toMM();
            } else if (targetUnit.equalsIgnoreCase(("cm"))){
                outputMeasurement = inputMeasurement.toCM();
            } else if (targetUnit.equalsIgnoreCase(("m"))){
                outputMeasurement = inputMeasurement.toM();
            } else if (targetUnit.equalsIgnoreCase(("km"))){
                outputMeasurement = inputMeasurement.toKM();
            }
            result = inputMeasurement.toString() + " equates to " + outputMeasurement.toString();
            req.setAttribute("result",result);
        }

        req.getRequestDispatcher("WEB-INF/metricconversion.jsp").forward(req,resp);
    }
}
