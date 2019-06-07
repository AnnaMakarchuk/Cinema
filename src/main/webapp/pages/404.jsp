<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="textBundle"/>

<html lang="${locale}>
<head>
    <meta charset="UTF-8">
    <title>Cinema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
    <div class="w3-container w3-teal w3-opacity w3-left-align front-size:20px">
        <h1> <fmt:message key="cinema.project" /></h1>
    </div>

    <div class="w3-container w3-center">
        <div class="w3-bar w3-padding-large w3-padding-24 w3-center front-size:50px">
            <h1>404  </h1>
            <p> <fmt:message key="error.404"/> </p>
        </div>
    </div>
    <div class="w3-container w3-right">
        <div class="w3-bar w3-padding-medium w3-padding-24">
            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large" onclick="location.href='/cinema'">
            <fmt:message key="on.main.page"/>
            </button>
    </div>
</body>
</html>