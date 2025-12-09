<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Movie Search</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container my-4">
    <div class="row">
        <div class="col-12">
            <h1>Movie Search</h1>
            <form method="POST" action="movies">
                <div class="form-group mb-2">
                    <label for="num1">Enter a movie title:</label>
                    <input type="text" class="form-control" name="search" value="${search}">
                    <div class="text-danger">${searchError}</div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
<div class="container my-4">
    <div class="row d-flex justify-content-between align-items-stretch">
        <c:forEach items="${movies}" var="movie">
            <div class="col-3">
                <div class="card mb-4 w-100">
                    <c:if test="${not empty movie.poster}">
                        <img class="card-img-top" src="https://image.tmdb.org/t/p/w220_and_h330_face/${movie.poster}" alt="${movie.title}">
                    </c:if>
                    <div class="card-body">
                        <h5 class="card-title">${movie.title}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${movie.year}</h6>
                        <p class="card-text">${movie.plot}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>