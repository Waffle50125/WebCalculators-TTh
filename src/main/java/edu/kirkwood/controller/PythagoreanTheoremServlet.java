package edu.kirkwood.controller;

import edu.kirkwood.model.PythagoreanTheorem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static edu.kirkwood.shared.Helpers.isANumber;

@WebServlet(value="/pt")
public class PythagoreanTheoremServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pt.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String side1 = req.getParameter("num1");
        String side2 = req.getParameter("num2");
        String decimalPlaces = req.getParameter("places");

        req.setAttribute("num1",side1);
        req.setAttribute("num2",side2);
        req.setAttribute("places",decimalPlaces);

        boolean errorsFound = false;
        if (side1 == null || side1.isEmpty()){
            req.setAttribute("num1Error","Please enter a value for side 1");
            errorsFound = true;
        }
        if (!isANumber(side1)) {
            req.setAttribute("num1Error","Side 1 is not a valid number");
            errorsFound = true;
        }
        if (side2 == null || side2.isEmpty()){
            req.setAttribute("num2Error","Please enter a value for side 2");
            errorsFound = true;
        }
        if (!isANumber(side2)) {
            req.setAttribute("num2Error","Side 2 is not a valid number");
            errorsFound = true;
        }

        // sets decimal places to two if user leaves it blank.
        if (decimalPlaces == null || decimalPlaces.isEmpty()){
            req.setAttribute("placesError","A value for decimal places is required.");
            errorsFound = true;
        }
        if (!isANumber(decimalPlaces)) {
            req.setAttribute("placesError","The entered value is not a valid decimal place.");
            errorsFound = true;
        }
        // this is done so this is only checked if textboxes are not empty.
        if (!errorsFound){
            if (Double.parseDouble(req.getParameter("num1"))<=0){
                req.setAttribute("num1Error","Side 1 is not a positive number");
                errorsFound = true;
            }

            if (Double.parseDouble(req.getParameter("num2"))<=0){
                req.setAttribute("num2Error","Side 2 is not a positive number");
                errorsFound = true;
            }
            if (Integer.parseInt(req.getParameter("places"))<0){
                req.setAttribute("num2Error","decimal place cannot be negitive.");
                errorsFound = true;
            }

        }


        if(!errorsFound){
            PythagoreanTheorem pt = new PythagoreanTheorem(Double.parseDouble(side1),Double.parseDouble(side2),Integer.parseInt(decimalPlaces));
            String result = String.format(pt.toString());
            req.setAttribute("result", result);
        }
        req.getRequestDispatcher("WEB-INF/pt.jsp").forward(req,resp);
    }
}
