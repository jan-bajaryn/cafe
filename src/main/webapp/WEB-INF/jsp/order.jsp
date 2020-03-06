<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="stylesheet" href="static/css/order/main.css">
    <link rel="stylesheet" href="static/css/order/additional.css">
</head>
<body>


<main>
    <div class="main-container">
        <header class="mt-4">
            <h1>
                Корзина
            </h1>
        </header>
        <div class="promo">
            <form action="?">
                <div class="form-group promo-group">
                    <label for="promo"></label>
                    <input class="form-control" placeholder="Promo" type="text" id="promo">
                    <button class="btn white__bg__orange">Применить</button>
                </div>
            </form>
        </div>

        <div class="product-list">

            <c:forEach var="product" items="${productMap}">
                <div class="product-item">
                    <div class="grid-part">
                        <div class="image-part">
                            <img src="../static/img/${product.key.productGroup.photoName}" alt="">
                        </div>
                        <div class="product-name">
                                ${product.key.productGroup.name}
                        </div>
                        <div class="product-type text-muted">
                                ${product.key.weight} гр.
                        </div>
                    </div>
                    <div class="flex-part">
                        <button class="btn mx-3 white__bg__black"> -</button>
                        <span>${product.value}</span>
                        <button class="btn mx-3 white__bg__black"> +</button>
                        <div class="prise mr-3">
                                ${String.format("%.2f",(product.key.price*product.value)/100)} руб.
                        </div>
                        <form action="/deleteAll" method="get">
                            <button class="abc" type="submit">
                                <i class="fa fa-trash mr-3" aria-hidden="true"></i>
                            </button>
                            <label>
                                <input name="id" type="number" value="${product.key.id}"
                                       style="display: none;">
                            </label>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="sum">
            <div class="sum-text">Сумма заказа:</div>
            <div class="sum-price"> 17,30 руб.</div>
        </div>

        <div class="decision mb-5 mt-2">
            <button class="btn orange__hover">Вернуться в меню</button>
            <button class="btn orange__bg">Заказать</button>
        </div>

    </div>
</main>
<footer class="bg-dark">
    <div class="container">
        <a href="?">О нас</a>
        <a href="?">Почему нашу пиццу все любят</a>
        <a href="?">Наш блог</a>
        <a href="?">Наши спонсоры</a>
    </div>
</footer>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>


</body>
</html>