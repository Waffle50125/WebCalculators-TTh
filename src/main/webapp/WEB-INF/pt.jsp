<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 11/13/2025
  Time: 9:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pythagorean Theorem Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container my-4">
    <div class="row">
        <div class="col-6">
            <h1>Pythagorean Theorem Calculator</h1>
            <p class="lead">Enter two numbers (and to how many decimal places) then press submit to get the hypotenuse of two sides.</p>
            <form method="POST" action="pt">
                <div class="form-group mb-2">
                    <label for="num1">Side 1:</label>
                    <input type="text" class="form-control" id="num1" name="num1" value="${num1}">
                    <div class="text-danger">${num1Error}</div>
                </div>
                <div class="form-group mb-2">
                    <label for="num2">Side 2:</label>
                    <input type="text" class="form-control" id="num2" name="num2" value="${num2}">
                    <div class="text-danger">${num2Error}</div>
                </div>
                <div class="form-group mb-2">
                    <label for="places">Decimal Places:</label>
                    <input type="text" class="form-control" id="places" name="places" value="${places}">
                    <div class="text-danger">${placesError}</div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <div class="text-success fs-1">${result}</div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
</body>
</html>
