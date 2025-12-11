package edu.kirkwood.model;

import java.text.DecimalFormat;

/**
 * This class represents two doubles and a hypotenuse from it.
 * Alongside how many decimal places that 3rd side is returned to, including a DecimalFormat for said places
 */
public class PythagoreanTheorem {
    private double a;
    private double b;
    private int c;
    // https://www.baeldung.com/java-decimalformat, allows formatting of double values to strings.
    DecimalFormat df;

    /**
     * getter method for c.
     * @return returns value of C
     */
    public int getC() {
        return c;
    }

    /**
     * setter method for c containing validation for if c is less than 0
     * @param c number of decimal places
     */
    public void setC(int c) {
        if (c >= 0) {
            this.c = c;
        } else {
            throw new ArithmeticException("You cannot have negative decimal places.");
        }
    }


    /**
     * Gets the 1st double (a) put in by the user for the PT (Pythagorean Theorem) calculation
     * @return returns the 1st double.
     */
    public double getA() {
        return a;
    }

    /**
     * Sets the 1st side of the PT calculation
     * @param a The 1st side.
     * @throws ArithmeticException throws exception if a =< 0
     */
    public void setA(double a) {
        if (a > 0) {
            this.a = a;
        } else {
            throw new ArithmeticException("You cannot have a side less than or equal to zero");
        }
    }



    /**
     * Gets the 2nd double (b) put in by the user for the PT  calculation
     * @return returns the 2nd double.
     * @throws ArithmeticException throws exception if b =< 0
     */
    public double getB() {
        return b;
    }

    /**
     * Sets the 2nd side of the PT calculation
     * @param b The 2nd side.
     */
    public void setB(double b) {
        if (b > 0) {
            this.b = b;
        } else {
            throw new ArithmeticException("You cannot have a side less than or equal to zero");
        }
    }



    /**
     * Default constructor
     * Initializes a and b for a pythagorean theorem alongside default places of 2.
     */
    public PythagoreanTheorem() {
        this.a = 6;
        this.b = 7;
        this.c = 2;
        df = new DecimalFormat("#." + "0".repeat(c));
    }

    /**
     * Input the 2 sides numbers to then reach a
     * @param a The first side
     * @param b The second side
     * @param c the decimal places side 3 is returned to.
     */
    public PythagoreanTheorem(double a, double b, int c) {
        setA(a);
        setB(b);
        setC(c);
        if (c == 0){
            // if c is zero take off decimal point.
            df = new DecimalFormat("#");
        } else {
            // the Decimal format object set here and the prior constructor sets a format to an x amount of decimal spaces
            // after the decimal point, places are added to the amount that c indicates.
            // This is done utilizing the String class Repeat method as seen below
            // https://www.geeksforgeeks.org/java/string-class-repeat-method-in-java-with-examples/
            df = new DecimalFormat("#." + "0".repeat(c));
        }
    }

    /**
     * Squares a double given as a parameter and returns it.
     * @param x The double inserted to be squared
     * @return Returns the squared double
     */
    public static double square(double x) {
        x = x * x;
        return x;
    }


    /**
     * Takes two doubles and adds them together to then be returned.
     * @param a 1st side squared
     * @param b 2nd side squared.
     * @return The sum of both squared sides
     */
    public static double add(double a, double b) {
        return a + b;
    }

    /**
     * takes a double and takes the root from it.
     * @param r the double returned from the add method.
     * @return the square root of r.
     */
    public static double takeRoot(double r) {
        return Math.sqrt(r);
    }

    /**
     * Displays the 3 sides length.
     * @return a and b parameters of the object, alongside the 3rd side reached through calculation
     * formated to c many places.
     */
    @Override
    public String toString() {
        return "Side 1: "+a+"\nSide 2: "+b+"\nSide 3: "+ df.format(takeRoot(add(square(a), square(b))));
    }
}
