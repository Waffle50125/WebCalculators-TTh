<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>Metric Conversion</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <div class="container my-5">
            <h1>Cel's Metric Conversion Calculator</h1>
            <p>Enter a measurement and choose the unit to convert to</p>
            <form method="POST" action="metricconversion">
                <div class="row">
                    <div class="col-10">
                        <div class="form-group mb-2">
                            <label for="number" class="form-label">Number</label>
                            <input type="text" class="form-control" name="number" id="number" value="${number}">
                        </div>
                    </div>
                    <div class="col">
                        <label for="inputUnit" class="form-label">Unit for Input</label>
                        <select class="form-select" id="inputUnit" name="inputUnit">
                            <option <c:if test="${inputUnit eq 'mm'}">selected</c:if> value="mm">Millimeters</option>
                            <option <c:if test="${inputUnit eq 'cm'}">selected</c:if> value="cm">Centimeters</option>
                            <option <c:if test="${inputUnit eq 'm'}">selected</c:if> value="m">Meters</option>
                            <option <c:if test="${inputUnit eq 'km'}">selected</c:if> value="km">Kilometers</option>
                        </select>
                    </div>
                    <div class="form-group d-flex mb-5">
                        <label for="targetUnit" class="form-label">Conversion Target</label>
                        <select class="form-select" id="targetUnit" name="targetUnit">
                            <option <c:if test="${targetUnit eq 'mm'}">selected</c:if> value="mm">Millimeters</option>
                            <option <c:if test="${targetUnit eq 'cm'}">selected</c:if> value="cm">Centimeters</option>
                            <option <c:if test="${targetUnit eq 'm'}">selected</c:if> value="m">Meters</option>
                            <option <c:if test="${targetUnit eq 'km'}">selected</c:if> value="km">Kilometers</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
                <div class="text-success fs-1">
                    <p>${result}</p>
                </div>
                <div class="text-danger">
                    <p>${errorMessage}</p>
                </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
