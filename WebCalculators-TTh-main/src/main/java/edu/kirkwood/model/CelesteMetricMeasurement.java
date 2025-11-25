package edu.kirkwood.model;

import java.math.BigDecimal;
import java.util.Objects;

public class CelesteMetricMeasurement {
    private double value;
    private String unit;

    public CelesteMetricMeasurement() {}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CelesteMetricMeasurement that = (CelesteMetricMeasurement) o;
        return Double.compare(value, that.value) == 0 && Objects.equals(unit, that.unit);
    }


    public CelesteMetricMeasurement(double value, String unit) {
        setValue(value);
        setUnit(unit);
    }

    public void setValue(double value) {
        if (value >= 0) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Measurement must be positive");
        }
    }
    public void setUnit(String unit) {
        String abbreviation;
        if (unit.equals("mm")) {
            abbreviation = "mm";
        } else if (unit.equals("cm")) {
            abbreviation = "cm";
        } else if (unit.equals("m")) {
            abbreviation = "m";
        } else if (unit.equals("km")) {
            abbreviation = "km";
        } else {
            throw new IllegalArgumentException("Invalid unit. System only takes: 'mm', 'cm', 'm', 'km'");
        }
        this.unit = abbreviation;
    }
    public double getValue() {
        return value;
    }
    public String getUnit() {
        return unit;
    }

    /**
     * Converts a given CelesteMetricMeasurement to millimeters.
     *
     * @return CelesteMetricMeasurement the converted measurement
     */
    public CelesteMetricMeasurement toMM() {
        CelesteMetricMeasurement result = new CelesteMetricMeasurement();
        result.setUnit("mm");
        if (unit.equals("mm")) {
            result.value = this.value;
        } else if (unit.equals("cm")) {
            result.value = this.value * 10;
        } else if (unit.equals("m")) {
            result.value = this.value * 1000;
        } else if (unit.equals("km")) {
            result.value = this.value * 1000000;
        }

        return result;
    }

    /**
     * Converts a given CelesteMetricMeasurement to centimeters.
     *
     * @return CelesteMetricMeasurement the converted measurement
     */
    public CelesteMetricMeasurement toCM() {
        CelesteMetricMeasurement result = new CelesteMetricMeasurement();
        result.setUnit("cm");
        if (unit.equals("mm")) {
            result.value = this.value / 10;
        } else if (unit.equals("cm")) {
            result.value = this.value;
        } else if (unit.equals("m")) {
            result.value = this.value * 100;
        } else if (unit.equals("km")) {
            result.value = this.value * 100000;
        }

        return result;
    }

    /**
     * Converts a given CelesteMetricMeasurement to meters.
     *
     * @return CelesteMetricMeasurement the converted measurement
     */
    public CelesteMetricMeasurement toM() {
        CelesteMetricMeasurement result = new CelesteMetricMeasurement();
        result.setUnit("m");
        if (unit.equals("mm")) {
            result.value = this.value / 1000;
        } else if (unit.equals("cm")) {
            result.value = this.value / 100;
        } else if (unit.equals("m")) {
            result.value = this.value;
        } else if (unit.equals("km")) {
            result.value = this.value * 1000;
        }

        return result;
    }

    /**
     * Converts a given CelesteMetricMeasurement to kilometers.
     *
     * @return CelesteMetricMeasurement the converted measurement
     */
    public CelesteMetricMeasurement toKM() {
        CelesteMetricMeasurement result = new CelesteMetricMeasurement();
        result.setUnit("km");
        if (unit.equals("mm")) {
            result.value = this.value / 1000000;
        } else if (unit.equals("cm")) {
            result.value = this.value / 100000;
        } else if (unit.equals("m")) {
            result.value = this.value / 1000;
        } else if (unit.equals("km")) {
            result.value = this.value;
        }

        return result;
    }


    @Override
    public String toString() {
        //Trailing zero removal and plain string code was from Google's AI internet thing
        return BigDecimal.valueOf(value).stripTrailingZeros().toPlainString() + unit;
    }
}