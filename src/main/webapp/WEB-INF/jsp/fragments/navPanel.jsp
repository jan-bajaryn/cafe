<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Nav Panel</title>
</head>
<body>
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
</body>
</html>
