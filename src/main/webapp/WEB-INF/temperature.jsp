<%--
  Created by IntelliJ IDEA.
  User: mouft
  Date: 11/29/2025
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Temperature Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<div class="container my-4">
    <h1>Mouftaou's Temperature Calculator</h1>
    <p class="lead">Convert temperatures or perform addition/subtraction between two temperatures.</p>

    <form method="POST" action="temperature">
        <div class="row">
            <div class="col-md-8">

                <!-- Temperature 1 -->
                <div class="card mb-3">
                    <div class="card-header bg-primary text-white">Temperature 1</div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mb-2">
                                    <label for="degree1">Degree:</label>
                                    <input type="text" class="form-control" id="degree1" name="degree1" value="${degree1}">
                                    <c:if test="${not empty degree1Error}">
                                        <div class="text-danger">${degree1Error}</div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group mb-2">
                                    <label for="scale1">Scale:</label>
                                    <select class="form-select" id="scale1" name="scale1">
                                        <option value="">-- Select Scale --</option>
                                        <option value="C" <c:if test="${scale1 == 'C'}">selected</c:if>>Celsius (C)</option>
                                        <option value="F" <c:if test="${scale1 == 'F'}">selected</c:if>>Fahrenheit (F)</option>
                                        <option value="K" <c:if test="${scale1 == 'K'}">selected</c:if>>Kelvin (K)</option>
                                    </select>
                                    <c:if test="${not empty scale1Error}">
                                        <div class="text-danger">${scale1Error}</div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Operation -->
                <div class="form-group mb-3">
                    <label for="operation">Operation:</label>
                    <select class="form-select" id="operation" name="operation">
                        <option value="">-- Select Operation --</option>
                        <option value="convert" <c:if test="${operation == 'convert'}">selected</c:if>>Convert</option>
                        <option value="add" <c:if test="${operation == 'add'}">selected</c:if>>Add (+)</option>
                        <option value="subtract" <c:if test="${operation == 'subtract'}">selected</c:if>>Subtract (-)</option>
                    </select>
                    <c:if test="${not empty operationError}">
                        <div class="text-danger">${operationError}</div>
                    </c:if>
                </div>

                <!-- Target Scale (for conversion only) -->
                <div class="card mb-3">
                    <div class="card-header bg-info text-white">Target Scale (for Convert only)</div>
                    <div class="card-body">
                        <div class="form-group mb-2">
                            <label for="targetScale">Convert To:</label>
                            <select class="form-select" id="targetScale" name="targetScale">
                                <option value="">-- Select Target Scale --</option>
                                <option value="C" <c:if test="${targetScale == 'C'}">selected</c:if>>Celsius (C)</option>
                                <option value="F" <c:if test="${targetScale == 'F'}">selected</c:if>>Fahrenheit (F)</option>
                                <option value="K" <c:if test="${targetScale == 'K'}">selected</c:if>>Kelvin (K)</option>
                            </select>
                            <c:if test="${not empty targetScaleError}">
                                <div class="text-danger">${targetScaleError}</div>
                            </c:if>
                        </div>
                    </div>
                </div>

                <!-- Temperature 2 (for add/subtract only) -->
                <div class="card mb-3">
                    <div class="card-header bg-secondary text-white">Temperature 2 (for Add/Subtract only)</div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mb-2">
                                    <label for="degree2">Degree:</label>
                                    <input type="text" class="form-control" id="degree2" name="degree2" value="${degree2}">
                                    <c:if test="${not empty degree2Error}">
                                        <div class="text-danger">${degree2Error}</div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group mb-2">
                                    <label for="scale2">Scale:</label>
                                    <select class="form-select" id="scale2" name="scale2">
                                        <option value="">-- Select Scale --</option>
                                        <option value="C" <c:if test="${scale2 == 'C'}">selected</c:if>>Celsius (C)</option>
                                        <option value="F" <c:if test="${scale2 == 'F'}">selected</c:if>>Fahrenheit (F)</option>
                                        <option value="K" <c:if test="${scale2 == 'K'}">selected</c:if>>Kelvin (K)</option>
                                    </select>
                                    <c:if test="${not empty scale2Error}">
                                        <div class="text-danger">${scale2Error}</div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary btn-lg">Submit</button>

            </div>
        </div>
    </form>

    <!-- Display Result -->
    <c:if test="${not empty result}">
        <div class="alert alert-success fs-1 mt-4">${result}</div>
    </c:if>

    <!-- Display Calculation Error (e.g., below absolute zero) -->
    <c:if test="${not empty calculationError}">
        <div class="alert alert-danger fs-4 mt-4">${calculationError}</div>
    </c:if>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
