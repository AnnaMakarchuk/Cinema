<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="textBundle"/>

<html lang="${locale}">
<head>
    <meta charset="UTF-8">
    <title>Cinema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" href=" https://www.w3schools.com/w3css/4/w3.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<script>
    function addUrlParameter(name, value) {
      var searchParams = new URLSearchParams(window.location.search)
      searchParams.set(name, value)
      window.location.search = searchParams.toString()
    }
</script>

<body class="w3-light-grey">
<div class="w3-container w3-teal w3-opacity w3-left-align front-size:20px">
            <div class="w3-dropdown-hover w3-right ">
                <button class="w3-button w3-teal"><fmt:message key="language"/></button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4" style="min-width:70px">
                  <a class="w3-bar-item w3-button w3-teal" onclick="addUrlParameter('locale', 'en')"><fmt:message key="language.en" /></a>
                  <a class="w3-bar-item w3-button  w3-teal" onclick="addUrlParameter('locale', 'ru')"><fmt:message key="language.ru" /></a>
                </div>
            </div>
            <div class="w3-container w3-center w3-padding ">
                <h1><fmt:message key="cinema.project"/></h1>
            </div>
</div>

<div class="w3-container w3-left-align">
    <div class="w3-container w3-center w3-text-teal">
        <h1><b><fmt:message key="admin.actions.button"/></b></h1>
    </div>
</div>

<div class="w3-center">
<div class="w3-bar" style="width:50%">
    <p>
        <button class="w3-button w3-teal w3-round-large w3-padding-large" style="width:50%" onclick="location.href='/cinema/adminmovielist'">
            <fmt:message key="adminadd.movie"/></button>
    </p>
    <p>
         <button class="w3-button w3-teal w3-round-large w3-padding-large" style="width:50%" onclick="location.href='/cinema/adminmovielistcancel'">
            <fmt:message key="cancel.movie"/></button>
    </p>
    <p>
         <button class="w3-button w3-teal w3-round-large w3-padding-large" style="width:50%" onclick="location.href='/cinema/adminviewtickets'">
            <fmt:message key="all.tickets"/></button>
    </p>

    <p>
          <button class="w3-button w3-teal w3-round-large w3-padding-large" style="width:50%" onclick="location.href='/cinema/adminnonactiveschedule'">
                <fmt:message key="non.active.schedule"/></button>
    </p>

    <p>
          <button class="w3-button w3-teal w3-round-large w3-padding-large" style="width:50%" onclick="location.href='/cinema/adminnonactivemovies'">
                 <fmt:message key="non.active.movies"/></button>
    </p>


</div>

<div class="w3-container w3-right-align">
    <p> <div class="w3-bar w3-padding-large w3-padding-24">
            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large w3-left-align" onclick="location.href='/cinema/admincabinet'">
                <fmt:message key="back.adminPage"/>
            </button>
        </div>
    </p>
</div>

</body>
</html>