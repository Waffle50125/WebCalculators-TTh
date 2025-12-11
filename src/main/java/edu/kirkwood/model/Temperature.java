package edu.kirkwood.model;

/**
 * Represents a temperature with a double  degree value and a string scale (C, F, K).
 * Supports conversion between scales  basic arithmetic operations ( addition and substraction).
 */

public class Temperature {
    private double degree;
    private String scale;

    /**
     * Default constructor initializes to 0 Celsius.
     */

    public Temperature() {
        this.degree = 0;
        this.scale = "C";
    }

    /**
     * Constructs a new Temperature with a specified degree value and Scale.
     * @param degree The temperature value.
     * @param scale The Scale ("C", "F", "K")
     * @throws IllegalArgumentException if scale is invalid.
     */

    public Temperature(double degree, String scale) {
        if (!isValidScale(scale)) {
            throw new IllegalArgumentException("Invalid scale " + scale);
        }
        if (isBelowAbsoluteZero(degree, scale)) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero for scale " + scale);
        }
        this.degree = degree;
        this.scale = scale.toUpperCase();
    }

    /**
     * Gets the degree of the temperature
     * @return the degree
     */

    public double getDegree() {

        return degree;
    }

    /**
     * sets the degree of the temperature
     * @param degree the new degree
     */
    public void setDegree(double degree) {
        if (isBelowAbsoluteZero(degree, this.scale)) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero for scale " + this.scale);
        }
        this.degree = degree;
    }

    /**
     * gets the scale of the temperature
     * @return the scale
     */
    public String getScale() {
        return scale;
    }

    /**
     * sets the scale of the temperature
     * @param scale the new scale
     * @throws IllegalArgumentException if the scale is invalid
     */
    public void setScale(String scale) {
        if (!isValidScale(scale)){
            throw new IllegalArgumentException("Invalid scale " + scale);
        }
        if(isBelowAbsoluteZero(this.degree, scale)){
            throw new IllegalArgumentException("Current temperature is below absolute zero for the new scale");
        }
        this.scale = scale.toUpperCase();
    }

    /**
     * Checks if temperature is below absolute zero for the given scale
     * @param degree temperature value
     * @param scale temperature scale
     * @return true if temperature is below absolute value
     */

    private boolean isBelowAbsoluteZero(double degree, String scale) {
        switch (scale.toUpperCase()) {
            case "C":
                return (degree < -273.15);
            case "F":
                return (degree < -459.67);
            case "K":
                return (degree < 0);
            default:
                return true;
        }
    }

    /**
     * Validates if the scale is supported
     * @param testScale scale to validate
     * @return true if scale is C, F, or K (case-insensitive)
     */

    private boolean isValidScale(String testScale) {
        if (testScale == null) return false;
        return testScale.equalsIgnoreCase("C") ||
                testScale.equalsIgnoreCase("F") ||
                testScale.equalsIgnoreCase("K");

    }

    /**
     * Returns a string representation of the fraction in the format : degree °scale("C", "F", "K")".
     * @return a string representation of the temperature
     */
    @Override
    public String toString() {
        // Check if degree is a whole number
        if(degree == (long) degree){
            return String.format("%.0f°%s", degree, scale);
        }else{
            return String.format("%.2f°%s", degree, scale);
        }
    }

    public String convertTo(String targetScale){
        if(!isValidScale(targetScale)){
            throw new IllegalArgumentException("Invalid scale " + targetScale);
        }
        double convertedDeg = degree;
        String source = scale.toUpperCase();
        String target = targetScale.toUpperCase();
        if(source.equals(target)){
            return String.format("%.2f°%s", degree, target);
        }

        // Convert to Celsius first
        switch (source){
            case "F":
                convertedDeg = (degree - 32)*5/9;
                break;
            case "K":
                convertedDeg = degree - 273.15;
                break;
        }

        // Convert from Celsius to target
        switch (target){
            case "F":
                convertedDeg = convertedDeg*9/5 + 32;
                break;
            case "K":
                convertedDeg = convertedDeg + 273.15;
                break;
        }
        return String.format("%.2f°%s", convertedDeg, target);
    }

    /**
     * Adds another temperature to this one after conversion to same scale.
     * @param other Temperature to add.
     * @return New Temperature object with the sum in original scale.
     */

    public Temperature add(Temperature other){
        String otherConverted = other.convertTo(this.scale);
        double otherDeg = Double.parseDouble(otherConverted.split("°")[0]);
        return new Temperature(this.degree + otherDeg, this.scale);

    }

    /**
     * Subtract from this one after conversion to the same scale.
     * @param other Temperature to subtract.
     * @return New Temperature object with the subtraction in the original scale
     */

    public Temperature subtract(Temperature other){
        String otherConverted = other.convertTo(this.scale);
        double otherDeg = Double.parseDouble(otherConverted.split("°")[0]);
        return new Temperature(this.degree - otherDeg, this.scale);
    }


}
