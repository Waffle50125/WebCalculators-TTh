<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Compound Interest Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body>
<div class="container my-4">
    <h1>Compound Interest Calculator</h1>
    <p class="lead">Enter information about investment including the principal, interest rate, periods per year, and
        time in years</p>
    <form method="POST" action="compoundInterest">
        <div class="row">
            <div class="row g-2 align-items-center">
                <div class="col-2">
                    <label class="col-form-label align-items-right">Principal</label>
                </div>
                <div class="col-auto">
                    <label>
                        <input type="text" class="form-control" name="principal" value="${principal}">
                    </label>
                </div>
                <div class="col-auto">
                    <div class="text-danger">${principalError}</div>
                </div>
            </div>
            <div class="row g-2 align-items-center">
                <div class="col-2">
                    <label class="col-form-label">Interest Rate</label>
                </div>
                <div class="col-auto">
                    <label>
                        <input type="text" class="form-control" name="interestRate" value="${interestRate}">
                    </label>
                </div>
                <div class="col-auto">
                    <div class="text-danger">${interestRateError}</div>
                </div>
            </div>
            <div class="row g-2 align-items-center">
                <div class="col-2">
                    <label class="col-form-label">Periods Per Year</label>
                </div>
                <div class="col-auto">
                    <label>
                        <input type="text" class="form-control" name="periodsPerYear" value="${periodsPerYear}">
                    </label>
                </div>
                <div class="col-auto">
                    <div class="text-danger">${periodsPerYearError}</div>
                </div>
            </div>
            <div class="row g-2 align-items-center">
                <div class="col-2">
                    <label class="col-form-label">Time</label>
                </div>
                <div class="col-auto">
                    <label>
                        <input type="text" class="form-control" name="time" value="${time}">
                    </label>
                </div>
                <div class="col-auto">
                    <div class="text-danger">${timeError}</div>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Calculate</button>
    </form>
    <div class="text-success fs-1">${result}</div>
</div>
</body>
</html>


