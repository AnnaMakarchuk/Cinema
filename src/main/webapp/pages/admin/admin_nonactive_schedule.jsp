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

<div class="w3-container w3-right-align">
    <div class="w3-bar w3-padding-large w3-padding-24">

            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large w3-left" onclick="location.href='/cinema/admincabinet'">
                            <fmt:message key="back.cabinet"/>
            </button>
     </div>
</div>
<div class="w3-container w3-center">
    <footer class="w3-container w3-teal w3-round-large" style="width:100% ">
        <h4><b><fmt:message key="non.active.schedule"/></b></h4>
    </footer>
</div>
<div><p></p></div>
<div class="w3-container w3-text-teal">
    <table class="w3-table w3-small w3-bordered">
        <tr>
            <th><fmt:message key="week.day"/></th>
            <th><fmt:message key="movie.time"/></th>
            <th><fmt:message key="action"/></th>

        </tr>
        <tr>
            <c:forEach var="schedule" items="${schedules}" varStatus = "loopStatus">
                <td><br><c:out value="${schedule.weekDay}"/></td>
                <td><br><c:out value="${schedule.time}"/></td>
                <td>
                <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-border w3-padding-small w3-center" data-modal="form-primary">
                    <fmt:message key="update.schedule.button"/>
                </button>
                    <div id="id01" class="w3-modal">
                         <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                            <div class="w3-center"><br>
                                <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
                            </div>
                            <form class="w3-container" method="POST" action="/cinema/changeschedule">
                                <div class="w3-section w3-left-align">
                                        <c:forEach var="movie" items="${movies}" varStatus = "loopStatus">
                                            <input type="radio" name="movie_id" value="${movie.movieId}"><c:out value="${movie.movieName}"/><br>
                                        </c:forEach>
                                <p></p>
                                <input type="hidden" name="schedule_id" value="${schedule.scheduleId}"/>
                                <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding">
                                    <fmt:message key="update.schedule.button"/></button>
                            </div>
                        </form>
                    </div>
                 </div>
            </tr>
            </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>