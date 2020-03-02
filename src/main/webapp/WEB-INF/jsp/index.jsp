<%@ page import="java.util.List" %>
<%@ page import="by.epam.cafe.entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
          crossorigin="anonymous">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
          crossorigin="anonymous">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="stylesheet" href="static/css/index/main.css">
    <%--    <link rel="stylesheet" href="static/css/footer.css">--%>
    <%--    <link rel="stylesheet" href="static/css/footer.css">--%>
    <%--    <link rel="stylesheet" href="static/css/nav__bar.css">--%>
</head>
<body>

<%
    List<Product> products = new ArrayList<>();

%>

<div class="main-container">
    <nav>
        <div class="nav-container">
            <div class="list-nav">
                <div class="icon">
                    <i class='fas fa-pizza-slice'></i>
                </div>
                <ul>
                    <li><a href="?">Пиццы</a></li>
                    <li><a href="?">Закуски</a></li>
                    <li><a href="?">Десерты</a></li>
                    <li><a href="?">Напитки</a></li>
                    <li><a href="?">Контакты</a></li>
                    <li><a href="?">О нас</a></li>
                </ul>
            </div>
            <div class="basket">
                <button class="btn">
                    <span class="border-right">Корзина</span>
                    <span>1</span>
                </button>
            </div>
        </div>
    </nav>
    <main class="container">
        <div class="title-container">
            Пиццы
        </div>
        <div class="p_card-list">
            <c:forEach var="product" items="${products}">
                <div class="p_card">
                    <div class="p_card-image">
                        <img src="static/img/${product.getPhotoName()}" alt="">
                    </div>
                    <div class="p_card-name">
                            ${product.getName()}
                    </div>
                    <div class="p_card-description text-muted">
                            ${product.getDescription()}
                    </div>
                    <div class="p_card-footer md-2">
                    <span>
                        от ${String.format("%.2f", product.getPrice()/100.0)} руб.
                    </span>
                        <button class="btn mr-5">
                            Выбрать
                        </button>
                    </div>
                </div>
            </c:forEach>
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
</div>
</body>
</html>