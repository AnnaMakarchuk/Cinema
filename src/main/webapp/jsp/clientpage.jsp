<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

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
    <h1>
    <fmt:message key="cinema.project" />
    </h1>
    <div class="w3-container w3-right">
    	<ul>
    		<li><a href="?lang=en"><fmt:message key="language.en" /></a></li>
    		<li><a href="?lang=ru"><fmt:message key="language.ru" /></a></li>
    	</ul>
    </div>
</div>

<div class="w3-container w3-left-align">
    <p> <div class="w3-bar w3-padding-large w3-padding-24">
            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large w3-left-align" onclick="location.href='/schedule'">
            <fmt:message key="schedule"/>
            </button>
        </div>
    </p>
</div>

<div class="w3-container w3-center w3-text-teal">
    <h1><b>Welcome! This is client page</b></h1>
</div>
</body>
</html>