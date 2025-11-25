<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>My First Web Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<%--This is a JSP Comment--%>
<!-- This is an HTML Comment -->
<div class="container my-4">
    <div class="row">
        <div class="col-6">
            <h1>My First Web Calculator</h1>
            <p class="lead">Enter two numbers and press submit to calculate the result.</p>
            <form method="POST" action="hello">
                <div class="form-group mb-2">
                    <label for="num1">Number 1:</label>
                    <input type="text" class="form-control" id="num1" name="num1" value="${num1}">
                    <div class="text-danger">${num1Error}</div>
                </div>
                <div class="form-group mb-2">
                    <label for="num2">Number 2:</label>
                    <input type="text" class="form-control" id="num2" name="num2" value="${num2}">
                    <div class="text-danger">${num2Error}</div>
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