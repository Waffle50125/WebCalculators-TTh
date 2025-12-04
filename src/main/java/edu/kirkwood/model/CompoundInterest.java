package edu.kirkwood.model;

/**
 * Represents the attributes needed to calculate the future value of money.
 * This class provides methods to calculate future value of money that
 * compounds a certain number of times in a year as well as continuously compounding
 * money. Attributes include principal, interest rate, periods per year, and time in years.
 */
public class CompoundInterest {
    private double principal;
    private double interestRate;
    private double periodsPerYear;
    private int time;

    /**
     * A default constructor for CompoundInterest
     * It does not set any values but allows creation of a parameterless CompoundInterest object
     */
    public CompoundInterest(){

    }

    /**
     * Constructs an object with the specified attributes
     * @param principal The amount of money you are beginning with
     * @param interestRate The rate of interest, must be greater than 0 and less than 1
     * @param periodsPerYear How many times per year the money compounds.
     *                       This may equal Math.e to compound continuously
     * @param time How many years the money compounds
     */
    public CompoundInterest(double principal, double interestRate, String periodsPerYear, int time) {
        setPrincipal(principal);
        setInterestRate(interestRate);
        setPeriodsPerYear(periodsPerYear);
        setTime(time);
    }

    /**
     * Gets the principal
     * @return The principal as a double
     */
    public double getPrincipal() {
        return principal;
    }

    /**
     * Sets the principal
     * @param principal Starting amount of money
     * @throws IllegalArgumentException if the amount is less than or equal to 0
     */
    public void setPrincipal(double principal) {
        if(principal <= 0) {
            throw new IllegalArgumentException("The principal must be greater than zero");
        } else {
            this.principal = principal;
        }
    }

    /**
     * Gets the interest rate
     * @return the interest rate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Sets the interest rate
     * @param interestRate the interest rate
     * @throws IllegalArgumentException if the interest rate is less than or equal to 0
     */
    public void setInterestRate(double interestRate) {
        if(interestRate <= 0 || interestRate > 1) {
            throw new IllegalArgumentException("The interest rate must be greater than zero and less than 1 (Example: .07 for 7% interest rate)");
        } else {
            this.interestRate = interestRate;
        }
    }

    /**
     * Gets the number of times the money compounds in a year
     * @return the periodsPerYear
     */
    public double getPeriodsPerYear() {
        return periodsPerYear;
    }

    /**
     * Sets the periods per year
     * @param input how many times in a year the interest compounds, can enter 'e' for continuous compounding
     * @throws IllegalArgumentException if the string entered is not 'e', or a parseable double. It will also throw if the number is not greater than 0
     */
    public void setPeriodsPerYear(String input) {
        if(input.equalsIgnoreCase("e")) { // Checking to see if it should compound continuously
            periodsPerYear = Math.E;
        } else { // If it isn't compounding continuously then attempt to parse a double from the input
            try {
                periodsPerYear = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input, must be a double or type 'e' for continuous compounding: " + e.getMessage());
            }
            if(periodsPerYear <= 0) {
                throw new IllegalArgumentException("The periods per year must be greater than zero and cannot be negative");
            }
        }
    }

    /**
     * Gets how many years the money is compounding
     * @return time in years as an integer
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets how many years the money compounds
     * @param time how many years as an integer
     * @throws IllegalArgumentException if the entered number is less than or equal to 0
     */
    public void setTime(int time) {
        if(time <= 0) {
            throw new IllegalArgumentException("The time in years must be greater than zero");
        } else {
            this.time = time;
        }
    }

    /**
     * Calculates the future value based on the values of a CompoundInterest object
     * @return the future value rounded to 2 decimal places
     */
    public double calculateFutureValue() {
        double futureValue;
        if(this.periodsPerYear == Math.E) {
            // Couldn't multiply to the power of with a double, so I found this on W3 schools https://www.w3schools.com/java/ref_math_pow.asp
            // Also looked up how to round so the double would look close to the format of currency https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java\
            double base = this.periodsPerYear;
            double exponent = this.interestRate * this.time;
            futureValue = Math.round(
                    this.principal * Math.pow(base, exponent)
                    * 100.0) / 100.0;
        } else {
            double base = (1 + (this.interestRate / this.periodsPerYear));
            double exponent = (this.periodsPerYear * this.time);
            futureValue = Math.round(
                    this.principal * Math.pow(base, exponent)
                    * 100.0) / 100.0;
        }
        return futureValue;
    }
}
